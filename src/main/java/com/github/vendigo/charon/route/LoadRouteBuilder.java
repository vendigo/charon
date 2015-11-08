package com.github.vendigo.charon.route;

import com.github.vendigo.charon.file.parsing.FileConfiguration;
import org.apache.camel.builder.RouteBuilder;

public class LoadRouteBuilder extends RouteBuilder {

    private final FileConfiguration fileConf;

    public LoadRouteBuilder(FileConfiguration fileConf) {
        this.fileConf = fileConf;
    }

    @Override
    public void configure() throws Exception {
        fromF("jpa://%1$s?consumer.delay=1000&consumeDelete=false&consumer.query=" +
                "select o from %1$s o where " +
                "exists(select i from com.github.vendigo.charon.file.registration.InFileStatus i where " +
                "i.state ='PARSED' and i.processed = true and " +
                "%2$s=(select f.fileConfigName from com.github.vendigo.charon.file.registration.InFile f where " +
                "f.fileId = i.fileId))",
                fileConf.getEntityFullName(), fileConf.getConfigName()).
                to("log:loadedData");
    }
}
