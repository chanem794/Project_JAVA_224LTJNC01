package dal;

import model.NguoiDung;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class NguoiDungDAO {
    private final Connection connection;

    public NguoiDungDAO() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    public boolean existsByEmail(String email) throws SQLException {
        String sql = "SELECT 1 FROM NguoiDung WHERE Email = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    public boolean insertUser(String email) throws SQLException {
        String maNguoiDung = "ND" + UUID.randomUUID().toString().substring(0, 7);
        String sql = "INSERT INTO NguoiDung (MaNguoiDung, TenNguoiDung, Email) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, maNguoiDung);
            ps.setString(2, "");
            ps.setString(3, email);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean updateOTP(String email, String otpCode) throws SQLException {
        String sql = "UPDATE NguoiDung SET OtpCode = ?, Otp_taoThoiGian = GETDATE() WHERE Email = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, otpCode);
            ps.setString(2, email);
            return ps.executeUpdate() > 0;
        }
    }

    public NguoiDung validateOTP(String email, String otpCode) throws SQLException {
        String sql = "SELECT MaNguoiDung, TenNguoiDung, Email, NgaySinh, OtpCode, Otp_taoThoiGian " +
                    "FROM NguoiDung WHERE Email = ? AND OtpCode = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, otpCode);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new NguoiDung(
                        rs.getString("MaNguoiDung"),
                        rs.getString("TenNguoiDung"),
                        rs.getString("Email"),
                        rs.getDate("NgaySinh"),
                        rs.getString("OtpCode"),
                        rs.getTimestamp("Otp_taoThoiGian")
                    );
                }
                return null;
            }
        }
    }

    public boolean updateUserInfo(String email, String tenNguoiDung, java.util.Date ngaySinh) throws SQLException {
        String sql = "UPDATE NguoiDung SET TenNguoiDung = ?, NgaySinh = ? WHERE Email = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, tenNguoiDung);
            ps.setDate(2, ngaySinh != null ? new java.sql.Date(ngaySinh.getTime()) : null);
            ps.setString(3, email);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean clearOTP(String email) throws SQLException {
        String sql = "UPDATE NguoiDung SET OtpCode = NULL, Otp_taoThoiGian = NULL WHERE Email = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email);
            return ps.executeUpdate() > 0;
        }
    }

    public NguoiDung getUserByEmail(String email) throws SQLException {
        String sql = "SELECT MaNguoiDung, TenNguoiDung, Email, NgaySinh, OtpCode, Otp_taoThoiGian " +
                    "FROM NguoiDung WHERE Email = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new NguoiDung(
                        rs.getString("MaNguoiDung"),
                        rs.getString("TenNguoiDung"),
                        rs.getString("Email"),
                        rs.getDate("NgaySinh"),
                        rs.getString("OtpCode"),
                        rs.getTimestamp("Otp_taoThoiGian")
                    );
                }
                return null;
            }
        }
    }
}