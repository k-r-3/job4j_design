package ru.job4j.jdbc;
import java.util.regex.Pattern;

public class ConnectionPattern {

    public Pattern getPatt(String value) {
        Pattern urlCompile = Pattern.compile(".*url.*");
        Pattern userCompile = Pattern.compile(".*username.*");
        Pattern passwrodCompile = Pattern.compile(".*password.*");
        Pattern driverCompile = Pattern.compile(".*driver.*");
        switch (value) {
            case "url" :
                return urlCompile;
            case "username" :
                return userCompile;
            case "password" :
                return passwrodCompile;
            case "driver" :
                return driverCompile;
            default :
                return null;
        }

    }
}
