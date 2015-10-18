package com.github.vendigo.charon.route;

import com.github.vendigo.charon.parser.FileConfiguration;

import static com.github.vendigo.charon.utils.JoinHelper.join;

public class SqlEndpointConfigurer {
    public static final String ENDPOINT_CONFIG_TEMPLATE = "sql:%s?dataSource=#dataSource&batch=%s";
    public static final String INSERT_TEMPLATE = "INSERT INTO %s (%s) VALUES (%s)";
    public static final String CLEAN_TEMPLATE = "TRUNCATE TABLE %s";

    FileConfiguration fileConf;

    public SqlEndpointConfigurer(FileConfiguration fileConf) {
        this.fileConf = fileConf;
    }

    public String clear(String tableName) {
        String sqlStatement = String.format(CLEAN_TEMPLATE, tableName);
        return String.format(ENDPOINT_CONFIG_TEMPLATE, sqlStatement, "false");
    }

    public String insert(String tableName) {
        String sqlStatement = String.format(INSERT_TEMPLATE, tableName, join(String::toUpperCase, fileConf.getAllColumnNames()),
                join(s -> ":#" + s, fileConf.getAllColumnNames()));
        return String.format(ENDPOINT_CONFIG_TEMPLATE, sqlStatement, "true");
    }
}
