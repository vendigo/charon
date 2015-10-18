package com.github.vendigo.charon.validation;

public enum ValidationConstraints {
    NOT_NULL((s,p)->s!=null&&!s.isEmpty()),
    NOT_LONGER((s,p)->s.length()<=(Integer)p);

    private final ValidateFunction validateFunction;

    ValidationConstraints(ValidateFunction validateFunction) {
        this.validateFunction = validateFunction;
    }

    public ValidateFunction getValidateFunction() {
        return validateFunction;
    }
}
