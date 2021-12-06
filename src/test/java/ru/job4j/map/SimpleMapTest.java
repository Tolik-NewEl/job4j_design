package ru.job4j.map;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleMapTest {

    @Test
    public void whenPutAndGetValue() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "abc");
        String rsl = map.get(1);
        assertThat(rsl, is("abc"));
    }

    @Test
    public void whenPutSameIndexThanFalse() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "abc");
        assertFalse(map.put(1, "def"));
    }

    @Test
    public void whenGetNullThanFalse() {
        SimpleMap<String, String> map = new SimpleMap<>();
        assertNull(map.get("a"));
    }

    @Test
    public void whenRemoveThanTrue() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "abc");
        map.put(2, "def");
        assertTrue(map.remove(1));
    }

    @Test
    public void whenNotRemoveThanFalse() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "abc");
        map.put(2, "def");
        assertFalse(map.remove(3));
    }

    @Test
    public void whenPutAndIterateMap() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "abc");
        map.put(2, "def");
        Iterator<Integer> it = map.iterator();
        assertThat(map.get(it.next()), is("abc"));
        assertThat(map.get(it.next()), is("def"));
    }

    @Test
    public void whenItHasNextTrueAndNotHasNextFalse() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "abc");
        map.put(2, "def");
        Iterator<Integer> it = map.iterator();
        it.next();
        assertTrue(it.hasNext());
        it.next();
        assertFalse(it.hasNext());
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenModifyAfterIterationThenThrowException() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "abc");
        map.put(2, "def");
        Iterator<Integer> it = map.iterator();
        map.put(3, "ghi");
        it.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenEmptyMapIteratorNextThenThrowException() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        Iterator<Integer> it = map.iterator();
        it.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenHasNoNextThenThrowException() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "abc");
        Iterator<Integer> it = map.iterator();
        it.next();
        it.next();
    }
}