package com.github.vendigo.charon.parser;

public interface ConvertFunction {
    Object convert(String stringValue, Object param) throws Exception;
}
