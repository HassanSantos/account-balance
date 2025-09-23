package com.itau.account.balance.application.port.in;

import com.itau.account.balance.domain.model.Account;

public interface GetAccountBalanceUseCase {
    Account getAccountBalance(String accountId);
}