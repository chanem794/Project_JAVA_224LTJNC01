package Data_Access_Layer;

import java.sql.*;
import java.util.ArrayList;

public class VeCuaToiDAO {
    public static ArrayList<String> layVeTheoKhachHang(String maKH) {
        ArrayList<String> dsVe = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT ID_BOOK_XE, CHUYEN_DI_NO, THOI_GIAN_XUAT_PHAT FROM BOOKXE WHERE KHACH_HANG_NO = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, maKH);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String ve = rs.getString("ID_BOOK_XE") + " - " + rs.getString("CHUYEN_DI_NO") + " - " + rs.getTimestamp("THOI_GIAN_XUAT_PHAT");
                dsVe.add(ve);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsVe;
    }
}
