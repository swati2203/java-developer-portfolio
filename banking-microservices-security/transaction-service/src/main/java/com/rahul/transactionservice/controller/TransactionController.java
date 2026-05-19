package com.rahul.transactionservice.controller;

import com.rahul.transactionservice.model.Transaction;
import com.rahul.transactionservice.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;

@RestController @RequestMapping("/api/transactions") @RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping("/transfer")
    public ResponseEntity<Transaction> transfer(
            @RequestParam String fromAccount,
            @RequestParam String toAccount,
            @RequestParam BigDecimal amount,
            @RequestParam(required=false) String description) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(transactionService.transfer(fromAccount, toAccount, amount, description));
    }

    @GetMapping("/account/{accountNumber}")
    public ResponseEntity<Page<Transaction>> getForAccount(
            @PathVariable String accountNumber, Pageable pageable) {
        return ResponseEntity.ok(transactionService.getTransactionsForAccount(accountNumber, pageable));
    }
}
