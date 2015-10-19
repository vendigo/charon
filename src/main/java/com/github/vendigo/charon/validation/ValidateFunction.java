package com.github.vendigo.charon.validation;

import com.github.vendigo.charon.parser.Column;

public interface ValidateFunction {
    boolean validate(String value, Column column);
}
