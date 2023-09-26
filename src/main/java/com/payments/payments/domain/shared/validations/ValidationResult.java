package com.payments.payments.domain.shared.validations;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ValidationResult {

    private Boolean success;
    private List<FieldError> errors;

    public ValidationResult() {
        this.success = true;
        this.errors = new ArrayList<>();
    }

    public void addError(String objectName, String field, String defaultMessage) {
        FieldError error = new FieldError(objectName, field, defaultMessage);
        errors.add(error);
    }
}
