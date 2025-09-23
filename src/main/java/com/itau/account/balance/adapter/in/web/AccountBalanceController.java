package com.itau.account.balance.adapter.in.web;

import com.itau.account.balance.application.service.AccountService;
import com.itau.account.balance.domain.model.Account;
import com.itau.account.balance.domain.model.Balance;
import io.micrometer.core.annotation.Timed;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Slf4j
@Controller
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
@Tag(name = "Account Balance", description = "Account Balance Management API")
public class AccountBalanceController {

    private final AccountService accountService;

    @GetMapping("/{accountId}/balance")
    @Timed(value = "account.balance.get", description = "Time taken to get account balance")
    @Operation(summary = "Get account balance", description = "Retrieve the current balance for a specific account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved account balance"),
            @ApiResponse(responseCode = "404", description = "Account not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<AccountBalanceResponse> getAccountBalance(
            @Parameter(description = "Account ID", required = true, example = "5b19c8b6-0cc4-4c72-a989-0c2ee15fa975")
            @PathVariable String accountId) {

        log.info("Received request for account balance: {}", accountId);

        Account account = accountService.getAccountBalance(accountId);

        AccountBalanceResponse response = AccountBalanceResponse.builder()
                .id(account.getId())
                .owner(account.getOwner())
                .balance(account.getBalance())
                .updatedAt(account.getUpdatedAt())
                .build();

        log.debug("Returning account balance for: {}", accountId);
        return ResponseEntity.ok(response);
    }

    public record AccountBalanceResponse(
            UUID id,
            String owner,
            Balance balance,
            java.time.Instant updatedAt
    ) {
        public static AccountBalanceResponseBuilder builder() {
            return new AccountBalanceResponseBuilder();
        }

        public static class AccountBalanceResponseBuilder {
            private UUID id;
            private String owner;
            private Balance balance;
            private java.time.Instant updatedAt;

            public AccountBalanceResponseBuilder id(UUID id) {
                this.id = id;
                return this;
            }

            public AccountBalanceResponseBuilder owner(String owner) {
                this.owner = owner;
                return this;
            }

            public AccountBalanceResponseBuilder balance(Balance balance) {
                this.balance = balance;
                return this;
            }

            public AccountBalanceResponseBuilder updatedAt(java.time.Instant updatedAt) {
                this.updatedAt = updatedAt;
                return this;
            }

            public AccountBalanceResponse build() {
                return new AccountBalanceResponse(id, owner, balance, updatedAt);
            }
        }
    }
}