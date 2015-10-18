package com.github.vendigo.charon.validation;

public enum ValidationConstraint {
    NOT_NULL((s,p)->s!=null&&!s.isEmpty()),
    NOT_LONGER((s,p)->s==null||s.length()<=(Integer)p);

    private final ValidateFunction validateFunction;

    ValidationConstraint(ValidateFunction validateFunction) {
        this.validateFunction = validateFunction;
    }

    public ValidateFunction getValidateFunction() {
        return validateFunction;
    }
}
