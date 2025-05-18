package dal;

import model.DatCho;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class DatChoDAO {
    private Connection connection;

    public DatChoDAO() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    public boolean createDatCho(DatCho datCho) throws SQLException {
        String sql = "INSERT INTO DatCho (MaDatCho, TrangThai, NgayDat, GioDat, DiemDi, DiemDen, NgayGioKhoiHanh, SoGheDat, GiaVe, MaNguoiDung, MaXe, TenHanhKhach, SoDienThoaiLienLac, EmailLienLac) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
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
            if (datCho.getNgayGioKhoiHanh() != null) {
                stmt.setTimestamp(7, new Timestamp(datCho.getNgayGioKhoiHanh().getTime()));
            } else {
                stmt.setNull(7, java.sql.Types.TIMESTAMP);
            }
            stmt.setInt(8, datCho.getSoGheDat());
            stmt.setInt(9, datCho.getGiaVe());
            stmt.setString(10, datCho.getMaNguoiDung());
            stmt.setInt(11, datCho.getMaXe());
            stmt.setString(12, datCho.getTenHanhKhach());
            stmt.setString(13, datCho.getSoDienThoaiLienLac());
            stmt.setString(14, datCho.getEmailLienLac());
            
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
                datCho.setTenHanhKhach(rs.getString("TenHanhKhach"));
                datCho.setSoDienThoaiLienLac(rs.getString("SoDienThoaiLienLac"));
                datCho.setEmailLienLac(rs.getString("EmailLienLac"));
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
                    datCho.setTenHanhKhach(rs.getString("TenHanhKhach"));
                    datCho.setSoDienThoaiLienLac(rs.getString("SoDienThoaiLienLac"));
                    datCho.setEmailLienLac(rs.getString("EmailLienLac"));
                    return datCho;
                }
            }
        }
        return null;
    }

    public boolean updateDatCho(DatCho datCho) throws SQLException {
        String sql = "UPDATE DatCho SET TrangThai = ?, NgayDat = ?, GioDat = ?, DiemDi = ?, DiemDen = ?, NgayGioKhoiHanh = ?, SoGheDat = ?, GiaVe = ?, MaNguoiDung = ?, MaXe = ?, TenHanhKhach = ?, SoDienThoaiLienLac = ?, EmailLienLac = ? WHERE MaDatCho = ?";
        
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
            if (datCho.getNgayGioKhoiHanh() != null) {
                stmt.setTimestamp(6, new Timestamp(datCho.getNgayGioKhoiHanh().getTime()));
            } else {
                stmt.setNull(6, java.sql.Types.TIMESTAMP);
            }
            stmt.setInt(7, datCho.getSoGheDat());
            stmt.setInt(8, datCho.getGiaVe());
            stmt.setString(9, datCho.getMaNguoiDung());
            stmt.setInt(10, datCho.getMaXe());
            stmt.setString(11, datCho.getTenHanhKhach());
            stmt.setString(12, datCho.getSoDienThoaiLienLac());
            stmt.setString(13, datCho.getEmailLienLac());
            stmt.setInt(14, datCho.getMaDatCho());
            
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
                    datCho.setTenHanhKhach(rs.getString("TenHanhKhach"));
                    datCho.setSoDienThoaiLienLac(rs.getString("SoDienThoaiLienLac"));
                    datCho.setEmailLienLac(rs.getString("EmailLienLac"));
                    datChoList.add(datCho);
                }
            }
        }
        return datChoList;
    }
}