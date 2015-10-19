package com.github.vendigo.charon.row.conversion;

import com.github.vendigo.charon.file.parsing.Column;
import com.github.vendigo.charon.file.parsing.FileConfiguration;
import com.github.vendigo.charon.row.validation.ColumnValidator;
import org.apache.camel.Body;
import org.apache.camel.Handler;
import org.apache.camel.Header;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component("validateAndCastRows")
public class RecordProcessor {

    private Logger log = LoggerFactory.getLogger(RecordProcessor.class);

    @Autowired
    ColumnConverter converter;
    @Autowired
    ColumnValidator validator;

    @Handler
    public List<Map<String, Object>> process(@Body List<Map<String, String>> chunk, @Header("fileConfiguration") FileConfiguration fileConf) {
        List<Map<String, Object>> castedChunk = new ArrayList<>();

        chunk.forEach(row->{
            Optional<Map<String, Object>> processResult = processRow(row, fileConf);
            if (processResult.isPresent()) {
                castedChunk.add(processResult.get());
            }
        });

        return castedChunk;
    }

    private Optional<Map<String, Object>> processRow(Map<String, String> row, FileConfiguration fileConf) {
        Map<String, Object> castedRow = new HashMap<>();

        for (Map.Entry<String, String> rowEntry : row.entrySet()) {
            String columnName = rowEntry.getKey();
            String sValue = rowEntry.getValue();
            Column column = fileConf.getColumnByName(columnName);

            if (validator.validate(column, sValue)) {
                Optional castResult = converter.convertColumn(column, sValue);
                if (castResult.isPresent()) {
                    castedRow.put(columnName, castResult.get());
                } else {
                    return Optional.empty();
                }
            } else {
                log.warn("Row {} Column {} validation failed", row, sValue);
                return Optional.empty();
            }
        }

        return Optional.of(castedRow);
    }
}
