package com.payments.payments.domain.transaction.dtos;

import java.math.BigDecimal;

public record CreateTransactionDTO(
        Long senderId,
        Long receiverId,
        BigDecimal value
) {
}
