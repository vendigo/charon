package com.github.vendigo.charon.route;

import com.github.vendigo.charon.configuration.AppProperties;
import com.github.vendigo.charon.file.parsing.FileConfiguration;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.CsvDataFormat;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleRouteBuilder extends RouteBuilder {

    private AppProperties appProperties;
    private FileConfiguration fileConf;
    SqlEndpointConfigurer sqlEndpointConfigurer;

    public SimpleRouteBuilder(AppProperties appProperties, FileConfiguration fileConf) {
        this.appProperties = appProperties;
        this.fileConf = fileConf;
        this.sqlEndpointConfigurer = new SqlEndpointConfigurer(fileConf);
    }

    private CsvDataFormat csvDataFormat() {
        CsvDataFormat dataFormat = new CsvDataFormat();
        dataFormat.setHeader(fileConf.getFileColumnNames());
        dataFormat.setDelimiter(fileConf.getDelimiter());
        dataFormat.setUseMaps(true);
        dataFormat.setIgnoreEmptyLines(true);
        return dataFormat;
    }

    private ExecutorService threadPool() {
        return Executors.newFixedThreadPool(appProperties.getThreadsCount());
    }

    private String directEndpoint(FileConfiguration fileConf, String endpointName) {
        String template = "direct:%1$s_%2$s";
        return String.format(template, fileConf.getConfigName(), endpointName);
    }

    @Override
    public void configure() throws Exception {
        fromF("file://%1$s?move=%2$s&moveFailed=%3$s&fileName=%4$s&idempotent=true",
                appProperties.getInFolder(),
                appProperties.getOutFolder(),
                appProperties.getFailedFolder(),
                fileConf.getFileNamePattern()).
                beanRef("registerFile").
                beanRef("checkBusinessDate").
                choice().when(header("apropriateBusinessDate").isEqualTo(true)).
                to(directEndpoint(fileConf, "processFile"));


        from(directEndpoint(fileConf, "processFile")).
                split().tokenize("\n", appProperties.getChunkSize()).streaming().
                executorService(threadPool()).
                unmarshal(csvDataFormat()).
                beanRef("addUtilityColumns").
                to(directEndpoint(fileConf, "saveRawRecords"));

        from(directEndpoint(fileConf, "saveRawRecords")).
                transacted("springTransactionPolicy").
                to(sqlEndpointConfigurer.insert(fileConf.getRawTableName())).
                beanRef("validateAndCastRows").
                to(directEndpoint(fileConf, "saveParsed"));

        from(directEndpoint(fileConf, "saveParsed")).
                transacted("springTransactionPolicy").
                to(sqlEndpointConfigurer.insert(fileConf.getParsedTableName())).
                beanRef("sout");

    }
}
