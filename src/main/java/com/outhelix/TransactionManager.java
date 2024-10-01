package com.outhelix;


import com.outhelix.dataBase.CRUDUtils;
import com.outhelix.dataBase.Transaction;
import com.outhelix.dataBase.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class TransactionManager {    // Adding, deleting, editing transactions
    private User user;
    private final Connection connection;

    public TransactionManager(User user, Connection connection) {
        this.user = user;
        this.connection = connection;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void addTransaction(double moneyAmount, String transactionType) {
        String addTransactionQuery = "INSERT INTO db.transactions (user_id, money_amount, transaction_type) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(addTransactionQuery)){
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setDouble(2, moneyAmount);
            preparedStatement.setString(3, transactionType);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteTransaction(int transactionId) {
        String deleteTransactionQuery = "DELETE FROM db.transactions WHERE id = ? AND user_id = ?;";

        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteTransactionQuery)){
            preparedStatement.setInt(1, transactionId);
            preparedStatement.setInt(2, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void editTransaction(int transactionId, double moneyAmount, String transactionType) {
        String editTransactionQuery = "UPDATE db.transactions SET money_amount = ?, transaction_type = ? WHERE id = ? AND user_id = ?;";

        try (PreparedStatement preparedStatement = connection.prepareStatement(editTransactionQuery)){
            preparedStatement.setDouble(1, moneyAmount);
            preparedStatement.setString(2, transactionType);
            preparedStatement.setInt(3, transactionId);
            preparedStatement.setInt(4, user.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
