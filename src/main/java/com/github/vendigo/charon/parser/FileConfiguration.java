package com.github.vendigo.charon.parser;

import java.util.List;
import java.util.stream.Collectors;

public class FileConfiguration {
    private String configName;
    private String fileNamePattern;
    private String delimiter = ",";
    private List<Column> columns;
    private String rawTableName;
    private String parsedTableName;
    private String histTableName;

    public FileConfiguration(String configName, String fileNamePattern, List<Column> columns) {
        //TODO Add Validation
        this.configName = configName;
        this.fileNamePattern = fileNamePattern;
        this.columns = columns;

        this.rawTableName = configName.toUpperCase()+"_RAW";
        this.parsedTableName = configName.toUpperCase();
        this.histTableName = configName.toUpperCase()+"_HIST";
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

    public String getRawTableName() {
        return rawTableName;
    }

    public void setRawTableName(String rawTableName) {
        this.rawTableName = rawTableName;
    }

    public String getParsedTableName() {
        return parsedTableName;
    }

    public void setParsedTableName(String parsedTableName) {
        this.parsedTableName = parsedTableName;
    }

    public String getHistTableName() {
        return histTableName;
    }

    public void setHistTableName(String histTableName) {
        this.histTableName = histTableName;
    }
}
