package com.itau.account.balance.adapter.out.persistence.entity;

import com.itau.account.balance.domain.model.Balance;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts", indexes = {
        @Index(name = "idx_account_owner", columnList = "owner"),
        @Index(name = "idx_account_updated", columnList = "updatedAt")
})
@AttributeOverrides({
        @AttributeOverride(name = "balance.amount", column = @Column(name = "amount")),
        @AttributeOverride(name = "balance.currency", column = @Column(name = "currency"))
})
public class AccountEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String owner;

    @Column(nullable = false)
    private Instant createdAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountStatus status;

    private Instant updatedAt;

    @Embedded
    private Balance balance;

    @OneToMany(mappedBy = "account")
    private List<TransactionEntity> transactionEntity;

    public enum AccountStatus {
        ENABLED, DISABLED
    }
}