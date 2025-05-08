package dal;   
   
import java.sql.Connection;   
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List; 
import model.Xe;
import model.Tuyen;
import raven.application.form.other.StationForm;

public class ConnectStationForm {

    private StationForm sf;
    private Connection con;
    private Statement stm;

    public ConnectStationForm(StationForm stationForm) throws SQLException {
        this.sf = stationForm;
        con = DatabaseConnection.getInstance().getConnection();
        stm = con.createStatement();
    }

    public List<Xe> loadStationData() throws SQLException {
        List<Xe> result = new ArrayList<>();
        String sql = "SELECT x.MaXe, x.MaTuyen, x.GioDi, x.GioDen, x.GiaVe, t.DiemDi, t.DiemDen "
                + "FROM Xe x JOIN Tuyen t ON x.MaTuyen = t.MaTuyen";
        ResultSet rs = stm.executeQuery(sql);

        while (rs.next()) {
            Xe xe = new Xe(
                rs.getInt("MaXe"),
                rs.getInt("MaTuyen"),
                rs.getTime("GioDi"),
                rs.getTime("GioDen"),
                rs.getInt("GiaVe"),
                rs.getString("DiemDi"),
                rs.getString("DiemDen")
            );
            result.add(xe);
        }
        return result;
    }

    public List<Xe> eventtextfield1(String searchText) throws SQLException {
        List<Xe> result = new ArrayList<>();
        String sql = "SELECT x.MaXe, x.MaTuyen, x.GioDi, x.GioDen, x.GiaVe, t.DiemDi, t.DiemDen "
                + "FROM Xe x JOIN Tuyen t ON x.MaTuyen = t.MaTuyen "
                + "WHERE t.DiemDi LIKE ?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, "%" + searchText.trim() + "%");
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            Xe xe = new Xe(
                rs.getInt("MaXe"),
                rs.getInt("MaTuyen"),
                rs.getTime("GioDi"),
                rs.getTime("GioDen"),
                rs.getInt("GiaVe"),
                rs.getString("DiemDi"),
                rs.getString("DiemDen")
            );
            result.add(xe);
        }
        return result;
    }

    public List<Xe> eventtextfield2(String searchText) throws SQLException {
        List<Xe> result = new ArrayList<>();
        String sql = "SELECT x.MaXe, x.MaTuyen, x.GioDi, x.GioDen, x.GiaVe, t.DiemDi, t.DiemDen "
                + "FROM Xe x JOIN Tuyen t ON x.MaTuyen = t.MaTuyen "
                + "WHERE t.DiemDen LIKE ?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, "%" + searchText.trim() + "%");
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            Xe xe = new Xe(
                rs.getInt("MaXe"),
                rs.getInt("MaTuyen"),
                rs.getTime("GioDi"),
                rs.getTime("GioDen"),
                rs.getInt("GiaVe"),
                rs.getString("DiemDi"),
                rs.getString("DiemDen")
            );
            result.add(xe);
        }
        return result;
    }

    public String getprice(String pickupStation, String dropoffStation) throws SQLException {
        String sql = "SELECT x.GiaVe "
                + "FROM Xe x JOIN Tuyen t ON x.MaTuyen = t.MaTuyen "
                + "WHERE t.DiemDi = ? AND t.DiemDen = ?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, pickupStation);
        pstmt.setString(2, dropoffStation);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            int fare = rs.getInt("GiaVe");
            return fare + " VNĐ";
        } else {
            return "Không tìm thấy tuyến";
        }
    }
}