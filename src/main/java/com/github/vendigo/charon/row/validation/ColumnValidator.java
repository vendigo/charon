package com.github.vendigo.charon.row.validation;

import com.github.vendigo.charon.routes.file.config.Column;
import org.springframework.stereotype.Component;

@Component("columnValidator")
public class ColumnValidator {
    public boolean validate(Column column, String stringValue) {
        return column.getValidationConstraints().stream().allMatch(c->c.getValidateFunction().validate(stringValue,
                column));
    }

}
