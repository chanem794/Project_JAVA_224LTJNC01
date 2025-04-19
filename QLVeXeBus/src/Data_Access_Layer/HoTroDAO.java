package dao;

import java.sql.*;

public class HoTroDAO {
    public static String layEmailKhachHang(String maKH) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT EMAIL FROM KHACHHANG WHERE ID_KHACH_HANG = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, maKH);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("EMAIL");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Không tìm thấy email";
    }
}
