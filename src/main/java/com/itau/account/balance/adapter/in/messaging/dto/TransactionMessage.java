package com.itau.account.balance.adapter.in.messaging.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record TransactionMessage(
        TransactionData transaction,
        AccountData account
) {
    public record TransactionData(
            String id,
            String type,
            BigDecimal amount,
            String currency,
            String status,
            Long timestamp
    ) {}

    public record AccountData(
            UUID id,
            String owner,
            String createdAt,
            String status,
            BalanceData balance
    ) {}

    public record BalanceData(
            BigDecimal amount,
            String currency
    ) {}
}