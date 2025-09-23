package com.itau.account.balance.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
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

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal balanceAmount;

    @Column(nullable = false, length = 3)
    private String balanceCurrency;

    @CreationTimestamp
    private Instant createdAtEntity;

    @UpdateTimestamp
    private Instant updatedAt;

    public enum AccountStatus {
        ENABLED, DISABLED
    }
}