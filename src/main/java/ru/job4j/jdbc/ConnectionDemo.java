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

public class ConnectionDemo {
    final static Logger LOG = LoggerFactory.getLogger(ConnectionDemo.class.getName());

    public static void main(String[] args) throws ClassNotFoundException {
        String url = "";
        String username = "";
        String password = "";
        try (BufferedReader reader = new BufferedReader(new FileReader("./app.properties"))) {
            ConnectionPattern cp = new ConnectionPattern();
            url = reader.lines()
                    .filter(l -> cp.getPatt("url")
                            .matcher(l)
                            .find())
                    .findFirst()
                    .get()
                    .split("=")[1];
            username = reader.lines()
                    .filter(l -> cp.getPatt("username")
                            .matcher(l)
                            .find())
                    .findFirst()
                    .get()
                    .split("=")[1];
            password = reader.lines()
                    .filter(l -> cp.getPatt("password")
                            .matcher(l)
                            .find())
                    .findFirst()
                    .get()
                    .split("=")[1];
        } catch (IOException e) {
            LOG.error("Reader exception", e);
        }
        Class.forName("org.postgresql.Driver");
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
