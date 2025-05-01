package dal;

import model.Tuyen;
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

    // Create: Thêm một tuyến mới vào bảng Tuyen
    public boolean createTuyen(Tuyen tuyen) throws SQLException {
        String sql = "INSERT INTO Tuyen (MaTuyen, DiemDi, DiemDen, QuangDuong) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, tuyen.getMaTuyen());
            stmt.setString(2, tuyen.getDiemDi());
            stmt.setString(3, tuyen.getDiemDen());
            stmt.setInt(4, tuyen.getQuangDuong());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    // Read: Lấy tất cả các tuyến từ bảng Tuyen
    public List<Tuyen> getAllTuyen() throws SQLException {
        List<Tuyen> tuyenList = new ArrayList<>();
        String sql = "SELECT * FROM Tuyen";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Tuyen tuyen = new Tuyen();
                tuyen.setMaTuyen(rs.getInt("MaTuyen"));
                tuyen.setDiemDi(rs.getString("DiemDi"));
                tuyen.setDiemDen(rs.getString("DiemDen"));
                tuyen.setQuangDuong(rs.getInt("QuangDuong"));
                tuyenList.add(tuyen);
            }
        }
        return tuyenList;
    }

    // Read: Lấy một tuyến theo MaTuyen
    public Tuyen getTuyenByMaTuyen(int maTuyen) throws SQLException {
        String sql = "SELECT * FROM Tuyen WHERE MaTuyen = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, maTuyen);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Tuyen tuyen = new Tuyen();
                    tuyen.setMaTuyen(rs.getInt("MaTuyen"));
                    tuyen.setDiemDi(rs.getString("DiemDi"));
                    tuyen.setDiemDen(rs.getString("DiemDen"));
                    tuyen.setQuangDuong(rs.getInt("QuangDuong"));
                    return tuyen;
                }
            }
        }
        return null; // Trả về null nếu không tìm thấy
    }

    // Update: Cập nhật thông tin một tuyến dựa trên MaTuyen
    public boolean updateTuyen(Tuyen tuyen) throws SQLException {
        String sql = "UPDATE Tuyen SET DiemDi = ?, DiemDen = ?, QuangDuong = ? WHERE MaTuyen = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, tuyen.getDiemDi());
            stmt.setString(2, tuyen.getDiemDen());
            stmt.setInt(3, tuyen.getQuangDuong());
            stmt.setInt(4, tuyen.getMaTuyen());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    // Delete: Xóa một tuyến dựa trên MaTuyen
    public boolean deleteTuyen(int maTuyen) throws SQLException {
        String sql = "DELETE FROM Tuyen WHERE MaTuyen = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, maTuyen);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
}