package com.itau.account.balance.application.service;

import com.itau.account.balance.application.port.in.ProcessTransactionUseCase;
import com.itau.account.balance.domain.model.Transaction;
import org.springframework.stereotype.Service;

@Service
public class TransactionService implements ProcessTransactionUseCase {


    @Override
    public void processTransaction(Transaction transaction) {

    }
}