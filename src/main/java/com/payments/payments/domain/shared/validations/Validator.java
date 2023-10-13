package com.payments.payments.domain.shared.validations;

import com.payments.payments.domain.transaction.exception.InvalidUserType;
import com.payments.payments.domain.transaction.exception.NotEnoughBalance;

public interface Validator<T> {
    ValidationResult validate(T input, ValidationResult previousResult) throws NotEnoughBalance, InvalidUserType;
}
