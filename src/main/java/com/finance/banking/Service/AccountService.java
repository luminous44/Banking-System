package com.finance.banking.Service;

import com.finance.banking.Dto.AccountDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface AccountService {

    AccountDto createAccount(AccountDto accountDto);
    AccountDto getAccountById(Long id);
    ResponseEntity<?> deleteAccountById(Long id);

    ResponseEntity<?> getAllAccount();

    ResponseEntity<?> depositAmount( Long id, Double amount);

    ResponseEntity<?> withdrawAmount(Long id, double amount);

    ResponseEntity<?> updateUser(Long id, String name);
}
