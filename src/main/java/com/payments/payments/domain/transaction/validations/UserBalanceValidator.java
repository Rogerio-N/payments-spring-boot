package com.payments.payments.domain.transaction.validations;

import com.payments.payments.domain.shared.validations.Validator;
import com.payments.payments.domain.transaction.entities.Transaction;
import com.payments.payments.domain.shared.validations.ValidationResult;
import com.payments.payments.domain.transaction.exception.NotEnoughBalance;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class UserBalanceValidator implements Validator<Transaction> {

    @Override
    public ValidationResult validate(Transaction input, ValidationResult previousResult) throws NotEnoughBalance {
        ValidationResult validationResult = new ValidationResult();

        BigDecimal userBalance = input.getSender().getBalance();
        BigDecimal transactionValue = input.getValue();

        if(userBalance.compareTo(transactionValue) < 0){
            validationResult.setSuccess(false);
            throw new NotEnoughBalance();
        }

        return validationResult;
    }
}
