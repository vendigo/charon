package com.github.vendigo.charon.routes.file.config;

import com.github.vendigo.charon.row.validation.ValidationConstraint;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.HashSet;
import java.util.Set;

import static com.github.vendigo.charon.utils.Validate2.notNegative;
import static com.github.vendigo.charon.utils.Validate2.notNullElements;
import static org.apache.commons.lang3.Validate.notEmpty;
import static org.apache.commons.lang3.Validate.notNull;

public class Column {
    private String name;
    private ColumnType columnType;
    private String dateFormat;
    private Integer maxLength;
    private Set<ValidationConstraint> validationConstraints = new HashSet<>();

    public Column(String name, ColumnType columnType) {
        this.name = notEmpty(name, "column name must be not empty");
        this.columnType = notNull(columnType, "columnType must be not null");
    }

    public String getName() {
        return name;
    }

    public ColumnType getColumnType() {
        return columnType;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = notEmpty(dateFormat, "dateFormat must be not empty");
    }

    public Integer getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(Integer maxLength) {
        this.maxLength = notNegative(maxLength, "maxLength");
    }

    public Set<ValidationConstraint> getValidationConstraints() {
        return validationConstraints;
    }

    public void setValidationConstraints(Set<ValidationConstraint> validationConstraints) {
        this.validationConstraints = notNullElements(validationConstraints, "validationConstraints");
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("columnType", columnType)
                .append("dateFormat", dateFormat)
                .append("maxLength", maxLength)
                .append("validationConstraints", validationConstraints)
                .toString();
    }
}
