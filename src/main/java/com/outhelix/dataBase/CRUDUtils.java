package com.outhelix.dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CRUDUtils {
    public static User getUserData(int userId) {
        String query = "SELECT * FROM db.users WHERE id = ?";
        User user = null;

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)){

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
}
