package com.github.vendigo.charon.converter;

import com.github.vendigo.charon.parser.Column;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("columnConverter")
public class ColumnConverter {
    private Logger log = LoggerFactory.getLogger(RecordProcessor.class);

    private Object performConversion(Column column, String stringValue) throws Exception {
        return column.getColumnType().getConversion().convert(stringValue, column.getDateFormat());
    }

    public boolean convertColumn(Map<String, Object> row, Column column, String stringValue) {
        try {
            Object castedValue = performConversion(column, stringValue);
            row.put(column.getName(), castedValue);
        } catch (Exception e) {
            log.warn("Failed to convert column: {}, exception: {}", column, e);
            return false;
        }
        return true;
    }
}
