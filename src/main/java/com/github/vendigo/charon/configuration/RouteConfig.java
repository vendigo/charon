package com.github.vendigo.charon.configuration;

import com.github.vendigo.charon.file.parsing.FileConfiguration;
import com.github.vendigo.charon.route.MainRouteBuilder;
import com.github.vendigo.charon.route.RollbackRouteBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.javaconfig.CamelConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class RouteConfig extends CamelConfiguration {

    @Autowired
    List<FileConfiguration> fileConfigurations;
    @Autowired
    AppProperties appProperties;

    @Bean
    @Override
    public List<RouteBuilder> routes() {
        List<RouteBuilder> routeBuilders = new ArrayList<>();
        //fileConfigurations.forEach(conf -> routeBuilders.add(new MainRouteBuilder(appProperties, conf)));

        fileConfigurations.forEach(conf -> routeBuilders.add(new RollbackRouteBuilder(appProperties, conf)));
        return routeBuilders;
    }
}
