package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TuyenDAO {
    private Connection connection;

    public TuyenDAO() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    // Lấy danh sách các điểm xuất phát (DiemDi) từ bảng Tuyen
    public List<String> getAllDiemDi() throws SQLException {
        List<String> diemDiList = new ArrayList<>();
        String sql = "SELECT DISTINCT DiemDi FROM Tuyen";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String diemDi = rs.getString("DiemDi");
                if (diemDi != null && !diemDi.isEmpty()) {
                    diemDiList.add(diemDi);
                }
            }
        }
        return diemDiList;
    }

    // Lấy danh sách các điểm đến (DiemDen) từ bảng Tuyen
    public List<String> getAllDiemDen() throws SQLException {
        List<String> diemDenList = new ArrayList<>();
        String sql = "SELECT DISTINCT DiemDen FROM Tuyen";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String diemDen = rs.getString("DiemDen");
                if (diemDen != null && !diemDen.isEmpty()) {
                    diemDenList.add(diemDen);
                }
            }
        }
        return diemDenList;
    }
}