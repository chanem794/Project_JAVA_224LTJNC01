/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package raven.application.form.other;

import bll.DatChoService;
import bll.NguoiDungService;
import bll.XeService;
import com.formdev.flatlaf.FlatLaf;
import java.awt.Color;
import java.awt.Image;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.ImageIcon;
import model.DatCho;
import model.NguoiDung;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.util.Collections;
import java.util.Comparator;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import model.Xe;


/**
 *
 * @author Admin
 */
public class YourBusTicketForm extends javax.swing.JPanel {
    private String maNguoiDung; // Mã người dùng hiện tại
    private DatChoService datChoService;
    private NguoiDungService nguoiDungService;
    private JPanel ticketPanel; // Panel chứa danh sách vé
    private XeService xeService; // Thêm XeService
    private List<JPanel> ticketItems = new ArrayList<>();
    /**
     * Creates new form YourBusTicketForm
     */
    
    public YourBusTicketForm(String maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
        this.datChoService = new DatChoService();
        this.nguoiDungService = new NguoiDungService();
        this.xeService = new XeService(); // Khởi tạo XeService
        initComponents();
        init();
        updatePanelColors();
        loadUserAndTicketData();
        // Đăng ký lắng nghe sự thay đổi theme
        UIManager.addPropertyChangeListener(evt -> {
            if ("lookAndFeel".equals(evt.getPropertyName())) {
                SwingUtilities.invokeLater(() -> {
                    refreshForm();
                });
            }
        });
}
    private void init() {
        ImageIcon iconLabel17 = new ImageIcon(getClass().getResource("/raven/icon/png/electronic-ticket.png"));
        Image scaledIcon17 = iconLabel17.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon17 = new ImageIcon(scaledIcon17);
        jLabel17.setIcon(resizedIcon17);
        jLabel17.setIconTextGap(5);
        jLabel17.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel17.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);

