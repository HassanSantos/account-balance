package com.itau.account.balance.application.port.out;

import com.itau.account.balance.domain.model.Account;
import java.util.Optional;

public interface LoadAccountPort {
    Optional<Account> loadAccount(String accountId);
    boolean existsById(String accountId);
}