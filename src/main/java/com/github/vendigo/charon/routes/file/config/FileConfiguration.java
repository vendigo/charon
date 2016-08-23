package com.github.vendigo.charon.routes.file.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.github.vendigo.charon.utils.Validate2.notNullElements;
import static org.apache.commons.lang3.Validate.notEmpty;
import static org.apache.commons.lang3.Validate.notNull;

public class FileConfiguration {
    private String configName;
    private String fileNamePattern;
    private List<Column> fileColumns;
    private List<Column> allColumns;

    private String delimiter = ",";
    private String rawTableName;
    private String parsedTableName;
    private String histTableName;
    private String insertRawParams;
    private boolean hasHeader = false;
    private boolean hasFooter = false;
    private volatile String footer;
    private volatile String header;
    private Class<?> entityClass;

    public FileConfiguration(String configName, String fileNamePattern, List<Column> fileColumns) {
        this.configName = notEmpty(configName, "configName must be not empty");
        this.fileNamePattern = notEmpty(fileNamePattern, "fileNamePattern must be not empty");
        this.fileColumns = notNullElements(fileColumns, "fileColumns");
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
        notEmpty(columnName, "columnName must be not empty");
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

    public boolean isHasHeader() {
        return hasHeader;
    }

    public void setHasHeader(boolean hasHeader) {
        this.hasHeader = hasHeader;
    }

    public boolean isHasFooter() {
        return hasFooter;
    }

    public void setHasFooter(boolean hasFooter) {
        this.hasFooter = hasFooter;
    }

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public Class<?> getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class<?> entityClass) {
        this.entityClass = notNull(entityClass, "entityClass must be not null");
    }
}
