package com.github.vendigo.charon.utils;

import java.util.Collection;
import java.util.StringJoiner;
import java.util.function.Function;

public class JoinHelper {
    public static String join(Function<String, String> transformation, Collection<String> elems) {
        StringJoiner joiner = new StringJoiner(", ");
        elems.forEach(elem -> joiner.add(transformation.apply(elem)));
        return joiner.toString();
    }
}
