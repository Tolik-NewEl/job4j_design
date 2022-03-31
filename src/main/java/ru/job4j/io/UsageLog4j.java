package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        char name = 'a';
        byte a = 1;
        short b = 2;
        int c = 3;
        long d = 4;
        float e = 2.1f;
        double f = 2.5;
        boolean g = a < b;
        LOG.debug("char: {}, byte: {}, short: {}, int: {}, long: {}, float: {}, double: {}, boolean: {}",
                name, a, b, c, d, e, f, g);
    }
}
