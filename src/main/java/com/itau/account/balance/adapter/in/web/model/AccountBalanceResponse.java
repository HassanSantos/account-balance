package com.itau.account.balance.adapter.in.web.model;

import com.itau.account.balance.domain.model.Balance;

import java.util.UUID;

public record AccountBalanceResponse(
        UUID id,
        String owner,
        Balance balance,
        java.time.Instant updatedAt
) {}
