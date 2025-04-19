package Data_Access_Layer;

import java.sql.*;
import java.util.ArrayList;

public class TrangChuDAO {
    public ArrayList<String> layDanhSachChuyenDiNoiBat() {
        ArrayList<String> dsChuyenDi = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(
                 "SELECT TOP 5 cd.TEN_CHUYEN_DI, cb.THOI_GIAN_XUAT_PHAT, cb.TIEN_XE, nx.TEN_NHA_XE " +
                 "FROM CHUYENDI cd " +
                 "JOIN CHUYENDI_BUS cb ON cd.ID_CHUYEN_DI = cb.CHUYEN_DI_NO " +
                 "JOIN NHAXEBUS nx ON cb.NHA_XE_BUS_NO = nx.ID_NHA_XE_BUS " +
                 "ORDER BY cb.THOI_GIAN_XUAT_PHAT")) {
            while (rs.next()) {
                String chuyen = String.format("%s - Xuất phát: %s - Giá: %,.0f VNĐ - Nhà xe: %s",
                    rs.getString("TEN_CHUYEN_DI"),
                    rs.getTimestamp("THOI_GIAN_XUAT_PHAT").toString(),
                    rs.getDouble("TIEN_XE"),
                    rs.getString("TEN_NHA_XE"));
                dsChuyenDi.add(chuyen);
            }
        } catch (SQLException e) {
            System.err.println("TrangChuDAO Error: " + e.getMessage());
            e.printStackTrace();
        }
        return dsChuyenDi;
    }

    public ArrayList<String> timKiemChuyenDi(String tu, String den, String ngay) {
        ArrayList<String> dsChuyenDi = new ArrayList<>();
        String sql = "SELECT cd.TEN_CHUYEN_DI, cb.THOI_GIAN_XUAT_PHAT, cb.THOI_GIAN_DEN, cb.TIEN_XE, nx.TEN_NHA_XE " +
                     "FROM CHUYENDI cd " +
                     "JOIN CHUYENDI_BUS cb ON cd.ID_CHUYEN_DI = cb.CHUYEN_DI_NO " +
                     "JOIN NHAXEBUS nx ON cb.NHA_XE_BUS_NO = nx.ID_NHA_XE_BUS " +
                     "JOIN TRAM t ON cd.TRAM_NO = t.ID_TRAM " +
                     "JOIN PHUONGXA px ON t.PHUONG_XA_NO = px.ID_PHUONG_XA " +
                     "JOIN QUANHUYEN qh ON px.QUAN_HUYEN_NO = qh.ID_QUAN_HUYEN " +
                     "JOIN TINH ti ON qh.TINH_NO = ti.ID_TINH " +
                     "WHERE ti.TEN_TINH = ? AND cd.TEN_CHUYEN_DI LIKE ? AND CAST(cb.THOI_GIAN_XUAT_PHAT AS DATE) = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, tu);
            pstmt.setString(2, "%" + den + "%");
            pstmt.setString(3, ngay);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String chuyen = String.format("%s - Xuất phát: %s - Đến: %s - Giá: %,.0f VNĐ - Nhà xe: %s",
                        rs.getString("TEN_CHUYEN_DI"),
                        rs.getTimestamp("THOI_GIAN_XUAT_PHAT").toString(),
                        rs.getTimestamp("THOI_GIAN_DEN").toString(),
                        rs.getDouble("TIEN_XE"),
                        rs.getString("TEN_NHA_XE"));
                    dsChuyenDi.add(chuyen);
                }
            }
        } catch (SQLException e) {
            System.err.println("TrangChuDAO timKiemChuyenDi Error: " + e.getMessage());
            e.printStackTrace();
        }
        return dsChuyenDi;
    }
}