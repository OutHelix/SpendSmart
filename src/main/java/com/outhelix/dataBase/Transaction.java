package com.outhelix.dataBase;

public class Transaction {
    private int id;
    private int userId;
    private double moneyAmount;
    private String transactionType;

    public Transaction(int id, int userId, double moneyAmount, String transactionType) {
        this.id = id;
        this.userId = userId;
        this.moneyAmount = moneyAmount;
        this.transactionType = transactionType;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public double getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(double moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", userId=" + userId +
                ", moneyAmount=" + moneyAmount +
                ", transactionType='" + transactionType + '\'' +
                '}';
    }
}
