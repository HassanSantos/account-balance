package com.itau.account.balance.application.port.in;

import com.itau.account.balance.domain.model.Transaction;

public interface ProcessTransactionUseCase {
    void processTransaction(Transaction transaction);
}
