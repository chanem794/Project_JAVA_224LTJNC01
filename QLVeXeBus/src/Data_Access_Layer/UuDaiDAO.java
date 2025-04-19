package Data_Access_Layer;

import java.sql.*;
import java.util.ArrayList;

public class UuDaiDAO {
    public static ArrayList<String> layDanhSachUuDai() {
        ArrayList<String> ds = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT CD.ID_CHUYEN_DI, CD.TEN_CHUYEN_DI, CB.TIEN_XE " +
                         "FROM CHUYENDI CD " +
                         "JOIN CHUYENDI_BUS CB ON CD.ID_CHUYEN_DI = CB.CHUYEN_DI_NO " +
                         "WHERE CB.TIEN_XE < 200000";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ds.add(rs.getString("TEN_CHUYEN_DI") + " - " + rs.getString("TIEN_XE") + " VND");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }
}
