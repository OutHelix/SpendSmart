package com.outhelix.controller;

import com.outhelix.model.Transaction;
import com.outhelix.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/user/{userId}")
    public List<Transaction> getTransactions(@PathVariable int userId) {
        return transactionService.getTransactionsByUserId(userId);
    }

//    @PostMapping("/create")
//    public void createTransaction(@RequestBody Transaction transaction) {
//        transactionService.saveTransaction(transaction);
//    }
}
