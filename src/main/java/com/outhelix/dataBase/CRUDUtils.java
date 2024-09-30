package com.outhelix.dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CRUDUtils {
    public static User getUserData(int userId, Connection connection) {
        String query = "SELECT * FROM db.users WHERE id = ?";
        User user = null;

        try (PreparedStatement statement = connection.prepareStatement(query)){

            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    double account = resultSet.getDouble("account");

                    user = new User(id, name, account);
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException("Error to load userdata",e);
        }
        return user;
    }

    public static ArrayList<Transaction> getUserTransactionsData(int userId, Connection connection) {
        String transactionsQuery = "SELECT * FROM db.transactions WHERE user_id = ?";
        ArrayList<Transaction> transactions = new ArrayList<>();
        Transaction transaction;

        try (PreparedStatement statement = connection.prepareStatement(transactionsQuery)) {
            statement.setInt(1, userId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int transactionId = resultSet.getInt("id");
                    int dbUserId = resultSet.getInt("user_id");
                    double moneyAmount = resultSet.getDouble("money_amount");
                    String transactionType = resultSet.getString("transaction_type");

                    transaction = new Transaction(transactionId, dbUserId, moneyAmount, transactionType);
                    transactions.add(transaction);
                }
            }
            return transactions;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
