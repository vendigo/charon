package com.github.vendigo.charon.utils;

import com.github.vendigo.charon.parser.FileConfiguration;
import org.apache.camel.Body;
import org.apache.camel.Handler;
import org.apache.camel.Header;
import org.apache.camel.Headers;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("sout")
public class Sout {
    @Handler
    public void print(@Body Object body, @Header("fileConfiguration") FileConfiguration fileConf) {
        String message = "Message: \n %s \n%s ------------";
        System.out.println(String.format(message, body, fileConf));
    }
}
