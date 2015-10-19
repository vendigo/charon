package com.github.vendigo.charon.row.validation;

import com.github.vendigo.charon.file.parsing.Column;

public interface ValidateFunction {
    boolean validate(String value, Column column);
}
