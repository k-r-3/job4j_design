package ru.job4j.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.stream.Stream;

public class ConnectionDemo {
    final static Logger LOG = LoggerFactory.getLogger(ConnectionDemo.class.getName());

    private String configParse(String value, ConnectionPattern cp, Stream<String> stream) {
        return stream
                .filter(l -> cp.getPatt(value)
                .matcher(l)
                .find())
                .findFirst()
                .get()
                .split("=")[1];
    }

    public static void main(String[] args) throws ClassNotFoundException {
        ConnectionDemo cd = new ConnectionDemo();
        String url = "";
        String username = "";
        String password = "";
        try (BufferedReader reader = new BufferedReader(new FileReader("./app.properties"))) {
            ConnectionPattern cp = new ConnectionPattern();
            url = cd.configParse("url", cp, reader.lines());
            username = cd.configParse("username", cp, reader.lines());
            password = cd.configParse("password", cp, reader.lines());
        } catch (IOException e) {
            LOG.error("Reader exception", e);
        }
        Class.forName("org.postgresql.Driver");
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        } catch (SQLException e) {
           LOG.error("DB connection exception", e);
        }
    }
}
