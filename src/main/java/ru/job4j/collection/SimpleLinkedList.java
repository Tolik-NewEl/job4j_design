package ru.job4j.collection;

import java.util.*;

public class SimpleLinkedList<E> implements List<E> {
    private Node<E> first;
    private Node<E> last;
    private int size;
    private int modCount;

    @Override
    public void add(E value) {
        Node<E> newNode = new Node<>(value);
        if (first == null) {
            newNode.next = null;
            newNode.prev = null;
            first = newNode;
        } else {
            last.next = newNode;
            newNode.prev = last;
        }
        last = newNode;
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> rsl = first;
        for (int i = 0; i < index; i++) {
            rsl = rsl.next;
        }
        return rsl.getItem();
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            final int expectedModCount = modCount;
            Node<E> node = first;
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return node != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E rsl = node.getItem();
                node = node.getNext();
                return rsl;
            }
        };
    }
}
