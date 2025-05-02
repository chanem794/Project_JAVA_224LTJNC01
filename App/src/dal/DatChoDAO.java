package dal;

import model.DatCho;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class DatChoDAO {
    private Connection connection;

    public DatChoDAO() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    public boolean createDatCho(DatCho datCho) throws SQLException {
        String sql = "INSERT INTO DatCho (MaDatCho, TrangThai, NgayDat, GioDat, DiemDi, DiemDen, NgayGioKhoiHanh, SoGheDat, GiaVe, MaNguoiDung, MaXe) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, datCho.getMaDatCho());
            stmt.setString(2, datCho.getTrangThai());
            if (datCho.getNgayDat() != null) {
                stmt.setDate(3, new java.sql.Date(datCho.getNgayDat().getTime()));
            } else {
                stmt.setNull(3, java.sql.Types.DATE);
            }
            stmt.setTime(4, datCho.getGioDat());
            stmt.setString(5, datCho.getDiemDi());
            stmt.setString(6, datCho.getDiemDen());
            stmt.setTimestamp(7, new java.sql.Timestamp(datCho.getNgayGioKhoiHanh().getTime()));
            stmt.setInt(8, datCho.getSoGheDat());
            stmt.setInt(9, datCho.getGiaVe());
            stmt.setString(10, datCho.getMaNguoiDung());
            stmt.setInt(11, datCho.getMaXe());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    public List<DatCho> getAllDatCho() throws SQLException {
        List<DatCho> datChoList = new ArrayList<>();
        String sql = "SELECT * FROM DatCho";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                DatCho datCho = new DatCho();
                datCho.setMaDatCho(rs.getInt("MaDatCho"));
                datCho.setTrangThai(rs.getString("TrangThai"));
                datCho.setNgayDat(rs.getDate("NgayDat"));
                datCho.setGioDat(rs.getTime("GioDat"));
                datCho.setDiemDi(rs.getString("DiemDi"));
                datCho.setDiemDen(rs.getString("DiemDen"));
                datCho.setNgayGioKhoiHanh(rs.getTimestamp("NgayGioKhoiHanh"));
                datCho.setSoGheDat(rs.getInt("SoGheDat"));
                datCho.setGiaVe(rs.getInt("GiaVe"));
                datCho.setMaNguoiDung(rs.getString("MaNguoiDung"));
                datCho.setMaXe(rs.getInt("MaXe"));
                datChoList.add(datCho);
            }
        }
        return datChoList;
    }

    public DatCho getDatChoByMaDatCho(int maDatCho) throws SQLException {
        String sql = "SELECT * FROM DatCho WHERE MaDatCho = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, maDatCho);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    DatCho datCho = new DatCho();
                    datCho.setMaDatCho(rs.getInt("MaDatCho"));
                    datCho.setTrangThai(rs.getString("TrangThai"));
                    datCho.setNgayDat(rs.getDate("NgayDat"));
                    datCho.setGioDat(rs.getTime("GioDat"));
                    datCho.setDiemDi(rs.getString("DiemDi"));
                    datCho.setDiemDen(rs.getString("DiemDen"));
                    datCho.setNgayGioKhoiHanh(rs.getTimestamp("NgayGioKhoiHanh"));
                    datCho.setSoGheDat(rs.getInt("SoGheDat"));
                    datCho.setGiaVe(rs.getInt("GiaVe"));
                    datCho.setMaNguoiDung(rs.getString("MaNguoiDung"));
                    datCho.setMaXe(rs.getInt("MaXe"));
                    return datCho;
                }
            }
        }
        return null;
    }

    public boolean updateDatCho(DatCho datCho) throws SQLException {
        String sql = "UPDATE DatCho SET TrangThai = ?, NgayDat = ?, GioDat = ?, DiemDi = ?, DiemDen = ?, NgayGioKhoiHanh = ?, SoGheDat = ?, GiaVe = ?, MaNguoiDung = ?, MaXe = ? WHERE MaDatCho = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, datCho.getTrangThai());
            if (datCho.getNgayDat() != null) {
                stmt.setDate(2, new java.sql.Date(datCho.getNgayDat().getTime()));
            } else {
                stmt.setNull(2, java.sql.Types.DATE);
            }
            stmt.setTime(3, datCho.getGioDat());
            stmt.setString(4, datCho.getDiemDi());
            stmt.setString(5, datCho.getDiemDen());
            stmt.setTimestamp(6, new java.sql.Timestamp(datCho.getNgayGioKhoiHanh().getTime()));
            stmt.setInt(7, datCho.getSoGheDat());
            stmt.setInt(8, datCho.getGiaVe());
            stmt.setString(9, datCho.getMaNguoiDung());
            stmt.setInt(10, datCho.getMaXe());
            stmt.setInt(11, datCho.getMaDatCho());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    public boolean deleteDatCho(int maDatCho) throws SQLException {
        String sql = "DELETE FROM DatCho WHERE MaDatCho = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, maDatCho);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    public List<DatCho> getDatChoByMaXe(int maXe) throws SQLException {
        List<DatCho> datChoList = new ArrayList<>();
        String sql = "SELECT * FROM DatCho WHERE MaXe = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, maXe);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    DatCho datCho = new DatCho();
                    datCho.setMaDatCho(rs.getInt("MaDatCho"));
                    datCho.setTrangThai(rs.getString("TrangThai"));
                    datCho.setNgayDat(rs.getDate("NgayDat"));
                    datCho.setGioDat(rs.getTime("GioDat"));
                    datCho.setDiemDi(rs.getString("DiemDi"));
                    datCho.setDiemDen(rs.getString("DiemDen"));
                    datCho.setNgayGioKhoiHanh(rs.getTimestamp("NgayGioKhoiHanh"));
                    datCho.setSoGheDat(rs.getInt("SoGheDat"));
                    datCho.setGiaVe(rs.getInt("GiaVe"));
                    datCho.setMaNguoiDung(rs.getString("MaNguoiDung"));
                    datCho.setMaXe(rs.getInt("MaXe"));
                    datChoList.add(datCho);
                }
            }
        }
        return datChoList;
    }

    public List<DatCho> getDatChoByMaNguoiDung(String maNguoiDung) throws SQLException {
        List<DatCho> datChoList = new ArrayList<>();
        String sql = "SELECT * FROM DatCho WHERE MaNguoiDung = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, maNguoiDung);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    DatCho datCho = new DatCho();
                    datCho.setMaDatCho(rs.getInt("MaDatCho"));
                    datCho.setTrangThai(rs.getString("TrangThai"));
                    datCho.setNgayDat(rs.getDate("NgayDat"));
                    datCho.setGioDat(rs.getTime("GioDat"));
                    datCho.setDiemDi(rs.getString("DiemDi"));
                    datCho.setDiemDen(rs.getString("DiemDen"));
                    datCho.setNgayGioKhoiHanh(rs.getTimestamp("NgayGioKhoiHanh"));
                    datCho.setSoGheDat(rs.getInt("SoGheDat"));
                    datCho.setGiaVe(rs.getInt("GiaVe"));
                    datCho.setMaNguoiDung(rs.getString("MaNguoiDung"));
                    datCho.setMaXe(rs.getInt("MaXe"));
                    datChoList.add(datCho);
                }
            }
        }
        return datChoList;
    }
}