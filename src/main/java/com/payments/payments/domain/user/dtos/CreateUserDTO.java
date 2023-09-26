package com.payments.payments.domain.user.dtos;

import com.payments.payments.domain.user.enums.UserType;

import java.math.BigDecimal;

public record CreateUserDTO(
        String firstName,
        String lastName,
        String email,
        String password,
        String document,
        BigDecimal balance,
        UserType userType
) {
}
