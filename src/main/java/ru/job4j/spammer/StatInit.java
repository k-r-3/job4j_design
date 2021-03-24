package ru.job4j.spammer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class StatInit {
    final static Logger LOG = LoggerFactory.getLogger(StatInit.class.getName());
    private Connection cn;

    public StatInit(Properties prop) throws SQLException, ClassNotFoundException {
        init(prop);
    }

    private void init(Properties prop) throws ClassNotFoundException, SQLException {
        Class.forName(prop.getProperty("jdbc.driver"));
        cn = DriverManager.getConnection(
                prop.getProperty("jdbc.url"),
                prop.getProperty("jdbc.username"),
                prop.getProperty("jdbc.password")
        );
    }

    public void createTable(String tableName) {
        try (Statement stat = cn.createStatement()) {
            String sql = String.format(
                    "create table "
                            + "%s(%s, %s, %s);",
                    tableName,
                    "id serial primary key",
                    "name varchar(255)",
                    "email varchar(255)"
            );
            stat.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropTable(String tableName) {
        try (Statement stat = cn.createStatement()) {
            String sql = "drop table " + tableName;
            stat.execute(sql);
        } catch (SQLException e) {
            LOG.error("delete exception", e);
        }
    }
}
