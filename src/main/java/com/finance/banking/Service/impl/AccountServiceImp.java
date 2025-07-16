package com.finance.banking.Service.impl;

import com.finance.banking.Dto.AccountDto;
import com.finance.banking.Entity.Account;
import com.finance.banking.Mapper.AccountMapper;
import com.finance.banking.Repository.AccountRepository;
import com.finance.banking.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public  class AccountServiceImp implements AccountService {
 @Autowired
 private  AccountRepository accountRepository;
    @Override
    public AccountDto createAccount(AccountDto accountDto){
        Account account = AccountMapper.mapToAccount(accountDto);
        Account saveAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(saveAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account not found with this id :"+id));
        return AccountMapper.mapToAccountDto(account);
    }
}