        ImageIcon iconLabel20 = new ImageIcon(getClass().getResource("/raven/icon/png/profile.png"));
        Image scaledIcon20 = iconLabel20.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon20 = new ImageIcon(scaledIcon20);
        jLabel20.setIcon(resizedIcon20);
        jLabel20.setIconTextGap(5);
        jLabel20.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel20.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);

        ImageIcon iconLabel21 = new ImageIcon(getClass().getResource("/raven/icon/png/infomation.png"));
        Image scaledIcon21 = iconLabel21.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon21 = new ImageIcon(scaledIcon21);
        jLabel21.setIcon(resizedIcon21);
        jLabel21.setIconTextGap(5);
        jLabel21.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel21.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);

        // Khởi tạo ticketPanel với MigLayout
        ticketPanel = new JPanel(new MigLayout("insets 10, wrap 1, fillx", "[grow]", ""));
        ticketPanel.setBackground(FlatLaf.isLafDark() ? new Color(50, 60, 70) : new Color(245, 245, 245));

        // Thêm JScrollPane để hỗ trợ cuộn
        JScrollPane scrollPane = new JScrollPane(ticketPanel);
        scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getViewport().setBackground(FlatLaf.isLafDark() ? new Color(50, 60, 70) : new Color(245, 245, 245));
        scrollPane.setBackground(FlatLaf.isLafDark() ? new Color(49, 62, 74, 255) : new Color(255, 255, 255));

        // Đặt layout BorderLayout cho jPanel1 và thêm scrollPane
        jPanel1.setLayout(new BorderLayout());
        jPanel1.add(scrollPane, BorderLayout.CENTER);

        // Đặt kích thước cho jPanel1
        jPanel1.setPreferredSize(new Dimension(780, 550));
        jPanel1.setMinimumSize(new Dimension(780, 550));
        // Bỏ setMaximumSize để hỗ trợ cuộn
        // jPanel1.setMaximumSize(new Dimension(780, 550));

        // Làm mới giao diện
        roundedPanel4.revalidate();
        roundedPanel4.repaint();
}
    public void refreshForm() {
        updatePanelColors();
        updateTicketItemsTheme(); // Cập nhật theme cho ticketItems
}
    private void updateTicketItemsTheme() {
        boolean isDarkMode = FlatLaf.isLafDark();
        Color backgroundColor = isDarkMode ? new Color(60, 70, 80) : new Color(250, 250, 250);
        Color borderColor = isDarkMode ? new Color(100, 110, 120) : new Color(200, 200, 200);
        Color textColor = isDarkMode ? Color.WHITE : Color.BLACK;
        Color confirmButtonColor = isDarkMode ? new Color(46, 204, 113) : new Color(46, 139, 87);
        Color cancelButtonColor = isDarkMode ? new Color(231, 76, 60) : new Color(220, 20, 60);

        for (JPanel ticketItem : ticketItems) {
            ticketItem.setBackground(backgroundColor);
            ticketItem.setBorder(new RoundedBorder(10, borderColor));
            for (java.awt.Component comp : ticketItem.getComponents()) {
                if (comp instanceof JLabel) {
                    ((JLabel) comp).setForeground(textColor);
                } else if (comp instanceof JPanel) {
                    JPanel buttonPanel = (JPanel) comp;
                    for (java.awt.Component btn : buttonPanel.getComponents()) {
                        if (btn instanceof JButton) {
                            JButton button = (JButton) btn;
                            if (button.getText().equals("Hoàn Thành")) {
                                button.setBackground(confirmButtonColor);
                            } else if (button.getText().equals("Hủy vé")) {
                                button.setBackground(cancelButtonColor);
                            }
                            button.setForeground(Color.WHITE);
                        }
                    }
                }
            }
        }
        ticketPanel.revalidate();
        ticketPanel.repaint();
}
    // Phương thức cập nhật màu nền của roundedPanel2 dựa trên theme
    private void updatePanelColors() {
        if (FlatLaf.isLafDark()) {
            roundedPanel1.setBackground(new Color(40, 50, 60));
            roundedPanel3.setBackground(new Color(40, 50, 60));
            roundedPanel4.setBackground(new Color(40, 50, 60));
            ticketPanel.setBackground(new Color(50, 60, 70));
            jComboBox1.setBackground(new Color(60, 70, 80));
            jComboBox1.setForeground(Color.WHITE);
        } else {
            roundedPanel1.setBackground(new Color(255, 255, 255));
            roundedPanel3.setBackground(new Color(255, 255, 255));
            roundedPanel4.setBackground(new Color(255, 255, 255));
            ticketPanel.setBackground(new Color(245, 245, 250));
            jComboBox1.setBackground(new Color(230, 230, 235));
            jComboBox1.setForeground(Color.BLACK);
        }
        updateTicketItemsTheme(); // Cập nhật theme cho ticketItems
        roundedPanel4.revalidate();
        roundedPanel4.repaint();
    }
    private void updateTicketPanel(String selectedStatus) {
        ticketItems.clear(); // Xóa danh sách cũ
        ticketPanel.removeAll();
        try {
            List<DatCho> datChoList = datChoService.getAllDatCho();
            if (datChoList == null || datChoList.isEmpty()) {
                JLabel noTicketsLabel = new JLabel("Không có vé nào.");
                noTicketsLabel.setForeground(FlatLaf.isLafDark() ? Color.WHITE : Color.BLACK);
                ticketPanel.add(noTicketsLabel, "align center");
                ticketPanel.revalidate();
                ticketPanel.repaint();
                return;
            }

            Collections.sort(datChoList, (d1, d2) -> d2.getNgayDat().compareTo(d1.getNgayDat()));

            boolean hasTickets = false;
            int ticketCount = 0;
            boolean isDarkMode = FlatLaf.isLafDark();
            Color ticketBackground = isDarkMode ? new Color(60, 70, 80) : new Color(250, 250, 250);
            Color borderColor = isDarkMode ? new Color(100, 110, 120) : new Color(200, 200, 200);
            Color textColor = isDarkMode ? Color.WHITE : Color.BLACK;
            Color confirmButtonColor = isDarkMode ? new Color(46, 204, 113) : new Color(46, 139, 87);
            Color cancelButtonColor = isDarkMode ? new Color(231, 76, 60) : new Color(220, 20, 60);

            for (DatCho datCho : datChoList) {
                if (datCho == null || datCho.getMaNguoiDung() == null || datCho.getTrangThai() == null) {
                    continue;
                }
                if (datCho.getMaNguoiDung().equals(maNguoiDung) &&
                    (selectedStatus.equals("Tất cả") || datCho.getTrangThai().equalsIgnoreCase(selectedStatus))) {
                    hasTickets = true;
                    ticketCount++;

                    // Tạo ticketItem với layout mới (2 cột, 3 hàng)
                    JPanel ticketItem = new JPanel(new MigLayout("insets 10, fillx", "[grow][200px]", "[][][]"));
                    ticketItem.setBackground(ticketBackground);
                    ticketItem.setBorder(new RoundedBorder(10, borderColor));
                    ticketItem.setPreferredSize(new Dimension(750, 110)); // Giảm chiều cao

                    // Định dạng ngày giờ
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

                    // Lấy thông tin Ngày đặt và Thời gian đặt
                    String ngayDatStr = datCho.getNgayDat() != null ? dateFormat.format(datCho.getNgayDat()) : "N/A";
                    String gioDatStr = "N/A";
                    if (datCho.getGioDat() != null) {
                        try {
                            // Chuyển đổi GioDat (java.sql.Time) sang định dạng HH:mm
                            gioDatStr = timeFormat.format(datCho.getGioDat());
                        } catch (Exception e) {
                            System.out.println("Lỗi khi định dạng GioDat: " + e.getMessage());
                        }
                    } else {
                        System.out.println("GioDat là null cho MaDatCho: " + datCho.getMaDatCho());
                    }

                    // Lấy thông tin xe
                    String tenXe = "Không xác định";
                    String gioDiStr = "N/A";
                    String gioDenStr = "N/A";
                    try {
                        Xe xe = xeService.getXeByMaXe(datCho.getMaXe());
                        if (xe != null) {
                            if (xe.getTenXe() != null) {
                                tenXe = xe.getTenXe();
                            }
                            if (xe.getGioDi() != null) {
                                gioDiStr = timeFormat.format(xe.getGioDi());
                            }
                            if (xe.getGioDen() != null) {
                                gioDenStr = timeFormat.format(xe.getGioDen());
                            }
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                    // Cột 1: Thông tin chính
                    JLabel tenXeLabel = new JLabel("🚍 " + tenXe);
                    tenXeLabel.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 14));
                    tenXeLabel.setForeground(textColor);
                    ticketItem.add(tenXeLabel, "cell 0 0");

                    JLabel hanhKhachLabel = new JLabel("👤 " + (datCho.getTenHanhKhach() != null ? datCho.getTenHanhKhach() : "N/A") + " | 📧 " + (datCho.getEmailLienLac() != null ? datCho.getEmailLienLac() : "N/A"));
                    hanhKhachLabel.setFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 12));
                    hanhKhachLabel.setForeground(textColor);
                    ticketItem.add(hanhKhachLabel, "cell 0 1");

                    JLabel diemDiDenLabel = new JLabel(datCho.getDiemDi() + " ➡️ " + (datCho.getDiemDen() != null ? datCho.getDiemDen() : "N/A") + " | 🕒 " + gioDiStr + " - " + gioDenStr);
                    diemDiDenLabel.setFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 12));
                    diemDiDenLabel.setForeground(textColor);
                    ticketItem.add(diemDiDenLabel, "cell 0 2");

                    // Cột 2: Thông tin đặt vé và hành động
                    JLabel datVeLabel = new JLabel("📅 " + ngayDatStr + " ⏰ " + gioDatStr);
                    datVeLabel.setFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 12));
                    datVeLabel.setForeground(textColor);
                    ticketItem.add(datVeLabel, "cell 1 0");

                    JLabel trangThaiGiaVeLabel = new JLabel("Trạng thái: " + datCho.getTrangThai() + " | 💵 " + datCho.getGiaVe() + " VNĐ");
                    trangThaiGiaVeLabel.setFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 12));
                    trangThaiGiaVeLabel.setForeground(textColor);
                    ticketItem.add(trangThaiGiaVeLabel, "cell 1 1");

                    if ("Đang chờ xác nhận".equalsIgnoreCase(datCho.getTrangThai())) {
                        JPanel buttonPanel = new JPanel(new MigLayout("insets 0, gap 5", "[][]", ""));
                        buttonPanel.setOpaque(false);

                        JButton confirmButton = new JButton("Hoàn Thành");
                        confirmButton.setBackground(confirmButtonColor);
                        confirmButton.setForeground(Color.WHITE);
                        confirmButton.setPreferredSize(new Dimension(90, 30));
                        confirmButton.setFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 12));
                        confirmButton.setToolTipText("Xác nhận hoàn thành");
                        confirmButton.addActionListener(e -> {
                            try {
                                datCho.setTrangThai("Thành công");
                                datChoService.updateDatCho(datCho);
                                updateTicketPanel(selectedStatus);
                                loadUserAndTicketData();
                                JOptionPane.showMessageDialog(this, "Đã xác nhận hoàn thành!");
                            } catch (SQLException ex) {
                                JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật: " + ex.getMessage());
                                ex.printStackTrace();
                            }
                        });
                        buttonPanel.add(confirmButton);

                        JButton cancelButton = new JButton("Hủy vé");
                        cancelButton.setBackground(cancelButtonColor);
                        cancelButton.setForeground(Color.WHITE);
                        cancelButton.setPreferredSize(new Dimension(90, 30));
                        cancelButton.setFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 12));
                        cancelButton.setToolTipText("Hủy vé");
                        cancelButton.addActionListener(e -> {
                            try {
                                datCho.setTrangThai("Đã hủy");
                                datChoService.updateDatCho(datCho);
                                updateTicketPanel(selectedStatus);
                                loadUserAndTicketData();
                                JOptionPane.showMessageDialog(this, "Đã hủy vé!");
                            } catch (SQLException ex) {
                                JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật: " + ex.getMessage());
                                ex.printStackTrace();
                            }
                        });
                        buttonPanel.add(cancelButton);

                        ticketItem.add(buttonPanel, "cell 1 2, align right");
                    }

                    ticketPanel.add(ticketItem, "growx, gapy 8");
                    ticketItems.add(ticketItem);
                }
            }

            if (!hasTickets) {
                JLabel noTicketsLabel = new JLabel("Không có vé nào phù hợp với trạng thái này.");
                noTicketsLabel.setForeground(textColor);
                ticketPanel.add(noTicketsLabel, "align center");
            } else {
                int panelHeight = ticketCount * 120 + 20; // Điều chỉnh chiều cao panel
                ticketPanel.setPreferredSize(new Dimension(750, panelHeight));
            }

            ticketPanel.revalidate();
            ticketPanel.repaint();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tải danh sách vé: " + e.getMessage());
            e.printStackTrace();
        }
}
    private void loadUserAndTicketData() {
        try {
            NguoiDung nguoiDung = nguoiDungService.getUserByMaNguoiDung(maNguoiDung);
            if (nguoiDung != null) {
                jLabel12.setText(nguoiDung.getTenNguoiDung() != null && !nguoiDung.getTenNguoiDung().isEmpty() ? nguoiDung.getTenNguoiDung() : "Chưa cập nhật");
                jLabel13.setText(nguoiDung.getEmail() != null ? nguoiDung.getEmail() : "N/A");
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                jLabel14.setText(nguoiDung.getNgaySinh() != null ? sdf.format(nguoiDung.getNgaySinh()) : "Chưa cập nhật");
                jLabel15.setText(nguoiDung.getMaNguoiDung() != null ? nguoiDung.getMaNguoiDung() : "N/A");

                // Kiểm tra mã người dùng và cập nhật jLabel11
                String maNguoiDung = nguoiDung.getMaNguoiDung();
                if (maNguoiDung != null && maNguoiDung.length() >= 2) {
                    String prefix = maNguoiDung.substring(0, 2).toUpperCase();
                    if ("AD".equals(prefix)) {
                        jLabel11.setText("Admin");
                    } else if ("LX".equals(prefix)) {
                        jLabel11.setText("Người lái xe");
                    } else {
                        jLabel11.setText("Khách hàng"); // Trường hợp mặc định
                    }
                } else {
                    jLabel11.setText("Khách hàng"); // Nếu mã người dùng không hợp lệ
                }
            } else {
                jLabel11.setText("Không tìm thấy");
                jLabel12.setText("Không tìm thấy");
                jLabel13.setText("Không tìm thấy");
                jLabel14.setText("Không tìm thấy");
                jLabel15.setText("Không tìm thấy");
            }

            int totalTickets = 0;
            int completedTickets = 0;
            int pendingTickets = 0;

            List<DatCho> datChoList = datChoService.getAllDatCho();
            for (DatCho datCho : datChoList) {
                if (datCho.getMaNguoiDung().equals(maNguoiDung)) {
                    totalTickets++;
                    if ("Thành công".equalsIgnoreCase(datCho.getTrangThai())) {
                        completedTickets++;
                    } else if ("Đang chờ xác nhận".equalsIgnoreCase(datCho.getTrangThai())) {
                        pendingTickets++;
                    }
                }
            }

            jLabel22.setText(String.valueOf(totalTickets));
            jLabel23.setText(String.valueOf(completedTickets));
            jLabel24.setText(String.valueOf(pendingTickets));

            updateTicketPanel((String) jComboBox1.getSelectedItem());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundedPanel2 = new raven.application.form.other.RoundedPanel();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        roundedPanel1 = new raven.application.form.other.RoundedPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        roundedPanel3 = new raven.application.form.other.RoundedPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        roundedPanel4 = new raven.application.form.other.RoundedPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();

        javax.swing.GroupLayout roundedPanel2Layout = new javax.swing.GroupLayout(roundedPanel2);
        roundedPanel2.setLayout(roundedPanel2Layout);
        roundedPanel2Layout.setHorizontalGroup(
            roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        roundedPanel2Layout.setVerticalGroup(
            roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jMenu1.setText("jMenu1");

        jMenu2.setText("jMenu2");

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel3.setText("Thông Tin Tài Khoản");

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel4.setText("Tài khoản:");

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel5.setText("Họ và tên:");

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel6.setText("Email:");

        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel7.setText("Ngày sinh:");

        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel8.setText("Mã hoạt động:");

        jLabel9.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        jLabel9.setText("Không chia sẻ thông tin");

        jLabel10.setFont(new java.awt.Font("SansSerif", 3, 16)); // NOI18N
        jLabel10.setText("CHO BẤT KÌ AI!");

        jLabel11.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel11.setText("User");

        jLabel12.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel12.setText("jLabel12");

        jLabel13.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel13.setText("jLabel13");

        jLabel14.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel14.setText("jLabel14");

        jLabel15.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel15.setText("jLabel15");

        javax.swing.GroupLayout roundedPanel1Layout = new javax.swing.GroupLayout(roundedPanel1);
        roundedPanel1.setLayout(roundedPanel1Layout);
        roundedPanel1Layout.setHorizontalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(roundedPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel15))
                            .addGroup(roundedPanel1Layout.createSequentialGroup()
                                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7))
                                .addGap(18, 18, 18)
                                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel11)))))
                    .addGroup(roundedPanel1Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jLabel9))
                    .addGroup(roundedPanel1Layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(jLabel10)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(21, 21, 21))
        );
        roundedPanel1Layout.setVerticalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel11))
                .addGap(30, 30, 30)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel12))
                .addGap(30, 30, 30)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel13))
                .addGap(30, 30, 30)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel14))
                .addGap(30, 30, 30)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel15))
                .addGap(36, 36, 36)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel2.setText("Thông Tin Hoạt Động");

        jLabel16.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel16.setText("Tổng số vé đã đặt: ");

        jLabel18.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel18.setText("Tổng vé đã hoàn thành:");

        jLabel19.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel19.setText("Tổng vé đang xác nhận:");

        jLabel22.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel22.setText("jLabel22");

        jLabel23.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel23.setText("jLabel23");

        jLabel24.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel24.setText("jLabel24");

        javax.swing.GroupLayout roundedPanel3Layout = new javax.swing.GroupLayout(roundedPanel3);
        roundedPanel3.setLayout(roundedPanel3Layout);
        roundedPanel3Layout.setHorizontalGroup(
            roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundedPanel3Layout.createSequentialGroup()
                        .addGroup(roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel16)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(jLabel24))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        roundedPanel3Layout.setVerticalGroup(
            roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27)
                .addGroup(roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel22))
                .addGap(44, 44, 44)
                .addGroup(roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jLabel24))
                .addGap(33, 33, 33))
        );

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        jLabel1.setText("Chi Tiết Đặt Vé");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Đang chờ xác nhận", "Thành công", "Đã Hủy" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 808, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 538, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout roundedPanel4Layout = new javax.swing.GroupLayout(roundedPanel4);
        roundedPanel4.setLayout(roundedPanel4Layout);
        roundedPanel4Layout.setHorizontalGroup(
            roundedPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(268, 268, 268))
            .addGroup(roundedPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(roundedPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        roundedPanel4Layout.setVerticalGroup(
            roundedPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel4Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(roundedPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(roundedPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roundedPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roundedPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(roundedPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundedPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(roundedPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(49, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        String selectedStatus = (String) jComboBox1.getSelectedItem();
    updateTicketPanel(selectedStatus);
    }//GEN-LAST:event_jComboBox1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JPanel jPanel1;
    private raven.application.form.other.RoundedPanel roundedPanel1;
    private raven.application.form.other.RoundedPanel roundedPanel2;
    private raven.application.form.other.RoundedPanel roundedPanel3;
    private raven.application.form.other.RoundedPanel roundedPanel4;
    // End of variables declaration//GEN-END:variables
}
