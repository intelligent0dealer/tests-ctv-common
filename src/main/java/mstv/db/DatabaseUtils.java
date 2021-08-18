package mstv.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static mstv.db.Database.USER;
import static mstv.db.Database.getConnectionTo;

public class DatabaseUtils {

    public static void confirmEmail(String email) {
        String sql = "UPDATE users SET email_confirmed = true, email_confirmation_token = null WHERE email = ?";
        try (Connection connection = getConnectionTo(USER);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}