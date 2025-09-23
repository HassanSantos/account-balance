package com.itau.account.balance.application.service;

import com.itau.account.balance.adapter.out.persistence.mapper.AccountMapper;
import com.itau.account.balance.adapter.out.persistence.mapper.TransactionMapper;
import com.itau.account.balance.adapter.out.persistence.repository.AccountRepository;
import com.itau.account.balance.adapter.out.persistence.repository.TransactionRepository;
import com.itau.account.balance.application.port.in.ProcessTransactionUseCase;
import com.itau.account.balance.domain.model.Account;
import com.itau.account.balance.domain.model.Transaction;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@AllArgsConstructor
@Slf4j
public class TransactionService implements ProcessTransactionUseCase {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final TransactionMapper transactionMapper;
    private final AccountMapper accountMapper;

    @Override
    public void processTransaction(Transaction transaction, Account account) {

        try {
//            var accountEntity = accountMapper.toEntity(account);
//            accountEntity.setCreatedAt(Instant.now());
//            accountRepository.save(accountEntity);
            transactionRepository.save(transactionMapper.toEntity(transaction, account));
        } catch (Exception e) {
            log.error("ERROR TO SAVE");
            log.error(e.getMessage(), e);
        }

    }


}