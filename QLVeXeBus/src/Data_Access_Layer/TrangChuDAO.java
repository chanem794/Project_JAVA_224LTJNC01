package dao;

import java.sql.*;
import java.util.ArrayList;

public class TrangChuDAO {
    public static ArrayList<String> layDanhSachChuyenDiNoiBat() {
        ArrayList<String> ds = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT TOP 5 TEN_CHUYEN_DI FROM CHUYENDI";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ds.add(rs.getString("TEN_CHUYEN_DI"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }
}

