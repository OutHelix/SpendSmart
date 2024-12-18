package com.outhelix.service;

import com.outhelix.model.Transaction;
import com.outhelix.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getTransactionsByUserId(int userId) {
        return transactionRepository.findByUserId(userId);
    }

    public void saveTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }
}
