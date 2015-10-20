package com.github.vendigo.charon.file.validation;

import com.github.vendigo.charon.configuration.AppProperties;
import com.github.vendigo.charon.file.parsing.FileConfiguration;
import org.apache.camel.Body;
import org.apache.camel.ExchangeProperty;
import org.apache.camel.Header;
import org.apache.camel.Headers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("extractHeaderAndFooter")
public class SpecialLineProcessor {

    public static final int FIRST_ELEM_INDEX = 0;
    @Autowired
    AppProperties appProperties;

    public String checkHeader(@Body String chunk, @Header("fileConfiguration") FileConfiguration fileConf,
                            @Headers Map<String, Object> headers,
                            @ExchangeProperty("CamelSplitIndex") int chunkNum,
                            @ExchangeProperty("CamelSplitComplete") boolean lastChunk) throws Exception {

        if (fileConf.isHasHeader() && chunkNum == FIRST_ELEM_INDEX) {
            int indexOfFirstLineEnd = chunk.indexOf(appProperties.getEndOfLine());
            String header = chunk.substring(0, indexOfFirstLineEnd).trim();
            fileConf.setHeader(header);
            return chunk.substring(indexOfFirstLineEnd).trim();
        }

        if (fileConf.isHasFooter() && lastChunk) {
            int indexIfLastLineEnd = chunk.lastIndexOf(appProperties.getEndOfLine());
            String footer = chunk.substring(indexIfLastLineEnd).trim();
            fileConf.setFooter(footer);
            return chunk.substring(0, indexIfLastLineEnd).trim();
        }

        return chunk;
    }

}
