package com.github.vendigo.charon.parser;

import com.github.vendigo.charon.configuration.AppProperties;
import org.apache.camel.Body;
import org.apache.camel.ExchangeProperty;
import org.apache.camel.Handler;
import org.apache.camel.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("prepareRawRow")
public class RawRowPreparer {
    @Autowired
    AppProperties appProperties;

    @Handler
    public void addLineNumber(@Body List<Map<String, String>> chunk, @ExchangeProperty("CamelSplitIndex") int chunkNum,
                              @Header("fileId")int fileId, @ExchangeProperty("insertRawParams")String insertRawParams) {
        int lineNumber = appProperties.getChunkSize()*chunkNum;
        for (Map<String, String> rawRow : chunk) {
            rawRow.put("fileId", String.valueOf(fileId));
            rawRow.put("lineNumber", String.valueOf(lineNumber++));
        }
    }
}
