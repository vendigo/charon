package com.github.vendigo.charon.parser;

import com.github.vendigo.charon.configuration.AppProperties;
import org.apache.camel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

@Component("prepareRawRow")
public class RawRowPreparer {
    @Autowired
    AppProperties appProperties;

    @Handler
    public void prepareRawRow(@Body List<Map<String, String>> chunk, @ExchangeProperty("CamelSplitIndex") int chunkNum,
                              @Headers() Map<String, Object> headers) {
        //TODO Add Header Wrapper class
        Long fileId = (Long)headers.get("fileId");
        FileConfiguration fileConf = (FileConfiguration)headers.get("fileConfiguration");
        addFileIdAndLineNumber(chunk, chunkNum, fileId);
        headers.put("insertRawParams", joinRows(chunk, fileConf));
    }

    private String joinRows(List<Map<String, String>> chunk, FileConfiguration fileConf) {
        StringJoiner joiner = new StringJoiner(", ");
        chunk.forEach(row -> joiner.add(joinOneRow(row, fileConf)));
        return joiner.toString();
    }

    private String joinOneRow(Map<String, String> row, FileConfiguration fileConf) {
        StringJoiner joiner = new StringJoiner(", ", "(", ")");
        joiner.add(row.get("fileId")).
               add(row.get("lineNumber"));
        fileConf.getColumnNames().stream().forEach(columnName -> joiner.add(wrapInQuotes(row.get(columnName))));
        return joiner.toString();
    }

    private String wrapInQuotes(String s) {
        return "'"+s+"'";
    }

    private void addFileIdAndLineNumber(List<Map<String, String>> chunk, int chunkNum, Long fileId) {
        int lineNumber = appProperties.getChunkSize()*chunkNum;
        for (Map<String, String> rawRow : chunk) {
            rawRow.put("fileId", String.valueOf(fileId));
            rawRow.put("lineNumber", String.valueOf(lineNumber++));
        }
    }
}
