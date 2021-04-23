package ru.job4j.wrong;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Properties;

public class Scheduler implements DateGetter {
    private Connection cn;

    public Scheduler(Connection cn) {
        this.cn = cn;
    }

    public void connect() {
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
        try (Statement stat = cn.createStatement()) {
            stat.execute(String.format("create table scheduler(%s)",
                    "date timestamp"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public LocalDateTime getDate() {
        Timestamp currentDate = Timestamp.valueOf(LocalDateTime.now());
        try (PreparedStatement prep = cn.prepareStatement(
                "insert into scheduler(date) values(?)")) {
            prep.setTimestamp(1, currentDate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currentDate.toLocalDateTime();
    }
}
