package com.github.vendigo.charon.parser;

import org.springframework.stereotype.Component;

@Component("columnConverter")
public class ColumnConverter {
    public Object convertColumn(Column column, String stringValue) throws Exception {
        return column.getColumnType().getConversion().convert(stringValue, column.getDateFormat());
    }
}
