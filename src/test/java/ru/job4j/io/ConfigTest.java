package ru.job4j.io;

import org.junit.Test;

import java.util.Objects;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(
                config.value("hibernate.connection.url"),
                is("jdbc:postgresql://127.0.0.1:5432/idea_db")
        );
    }

    @Test
    public void whenPairWithComment() {
        String path = "./app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(
                Objects.isNull(config.value("hibernate.dialec")),
                is(true)
        );
    }

    @Test
    public void whenLinesWithComment() {
        String path = "./propertiesWithComment.txt";
        Config config = new Config(path);
        config.load();
        assertThat(
                Objects.isNull(config.value("hibernate.connection.driver_class")),
                is(true)
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenKeyWithoutValue() {
        String path = "./keyWithoutValue.txt";
        Config config = new Config(path);
        config.load();
    }
}