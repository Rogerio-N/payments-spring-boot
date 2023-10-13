package com.payments.payments.domain.shared.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseException extends Exception{
    private String message;
    private HttpStatusCode code;
    private Throwable exceptionCause;
}
