package ru.job4j.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class TableEditor implements AutoCloseable {
    final static Logger LOG = LoggerFactory.getLogger(ConnectionDemo.class.getName());
    private Connection connection;
    private Properties properties;
    private String path;

    public TableEditor(Properties properties) throws SQLException, ClassNotFoundException {
        this.properties = properties;
        this.path = path;
        initConnection();
    }

    private void initConnection() throws SQLException, ClassNotFoundException {
        try (InputStream in = TableEditor.class.getClassLoader()
                .getResourceAsStream("app.properties")) {
         properties.load(in);
         Class.forName(properties.getProperty("driver_class"));
         connection = DriverManager.getConnection(
                 properties.getProperty("url"),
                 properties.getProperty("username"),
                 properties.getProperty("password"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createTable(String tableName) {
        try (Statement statement = connection.createStatement()) {
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
        try (Statement statement = connection.createStatement()) {
            String sql = "drop table " + tableName;
            statement.execute(sql);
        } catch (SQLException e) {
            LOG.error("drop table exception", e);
        }
    }

    public void addColumn(String tableName, String columnName, String type) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format("alter table %s add %s %s", tableName,
                    columnName, type);
            statement.execute(sql);
        } catch (SQLException e) {
            LOG.error("add column exception", e);
        }
    }

    public void dropColumn(String tableName, String columnName) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format("alter table %s drop column %s",
                    tableName, columnName);
            statement.execute(sql);
        } catch (SQLException e) {
            LOG.error("add column exception", e);
        }
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        try (Statement statement = connection.createStatement()) {
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
