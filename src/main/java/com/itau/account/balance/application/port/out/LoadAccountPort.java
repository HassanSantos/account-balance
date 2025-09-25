package com.itau.account.balance.application.port.out;

import com.itau.account.balance.adapter.out.persistence.entity.AccountEntity;

import java.util.Optional;

public interface LoadAccountPort {
    Optional<AccountEntity> loadAccount(String accountId);
}