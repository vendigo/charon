package com.github.vendigo.charon.parser;

import java.util.List;
import java.util.stream.Collectors;

public class FileConfiguration {
    private String configName;
    private String fileNamePattern;
    private String delimiter = ",";
    private List<Column> columns;

    public FileConfiguration(String configName, String fileNamePattern, List<Column> columns) {
        this.configName = configName;
        this.fileNamePattern = fileNamePattern;
        this.columns = columns;
    }

    public String getConfigName() {
        return configName;
    }

    public String getFileNamePattern() {
        return fileNamePattern;
    }

    public String getDelimiter() {
        return delimiter;
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public List<String> getColumnNames() {
        return columns.stream().map(Column::getName).collect(Collectors.toList());
    }
}
