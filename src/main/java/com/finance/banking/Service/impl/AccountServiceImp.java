package com.finance.banking.Service.impl;

import com.finance.banking.Dto.AccountDto;
import com.finance.banking.Entity.Account;
import com.finance.banking.Mapper.AccountMapper;
import com.finance.banking.Repository.AccountRepository;
import com.finance.banking.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public ResponseEntity<?> deleteAccountById(Long id) {
        Optional<Account> account = accountRepository.findById(id);
        if (account.isPresent()) {
            accountRepository.deleteById(id);
            return ResponseEntity.ok("Account deleted successfully.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found with id: " + id);

    }

    @Override
    public ResponseEntity<?> getAllAccount() {
        List<Account> allAcc =  accountRepository.findAll();
        if(!allAcc.isEmpty()){
            return new ResponseEntity<>(allAcc,HttpStatus.OK);
        }
        return  ResponseEntity.status(HttpStatus.NO_CONTENT).body("No Entry Available");
    }

    @Override
    public ResponseEntity<?> depositAmount(Long id, Double amount) {
        Optional<Account> optionalAccount = accountRepository.findById(id);

        if (optionalAccount.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Account not found with id: " + id);
        }

        Account account = optionalAccount.get();
        double updatedBalance = account.getBalance() + amount;
        account.setBalance(updatedBalance);
        accountRepository.save(account);

        return ResponseEntity.ok("Amount deposited successfully.\nNew balance: " + updatedBalance);
    }

    @Override
    public ResponseEntity<?> withdrawAmount(Long id, double amount) {
        Optional<Account> optionalAccount = accountRepository.findById(id);

        if (optionalAccount.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Account not found with id: " + id);
        }

        Account account = optionalAccount.get();
        if(account.getBalance() < amount){
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                    .body("Insufficient withdrawal amount !! Try again");
        }
        double updatedBalance = account.getBalance() - amount;
        account.setBalance(updatedBalance);
        accountRepository.save(account);
        return ResponseEntity.ok("Amount withdrawal successfully.\nNew balance: " + updatedBalance);
    }

    @Override
    public ResponseEntity<?> updateUser(Long id, String name) {
        Optional<Account> optionalAccount = accountRepository.findById(id);

        if (optionalAccount.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Account not found with id: " + id);
        }
        Account acc = optionalAccount.get();
        acc.setAccountHolderName(name);
        accountRepository.save(acc);
        return ResponseEntity.ok("Updated name successfully");
    }


}
