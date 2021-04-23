package ru.job4j.template;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class GeneratorTest {

    @Test
    public void whenGenerate() {
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> filler = Map.of("name", "Petr Arsentev", "subject", "you");
        Generator generator = new NameGenerator();
        String expected = generator.produce(template, filler);
        String actual = "I am a Petr Arsentev, Who are you? ";
        assertThat(actual, is(expected));
    }

    @Test(expected = NoKeysException.class)
    public void whenNoKeys() {
        String template = "I am a ${name}, Who are ${subject}, and what you ${question}? ";
        Map<String, String> filler = Map.of("name", "Petr Arsentev", "subject", "you");
        Generator generator = new NameGenerator();
        String expected = generator.produce(template, filler);
    }

    @Test(expected = RedundantEntryException.class)
    public void whenRedundantEntry() {
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> filler = Map.of("name", "Petr Arsentev", "subject", "you",
                "question", "want");
        Generator generator = new NameGenerator();
        String expected = generator.produce(template, filler);
    }

    @Test(expected = RedundantEntryException.class)
    public void whenRedundantEntry2() {
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> filler = Map.of("subject", "you", "question", "want");
        Generator generator = new NameGenerator();
        String expected = generator.produce(template, filler);
    }
}