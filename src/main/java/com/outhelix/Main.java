package com.outhelix;


import com.outhelix.dataBase.CRUDUtils;
import com.outhelix.dataBase.User;


public class Main {
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            User user = CRUDUtils.getUserData(i);
            System.out.println(user.toString());
        }
    }
}
