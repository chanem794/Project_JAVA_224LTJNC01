package dal;

import model.Xe;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class XeDAO {
    private Connection connection;  

    public XeDAO() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    public boolean createXe(Xe xe) throws SQLException {
        String sql = "INSERT INTO Xe (MaXe, TenXe, LoaiXe, DiemDi, DiemDen, NgayKhoiHanh, GioDen, GioDi, SoGhe, GheConTrong, GiaVe, MaTuyen) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, xe.getMaXe());
            stmt.setString(2, xe.getTenXe());
            stmt.setString(3, xe.getLoaiXe());
            stmt.setString(4, xe.getDiemDi());
            stmt.setString(5, xe.getDiemDen());
            if (xe.getNgayKhoiHanh() != null) {
                stmt.setDate(6, new java.sql.Date(xe.getNgayKhoiHanh().getTime()));
            } else {
                stmt.setNull(6, java.sql.Types.DATE);
            }
            stmt.setTime(7, xe.getGioDen());
            stmt.setTime(8, xe.getGioDi());
            stmt.setInt(9, xe.getSoGhe());
            stmt.setInt(10, xe.getGheConTrong());
            stmt.setInt(11, xe.getGiaVe());
            stmt.setInt(12, xe.getMaTuyen());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    public List<Xe> getAllXe() throws SQLException {
        List<Xe> xeList = new ArrayList<>();
        String sql = "SELECT * FROM Xe";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Xe xe = new Xe();
                xe.setMaXe(rs.getInt("MaXe"));
                xe.setTenXe(rs.getString("TenXe"));
                xe.setLoaiXe(rs.getString("LoaiXe"));
                xe.setDiemDi(rs.getString("DiemDi"));
                xe.setDiemDen(rs.getString("DiemDen"));
                xe.setNgayKhoiHanh(rs.getDate("NgayKhoiHanh"));
                xe.setGioDen(rs.getTime("GioDen"));
                xe.setGioDi(rs.getTime("GioDi"));
                xe.setSoGhe(rs.getInt("SoGhe"));
                xe.setGheConTrong(rs.getInt("GheConTrong"));
                xe.setGiaVe(rs.getInt("GiaVe"));
                xe.setMaTuyen(rs.getInt("MaTuyen"));
                xeList.add(xe);
            }
        }
        return xeList;
    }

    public Xe getXeByMaXe(int maXe) throws SQLException {
        String sql = "SELECT * FROM Xe WHERE MaXe = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, maXe);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Xe xe = new Xe();
                    xe.setMaXe(rs.getInt("MaXe"));
                    xe.setTenXe(rs.getString("TenXe"));
                    xe.setLoaiXe(rs.getString("LoaiXe"));
                    xe.setDiemDi(rs.getString("DiemDi"));
                    xe.setDiemDen(rs.getString("DiemDen"));
                    xe.setNgayKhoiHanh(rs.getDate("NgayKhoiHanh"));
                    xe.setGioDen(rs.getTime("GioDen"));
                    xe.setGioDi(rs.getTime("GioDi"));
                    xe.setSoGhe(rs.getInt("SoGhe"));
                    xe.setGheConTrong(rs.getInt("GheConTrong"));
                    xe.setGiaVe(rs.getInt("GiaVe"));
                    xe.setMaTuyen(rs.getInt("MaTuyen"));
                    return xe;
                }
            }
        }
        return null;
    }

    public boolean updateXe(Xe xe) throws SQLException {
        String sql = "UPDATE Xe SET TenXe = ?, LoaiXe = ?, DiemDi = ?, DiemDen = ?, NgayKhoiHanh = ?, GioDen = ?, GioDi = ?, SoGhe = ?, GheConTrong = ?, GiaVe = ?, MaTuyen = ? WHERE MaXe = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, xe.getTenXe());
            stmt.setString(2, xe.getLoaiXe());
            stmt.setString(3, xe.getDiemDi());
            stmt.setString(4, xe.getDiemDen());
            if (xe.getNgayKhoiHanh() != null) {
                stmt.setDate(5, new java.sql.Date(xe.getNgayKhoiHanh().getTime()));
            } else {
                stmt.setNull(5, java.sql.Types.DATE);
            }
            stmt.setTime(6, xe.getGioDen());
            stmt.setTime(7, xe.getGioDi());
            stmt.setInt(8, xe.getSoGhe());
            stmt.setInt(9, xe.getGheConTrong());
            stmt.setInt(10, xe.getGiaVe());
            stmt.setInt(11, xe.getMaTuyen());
            stmt.setInt(12, xe.getMaXe());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    public boolean deleteXe(int maXe) throws SQLException {
        String sql = "DELETE FROM Xe WHERE MaXe = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, maXe);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    public List<Xe> getXeByMaTuyen(int maTuyen) throws SQLException {
        List<Xe> xeList = new ArrayList<>();
        String sql = "SELECT * FROM Xe WHERE MaTuyen = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, maTuyen);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Xe xe = new Xe();
                    xe.setMaXe(rs.getInt("MaXe"));
                    xe.setTenXe(rs.getString("TenXe"));
                    xe.setLoaiXe(rs.getString("LoaiXe"));
                    xe.setDiemDi(rs.getString("DiemDi"));
                    xe.setDiemDen(rs.getString("DiemDen"));
                    xe.setNgayKhoiHanh(rs.getDate("NgayKhoiHanh"));
                    xe.setGioDen(rs.getTime("GioDen"));
                    xe.setGioDi(rs.getTime("GioDi"));
                    xe.setSoGhe(rs.getInt("SoGhe"));
                    xe.setGheConTrong(rs.getInt("GheConTrong"));
                    xe.setGiaVe(rs.getInt("GiaVe"));
                    xe.setMaTuyen(rs.getInt("MaTuyen"));
                    xeList.add(xe);
                }
            }
        }
        return xeList;
    }
}