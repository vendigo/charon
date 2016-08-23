package com.github.vendigo.charon.configuration;

import com.github.vendigo.charon.routes.file.config.FileConfiguration;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.javaconfig.CamelConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ComponentScan(basePackages = "com.github.vendigo.charon")
public class RouteConfig extends CamelConfiguration {

    @Autowired
    List<FileConfiguration> fileConfigurations;
    @Autowired
    BeansHolder beansHolder;

    @Bean
    @Override
    public List<RouteBuilder> routes() {
        List<RouteBuilder> routeBuilders = new ArrayList<>();
        fileConfigurations.forEach(conf -> routeBuilders.add(new MainRouteBuilder(beansHolder, conf)));
        return routeBuilders;
    }
}
