package com.github.vendigo.charon;

import org.apache.camel.spring.javaconfig.Main;

import java.util.concurrent.TimeUnit;

public class Launcher {
    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.setBasedPackages("com.github.vendigo.charon.configuration");
        main.run();
    }
}
