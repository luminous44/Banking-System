package com.finance.banking.Controller;

import com.finance.banking.Dto.AccountDto;
import com.finance.banking.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/api/bank")
public class AccountController {
    @Autowired
    private  AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto){
        AccountDto createdAccount = accountService.createAccount(accountDto);
        return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
    }
    @PutMapping("/{id}/deposit")
    public ResponseEntity<?> depositAmount(@PathVariable Long id, @RequestBody Map<String, Double> request){
        double amount = request.get("amount");
        return accountService.depositAmount(id, amount);
    }
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<?> withdrawAmount(@PathVariable Long id, @RequestBody Map<String, Double> request){
        double amount = request.get("amount");
        return accountService.withdrawAmount(id, amount);
    }
    @GetMapping
    public ResponseEntity<?>  getAll(){
        return  accountService.getAllAccount();

    }
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
        AccountDto account = accountService.getAccountById(id);
        return new ResponseEntity<>(account,HttpStatus.OK);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAccountById(@PathVariable Long id) {
        return accountService.deleteAccountById(id);
    }

}
