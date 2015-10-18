package com.github.vendigo.charon.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FileConfiguration {
    private String configName;
    private String fileNamePattern;
    private String delimiter = ",";
    private List<Column> fileColumns;
    private List<Column> allColumns;
    private String rawTableName;
    private String parsedTableName;
    private String histTableName;
    private String insertRawParams;

    public FileConfiguration(String configName, String fileNamePattern, List<Column> fileColumns) {
        //TODO Add Validation
        this.configName = configName;
        this.fileNamePattern = fileNamePattern;
        this.fileColumns = fileColumns;
        this.allColumns = collectAllColumns();
        this.rawTableName = configName.toUpperCase() + "_RAW";
        this.parsedTableName = configName.toUpperCase();
        this.histTableName = configName.toUpperCase() + "_HIST";
    }

    private List<Column> collectAllColumns() {
        List<Column> allColumns = new ArrayList<>();
        allColumns.add(new Column("fileId", ColumnType.INT));
        allColumns.add(new Column("lineNumber", ColumnType.INT));
        allColumns.addAll(fileColumns);
        return allColumns;
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

    public List<String> getFileColumnNames() {
        return fileColumns.stream().map(Column::getName).collect(Collectors.toList());
    }

    public List<String> getAllColumnNames() {
        return allColumns.stream().map(Column::getName).collect(Collectors.toList());
    }

    public Column getColumnByName(String columnName) {
        //TODO Add Validation
        Optional<Column> result = allColumns.stream().filter(column -> columnName.equals(column.getName())).findFirst();

        if (result.isPresent()) {
            return result.get();
        }

        throw new IllegalArgumentException("Nonexistent column");
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

    public String getInsertRawParams() {
        return insertRawParams;
    }

    public void setInsertRawParams(String insertRawParams) {
        this.insertRawParams = insertRawParams;
    }

    @Override
    public String toString() {
        return "FileConfiguration{" +
                "configName='" + configName + '\'' +
                ", fileNamePattern='" + fileNamePattern + '\'' +
                ", delimiter='" + delimiter + '\'' +
                ", allColumns=" + allColumns +
                ", rawTableName='" + rawTableName + '\'' +
                ", parsedTableName='" + parsedTableName + '\'' +
                ", histTableName='" + histTableName + '\'' +
                ", insertRawParams='" + insertRawParams + '\'' +
                '}';
    }
}
