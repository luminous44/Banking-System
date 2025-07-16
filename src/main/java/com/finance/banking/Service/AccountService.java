package com.finance.banking.Service;

import com.finance.banking.Dto.AccountDto;
import org.springframework.stereotype.Component;

@Component
public interface AccountService {

    AccountDto createAccount(AccountDto accountDto);
    AccountDto getAccountById(Long id);

}
