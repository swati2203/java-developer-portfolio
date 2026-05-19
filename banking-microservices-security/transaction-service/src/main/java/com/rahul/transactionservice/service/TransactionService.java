package com.rahul.transactionservice.service;

import com.rahul.transactionservice.model.*;
import com.rahul.transactionservice.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;

@Service @RequiredArgsConstructor @Slf4j
public class TransactionService {
    private final TransactionRepository transactionRepository;

    @Transactional
    public Transaction transfer(String fromAccount, String toAccount, BigDecimal amount, String desc) {
        Transaction txn = Transaction.builder()
                .fromAccount(fromAccount).toAccount(toAccount)
                .amount(amount).type(TransactionType.TRANSFER)
                .status(TransactionStatus.COMPLETED).description(desc).build();
        Transaction saved = transactionRepository.save(txn);
        log.info("Transfer {} -> {}: {}", fromAccount, toAccount, amount);
        return saved;
    }

    public Page<Transaction> getTransactionsForAccount(String accountNumber, Pageable pageable) {
        return transactionRepository.findByFromAccountOrToAccount(accountNumber, accountNumber, pageable);
    }
}
