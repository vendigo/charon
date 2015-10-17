package com.github.vendigo.charon.persist;

import com.github.vendigo.charon.parser.FileConfiguration;

import java.util.StringJoiner;

public class QueryBuilder {

    public static final String INERT_RAW_TEMPLATE = "INSERT INTO %s %s VALUES #";
    private final FileConfiguration fileConf;

    public QueryBuilder(FileConfiguration fileConf) {
        this.fileConf = fileConf;
    }

    public String insertRawQuery() {
        return String.format(INERT_RAW_TEMPLATE, fileConf.getRawTableName(), columnNames());

    }

    private String columnNames() {
        StringJoiner joiner = new StringJoiner(", ", "(", ")");
        joiner.add("fileId").
               add("lineNumber");
        fileConf.getColumnNames().stream().forEach(joiner::add);
        return joiner.toString();
    }
}
