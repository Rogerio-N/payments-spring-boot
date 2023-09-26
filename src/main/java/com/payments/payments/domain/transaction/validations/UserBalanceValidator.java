package com.payments.payments.domain.transaction.validations;

import com.payments.payments.domain.shared.validations.Validator;
import com.payments.payments.domain.transaction.entities.Transaction;
import com.payments.payments.domain.shared.validations.ValidationResult;
import com.payments.payments.domain.user.entities.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import java.math.BigDecimal;

@Component
public class UserBalanceValidator implements Validator<Transaction> {

    @Override
    public ValidationResult validate(Transaction input, ValidationResult previousResult) {
        ValidationResult validationResult = new ValidationResult();

        BigDecimal userBalance = input.getSender().getBalance();
        BigDecimal transactionValue = input.getValue();

        if(userBalance.compareTo(transactionValue) < 0){
            validationResult.setSuccess(false);
            FieldError fieldError = new FieldError("user", "balance","Saldo insuficiente para realizar a transação");
            validationResult.getErrors().add(fieldError);
        }

        return validationResult;
    }
}
