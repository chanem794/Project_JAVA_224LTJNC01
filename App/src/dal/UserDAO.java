package dal;

import model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class UserDAO {
    private final Connection connection;

    public UserDAO() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    public boolean existsByEmail(String email) throws SQLException {
        String sql = "SELECT 1 FROM users WHERE email = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    public boolean insertUser(String email) throws SQLException {
        String sql = "INSERT INTO users (email) VALUES (?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean updateOTP(String email, String otpCode) throws SQLException {
        String sql = "UPDATE users SET otp_code = ?, otp_created_at = GETDATE() WHERE email = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, otpCode);
            ps.setString(2, email);
            return ps.executeUpdate() > 0;
        }
    }

    public User validateOTP(String email, String otpCode) throws SQLException {
        String sql = "SELECT email, full_name, birth_date, otp_code, otp_created_at FROM users WHERE email = ? AND otp_code = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, otpCode);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new User(
                        rs.getString("email"),
                        rs.getString("full_name"),
                        rs.getDate("birth_date"),
                        rs.getString("otp_code"),
                        rs.getTimestamp("otp_created_at")
                    );
                }
                return null;
            }
        }
    }

    public boolean updateUserInfo(String email, String fullName, Date birthDate) throws SQLException {
        String sql = "UPDATE users SET full_name = ?, birth_date = ? WHERE email = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, fullName);
            ps.setDate(2, birthDate != null ? new java.sql.Date(birthDate.getTime()) : null);
            ps.setString(3, email);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean clearOTP(String email) throws SQLException {
        String sql = "UPDATE users SET otp_code = NULL, otp_created_at = NULL WHERE email = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email);
            return ps.executeUpdate() > 0;
        }
    }

    public User getUserByEmail(String email) throws SQLException {
        String sql = "SELECT email, full_name, birth_date, otp_code, otp_created_at FROM users WHERE email = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new User(
                        rs.getString("email"),
                        rs.getString("full_name"),
                        rs.getDate("birth_date"),
                        rs.getString("otp_code"),
                        rs.getTimestamp("otp_created_at")
                    );
                }
                return null;
            }
        }
    }
}