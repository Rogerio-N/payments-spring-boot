package com.payments.payments.domain.transaction.validations;

import com.payments.payments.domain.shared.validations.ValidationResult;
import com.payments.payments.domain.shared.validations.Validator;
import com.payments.payments.domain.transaction.entities.Transaction;
import com.payments.payments.domain.user.entities.User;
import com.payments.payments.domain.user.enums.UserType;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

@Component
public class UserTypeValidator implements Validator<Transaction> {

    @Override
    public ValidationResult validate(Transaction input, ValidationResult previousResult) {
        ValidationResult validationResult = new ValidationResult();

        if( input.getSender().getUserType().equals(UserType.PJ)){
            validationResult.setSuccess(false);
            FieldError fieldError = new FieldError("user", "UserType","O usuário não pode ser PJ");
            validationResult.getErrors().add(fieldError);
        }
        return validationResult;
    }
}
