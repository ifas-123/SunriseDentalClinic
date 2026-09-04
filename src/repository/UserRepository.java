package repository;

import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {

    public User login(
            String username,
            String password) {

        String sql = """
                SELECT user_id, username, role
                FROM users
                WHERE username = ?
                AND password = ?
                """;

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(
                    1,
                    username
            );

            statement.setString(
                    2,
                    password
            );

            ResultSet resultSet =
                    statement.executeQuery();

            if (resultSet.next()) {

                return new User(
                        resultSet.getInt(
                                "user_id"
                        ),
                        resultSet.getString(
                                "username"
                        ),
                        resultSet.getString(
                                "role"
                        )
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "Login error: "
                            + e.getMessage()
            );
        }

        return null;
    }
}