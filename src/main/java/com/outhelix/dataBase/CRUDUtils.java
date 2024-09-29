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
        String transactionsCountQuery = "SELECT COUNT(*) FROM db.transactions WHERE user_id = ?";
        String transactionsQuery = "SELECT * FROM db.transactions WHERE user_id = ?";
        Transaction transaction = null;
        ArrayList<Transaction> transactions = new ArrayList<>();
        int transactionsCount = 0;


        try (PreparedStatement statement = connection.prepareStatement(transactionsCountQuery)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                transactionsCount = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (transactionsCount != 0) {
            try (PreparedStatement statement = connection.prepareStatement(transactionsQuery)) {
                statement.setInt(1, userId);

                try (ResultSet resultSet = statement.executeQuery()) {
                    for (int i = 0; i <= transactionsCount ; i++) {
                        if (resultSet.next()) {
                            int id = resultSet.getInt("id");
                            int user_id = resultSet.getInt("user_id");
                            double money_amount = resultSet.getDouble("money_amount");
                            String transactions_type = resultSet.getString("transaction_type");

                            transaction = new Transaction(id, user_id, money_amount, transactions_type);
                            transactions.add(transaction);
                        }
                    }

                }
                return transactions;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return transactions;
    }
}
