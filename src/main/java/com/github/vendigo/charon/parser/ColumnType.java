package com.github.vendigo.charon.parser;

import com.github.vendigo.charon.converter.ConvertFunction;

import java.text.SimpleDateFormat;

public enum ColumnType {
    STRING((s, p) -> s),
    INT((s, p) -> Long.valueOf(s)),
    BOOLEAN((s, p) -> s.equals("1") ? Boolean.TRUE : Boolean.valueOf(s)),
    DECIMAL((s, p) -> Double.valueOf(s)),
    DATE((s, p) -> new SimpleDateFormat((String) p).parse(s));

    private final ConvertFunction conversion;

    ColumnType(ConvertFunction conversion) {
        this.conversion = conversion;
    }

    public ConvertFunction getConversion() {
        return conversion;
    }
}
