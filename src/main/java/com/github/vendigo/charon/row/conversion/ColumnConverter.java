package com.github.vendigo.charon.row.conversion;

import com.github.vendigo.charon.file.parsing.Column;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("columnConverter")
public class ColumnConverter {
    private Logger log = LoggerFactory.getLogger(ColumnConverter.class);

    private Object performConversion(Column column, String stringValue) throws Exception {
        return column.getColumnType().getConversion().convert(stringValue, column);
    }

    public Optional convertColumn(Column column, String stringValue) {
        try {
            return Optional.ofNullable(performConversion(column, stringValue));
        } catch (Exception e) {
            log.warn("Failed to convert column: {}, exception: {}", column, e);
            return Optional.empty();
        }
    }
}
