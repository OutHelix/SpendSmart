package com.outhelix.controller;

import com.outhelix.model.Transaction;
import com.outhelix.model.User;
import com.outhelix.service.TransactionService;
import com.outhelix.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class UserTransactionController {

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/transactions/{id}")
    public String viewTransactions(@PathVariable("id") int userId, Model model) {
        User user = userService.getUserById(userId).orElse(null);
        List<Transaction> transactions = transactionService.getTransactionsByUserId(userId);
        model.addAttribute("user", user);
        model.addAttribute("transactions", transactions);
        return "transactions";
    }
}
