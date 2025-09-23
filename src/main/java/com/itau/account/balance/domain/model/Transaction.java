package com.itau.account.balance.domain.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Transaction {

    private String id;
    private String type;
    private BigDecimal amount;
    private String currency;
    private String status;
    private LocalDateTime timestamp;
    private UUID accountId;
}
