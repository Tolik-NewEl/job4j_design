package ru.job4j.set;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class SimpleSetTest {

    @Test
    public void whenAddNonNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(1));
        assertTrue(set.contains(1));
        assertFalse(set.add(1));
    }

    @Test
    public void whenAddNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(null));
        assertTrue(set.contains(null));
        assertFalse(set.add(null));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNoSuchElement() {
        Set<Integer> set = new SimpleSet<>();
        set.add(1);
        set.add(2);
        Iterator<Integer> i = set.iterator();
        i.next();
        i.next();
        i.next();
    }

    @Test
    public void whenNoHasNext() {
        SimpleSet<Integer> set = new SimpleSet<>();
        set.add(1);
        set.add(2);
        Iterator<Integer> i = set.iterator();
        i.next();
        i.next();
        assertFalse(i.hasNext());
    }

}