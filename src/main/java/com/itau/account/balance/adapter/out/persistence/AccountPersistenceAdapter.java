package com.itau.account.balance.adapter.out.persistence;

import com.itau.account.balance.adapter.out.persistence.repository.AccountRepository;
import com.itau.account.balance.application.port.out.LoadAccountPort;
import com.itau.account.balance.domain.model.Account;
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
//    private final AccountMapper accountMapper;

    @Override
    public Optional<Account> loadAccount(String accountId) {
        log.debug("Loading account from database: {}", accountId);
        return Optional.empty();
//        TODO ALTERAR
    }

    @Override
    public boolean existsById(String accountId) {
        return accountRepository.existsById(UUID.randomUUID());
    }

//    @Override
//    public Account saveAccount(Account account) {
//        log.debug("Saving account to database: {}", account.getId());
//        AccountEntity entity = accountMapper.toEntity(account);
//        AccountEntity savedEntity = accountRepository.save(entity);
//        return accountMapper.toDomain(savedEntity);
//    }

}