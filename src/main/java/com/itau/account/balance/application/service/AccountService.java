package com.itau.account.balance.application.service;

import com.itau.account.balance.adapter.out.persistence.mapper.AccountMapper;
import com.itau.account.balance.application.port.in.GetAccountBalanceUseCase;
import com.itau.account.balance.application.port.out.LoadAccountPort;
import com.itau.account.balance.domain.model.Account;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AccountService implements GetAccountBalanceUseCase {

    private final LoadAccountPort loadAccountPort;
    private final AccountMapper accountMapper;

    @Override
    public Account getAccountBalance(String accountId) {
        log.debug("Fetching balance for account: {}", accountId);

        return loadAccountPort.loadAccount(accountId).map(i ->
                accountMapper.toAccount(i)).get();
    }

    public static class AccountNotFoundException extends RuntimeException {
        public AccountNotFoundException(String message) {
            super(message);
        }
    }
}