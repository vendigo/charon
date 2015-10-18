package com.github.vendigo.charon.parser;

public class Column {
    private String name;
    private ColumnType columnType;
    private boolean nullable = true;
    private Integer maxLength;
    private Integer fixedLength;
    private String dateFormat;

    public Column(String name, ColumnType columnType) {
        this.name = name;
        this.columnType = columnType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ColumnType getColumnType() {
        return columnType;
    }

    public void setColumnType(ColumnType columnType) {
        this.columnType = columnType;
    }

    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    public Integer getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

    public Integer getFixedLength() {
        return fixedLength;
    }

    public void setFixedLength(Integer fixedLength) {
        this.fixedLength = fixedLength;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    @Override
    public String toString() {
        return "Column{" +
                "name='" + name + '\'' +
                ", columnType=" + columnType +
                ", nullable=" + nullable +
                ", maxLength=" + maxLength +
                ", fixedLength=" + fixedLength +
                ", dateFormat='" + dateFormat + '\'' +
                '}';
    }
}
