package com.itau.account.balance.adapter.out.persistence.mapper;

import com.itau.account.balance.adapter.out.persistence.entity.AccountEntity;
import com.itau.account.balance.domain.model.Account;
import com.itau.account.balance.domain.model.Balance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(target = "balanceAmount", source = "balance.amount")
    @Mapping(target = "balanceCurrency", source = "balance.currency")
    AccountEntity toEntity(Account account);

    @Mapping(target = "balance", source = "entity", qualifiedByName = "mapBalance")
    Account toDomain(AccountEntity entity);

//    List<AccountEntity> toEntities(List<Account> accounts);

    List<Account> toDomains(List<AccountEntity> entities);

    @Named("mapBalance")
    default Balance mapBalance(AccountEntity entity) {
        if (entity == null) {
            return null;
        }
        return Balance.builder()
                .amount(entity.getBalanceAmount())
                .currency(entity.getBalanceCurrency())
                .build();
    }
}