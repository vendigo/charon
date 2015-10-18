package com.github.vendigo.charon.route;

import com.github.vendigo.charon.parser.FileConfiguration;

import static com.github.vendigo.charon.utils.JoinHelper.join;

public class PreparedStatementCreator {
    FileConfiguration fileConf;

    public PreparedStatementCreator(FileConfiguration fileConf) {
        this.fileConf = fileConf;
    }

    public String insertRawRow() {
        String template = "INSERT INTO %s (%s) VALUES (%s)";

        return String.format(template, fileConf.getRawTableName(), join(String::toUpperCase, fileConf.getAllColumnNames()),
                join(s -> ":#" + s, fileConf.getAllColumnNames()));
    }
}
