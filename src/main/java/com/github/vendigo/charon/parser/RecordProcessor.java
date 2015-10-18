package com.github.vendigo.charon.parser;

import org.apache.camel.Body;
import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.apache.camel.Header;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Component("castRows")
public class RecordProcessor {

    private Logger log = LoggerFactory.getLogger(RecordProcessor.class);

    @Autowired
    ColumnConverter converter;

    @Handler
    public void process(@Body List<Map<String, Object>> chunk, @Header("fileConfiguration") FileConfiguration fileConf,
                        Exchange exchange) {
        chunk.forEach(row -> processRow(row, fileConf, exchange));
    }

    private void processRow(Map<String, Object> row, FileConfiguration fileConf, Exchange exchange) {
        for (String columnName : row.keySet()) {
            Column column = fileConf.getColumnByName(columnName);
            String stringValue = (String) row.get(columnName);

            try {
                Object castedValue = converter.convertColumn(column, stringValue);
                row.put(columnName, castedValue);
            } catch (Exception e) {
                log.warn("Failed to convert column: {}, exception: {}", column, e);
            }
        }
    }

    private Object castValue(String stringValue, Column column) throws ParseException {
        switch (column.getColumnType()) {
            case STRING:
                return stringValue;
            case INT:
                return Long.valueOf(stringValue);
            case DECIMAL:
                return Double.valueOf(stringValue);
            case BOOLEAN:
                return parseBoolean(stringValue);
            case DATE:
                return parseDate(stringValue, column);
        }

        return null;
    }

    private Boolean parseBoolean(String stringValue) {
        return stringValue.equals("1") ? Boolean.TRUE : Boolean.valueOf(stringValue);
    }

    private Object parseDate(String stringValue, Column column) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(column.getDateFormat());
        return dateFormat.parse(stringValue);
    }
}
