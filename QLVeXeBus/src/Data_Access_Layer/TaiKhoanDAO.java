package dao;

import java.sql.*;

public class TaiKhoanDAO {
    public static boolean dangNhap(String email, String matKhau) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM KHACHHANG WHERE EMAIL = ? AND MAT_KHAU = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, matKhau);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
