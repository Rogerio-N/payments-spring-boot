package com.payments.payments.domain.shared.validations;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.FieldError;

import java.util.List;

@Getter
@Setter
public class ValidationResult {

    private Boolean success;
    private List<FieldError> errors;
}
