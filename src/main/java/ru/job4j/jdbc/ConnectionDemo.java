package ru.job4j.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class ConnectionDemo {
    final static Logger LOG = LoggerFactory.getLogger(ConnectionDemo.class.getName());

    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        ConnectionDemo cd = new ConnectionDemo();
        String url = "";
        String username = "";
        String password = "";
        try (BufferedReader reader = new BufferedReader(new FileReader("./app.properties"))) {
            ConfigParser parser = new ConfigParser(reader);
            ConnectionPattern cp = new ConnectionPattern();
            url = parser.getConfig("url", cp);
            username = parser.getConfig("username", cp);
            password = parser.getConfig("password", cp);
            Class.forName(parser.getConfig("driver", cp));
        } catch (IOException e) {
            LOG.error("Reader exception", e);
        }
        return DriverManager.getConnection(url, username, password);
    }

    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        StringBuilder scheme = new StringBuilder();
        DatabaseMetaData metaData = connection.getMetaData();
        try (ResultSet columns = metaData.getColumns(null, null, tableName, null)) {
            scheme.append(String.format("%-15s %-15s%n", "column", "type"));
            while (columns.next()) {
                scheme.append(String.format("%-15s %-15s%n",
                        columns.getString("COLUMN_NAME"),
                        columns.getString("TYPE_NAME")));
            }
        }
        return scheme.toString();
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        try (Connection connection = getConnection()) {
            try (Statement statement = connection.createStatement()) {
                String sql = String.format(
                        "create table if not exists demo_table(%s, %s);",
                        "id serial primary key",
                        "name varchar(255)"
                );
                statement.execute(sql);
                System.out.println(getTableScheme(connection, "demo_table"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
