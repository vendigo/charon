package com.github.vendigo.charon.utils;

import com.github.vendigo.charon.file.parsing.FileConfiguration;
import org.apache.camel.Body;
import org.apache.camel.Handler;
import org.apache.camel.Header;
import org.springframework.stereotype.Component;

@Component("sout")
public class Sout {
    @Handler
    public void print(@Body Object body, @Header("fileConfiguration") FileConfiguration fileConf) {
        String message = "Message: \n %s \n%s ------------";
        System.out.println(String.format(message, body, fileConf));
    }
}
