package com.github.vendigo.charon.utils;

import org.apache.camel.Body;
import org.apache.camel.Handler;
import org.springframework.stereotype.Component;

@Component("sout")
public class Sout {
    @Handler
    public void print(@Body Object body) {
        String message = "Message: \n %s \n ------------";
        System.out.println(String.format(message, body));
    }
}
