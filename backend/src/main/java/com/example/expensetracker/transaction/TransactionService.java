package com.example.expensetracker.transaction;

import com.example.expensetracker.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public List<Transaction> getTransactionsForUser(
            User user,
            LocalDateTime from,
            LocalDateTime to
    ) {
        return transactionRepository.findByUserAndOccurredAtBetweenOrderByOccurredAtDesc(user, from, to);
    }

    // Additional methods for create/update/delete can be added later
}


