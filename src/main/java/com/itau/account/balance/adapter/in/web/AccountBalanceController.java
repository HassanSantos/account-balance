package com.itau.account.balance.adapter.in.web;

import com.itau.account.balance.adapter.in.web.model.AccountBalanceResponse;
import com.itau.account.balance.application.port.in.GetAccountBalanceUseCase;
import com.itau.account.balance.domain.model.Account;
import io.micrometer.core.annotation.Timed;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//TODO: ADICIONAR SWAGGER CODE GENERATE
@Slf4j
@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
@Tag(name = "Account Balance", description = "Account Balance Management API")
public class AccountBalanceController {

    private final GetAccountBalanceUseCase getAccountBalanceUseCase;

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

        Account account = getAccountBalanceUseCase.getAccountBalance(accountId);

        AccountBalanceResponse response = new AccountBalanceResponse(
                account.getId(),
                account.getOwner(),
                account.getBalance(),
                account.getUpdatedAt()
        );

        log.debug("Returning account balance for: {}", accountId);
        return ResponseEntity.ok(response);
    }
}