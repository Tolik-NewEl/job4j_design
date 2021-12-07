package ru.job4j.map;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean rsl;
        if (count >= capacity * LOAD_FACTOR) {
            expand();
        }
        if (table[indexFor(key)] != null) {
            rsl = false;
        } else {
            table[indexFor(key)] = new MapEntry<>(key, value);
            count++;
            modCount++;
            rsl = true;
        }
        return rsl;
    }

    private int hash(K key) {
        int hash = 31;
        hash = hash * 17 + key.hashCode();
        return hash;
    }

    private int indexFor(K key) {
        return hash(key) & (capacity - 1);
    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] oldTable = table;
        table = new MapEntry[capacity];
        count = 0;
        for (MapEntry<K, V> kvMapEntry : oldTable) {
            if (kvMapEntry != null) {
                table[indexFor(kvMapEntry.key)] = kvMapEntry;
                count++;
            }
        }
    }

    @Override
    public V get(K key) {
        return table[indexFor(key)] != null
                && key.equals(table[indexFor(key)].key)
                ? table[indexFor(key)].value : null;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        if (table[indexFor(key)] != null && key.equals(table[indexFor(key)].key)) {
            table[indexFor(key)] = null;
            count--;
            modCount++;
            rsl = true;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            final int expectedModCount = modCount;
            int point = 0;
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                boolean rsl = false;
                for (int i = point; i < table.length; i++) {
                    if (table[point] != null) {
                        rsl = true;
                        break;
                    }
                    point++;
                }
                return rsl;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[point++].key;
            }
        };

    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
