package com.github.vendigo.charon.parser;

import com.github.vendigo.charon.validation.ValidationConstraint;

import java.util.HashSet;
import java.util.Set;

public class Column {
    private String name;
    private ColumnType columnType;
    private String dateFormat;
    private Integer maxLength;
    private Set<ValidationConstraint> validationConstraints = new HashSet<>();

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

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public Integer getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

    public Set<ValidationConstraint> getValidationConstraints() {
        return validationConstraints;
    }

    public void setValidationConstraints(Set<ValidationConstraint> validationConstraints) {
        this.validationConstraints = validationConstraints;
    }

    @Override
    public String toString() {
        return "Column{" +
                "name='" + name + '\'' +
                ", columnType=" + columnType +
                ", dateFormat='" + dateFormat + '\'' +
                ", maxLength=" + maxLength +
                ", validationConstraints=" + validationConstraints +
                '}';
    }
}
