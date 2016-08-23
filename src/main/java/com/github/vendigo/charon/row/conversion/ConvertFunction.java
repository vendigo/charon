package com.github.vendigo.charon.row.conversion;

import com.github.vendigo.charon.routes.file.config.Column;

public interface ConvertFunction {
    Object convert(String stringValue, Column column) throws Exception;
}
