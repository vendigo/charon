package com.github.vendigo.charon.row;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

import java.util.List;

public class ChunkAggregationStrategy implements AggregationStrategy {
    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        if (oldExchange==null) {
            return newExchange;
        }

        if (newExchange==null) {
            return oldExchange;
        }

        List oldBody = (List) oldExchange.getIn().getBody();
        List newBody = (List) newExchange.getIn().getBody();
        oldBody.addAll(newBody);
        return oldExchange;
    }
}
