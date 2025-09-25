package com.itau.account.balance.adapter.out.persistence;

import com.itau.account.balance.adapter.out.persistence.entity.AccountEntity;
import com.itau.account.balance.adapter.out.persistence.repository.AccountRepository;
import com.itau.account.balance.application.port.out.LoadAccountPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountPersistenceAdapter implements LoadAccountPort {

    private final AccountRepository accountRepository;

    @Override
    public Optional<AccountEntity> loadAccount(String accountId) {
      return accountRepository.findById(UUID.fromString(accountId));
    }
}