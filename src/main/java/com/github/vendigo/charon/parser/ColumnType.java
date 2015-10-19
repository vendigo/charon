package com.github.vendigo.charon.parser;

import com.github.vendigo.charon.converter.ConvertFunction;

import java.text.SimpleDateFormat;

public enum ColumnType {
    STRING((s, c) -> s),
    INT((s, c) -> Long.valueOf(s)),
    BOOLEAN((s, c) -> s.equals("1") ? Boolean.TRUE : Boolean.valueOf(s)),
    DECIMAL((s, c) -> Double.valueOf(s)),
    DATE((s, c) -> new SimpleDateFormat(c.getDateFormat()).parse(s));

    private final ConvertFunction conversion;

    ColumnType(ConvertFunction conversion) {
        this.conversion = conversion;
    }

    public ConvertFunction getConversion() {
        return conversion;
    }
}
