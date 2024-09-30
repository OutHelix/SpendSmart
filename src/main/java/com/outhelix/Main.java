package com.outhelix;


import com.outhelix.dataBase.CRUDUtils;
import com.outhelix.dataBase.DBUtils;
import com.outhelix.dataBase.Transaction;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Connection connection = DBUtils.getConnection();
        Scanner sc = new Scanner(System.in);
        TransactionManager tm = new TransactionManager();

        try {

            System.out.println("Введите user_id");
            int userId = sc.nextInt();
            System.out.println("Введите moneyAmount");
            double moneyAmount = sc.nextDouble();
            System.out.println("Введите transactionType (0 - deposit, 1 - withdrawal)");
            String transactionType = sc.nextLine().equals("0") ? "deposit" : "withdrawal";

            tm.addTransaction(userId, moneyAmount, transactionType, connection);







//            System.out.println("Enter a user_id:");
//            int user_id = sc.nextInt();
//
//            ArrayList<Transaction> transactions = CRUDUtils.getUserTransactionsData(user_id, connection);
//
//            System.out.println(transactions);
//
//            for (Transaction transaction : transactions) {
//                System.out.println(transaction.toString());
//            }




//            for (int i = 1; i <= 10; i++) {
//                User user = CRUDUtils.getUserData(i, connection);
//                System.out.println(user.toString());
//            }
//
//            for (int i = 1; i <= 10; i++) {
//                ArrayList<Transaction> transactions = CRUDUtils.getUserTransactionsData(i, connection);
//                for (Transaction transaction : transactions) {
//                    System.out.println(transaction.toString());
//                }
//            }


        } finally {
            DBUtils.closeConnection(connection);
        }
    }
}


