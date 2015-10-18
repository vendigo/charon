package com.github.vendigo.charon.converter;

public interface ConvertFunction {
    Object convert(String stringValue, Object param) throws Exception;
}
