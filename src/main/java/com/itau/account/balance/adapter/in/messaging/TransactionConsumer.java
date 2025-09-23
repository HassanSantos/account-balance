package com.itau.account.balance.adapter.in.messaging;

import com.itau.account.balance.adapter.in.messaging.dto.TransactionMessage;
import com.itau.account.balance.adapter.in.messaging.mapper.AccountMessageMapper;
import com.itau.account.balance.application.port.in.ProcessTransactionUseCase;
import com.itau.account.balance.domain.model.Account;
import com.itau.account.balance.domain.model.Transaction;
import io.awspring.cloud.sqs.annotation.SqsListener;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

@Component
@RequiredArgsConstructor
public class TransactionConsumer {

    private static final Logger logger = LoggerFactory.getLogger(TransactionConsumer.class);
    private final ProcessTransactionUseCase processTransactionUseCase;
    private final SqsTemplate sqsTemplate;
    private final AccountMessageMapper accountMessageMapper;

    @SqsListener(value = "${aws.sqs.queue.transactions}")
    public void receiveTransaction(Message<TransactionMessage> message) {
        try {
            TransactionMessage payload = message.getPayload();
            logger.info("Received transaction: {}", payload.transaction().id());

            Transaction transaction = convertToDomain(payload);
            Account account = convertAccount(payload);
            processTransactionUseCase.processTransaction(transaction, account);

            logger.info("Successfully processed transaction: {}", payload.transaction().id());
        } catch (Exception e) {
            logger.error("Error processing transaction: {}", e.getMessage(), e);
            // Você pode implementar DLQ (Dead Letter Queue) aqui se necessário
            throw e; // Relança a exceção para que o SQS possa reprocessar a mensagem
        }
    }

    private Transaction convertToDomain(TransactionMessage message) {
        LocalDateTime timestamp = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(message.transaction().timestamp()),
                TimeZone.getDefault().toZoneId()
        );

        return new Transaction(
                message.transaction().id(),
                message.transaction().type(),
                message.transaction().amount(),
                message.transaction().currency(),
                message.transaction().status(),
                timestamp,
                message.account().id()
        );

    }

    private Account convertAccount(TransactionMessage message) {

        return accountMessageMapper.toDomain(message.account());
    }
}