package com.github.vendigo.charon.utils;

import org.apache.commons.lang3.Validate;

import java.util.Collection;

import static org.apache.commons.lang3.Validate.notNull;

public class Validate2 {

    private Validate2() {
    }

    public static int notNegative(int argument, String argumentName) {
        if (argument < 0) {
            fail(argumentName, "must be positive");
        }
        return argument;
    }

    public static long notNegative(long argument, String argumentName) {
        if (argument < 0) {
            fail(argumentName, "must be positive");
        }
        return argument;
    }

    public static <T extends Collection<E>, E> T notNullElements(T elements, String collectionName) {
        notNull(elements, collectionName + "must be not null");
        Validate.noNullElements(elements.toArray());
        return elements;
    }

    private static void fail(String argumentName, String errorMessage) {
        throw new IllegalArgumentException(argumentName + " " + errorMessage);
    }
}
