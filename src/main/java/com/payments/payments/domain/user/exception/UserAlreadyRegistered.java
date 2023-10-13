package com.payments.payments.domain.user.exception;

import com.payments.payments.domain.shared.exception.BaseException;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public class UserAlreadyRegistered extends BaseException {

    private final String message;
    private final HttpStatusCode code;

    public UserAlreadyRegistered() {
        this.message = "Email ou documento já cadastrados";
        this.code = HttpStatus.NOT_FOUND;
    }
}
