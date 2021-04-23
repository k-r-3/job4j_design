package ru.job4j.wrong;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Properties;

public class Finder {
    private Connection cn;

    public Finder(Connection cn) {
        this.cn = cn;
    }

    public void connect() throws SQLException {
        Properties prop = new Properties();
        try (InputStream input = Scheduler.class.getResourceAsStream("appOld.properties")) {
            prop.load(input);
            Class.forName(prop.getProperty("driver_class"));
            cn = DriverManager.getConnection(
                    prop.getProperty("url"),
                    prop.getProperty("username"),
                    prop.getProperty("password"));
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Path find() throws SQLException {
        Visitor visitor = null;
        try (PreparedStatement prep = cn.prepareStatement("select c.cond from conditions c"
                + "where id = 2")) {
            try (ResultSet result = prep.executeQuery()) {
                while (result.next()) {
                    visitor = new Visitor(p -> {
                        try {
                            return p.equals(Path.of(result.getString(1)));
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        return false;
                    });
                }
            }
            Files.walkFileTree(Paths.get("c:/projects"), visitor);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return visitor.getSought();
    }
}

