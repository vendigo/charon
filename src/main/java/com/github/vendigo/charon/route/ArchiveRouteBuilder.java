package com.github.vendigo.charon.route;

import org.apache.camel.builder.RouteBuilder;

public class ArchiveRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        fromF("jpa://InFileStatus?consumer.delay=1000&consumeDelete=false&" +
                "consumer.query=select o " +
                "from InFileStatus o " +
                "where o.state='PARSED' and o.processed=false").
                beanRef("findTableNames").
                beanRef("dataArchiver", "moveDataToHist").
                beanRef("dataArchiver", "cleanUpArchivedData").
                beanRef("createInFileStatus", "archived");
    }
}
