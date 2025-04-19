package Data_Access_Layer;

import java.sql.*;
import java.util.ArrayList;

public class TinhDAO {
    public ArrayList<String> layDanhSachTinh() {
        ArrayList<String> dsTinh = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT TEN_TINH FROM TINH")) {
            while (rs.next()) {
                String tinh = rs.getString("TEN_TINH");
                dsTinh.add(tinh);
            }
            if (dsTinh.isEmpty()) {
                System.out.println("Không tìm thấy tỉnh nào trong bảng TINH.");
            }
        } catch (SQLException e) {
            System.err.println("TinhDAO SQL Error: " + e.getMessage());
            e.printStackTrace();
        }
        return dsTinh;
    }
}