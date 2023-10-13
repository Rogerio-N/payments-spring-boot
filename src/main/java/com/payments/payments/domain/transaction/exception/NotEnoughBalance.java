package com.payments.payments.domain.transaction.exception;

import com.payments.payments.domain.shared.exception.BaseException;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public class NotEnoughBalance extends BaseException {

    private final String message;
    private final HttpStatusCode code;

    public NotEnoughBalance() {
        this.message = "Saldo insuficiente para realizar a transação";
        this.code = HttpStatus.BAD_REQUEST;
    }
}
