package com.payments.payments.domain.shared.validations;

public interface Validator<T> {
    ValidationResult validate(T input, ValidationResult previousResult);
}
