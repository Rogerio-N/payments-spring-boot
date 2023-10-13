package com.payments.payments.domain.transaction.exception;

import com.payments.payments.domain.shared.exception.BaseException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
public class NotEnoughBalance extends BaseException {

    private final String message;
    private final HttpStatusCode code;

    public NotEnoughBalance() {
        this.message = "Saldo insuficiente para realizar a transação";
        this.code = HttpStatus.BAD_REQUEST;
    }
}
