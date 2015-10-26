package com.github.vendigo.charon.route;

import com.github.vendigo.charon.configuration.AppProperties;
import com.github.vendigo.charon.file.parsing.FileConfiguration;
import org.apache.camel.builder.RouteBuilder;

public class RollbackRouteBuilder extends RouteBuilder {

    private AppProperties appProperties;
    private FileConfiguration fileConf;

    public RollbackRouteBuilder(AppProperties appProperties, FileConfiguration fileConf) {
        this.appProperties = appProperties;
        this.fileConf = fileConf;
    }

    @Override
    public void configure() throws Exception {
        fromF("jpa://com.github.vendigo.charon.file.registration.InFileStatus?consumer.delay=1000&consumeDelete=false&" +
                "consumer.query=select o " +
                "from com.github.vendigo.charon.file.registration.InFileStatus o " +
                "where o.fileConfigName = '%1$s' and o.state='FAILED'", fileConf.getConfigName()).to("log:rollback");
    }
}
