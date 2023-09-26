package com.payments.payments.domain.transaction.validations;

import com.payments.payments.domain.shared.validations.ValidationResult;
import com.payments.payments.domain.shared.validations.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidationChain<T> {

    private final List<Validator<T>> validators;

    @Autowired
    public ValidationChain(List<Validator<T>> validators) {
        this.validators = validators;
    }

    public ValidationResult validate(T input) throws Exception {
        ValidationResult result = new ValidationResult();

        for(Validator<T> validator: validators) {
            result = validator.validate(input, result);
            if(!result.getSuccess()){
                break;
            }

        }

        return result;
    }

}
