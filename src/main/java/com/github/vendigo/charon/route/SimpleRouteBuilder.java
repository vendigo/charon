package com.github.vendigo.charon.route;

import com.github.vendigo.charon.configuration.AppProperties;
import com.github.vendigo.charon.parser.FileConfiguration;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.CsvDataFormat;

public class SimpleRouteBuilder extends RouteBuilder {

    private AppProperties appProperties;
    private FileConfiguration fileConf;

    public SimpleRouteBuilder(AppProperties appProperties, FileConfiguration fileConf) {
        this.appProperties = appProperties;
        this.fileConf = fileConf;
    }

    private CsvDataFormat csvDataFormat() {
        CsvDataFormat dataFormat = new CsvDataFormat();
        dataFormat.setHeader(fileConf.getColumnNames());
        dataFormat.setDelimiter(fileConf.getDelimiter());
        dataFormat.setUseMaps(true);
        dataFormat.setIgnoreEmptyLines(true);
        return dataFormat;
    }

    @Override
    public void configure() throws Exception {
        //TODO Return file moving
        //String pattern = "file://%1$s?move=%2$s&moveFailed=%3$s";
        fromF("file://%1$s?noop=true&fileName=%2$s", appProperties.getInFolder(), fileConf.getFileNamePattern()).
                beanRef("registerFile").
                split().tokenize("\n", appProperties.getChunkSize()).streaming().parallelProcessing().
                unmarshal(csvDataFormat()).
                beanRef("prepareRawRow").
                beanRef("sout").
                to("sql:insert into SAMPLE_RAW (FILEID, LINENUMBER, ID, NAME, AGE) VALUES :#${in.header.insertRawParams}?dataSource=#dataSource").
                beanRef("sout");

    }
}
