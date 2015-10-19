package com.github.vendigo.charon.converter;

import com.github.vendigo.charon.parser.Column;

public interface ConvertFunction {
    Object convert(String stringValue, Column column) throws Exception;
}
