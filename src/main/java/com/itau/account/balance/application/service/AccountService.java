package com.itau.account.balance.application.service;

import com.itau.account.balance.application.port.in.GetAccountBalanceUseCase;
import com.itau.account.balance.application.port.out.LoadAccountPort;
import com.itau.account.balance.domain.model.Account;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
public class AccountService implements GetAccountBalanceUseCase {

    private final LoadAccountPort loadAccountPort;
    private final MeterRegistry meterRegistry;
    private final Counter accountNotFoundCounter;

    public AccountService(LoadAccountPort loadAccountPort, MeterRegistry meterRegistry) {
        this.loadAccountPort = loadAccountPort;
        this.meterRegistry = meterRegistry;
        this.accountNotFoundCounter = Counter.builder("account.balance.not_found")
                .description("Number of account not found errors")
                .register(meterRegistry);
    }

    @Override
    public Account getAccountBalance(String accountId) {
        log.debug("Fetching balance for account: {}", accountId);

        return loadAccountPort.loadAccount(accountId)
                .map(account -> {
                    log.debug("Account found: {}", accountId);
                    meterRegistry.counter("account.balance.retrieved").increment();
                    return account;
                })
                .orElseThrow(() -> {
                    log.warn("Account not found: {}", accountId);
                    accountNotFoundCounter.increment();
                    throw new AccountNotFoundException("Account not found: " + accountId);
                });
    }

    public static class AccountNotFoundException extends RuntimeException {
        public AccountNotFoundException(String message) {
            super(message);
        }
    }
}