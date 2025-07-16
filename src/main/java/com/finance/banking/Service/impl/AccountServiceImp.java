package com.finance.banking.Service.impl;

import com.finance.banking.Dto.AccountDto;
import com.finance.banking.Entity.Account;
import com.finance.banking.Mapper.AccountMapper;
import com.finance.banking.Repository.AccountRepository;
import com.finance.banking.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AccountServiceImp implements AccountService {
 @Autowired
 private  AccountRepository accountRepository;
    @Override
    public AccountDto createAccount(AccountDto accountDto){
        Account account = AccountMapper.mapToAccount(accountDto);
        Account saveAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(saveAccount);
    }
}
