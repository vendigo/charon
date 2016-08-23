package com.github.vendigo.charon.route.helpers;

import com.github.vendigo.charon.routes.file.config.FileConfiguration;

import static com.github.vendigo.charon.utils.JoinHelper.join;

public class SqlEndpointConfigurer {
    public static final String ENDPOINT_CONFIG_TEMPLATE = "sql:%s?dataSource=#dataSource&batch=true";
    public static final String INSERT_TEMPLATE = "INSERT INTO %s (%s) VALUES (%s)";

    FileConfiguration fileConf;

    public SqlEndpointConfigurer(FileConfiguration fileConf) {
        this.fileConf = fileConf;
    }

    public String insert(String tableName) {
        String sqlStatement = String.format(INSERT_TEMPLATE, tableName, join(String::toUpperCase, fileConf.getAllColumnNames()),
                join(s -> ":#" + s, fileConf.getAllColumnNames()));
        return String.format(ENDPOINT_CONFIG_TEMPLATE, sqlStatement);
    }
}
