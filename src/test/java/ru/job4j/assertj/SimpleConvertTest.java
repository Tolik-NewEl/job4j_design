package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> rsl = simpleConvert.toList("one", "two", "three", "four", "five");
        assertThat(rsl).isNotEmpty()
                .doesNotContainNull()
                .containsAnyOf("three", "six");
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> rsl = simpleConvert.toSet("one", "one", "two", "two", "three");
        assertThat(rsl).hasSize(3)
                .containsExactly("one", "two", "three");
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> rsl = simpleConvert.toMap("one", "two", "three", "four", "five");
        assertThat(rsl).hasSize(5)
                .containsKeys("one", "two", "three", "four", "five")
                .containsValues(0, 4, 3, 2, 1)
                .doesNotContainValue(5);
    }
}