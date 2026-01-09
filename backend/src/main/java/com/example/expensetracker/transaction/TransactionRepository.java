package com.example.expensetracker.transaction;

import com.example.expensetracker.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByUserAndOccurredAtBetweenOrderByOccurredAtDesc(
            User user,
            LocalDateTime from,
            LocalDateTime to
    );
}


