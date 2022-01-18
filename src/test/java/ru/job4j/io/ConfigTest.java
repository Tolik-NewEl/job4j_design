package ru.job4j.io;

import org.hamcrest.Matchers;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("Anatoly"), is("admin"));
        assertThat(config.value("surname"), is(Matchers.nullValue()));
    }

    @Test
    public void whenPairWithComment() {
        String path = "./data/pair_with_comments.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("anatoly"), is("admin"));
        assertThat(config.value("surname"), is(Matchers.nullValue()));
    }

    @Test
    public void whenPairWithBlankString() {
        String path = "./data/pair_with_blank_string.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("anatoly"), is("admin"));
        assertThat(config.value("sonic"), is("hedgehog"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenIncorrectPair() {
        String path = "./data/pair_incorrect.properties";
        Config config = new Config(path);
        config.load();
    }
}