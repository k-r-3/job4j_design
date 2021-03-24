package ru.job4j.spammer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class ImportDB {
    final static Logger LOG = LoggerFactory.getLogger(StatInit.class.getName());
    private Properties cfg;
    private String dump;

    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            rd.lines()
                    .map(s -> s.split(";"))
                    .collect(Collectors.toMap(m -> m[0], m -> m[1]))
                    .forEach((key, value) -> users.add(new User(key, value)));
        }
        return users;
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(cfg.getProperty("jdbc.driver"));
        try (Connection cnt = DriverManager.getConnection(
                cfg.getProperty("jdbc.url"),
                cfg.getProperty("jdbc.username"),
                cfg.getProperty("jdbc.password")
        )) {
            for (User user : users) {
                try (PreparedStatement ps = cnt.
                        prepareStatement("insert into users(name, email) values(?, ?)")) {
                    ps.setString(1, user.name);
                    ps.setString(2, user.email);
                    ps.execute();
                }
            }
        }
    }

    private static class User {
        private String name;
        private String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }

    public void show() throws SQLException {
        try (Connection cn = DriverManager.getConnection(
                cfg.getProperty("jdbc.url"),
                cfg.getProperty("jdbc.username"),
                cfg.getProperty("jdbc.password")
        )) {
            StringBuilder sb = new StringBuilder();
            try (PreparedStatement prep = cn.prepareStatement("select * from users")) {
                try (ResultSet result = prep.executeQuery()) {
                    while (result.next()) {
                        sb.append(String.format("%-15s  %-15s%n",
                                result.getString("name"),
                                result.getString("email")));
                    }
                    System.out.println(sb.toString());
                }
            } catch (SQLException e) {
                LOG.error("show exception", e);
            }
        }

    }

    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
        try (FileInputStream in = new FileInputStream("./app.properties")) {
            cfg.load(in);
            StatInit stat = new StatInit(cfg);
            stat.dropTable("users");
            stat.createTable("users");
        }
        ImportDB db = new ImportDB(cfg, "./dump.txt");
        db.save(db.load());
        db.show();
    }
}
