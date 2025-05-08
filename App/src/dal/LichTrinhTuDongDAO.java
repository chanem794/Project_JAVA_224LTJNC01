package dal;

import model.LichTrinhTuDong;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LichTrinhTuDongDAO {
    private Connection connection;

    public LichTrinhTuDongDAO() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    public boolean createLichTrinhTuDong(LichTrinhTuDong lichTrinh) throws SQLException {
        String sql = "INSERT INTO LichTrinhTuDong (MaXe, ThuTu, DiaDiem, ThoiGianDuKien) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, lichTrinh.getMaXe());
            stmt.setInt(2, lichTrinh.getThuTu());
            stmt.setString(3, lichTrinh.getDiaDiem());
            stmt.setInt(4, lichTrinh.getThoiGianDuKien());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    public List<LichTrinhTuDong> getAllLichTrinhTuDong() throws SQLException {
        List<LichTrinhTuDong> lichTrinhList = new ArrayList<>();
        String sql = "SELECT * FROM LichTrinhTuDong";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                LichTrinhTuDong lichTrinh = new LichTrinhTuDong();
                lichTrinh.setMaLichTrinhTuDong(rs.getInt("MaLichTrinhTuDong"));
                lichTrinh.setMaXe(rs.getInt("MaXe"));
                lichTrinh.setThuTu(rs.getInt("ThuTu"));
                lichTrinh.setDiaDiem(rs.getString("DiaDiem"));
                lichTrinh.setThoiGianDuKien(rs.getInt("ThoiGianDuKien"));
                lichTrinhList.add(lichTrinh);
            }
        }
        return lichTrinhList;
    }

    public LichTrinhTuDong getLichTrinhTuDongById(int maLichTrinhTuDong) throws SQLException {
        String sql = "SELECT * FROM LichTrinhTuDong WHERE MaLichTrinhTuDong = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, maLichTrinhTuDong);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    LichTrinhTuDong lichTrinh = new LichTrinhTuDong();
                    lichTrinh.setMaLichTrinhTuDong(rs.getInt("MaLichTrinhTuDong"));
                    lichTrinh.setMaXe(rs.getInt("MaXe"));
                    lichTrinh.setThuTu(rs.getInt("ThuTu"));
                    lichTrinh.setDiaDiem(rs.getString("DiaDiem"));
                    lichTrinh.setThoiGianDuKien(rs.getInt("ThoiGianDuKien"));
                    return lichTrinh;
                }
            }
        }
        return null;
    }

    public boolean updateLichTrinhTuDong(LichTrinhTuDong lichTrinh) throws SQLException {
        String sql = "UPDATE LichTrinhTuDong SET MaXe = ?, ThuTu = ?, DiaDiem = ?, ThoiGianDuKien = ? WHERE MaLichTrinhTuDong = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, lichTrinh.getMaXe());
            stmt.setInt(2, lichTrinh.getThuTu());
            stmt.setString(3, lichTrinh.getDiaDiem());
            stmt.setInt(4, lichTrinh.getThoiGianDuKien());
            stmt.setInt(5, lichTrinh.getMaLichTrinhTuDong());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    public boolean deleteLichTrinhTuDong(int maLichTrinhTuDong) throws SQLException {
        String sql = "DELETE FROM LichTrinhTuDong WHERE MaLichTrinhTuDong = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, maLichTrinhTuDong);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    public List<LichTrinhTuDong> getLichTrinhTuDongByMaXe(int maXe) throws SQLException {
        List<LichTrinhTuDong> lichTrinhList = new ArrayList<>();
        String sql = "SELECT * FROM LichTrinhTuDong WHERE MaXe = ? ORDER BY ThuTu";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, maXe);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    LichTrinhTuDong lichTrinh = new LichTrinhTuDong();
                    lichTrinh.setMaLichTrinhTuDong(rs.getInt("MaLichTrinhTuDong"));
                    lichTrinh.setMaXe(rs.getInt("MaXe"));
                    lichTrinh.setThuTu(rs.getInt("ThuTu"));
                    lichTrinh.setDiaDiem(rs.getString("DiaDiem"));
                    lichTrinh.setThoiGianDuKien(rs.getInt("ThoiGianDuKien"));
                    lichTrinhList.add(lichTrinh);
                }
            }
        }
        return lichTrinhList;
    }
}