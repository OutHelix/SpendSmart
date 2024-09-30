package com.outhelix;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionManager {
    // Adding, deleting, editing transactions

    public void addTransaction(int userId, double moneyAmount, String transactionType, Connection connection) {
        String addTransactionQuery = "INSERT INTO db.transactions (user_id, money_amount, transaction_type) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(addTransactionQuery)){
            preparedStatement.setInt(1, userId);
            preparedStatement.setDouble(2, moneyAmount);
            preparedStatement.setString(3, transactionType);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
