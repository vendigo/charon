package com.github.vendigo.charon.route;

import com.github.vendigo.charon.parser.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

import static com.github.vendigo.charon.utils.JoinHelper.join;

public class PreparedStatementCreator {
    FileConfiguration fileConf;

    public PreparedStatementCreator(FileConfiguration fileConf) {
        this.fileConf = fileConf;
    }

    public String insertRawRow() {
        String template = "INSERT INTO %s (%s) VALUES (%s)";

        List<String> columnNames = new ArrayList<>();
        columnNames.add("fileId");
        columnNames.add("lineNumber");
        columnNames.addAll(fileConf.getColumnNames());

        return String.format(template, fileConf.getRawTableName(), join(String::toUpperCase, columnNames),
                join(s -> ":#" + s, columnNames));
    }
}
