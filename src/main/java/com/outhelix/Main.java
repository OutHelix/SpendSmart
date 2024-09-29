package com.outhelix;


import com.outhelix.dataBase.CRUDUtils;
import com.outhelix.dataBase.DBUtils;
import com.outhelix.dataBase.Transaction;
import com.outhelix.dataBase.User;

import java.sql.Connection;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Connection connection = DBUtils.getConnection();

        try {
            for (int i = 1; i <= 10; i++) {
                User user = CRUDUtils.getUserData(i, connection);
                System.out.println(user.toString());
            }

            for (int i = 1; i <= 10; i++) {
                ArrayList<Transaction> transactions = CRUDUtils.getUserTransactionsData(i, connection);
                for (Transaction transaction : transactions) {
                    System.out.println(transaction.toString());
                }
            }


        } finally {
            DBUtils.closeConnection(connection);
        }
    }
}


