package com.itau.account.balance.adapter.in.messaging.mapper;

import com.itau.account.balance.adapter.in.messaging.dto.TransactionMessage;
import com.itau.account.balance.domain.model.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    Transaction toTransaction(TransactionMessage.TransactionData transaction);
}
