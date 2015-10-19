package com.github.vendigo.charon.row.validation;

public enum ValidationConstraint {
    NOT_NULL((s,c)->s!=null&&!s.isEmpty()),
    NOT_LONGER((s,c)->s==null||s.length()<=c.getMaxLength());

    private final ValidateFunction validateFunction;

    ValidationConstraint(ValidateFunction validateFunction) {
        this.validateFunction = validateFunction;
    }

    public ValidateFunction getValidateFunction() {
        return validateFunction;
    }
}
