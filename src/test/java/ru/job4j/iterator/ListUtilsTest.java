package ru.job4j.iterator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test
    public void whenRemove() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        ListUtils.removeIf(input, s -> s % 2 == 0);
        assertThat(input, is(Arrays.asList(2, 4)));
    }

    @Test
    public void whenReplace() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3));
        ListUtils.replaceIf(input, s -> s > 1, 1);
        assertThat(input, is(Arrays.asList(1, 1, 1)));
    }

    @Test
    public void whenRemoveAll() {
        List<Integer> original = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> duplicate = new ArrayList<>(Arrays.asList(2, 4));
        ListUtils.removeAll(original, duplicate);
        assertThat(original, is(Arrays.asList(1, 3, 5)));
    }
}