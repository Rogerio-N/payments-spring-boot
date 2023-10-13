package com.payments.payments.domain.user.exception;

import com.payments.payments.domain.shared.exception.BaseException;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public class UserNotFound extends BaseException {

    private final String message;
    private final HttpStatusCode code;

    public UserNotFound() {
        this.message = "Cliente não encontrado";
        this.code = HttpStatus.NOT_FOUND;
    }
}
