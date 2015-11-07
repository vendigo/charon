package com.github.vendigo.charon.route.rollback;

import com.github.vendigo.charon.configuration.AppProperties;
import org.apache.camel.builder.RouteBuilder;

public class RollbackRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        fromF("jpa://com.github.vendigo.charon.file.registration.InFileStatus?consumer.delay=1000&consumeDelete=false&" +
                "consumer.query=select o " +
                "from com.github.vendigo.charon.file.registration.InFileStatus o " +
                "where o.state='FAILED' and o.processed=false").
                beanRef("findParsedTableName").
                beanRef("cleanUpParsedTable");
    }
}
