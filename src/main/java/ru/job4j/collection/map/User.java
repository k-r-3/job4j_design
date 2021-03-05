package ru.job4j.collection.map;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class User {
    private String name  = "asd";
    private Calendar birthday;
    private int i = 10;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(name, user.name)
                && Objects.equals(birthday, user.birthday)
                && Objects.equals(i, user.i);
    }

    @Override
    public int hashCode() {
        int result = Integer.hashCode(i);
        result = 31 * result + name.hashCode();
        result = 31 * result + birthday.hashCode();
        return result;
    }
}
