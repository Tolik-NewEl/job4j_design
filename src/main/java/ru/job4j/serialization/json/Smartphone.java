package ru.job4j.serialization.json;

import java.util.Arrays;

public class Smartphone {
    private final boolean available;
    private final int memsize;
    private final String[] memory;
    private final Contact contact;

    public Smartphone(boolean available, int memsize, String[] memory, Contact contact) {
        this.available = available;
        this.memsize = memsize;
        this.memory = memory;
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Smartphone{"
                + "available=" + available
                + ", memsize=" + memsize
                + ", memory=" + Arrays.toString(memory)
                + ", contact=" + contact + '}';
    }
}
