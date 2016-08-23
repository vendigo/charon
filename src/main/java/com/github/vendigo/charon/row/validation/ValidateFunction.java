package com.github.vendigo.charon.row.validation;

import com.github.vendigo.charon.routes.file.config.Column;

public interface ValidateFunction {
    boolean validate(String value, Column column);
}
