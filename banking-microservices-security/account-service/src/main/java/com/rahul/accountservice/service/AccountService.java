package com.rahul.accountservice.service;
import com.rahul.accountservice.model.*;
import com.rahul.accountservice.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.*;

@Service @RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public Account createAccount(String username, AccountType type) {
        Account account = Account.builder()
                .accountNumber(UUID.randomUUID().toString().replace("-","").substring(0,16).toUpperCase())
                .ownerUsername(username).accountType(type)
                .balance(BigDecimal.ZERO).status(AccountStatus.ACTIVE).build();
        return accountRepository.save(account);
    }

    public List<Account> getByUsername(String username) { return accountRepository.findByOwnerUsername(username); }

    @Transactional
    public void credit(String accountNumber, BigDecimal amount) {
        Account acc = findOrThrow(accountNumber);
        acc.setBalance(acc.getBalance().add(amount));
        accountRepository.save(acc);
    }

    @Transactional
    public void debit(String accountNumber, BigDecimal amount) {
        Account acc = findOrThrow(accountNumber);
        if (acc.getBalance().compareTo(amount) < 0) throw new IllegalStateException("Insufficient balance");
        acc.setBalance(acc.getBalance().subtract(amount));
        accountRepository.save(acc);
    }

    private Account findOrThrow(String num) {
        return accountRepository.findByAccountNumber(num).orElseThrow(() -> new RuntimeException("Account not found: " + num));
    }
}
