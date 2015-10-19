package com.github.vendigo.charon.row;

import com.github.vendigo.charon.configuration.AppProperties;
import org.apache.camel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

@Component("addUtilityColumns")
public class RawRowPreparer {
    @Autowired
    AppProperties appProperties;

    @Handler
    public void prepareRawRow(@Body List<Map<String, String>> chunk, @ExchangeProperty("CamelSplitIndex") int chunkNum,
                              @Header("fileId") Long fileId) {
        addFileIdAndLineNumber(chunk, chunkNum, fileId);
    }

    private void addFileIdAndLineNumber(List<Map<String, String>> chunk, int chunkNum, Long fileId) {
        int lineNumber = appProperties.getChunkSize()*chunkNum;
        for (Map<String, String> rawRow : chunk) {
            rawRow.put("fileId", String.valueOf(fileId));
            rawRow.put("lineNumber", String.valueOf(lineNumber++));
        }
    }
}
