package com.github.vendigo.charon.route;

import com.github.vendigo.charon.configuration.AppProperties;
import com.github.vendigo.charon.routes.file.config.FileConfiguration;
import com.github.vendigo.charon.route.helpers.SqlEndpointConfigurer;
import com.github.vendigo.charon.row.ChunkAggregationStrategy;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.CsvDataFormat;
import org.apache.camel.model.language.ConstantExpression;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParsingRouteBuilder extends RouteBuilder {

    public static final int ONE_SECOND = 1000;
    private AppProperties appProperties;
    private FileConfiguration fileConf;
    SqlEndpointConfigurer sqlEndpointConfigurer;

    public ParsingRouteBuilder(AppProperties appProperties, FileConfiguration fileConf) {
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
        fromF("file://%1$s?&include=%1$s&noop=true",
                fileConf.getFileNamePattern()).
                beanRef("registerFile").
                split().tokenize(appProperties.getEndOfLine(), appProperties.getChunkSize()).streaming().
                executorService(threadPool()).
                beanRef("extractHeaderAndFooter").
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
                aggregate(new ConstantExpression("expr"), new ChunkAggregationStrategy()).
                completionTimeout(ONE_SECOND).
                beanRef("validateFooter");
    }
}
