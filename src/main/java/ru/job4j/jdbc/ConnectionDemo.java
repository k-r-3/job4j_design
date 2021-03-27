package ru.job4j.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class ConnectionDemo {
    final static Logger LOG = LoggerFactory.getLogger(ConnectionDemo.class.getName());

    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        Properties properties = new Properties();
        Connection connection = null;
        try (InputStream in = ConnectionDemo.class.getClassLoader()
                .getResourceAsStream("appOld.properties")) {
            properties.load(in);
            Class.forName(properties.getProperty("driver_class"));
            connection = DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("username"),
                    properties.getProperty("password"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return connection;
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
