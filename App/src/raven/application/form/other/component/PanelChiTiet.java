package raven.application.form.other.component;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.ui.FlatUIUtils;
import net.miginfocom.swing.MigLayout;
import dal.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PanelChiTiet extends JPanel {
    private JButton btnClose;
    private int maXe; 

    public PanelChiTiet(int maXe) {
        this.maXe = maXe;
        initComponents();
    }

    private void initComponents() {
        // Adjust column constraints to allow more flexibility for long text
        setLayout(new MigLayout("fill, wrap, insets 20", "[grow]", "[]10[]10[]10[]10[]10[]10[]10[]"));
        setBackground(new java.awt.Color(235, 235, 235));
        putClientProperty(FlatClientProperties.STYLE, "arc:20");

        // Initialize variables with default values
        String route = "N/A";
        String tenXe = "N/A";
        String loaiXe = "N/A";
        int soGheDat = 1;
        String maGhe = "A1.1"; 
        int giaVe = 0;
        String diemDi = "N/A";
        String diemDiDetails = "";
        String thoiGianDon = "N/A";
        String diemDen = "N/A";
        String diemDenDetails = "";
        String thoiGianTra = "N/A";

        try {
            Connection conn = DatabaseConnection.getInstance().getConnection();
            String query = "SELECT t.DiemDi, t.DiemDen, x.TenXe, x.LoaiXe, x.NgayKhoiHanh, x.GioDi, " +
                          "COALESCE(dc.SoGheDat, 0) AS SoGheDat, x.GiaVe, " +
                          "COALESCE(dc.DiemDi, t.DiemDi) AS PickupPoint, COALESCE(dc.DiemDen, t.DiemDen) AS DropoffPoint, " +
                          "l1.DiaDiem AS FirstStop, l5.DiaDiem AS LastStop, l5.ThoiGianDuKien AS LastStopTime " +
                          "FROM Xe x " +
                          "JOIN Tuyen t ON x.MaTuyen = t.MaTuyen " +
                          "LEFT JOIN DatCho dc ON dc.MaXe = x.MaXe " +
                          "LEFT JOIN LichTrinhTuDong l1 ON x.MaXe = l1.MaXe AND l1.ThuTu = 1 " +
                          "LEFT JOIN LichTrinhTuDong l5 ON x.MaXe = l5.MaXe AND l5.ThuTu = 5 " +
                          "WHERE x.MaXe = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, maXe);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                route = rs.getString("DiemDi") + "->" + rs.getString("DiemDen");
                tenXe = rs.getString("TenXe");
                loaiXe = rs.getString("LoaiXe");
                soGheDat = rs.getInt("SoGheDat");
                giaVe = rs.getInt("GiaVe");
                diemDi = rs.getString("FirstStop") != null ? rs.getString("FirstStop") : rs.getString("DiemDi");
                diemDiDetails = rs.getString("PickupPoint") != null ? rs.getString("PickupPoint") : "";
                diemDen = rs.getString("LastStop") != null ? rs.getString("LastStop") : rs.getString("DiemDen");
                diemDenDetails = rs.getString("DropoffPoint") != null ? rs.getString("DropoffPoint") : "";

                // Format pickup time (combine NgayKhoiHanh and GioDi)
                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm dd/MM/yyyy");
                Date ngayKhoiHanh = rs.getDate("NgayKhoiHanh");
                String gioDi = rs.getString("GioDi");
                if (ngayKhoiHanh != null && gioDi != null) {
                    String dateTimeStr = ngayKhoiHanh.toString() + " " + gioDi;
                    SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date pickupDateTime = parser.parse(dateTimeStr);
                    thoiGianDon = dateFormat.format(pickupDateTime);

                    // Calculate drop-off time
                    int totalJourneyTime = rs.getInt("LastStopTime"); // In seconds
                    long dropoffTimeMillis = pickupDateTime.getTime() + (totalJourneyTime * 1000L);
                    thoiGianTra = dateFormat.format(new Date(dropoffTimeMillis));
                }
            }

            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }

        // Header panel for Back button and Title
        JPanel headerPanel = new JPanel(new MigLayout("insets 0", "[left][left][grow]", "[]"));
        headerPanel.setOpaque(false);

        // Back button
        JButton btnBack = new JButton("");
        btnBack.setFocusPainted(false);
        btnBack.setBackground(new java.awt.Color(235, 235, 235));
        btnBack.setForeground(Color.BLACK);
        btnBack.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        btnBack.putClientProperty(FlatClientProperties.STYLE, "font:bold +15");
        btnBack.addActionListener(e -> btnClose.doClick()); 
        ImageIcon BackIcon = new ImageIcon(getClass().getResource("/raven/thanhtoan/icon/Back.png"));
        Image scaledBackImage = BackIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);  
        ImageIcon resizedIconBackIcon = new ImageIcon(scaledBackImage);
        btnBack.setIcon(resizedIconBackIcon);
        headerPanel.add(btnBack, "gapbottom 0"); 

        // Title
        JLabel lbTitle = new JLabel("Chi Tiết Vé Xe Của Bạn");
        lbTitle.putClientProperty(FlatClientProperties.STYLE, "font:bold +10");
        headerPanel.add(lbTitle);

        // Add header panel to main layout
        add(headerPanel, "growx, wrap");
   
        //Tuyến
        JPanel tuyenPanel = new JPanel(new MigLayout("insets 0", "[left][right]", "[]"));
        tuyenPanel.setOpaque(false);
        JLabel lbTuyen = new JLabel("Tuyến:");
        JLabel lbTuyenValue = new JLabel(route); // Updated to use dynamic route
        lbTuyenValue.putClientProperty(FlatClientProperties.STYLE, "font:$h3.font");
        tuyenPanel.add(lbTuyen);
        tuyenPanel.add(lbTuyenValue);
        add(tuyenPanel, "growx, wrap");

        // Nhà Xe
        JPanel nhaXePanel = new JPanel(new MigLayout("insets 0", "[left][grow,push][right]", "[]"));
        nhaXePanel.setOpaque(false);
        JLabel lbNhaXe = new JLabel("Nhà xe:");
        JLabel lbNhaXeValue = new JLabel(tenXe);
        lbNhaXeValue.putClientProperty(FlatClientProperties.STYLE, "font:$h3.font");
        nhaXePanel.add(lbNhaXe);
        nhaXePanel.add(lbNhaXeValue, "align right");
        add(nhaXePanel, "growx, wrap");

        // Loại Xe
        JPanel loaiXePanel = new JPanel(new MigLayout("insets 0", "[left][grow,push][right]", "[]"));
        loaiXePanel.setOpaque(false);
        JLabel lbLoaiXe = new JLabel("Loại xe:");
        JLabel lbLoaiXeValue = new JLabel(loaiXe);
        lbLoaiXeValue.putClientProperty(FlatClientProperties.STYLE, "font:$h3.font");
        loaiXePanel.add(lbLoaiXe);
        loaiXePanel.add(lbLoaiXeValue, "align right");
        add(loaiXePanel, "growx, wrap");

        // Số lượng
        JPanel soLuongVePanel = new JPanel(new MigLayout("insets 0", "[left][grow,push][right]", "[]"));
        soLuongVePanel.setOpaque(false);
        JLabel lbSoLuongVe = new JLabel("Số lượng:");
        JLabel lbSoLuongVeValue = new JLabel("1 vé"); // Updated to use dynamic soGheDat
        lbSoLuongVeValue.putClientProperty(FlatClientProperties.STYLE, "font:$h3.font");
        soLuongVePanel.add(lbSoLuongVe);
        soLuongVePanel.add(lbSoLuongVeValue, "align right");
        add(soLuongVePanel, "growx, wrap");

        // Mã ghế/ giường
        JPanel maGhePanel = new JPanel(new MigLayout("insets 0", "[left][grow,push][right]", "[]"));
        maGhePanel.setOpaque(false);
        JLabel lbMaGhe = new JLabel("Mã ghế/ giường:");
        JLabel lbMaGheValue = new JLabel(maGhe);
        lbMaGheValue.putClientProperty(FlatClientProperties.STYLE, "font:$h3.font");
        maGhePanel.add(lbMaGhe);
        maGhePanel.add(lbMaGheValue, "align right");
        add(maGhePanel, "growx, wrap");

        // Tạm tính
        JPanel tongTienPanel = new JPanel(new MigLayout("insets 0", "[left][grow,push][right]", "[]"));
        tongTienPanel.setOpaque(false);
        JLabel lbTongTien = new JLabel("Tạm tính:");
        JLabel lbTongTienValue = new JLabel(String.format("%,dđ", giaVe));
        lbTongTienValue.putClientProperty(FlatClientProperties.STYLE, "font:$h3.font");
        tongTienPanel.add(lbTongTien);
        tongTienPanel.add(lbTongTienValue, "align right");
        add(tongTienPanel, "growx, wrap");

        // Điểm đón
        FlatSVGIcon DiemDiicon = new FlatSVGIcon("raven/thanhtoan/icon/diemdi.svg", 20, 20);
        DiemDiicon.setColorFilter(new FlatSVGIcon.ColorFilter()
            .add(Color.decode("#969696"),
                 FlatUIUtils.getUIColor("Menu.icon.lightColor", Color.red),
                 FlatUIUtils.getUIColor("Menu.icon.darkColor", Color.red))
        );
        JLabel lbDiemDonTitle = new JLabel("ĐIỂM ĐÓN");
        lbDiemDonTitle.setIcon(DiemDiicon);
        lbDiemDonTitle.putClientProperty(FlatClientProperties.STYLE, "font:bold +2");
        add(lbDiemDonTitle, "align left, wrap");

        JPanel diemDonPanel = new JPanel(new MigLayout("insets 0", "[left][right]", "[]"));
        diemDonPanel.setOpaque(false);
        JLabel lbDiemDon = new JLabel(diemDi);
        diemDonPanel.add(lbDiemDon);
        lbDiemDon.putClientProperty(FlatClientProperties.STYLE, "font: +2");
        add(diemDonPanel, "growx, wrap");

        JPanel thoiGianDonPanel = new JPanel(new MigLayout("insets 0", "[left][right]", "[]"));
        thoiGianDonPanel.setOpaque(false);
        JLabel lbThoiGianDon = new JLabel("Dự kiến đón lúc:");
        lbThoiGianDon.putClientProperty(FlatClientProperties.STYLE, "font:$h3.font");
        JLabel lbThoiGianDonValue = new JLabel(thoiGianDon);
        lbThoiGianDonValue.putClientProperty(FlatClientProperties.STYLE, "font:$h3.font");
        thoiGianDonPanel.add(lbThoiGianDon);
        thoiGianDonPanel.add(lbThoiGianDonValue);
        add(thoiGianDonPanel, "growx, wrap");

        // Điểm trả
        FlatSVGIcon DiemDenicon = new FlatSVGIcon("raven/thanhtoan/icon/diemden.svg", 20, 20);
        DiemDenicon.setColorFilter(new FlatSVGIcon.ColorFilter()
            .add(Color.decode("#969696"),
                 FlatUIUtils.getUIColor("Menu.icon.lightColor", Color.red),
                 FlatUIUtils.getUIColor("Menu.icon.darkColor", Color.red))
        );
        JLabel lbDiemTraTitle = new JLabel("ĐIỂM TRẢ");
        lbDiemTraTitle.setIcon(DiemDenicon);
        lbDiemTraTitle.putClientProperty(FlatClientProperties.STYLE, "font:bold +2");
        add(lbDiemTraTitle, "align left, wrap");

        JPanel diemTraPanel = new JPanel(new MigLayout("insets 0", "[left][right]", "[]"));
        diemTraPanel.setOpaque(false);
        JLabel lbDiemTra = new JLabel(diemDen);
        diemTraPanel.add(lbDiemTra);
        lbDiemTra.putClientProperty(FlatClientProperties.STYLE, "font: +2");
        add(diemTraPanel, "growx, wrap");

        JPanel thoiGianTraPanel = new JPanel(new MigLayout("insets 0", "[left][right]", "[]"));
        thoiGianTraPanel.setOpaque(false);
        JLabel lbThoiGianTra = new JLabel("Dự kiến trả lúc:");
        lbThoiGianTra.putClientProperty(FlatClientProperties.STYLE, "font:$h3.font");
        JLabel lbThoiGianTraValue = new JLabel(thoiGianTra);
        lbThoiGianTraValue.putClientProperty(FlatClientProperties.STYLE, "font:$h3.font");
        thoiGianTraPanel.add(lbThoiGianTra);
        thoiGianTraPanel.add(lbThoiGianTraValue);
        add(thoiGianTraPanel, "growx, wrap");

        JSeparator separator = new JSeparator();
        add(separator, "growx, gaptop 15, wrap");

        //button đóng
        btnClose = new JButton("Đóng");
        btnClose.setBackground(new java.awt.Color(13, 43, 86));
        btnClose.setForeground(Color.WHITE);
        btnClose.putClientProperty(FlatClientProperties.STYLE, "font:$h3.font");
        add(btnClose, "align center, gaptop 7, h 40!, w 355!");
    }

    public JButton getBtnClose() {
        return btnClose;
    }
}


