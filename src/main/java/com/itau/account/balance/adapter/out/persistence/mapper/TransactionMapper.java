package com.itau.account.balance.adapter.out.persistence.mapper;

import com.itau.account.balance.adapter.out.persistence.entity.TransactionEntity;
import com.itau.account.balance.domain.model.Transaction;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TransactionMapper {

    public TransactionEntity toEntity(Transaction transaction) {
        if (transaction == null) {
            return null;
        }

        TransactionEntity entity = new TransactionEntity();
        entity.setId(convertToUUID(transaction.getId()));
        entity.setType(transaction.getType());
        entity.setAmount(transaction.getAmount());
        entity.setCurrency(transaction.getCurrency());
        entity.setStatus(transaction.getStatus());
        entity.setTimestamp(transaction.getTimestamp());
        entity.setAccountId(convertToUUID(transaction.getAccountId()));

        return entity;
    }

    public Transaction toDomain(TransactionEntity entity) {
        if (entity == null) {
            return null;
        }

        return Transaction.builder()
                .id(entity.getId().toString())
                .type(entity.getType())
                .amount(entity.getAmount())
                .currency(entity.getCurrency())
                .status(entity.getStatus())
                .timestamp(entity.getTimestamp())
                .accountId(entity.getAccountId().toString())
                .build();
    }

    private UUID convertToUUID(String id) {
        if (id == null) {
            return null;
        }
        try {
            return UUID.fromString(id);
        } catch (IllegalArgumentException e) {
//            TODO: MELHORAR ISSO AQUI
           return UUID.randomUUID();
        }
    }
}