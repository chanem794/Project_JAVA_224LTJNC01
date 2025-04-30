package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import static raven.application.form.other.PanelDataStation.createStationPanel;
import raven.application.form.other.StationForm;

public class ConnectStationForm {

    private StationForm sf;
    private Connection con;
    private Statement stm;

    public ConnectStationForm(StationForm stationForm) throws SQLException {
        this.sf = stationForm;
        con = DatabaseConnection.getInstance().getConnection();
        stm = con.createStatement();
        loadStationData();
    }

    private void loadStationData() throws SQLException {
        if ((sf.getJTextField1().getText().equals(" 🔍  Tìm trong danh sách") || sf.getJTextField1().getText().isEmpty())
                && (sf.getJTextField2().getText().equals(" 🔍  Tìm trong danh sách") || sf.getJTextField2().getText().isEmpty())) {
            // Xóa nội dung cũ
            sf.getRoundedPanel6().removeAll();
            sf.getRoundedPanel8().removeAll();
            sf.getRoundedPanel6().setLayout(new BoxLayout(sf.getRoundedPanel6(), BoxLayout.Y_AXIS));
            sf.getRoundedPanel8().setLayout(new BoxLayout(sf.getRoundedPanel8(), BoxLayout.Y_AXIS));

            String sql = "SELECT x.GioDi, x.GioDen, t.DiemDi, t.DiemDen "
                    + "FROM Xe x JOIN Tuyen t ON x.MaTuyen = t.MaTuyen";
            ResultSet rs = stm.executeQuery(sql);

            // Tạo panel động cho mỗi dòng dữ liệu
            while (rs.next()) {
                JPanel pickupPanel = createStationPanel(
                        rs.getTime("GioDi").toString(),
                        rs.getString("DiemDi"),
                        rs.getString("DiemDi")
                );
                sf.getRoundedPanel6().add(pickupPanel);
                JPanel dropoffPanel = createStationPanel(
                        rs.getTime("GioDen").toString(),
                        rs.getString("DiemDen"),
                        rs.getString("DiemDen")
                );
                sf.getRoundedPanel8().add(dropoffPanel);
            }

            // Cập nhật giao diện
            sf.getRoundedPanel6().revalidate();
            sf.getRoundedPanel6().repaint();
            sf.getRoundedPanel8().revalidate();
            sf.getRoundedPanel8().repaint();
            sf.revalidate();
            sf.repaint();
            sf.addRadioButtonListeners();
        }
    }

    public void eventtextfield1() {
        try {
            sf.getRoundedPanel6().removeAll();
            sf.getRoundedPanel6().setLayout(new BoxLayout(sf.getRoundedPanel6(), BoxLayout.Y_AXIS));

            String sql = "SELECT x.GioDi, x.GioDen, t.DiemDi, t.DiemDen "
                    + "FROM Xe x JOIN Tuyen t ON x.MaTuyen = t.MaTuyen "
                    + "WHERE t.DiemDi LIKE ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, "%" + sf.getJTextField1().getText().trim() + "%");

            ResultSet rs = pstmt.executeQuery();
            int rowCount = 0;
            while (rs.next()) {
                rowCount++;
                JPanel pickupPanel = createStationPanel(
                        rs.getTime("GioDi").toString(),
                        rs.getString("DiemDi"),
                        rs.getString("DiemDi")
                );
                sf.getRoundedPanel6().add(pickupPanel);
            }
            System.out.println("Số bản ghi tìm thấy (pickup): " + rowCount);

            // Cập nhật giao diện
            sf.getRoundedPanel6().revalidate();
            sf.getRoundedPanel6().repaint();
            sf.revalidate();
            sf.repaint();
            sf.addRadioButtonListeners();
        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null, "Lỗi khi tìm kiếm: " + ex.getMessage());
        }
    }

    public void eventtextfield2() {
        try {
            sf.getRoundedPanel8().removeAll();
            sf.getRoundedPanel8().setLayout(new BoxLayout(sf.getRoundedPanel8(), BoxLayout.Y_AXIS));

            String sql = "SELECT x.GioDi, x.GioDen, t.DiemDi, t.DiemDen "
                    + "FROM Xe x JOIN Tuyen t ON x.MaTuyen = t.MaTuyen "
                    + "WHERE t.DiemDen LIKE ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, "%" + sf.getJTextField2().getText().trim() + "%");

            ResultSet rs = pstmt.executeQuery();
            int rowCount = 0;
            while (rs.next()) {
                rowCount++;
                JPanel dropoffPanel = createStationPanel(
                        rs.getTime("GioDen").toString(),
                        rs.getString("DiemDen"),
                        rs.getString("DiemDen")
                );
                sf.getRoundedPanel8().add(dropoffPanel);
            }
            System.out.println("Số bản ghi tìm thấy (dropoff): " + rowCount);

            // Cập nhật giao diện
            sf.getRoundedPanel8().revalidate();
            sf.getRoundedPanel8().repaint();
            sf.revalidate();
            sf.repaint();
            sf.addRadioButtonListeners();
        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null, "Lỗi khi tìm kiếm: " + ex.getMessage());
        }
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
