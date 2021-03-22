package ru.job4j.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class TableEditor implements AutoCloseable {
    final static Logger LOG = LoggerFactory.getLogger(ConnectionDemo.class.getName());

    private Connection connection;
    private Statement statement;
    private Properties properties;
    private String path;

    public TableEditor(String path) throws SQLException, ClassNotFoundException {
        this.properties = properties;
        this.path = path;
        initConnection();
        initStatement();
    }

    private void initConnection() throws SQLException, ClassNotFoundException {
        String url = "";
        String username = "";
        String password = "";
        ConfigParser parser = new ConfigParser();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            ConnectionPattern pattern = new ConnectionPattern();
            url = parser.getConfig("url", pattern, reader.lines());
            username = parser.getConfig("username", pattern, reader.lines());
            password = parser.getConfig("password", pattern, reader.lines());
        } catch (IOException e) {
            LOG.error("Reader exception", e);
        }
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(url, username, password);
    }

    private void initStatement() throws SQLException {
        statement = connection.createStatement();
    }

    public void createTable(String tableName) {
        try {
            String sql = String.format(
                    "create table "
                            + "%s(%s, %s);",
                    tableName,
                    "id serial primary key",
                    "name varchar(255)"
            );
            statement.execute(sql);
        } catch (SQLException e) {
            LOG.error("create table exception", e);
        }
    }

    public void dropTable(String tableName) {
        try {
            String sql = "drop table " + tableName;
            statement.execute(sql);
        } catch (SQLException e) {
            LOG.error("drop table exception", e);
        }
    }

    public void addColumn(String tableName, String columnName, String type) {
        try {
            String sql = String.format("alter table %s add %s %s", tableName,
                    columnName, type);
            statement.execute(sql);
        } catch (SQLException e) {
            LOG.error("add column exception", e);
        }
    }

    public void dropColumn(String tableName, String columnName) {
        try {
            String sql = String.format("alter table %s drop column %s",
                    tableName, columnName);
            statement.execute(sql);
        } catch (SQLException e) {
            LOG.error("add column exception", e);
        }
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        try {
            String sql = String.format("alter table %s rename column %s to %s", tableName,
                    columnName, newColumnName);
            statement.execute(sql);
        } catch (SQLException e) {
            LOG.error("add column exception", e);
        }
    }

    public String getScheme(String tableName) throws SQLException {
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

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}
