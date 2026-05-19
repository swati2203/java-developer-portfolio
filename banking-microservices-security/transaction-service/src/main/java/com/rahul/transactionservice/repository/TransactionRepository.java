package com.rahul.transactionservice.repository;
import com.rahul.transactionservice.model.Transaction;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Page<Transaction> findByFromAccountOrToAccount(String from, String to, Pageable pageable);
    List<Transaction> findByFromAccount(String fromAccount);
}
