package com.itau.account.balance.application.service;

import com.itau.account.balance.adapter.out.persistence.mapper.TransactionMapper;
import com.itau.account.balance.adapter.out.persistence.repository.TransactionRepository;
import com.itau.account.balance.application.port.in.ProcessTransactionUseCase;
import com.itau.account.balance.domain.model.Transaction;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class TransactionService implements ProcessTransactionUseCase {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    @Override
    public void processTransaction(Transaction transaction) {

        try {

        transactionRepository.save(transactionMapper.toEntity(transaction));
        }catch (Exception e){
            log.error("ERROR TO SAVE");
            log.error(e.getMessage(), e);
        }

    }


}