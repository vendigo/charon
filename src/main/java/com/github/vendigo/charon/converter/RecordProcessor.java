package com.github.vendigo.charon.converter;

import com.github.vendigo.charon.parser.Column;
import com.github.vendigo.charon.parser.FileConfiguration;
import org.apache.camel.Body;
import org.apache.camel.Handler;
import org.apache.camel.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Component("castRows")
public class RecordProcessor {

    @Autowired
    ColumnConverter converter;

    @Handler
    public void process(@Body List<Map<String, Object>> chunk, @Header("fileConfiguration") FileConfiguration fileConf) {
        Iterator<Map<String, Object>> iterator = chunk.iterator();
        while (iterator.hasNext()) {
            Map<String, Object> row = iterator.next();
            if (!processRow(row, fileConf)) {
                iterator.remove();
            }
        }
    }

    private boolean processRow(Map<String, Object> row, FileConfiguration fileConf) {
        for (String columnName : row.keySet()) {
            Column column = fileConf.getColumnByName(columnName);
            String stringValue = (String) row.get(columnName);

            if (!converter.convertColumn(row, column, stringValue)) {
                return false;
            }
        }
        return true;
    }
}
