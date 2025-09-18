package com.itau.account.balance.adapter.in.messaging.dto;

import java.math.BigDecimal;

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
            String id,
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