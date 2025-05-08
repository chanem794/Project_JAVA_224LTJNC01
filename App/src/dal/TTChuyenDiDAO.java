package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.TTChuyenDi;

public class TTChuyenDiDAO {
    private Connection connection;

    public TTChuyenDiDAO() {
        this.connection = DatabaseConnection.getInstance().getConnection();
        if (this.connection == null) {
            System.err.println("Database connection is null in XeDAO constructor.");
        } else {
            try {
                if (this.connection.isClosed()) {
                    System.err.println("Database connection is closed in XeDAO constructor.");
                } else {
                    System.out.println("Database connection is valid in XeDAO constructor.");
                }
            } catch (SQLException e) {
                System.err.println("Error checking connection in XeDAO: " + e.getMessage());
            }
        }
    }

    public TTChuyenDi getTripDetails(int maXe) {
        String query = "SELECT TenXe, LoaiXe, DiemDi, DiemDen, NgayKhoiHanh, GioDi, GioDen, SoGhe, GheConTrong, GiaVe " +
                      "FROM Xe WHERE MaXe = ?";
        System.out.println("Executing query: " + query + " with MaXe: " + maXe);
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, maXe);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                TTChuyenDi xe = new TTChuyenDi();
                xe.setTenXe(rs.getString("TenXe") != null ? rs.getString("TenXe") : "");
                xe.setLoaiXe(rs.getString("LoaiXe") != null ? rs.getString("LoaiXe") : "");
                xe.setDiemDi(rs.getString("DiemDi") != null ? rs.getString("DiemDi") : "");
                xe.setDiemDen(rs.getString("DiemDen") != null ? rs.getString("DiemDen") : "");
                xe.setNgayKhoiHanh(rs.getDate("NgayKhoiHanh"));
                xe.setGioDi(rs.getTime("GioDi"));
                xe.setGioDen(rs.getTime("GioDen"));
                xe.setSoGhe(rs.getInt("SoGhe"));
                xe.setGheConTrong(rs.getInt("GheConTrong"));
                xe.setGiaVe(rs.getInt("GiaVe"));
                System.out.println("Trip details retrieved: TenXe=" + xe.getTenXe() + ", GiaVe=" + xe.getGiaVe());
                return xe;
            } else {
                System.err.println("No trip found for MaXe: " + maXe);
            }
        } catch (SQLException e) {
            System.err.println("SQLException in getTripDetails for MaXe " + maXe + ": " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}