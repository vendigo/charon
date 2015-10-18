package com.github.vendigo.charon.parser;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component("recordValidator")
public class RecordValidator {
    public boolean validate(FileConfiguration fileConf, Map<String, String> rawRow) {
        for (String columnName : rawRow.keySet()) {
            if (!isValidColumn(fileConf.getColumnByName(columnName), rawRow.get(columnName))) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidColumn(Column columnByName, String stringValue) {

        return true;
    }
}
