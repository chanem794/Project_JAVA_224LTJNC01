package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.BusTicket;

public class BusTicketDAO {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public BusTicketDAO() {
        this.conn = DatabaseConnection.getInstance().getConnection();
    }

    // Lấy tất cả vé xe theo mã tuyến
    public List<BusTicket> getBusTicketsByMaTuyen(int maTuyen) throws SQLException {
        List<BusTicket> tickets = new ArrayList<>();
        String query = "SELECT * FROM Xe WHERE MaTuyen = ?";
        
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, maTuyen);
            rs = ps.executeQuery();

            while (rs.next()) {
                BusTicket ticket = new BusTicket(
                    rs.getInt("MaXe"),
                    rs.getString("TenXe"),
                    rs.getString("LoaiXe"),
                    rs.getString("DiemDi"),
                    rs.getString("DiemDen"),
                    rs.getDate("NgayKhoiHanh"),
                    rs.getTime("GioDi"),
                    rs.getTime("GioDen"),
                    rs.getInt("SoGhe"),
                    rs.getInt("GheConTrong"),
                    rs.getInt("GiaVe"),
                    rs.getInt("MaTuyen")
                );
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            throw new SQLException("Error retrieving bus tickets: " + e.getMessage());
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            // Không đóng connection vì DatabaseConnection sử dụng Singleton
        }
        return tickets;
    }

    // Lấy vé xe theo mã xe
    public BusTicket getBusTicketByMaXe(int maXe) throws SQLException {
        String query = "SELECT * FROM Xe WHERE MaXe = ?";
        BusTicket ticket = null;

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, maXe);
            rs = ps.executeQuery();

            if (rs.next()) {
                ticket = new BusTicket(
                    rs.getInt("MaXe"),
                    rs.getString("TenXe"),
                    rs.getString("LoaiXe"),
                    rs.getString("DiemDi"),
                    rs.getString("DiemDen"),
                    rs.getDate("NgayKhoiHanh"),
                    rs.getTime("GioDi"),
                    rs.getTime("GioDen"),
                    rs.getInt("SoGhe"),
                    rs.getInt("GheConTrong"),
                    rs.getInt("GiaVe"),
                    rs.getInt("MaTuyen")
                );
            }
        } catch (SQLException e) {
            throw new SQLException("Error retrieving bus ticket by MaXe: " + e.getMessage());
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            // Không đóng connection vì DatabaseConnection sử dụng Singleton
        }
        return ticket;
    }
}