package raven.application.form.other;

import bll.XeService;
import com.formdev.flatlaf.FlatLaf;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.Xe;
import net.miginfocom.swing.MigLayout;
import raven.application.form.other.RoundedPanel;
import raven.toast.Notifications;

/**
 * Form quản lý chuyến xe với giao diện đẹp, tìm kiếm cải tiến, và kích thước cố định.
 */
public class ManageTripForm extends javax.swing.JPanel {
    private XeService xeService;
    private final String userId;
    private DefaultTableModel tableModel;
    private List<Xe> fullXeList; // Danh sách toàn bộ xe để lọc
    private static final String SEARCH_PLACEHOLDER = "Nhập tên xe, điểm đi/đến, hoặc mã tuyến...";

    public ManageTripForm(String userId) {
        this.userId = userId;
        if (!userId.startsWith("AD")) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Bạn không có quyền truy cập!");
            throw new SecurityException("Chỉ admin (mã bắt đầu bằng AD) được phép truy cập!");
        }
        initComponents();
        init();
        updatePanelColors();
    }

    private void init() {
        setLayout(new MigLayout("al center center, ins 20", "[grow]", "[grow]"));
        xeService = new XeService();

        // Thiết lập bảng
        tableModel = new DefaultTableModel(
            new Object[]{"Mã Xe", "Tên Xe", "Loại Xe", "Điểm Đi", "Điểm Đến", "Ngày", "Giờ Đi", "Giờ Đến", "Số Ghế", "Ghế Còn", "Giá Vé", "Mã Tuyến"},
            0
        );
        jTable1.setModel(tableModel);
        jTable1.setRowHeight(35);
        jTable1.setFont(new Font("SansSerif", Font.PLAIN, 14));
        jTable1.setShowGrid(false);
        jTable1.setOpaque(false);

        // Tùy chỉnh renderer cho bảng
        jTable1.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setFont(new Font("SansSerif", Font.PLAIN, 14));
                if (isSelected) {
                    c.setBackground(FlatLaf.isLafDark() ? new Color(90, 100, 110) : new Color(135, 206, 250));
                    c.setForeground(Color.WHITE);
                } else {
                    c.setBackground(row % 2 == 0 ? (FlatLaf.isLafDark() ? new Color(40, 50, 60) : Color.WHITE) : 
                        (FlatLaf.isLafDark() ? new Color(30, 40, 50) : new Color(245, 245, 245)));
                    c.setForeground(FlatLaf.isLafDark() ? Color.WHITE : Color.BLACK);
                }
                setHorizontalAlignment(CENTER);
                return c;
            }
        });

        // Tùy chỉnh header của bảng
        jTable1.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        jTable1.getTableHeader().setBackground(FlatLaf.isLafDark() ? new Color(70, 80, 90) : new Color(30, 144, 255));
        jTable1.getTableHeader().setForeground(Color.WHITE);
        jTable1.getTableHeader().setOpaque(false);

        loadXeData();

        // Hiệu ứng hover cho bảng
        jTable1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                jTable1.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                jTable1.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        // Lắng nghe chọn hàng
        jTable1.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && jTable1.getSelectedRow() >= 0) {
                displayXeDetails(jTable1.getSelectedRow());
            }
        });

        // Xử lý placeholder cho thanh tìm kiếm
        jTextFieldSearch.setText(SEARCH_PLACEHOLDER);
        jTextFieldSearch.setForeground(Color.GRAY);
        jTextFieldSearch.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (jTextFieldSearch.getText().equals(SEARCH_PLACEHOLDER)) {
                    jTextFieldSearch.setText("");
                    jTextFieldSearch.setForeground(FlatLaf.isLafDark() ? Color.WHITE : Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (jTextFieldSearch.getText().isEmpty()) {
                    jTextFieldSearch.setText(SEARCH_PLACEHOLDER);
                    jTextFieldSearch.setForeground(Color.GRAY);
                }
            }
        });

        // Sự kiện tìm kiếm
        jButtonSearch.addActionListener(e -> {
            if (jTextFieldSearch.getText().equals(SEARCH_PLACEHOLDER)) {
                jTextFieldSearch.setText("");
            }
            searchXe();
        });

        // Lắng nghe thay đổi giao diện
        javax.swing.UIManager.addPropertyChangeListener(evt -> {
            if ("lookAndFeel".equals(evt.getPropertyName())) {
                updatePanelColors();
            }
        });
    }

    private void loadXeData() {
        try {
            fullXeList = xeService.getAllXe();
            tableModel.setRowCount(0);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            for (Xe xe : fullXeList) {
                tableModel.addRow(new Object[]{
                    xe.getMaXe(), xe.getTenXe(), xe.getLoaiXe(), xe.getDiemDi(), xe.getDiemDen(),
                    xe.getNgayKhoiHanh() != null ? dateFormat.format(xe.getNgayKhoiHanh()) : "",
                    xe.getGioDi() != null ? timeFormat.format(xe.getGioDi()) : "",
                    xe.getGioDen() != null ? timeFormat.format(xe.getGioDen()) : "",
                    xe.getSoGhe(), xe.getGheConTrong(), xe.getGiaVe(), xe.getMaTuyen()
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Lỗi khi tải danh sách chuyến xe!");
        }
    }

    private void searchXe() {
        String keyword = jTextFieldSearch.getText().trim().toLowerCase();
        tableModel.setRowCount(0);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

        for (Xe xe : fullXeList) {
            if (keyword.isEmpty() ||
                xe.getTenXe().toLowerCase().contains(keyword) ||
                xe.getDiemDi().toLowerCase().contains(keyword) ||
                xe.getDiemDen().toLowerCase().contains(keyword) ||
                String.valueOf(xe.getMaTuyen()).contains(keyword)) {
                tableModel.addRow(new Object[]{
                    xe.getMaXe(), xe.getTenXe(), xe.getLoaiXe(), xe.getDiemDi(), xe.getDiemDen(),
                    xe.getNgayKhoiHanh() != null ? dateFormat.format(xe.getNgayKhoiHanh()) : "",
                    xe.getGioDi() != null ? timeFormat.format(xe.getGioDi()) : "",
                    xe.getGioDen() != null ? timeFormat.format(xe.getGioDen()) : "",
                    xe.getSoGhe(), xe.getGheConTrong(), xe.getGiaVe(), xe.getMaTuyen()
                });
            }
        }
        if (tableModel.getRowCount() == 0) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_RIGHT, "Không tìm thấy chuyến xe nào!");
        }
    }

    private void displayXeDetails(int row) {
        try {
            int maXe = (int) tableModel.getValueAt(row, 0);
            Xe xe = xeService.getXeByMaXe(maXe);
            if (xe != null) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
                jLabel16.setText(xe.getTenXe());
                jLabel17.setText(xe.getLoaiXe());
                jLabel18.setText(xe.getDiemDi());
                jLabel19.setText(xe.getDiemDen());
                jLabel20.setText(xe.getNgayKhoiHanh() != null ? dateFormat.format(xe.getNgayKhoiHanh()) : "");
                jLabel21.setText(xe.getGioDi() != null ? timeFormat.format(xe.getGioDi()) : "");
                jLabel22.setText(xe.getGioDen() != null ? timeFormat.format(xe.getGioDen()) : "");
                jLabel23.setText(String.valueOf(xe.getSoGhe()));
                jLabel24.setText(String.valueOf(xe.getGheConTrong()));
                jLabel25.setText(String.valueOf(xe.getGiaVe()));
                jLabel26.setText(String.valueOf(xe.getMaTuyen()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Lỗi khi tải chi tiết chuyến xe!");
        }
    }

    private void updatePanelColors() {
        if (FlatLaf.isLafDark()) {
            roundedPanel1.setBackground(new Color(50, 60, 70, 255));
            roundedPanel2.setBackground(new Color(30, 40, 50, 255));
            roundedPanel3.setBackground(new Color(30, 40, 50, 255));
            jButton1.setBackground(new Color(70, 80, 90, 255));
            jButtonSearch.setBackground(new Color(70, 80, 90, 255));
            jTextFieldSearch.setBackground(new Color(40, 50, 60));
            jTextFieldSearch.setForeground(jTextFieldSearch.getText().equals(SEARCH_PLACEHOLDER) ? Color.GRAY : Color.WHITE);
            jTable1.getTableHeader().setBackground(new Color(70, 80, 90));
            jTable1.setForeground(Color.WHITE);
        } else {
            roundedPanel1.setBackground(new Color(240, 248, 255));
            roundedPanel2.setBackground(new Color(255, 255, 255));
            roundedPanel3.setBackground(new Color(245, 245, 245));
            jButton1.setBackground(new Color(30, 144, 255, 255));
            jButtonSearch.setBackground(new Color(30, 144, 255, 255));
            jTextFieldSearch.setBackground(Color.WHITE);
            jTextFieldSearch.setForeground(jTextFieldSearch.getText().equals(SEARCH_PLACEHOLDER) ? Color.GRAY : Color.BLACK);
            jTable1.getTableHeader().setBackground(new Color(30, 144, 255));
            jTable1.setForeground(Color.BLACK);
        }
        revalidate();
        repaint();
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow < 0) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Vui lòng chọn một chuyến xe để xóa!");
            return;
        }
        int maXe = (int) tableModel.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(
            this, "Bạn có chắc chắn muốn xóa chuyến xe với Mã Xe " + maXe + "?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION
        );
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                boolean deleted = xeService.deleteXe(maXe);
                if (deleted) {
                    Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, "Xóa chuyến xe thành công!");
                    loadXeData();
                    clearDetails();
                } else {
                    Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Không thể xóa chuyến xe!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Lỗi khi xóa chuyến xe: " + e.getMessage());
            }
        }
    }

    private void clearDetails() {
        jLabel16.setText("");
        jLabel17.setText("");
        jLabel18.setText("");
        jLabel19.setText("");
        jLabel20.setText("");
        jLabel21.setText("");
        jLabel22.setText("");
        jLabel23.setText("");
        jLabel24.setText("");
        jLabel25.setText("");
        jLabel26.setText("");
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        roundedPanel1 = new RoundedPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                GradientPaint gp = FlatLaf.isLafDark() ?
                    new GradientPaint(0, 0, new Color(50, 60, 70), 0, getHeight(), new Color(70, 80, 90)) :
                    new GradientPaint(0, 0, new Color(240, 248, 255), 0, getHeight(), new Color(230, 240, 250));
                g2.setPaint(gp);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                super.paintComponent(g);
            }
        };
        jLabel1 = new javax.swing.JLabel();
        roundedPanel2 = new RoundedPanel();
        jTextFieldSearch = new javax.swing.JTextField();
        jButtonSearch = new javax.swing.JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                GradientPaint gp = FlatLaf.isLafDark() ?
                    new GradientPaint(0, 0, new Color(70, 80, 90), 0, getHeight(), new Color(90, 100, 110)) :
                    new GradientPaint(0, 0, new Color(30, 144, 255), 0, getHeight(), new Color(0, 120, 215));
                g2.setPaint(gp);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                super.paintComponent(g);
            }
            {
                addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        setCursor(new Cursor(Cursor.HAND_CURSOR));
                        setBackground(FlatLaf.isLafDark() ? new Color(90, 100, 110) : new Color(0, 120, 215));
                    }
                    @Override
                    public void mouseExited(MouseEvent e) {
                        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                        updatePanelColors();
                    }
                });
            }
        };
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                GradientPaint gp = FlatLaf.isLafDark() ?
                    new GradientPaint(0, 0, new Color(70, 80, 90), 0, getHeight(), new Color(90, 100, 110)) :
                    new GradientPaint(0, 0, new Color(30, 144, 255), 0, getHeight(), new Color(0, 120, 215));
                g2.setPaint(gp);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                super.paintComponent(g);
            }
            {
                addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        setCursor(new Cursor(Cursor.HAND_CURSOR));
                        setBackground(FlatLaf.isLafDark() ? new Color(90, 100, 110) : new Color(0, 120, 215));
                    }
                    @Override
                    public void mouseExited(MouseEvent e) {
                        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                        updatePanelColors();
                    }
                });
            }
        };
        roundedPanel3 = new RoundedPanel();
        jLabel2 = new javax.swing.JLabel();
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
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();

        // Tiêu đề chính
        ImageIcon busIcon = new ImageIcon("src/raven/icon/bus.png"); // Thay bằng đường dẫn thực tế
        jLabel1.setIcon(new ImageIcon(busIcon.getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)));
        jLabel1.setFont(new Font("SansSerif", Font.BOLD, 36));
        jLabel1.setText(" Quản Lý Chuyến Xe");
        jLabel1.setForeground(FlatLaf.isLafDark() ? Color.WHITE : new Color(30, 144, 255));

        // Thanh tìm kiếm
        jTextFieldSearch.setFont(new Font("SansSerif", Font.PLAIN, 14));
        jTextFieldSearch.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));

        jButtonSearch.setFont(new Font("SansSerif", Font.BOLD, 14));
        jButtonSearch.setText("Tìm kiếm");
        jButtonSearch.setIcon(new ImageIcon(new ImageIcon("src/raven/icon/search.png").getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
        jButtonSearch.setForeground(Color.WHITE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{},
            new String[]{"Mã Xe", "Tên Xe", "Loại Xe", "Điểm Đi", "Điểm Đến", "Ngày", "Giờ Đi", "Giờ Đến", "Số Ghế", "Ghế Còn", "Giá Vé", "Mã Tuyến"}
        ));
        jScrollPane1.setViewportView(jTable1);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(650, 446));

        jButton1.setFont(new Font("SansSerif", Font.BOLD, 14));
        jButton1.setText("Xóa Chuyến Xe");
        jButton1.setIcon(new ImageIcon(new ImageIcon("src/raven/icon/tickets.png").getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
        jButton1.setForeground(Color.WHITE);
        jButton1.addActionListener(evt -> jButton1ActionPerformed(evt));

        jLabel2.setFont(new Font("SansSerif", Font.BOLD, 16));
        jLabel2.setText("Chi Tiết Chuyến Xe");
        jLabel2.setForeground(FlatLaf.isLafDark() ? Color.WHITE : Color.DARK_GRAY);

        jLabel3.setFont(new Font("SansSerif", Font.BOLD, 14));
        jLabel3.setText("Tên Xe:");
        jLabel3.setIcon(new ImageIcon(new ImageIcon("src/raven/icon/bus.png").getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
        jLabel4.setFont(new Font("SansSerif", Font.BOLD, 14));
        jLabel4.setText("Loại Xe:");
        jLabel5.setFont(new Font("SansSerif", Font.BOLD, 14));
        jLabel5.setText("Điểm Đi:");
        jLabel5.setIcon(new ImageIcon(new ImageIcon("src/raven/icon/location.png").getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
        jLabel6.setFont(new Font("SansSerif", Font.BOLD, 14));
        jLabel6.setText("Điểm Đến:");
        jLabel6.setIcon(new ImageIcon(new ImageIcon("src/raven/icon/location.png").getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
        jLabel7.setFont(new Font("SansSerif", Font.BOLD, 14));
        jLabel7.setText("Ngày Khởi Hành:");
        jLabel7.setIcon(new ImageIcon(new ImageIcon("src/raven/icon/calendar.png").getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
        jLabel8.setFont(new Font("SansSerif", Font.BOLD, 14));
        jLabel8.setText("Giờ Đi:");
        jLabel9.setFont(new Font("SansSerif", Font.BOLD, 14));
        jLabel9.setText("Giờ Đến:");
        jLabel10.setFont(new Font("SansSerif", Font.BOLD, 14));
        jLabel10.setText("Số Ghế:");
        jLabel11.setFont(new Font("SansSerif", Font.BOLD, 14));
        jLabel11.setText("Ghế Còn Trống:");
        jLabel12.setFont(new Font("SansSerif", Font.BOLD, 14));
        jLabel12.setText("Giá Vé:");
        jLabel13.setFont(new Font("SansSerif", Font.BOLD, 14));
        jLabel13.setText("Mã Tuyến:");

        jLabel16.setFont(new Font("SansSerif", Font.PLAIN, 14));
        jLabel16.setText("");
        jLabel17.setFont(new Font("SansSerif", Font.PLAIN, 14));
        jLabel17.setText("");
        jLabel18.setFont(new Font("SansSerif", Font.PLAIN, 14));
        jLabel18.setText("");
        jLabel19.setFont(new Font("SansSerif", Font.PLAIN, 14));
        jLabel19.setText("");
        jLabel20.setFont(new Font("SansSerif", Font.PLAIN, 14));
        jLabel20.setText("");
        jLabel21.setFont(new Font("SansSerif", Font.PLAIN, 14));
        jLabel21.setText("");
        jLabel22.setFont(new Font("SansSerif", Font.PLAIN, 14));
        jLabel22.setText("");
        jLabel23.setFont(new Font("SansSerif", Font.PLAIN, 14));
        jLabel23.setText("");
        jLabel24.setFont(new Font("SansSerif", Font.PLAIN, 14));
        jLabel24.setText("");
        jLabel25.setFont(new Font("SansSerif", Font.PLAIN, 14));
        jLabel25.setText("");
        jLabel26.setFont(new Font("SansSerif", Font.PLAIN, 14));
        jLabel26.setText("");

        javax.swing.GroupLayout roundedPanel3Layout = new javax.swing.GroupLayout(roundedPanel3);
        roundedPanel3.setLayout(roundedPanel3Layout);
        roundedPanel3Layout.setHorizontalGroup(
            roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(roundedPanel3Layout.createSequentialGroup()
                        .addGroup(roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addGap(10, 10, 10)
                        .addGroup(roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19)
                            .addComponent(jLabel20)
                            .addComponent(jLabel21)
                            .addComponent(jLabel22)
                            .addComponent(jLabel23)
                            .addComponent(jLabel24)
                            .addComponent(jLabel25)
                            .addComponent(jLabel26))))
                .addContainerGap(150, Short.MAX_VALUE))
        );
        roundedPanel3Layout.setVerticalGroup(
            roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel3)
                    .addComponent(jLabel16))
                .addGap(18, 18, 18)
                .addGroup(roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel4)
                    .addComponent(jLabel17))
                .addGap(18, 18, 18)
                .addGroup(roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel5)
                    .addComponent(jLabel18))
                .addGap(18, 18, 18)
                .addGroup(roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel6)
                    .addComponent(jLabel19))
                .addGap(18, 18, 18)
                .addGroup(roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel7)
                    .addComponent(jLabel20))
                .addGap(18, 18, 18)
                .addGroup(roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel8)
                    .addComponent(jLabel21))
                .addGap(18, 18, 18)
                .addGroup(roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel9)
                    .addComponent(jLabel22))
                .addGap(18, 18, 18)
                .addGroup(roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel10)
                    .addComponent(jLabel23))
                .addGap(18, 18, 18)
                .addGroup(roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel11)
                    .addComponent(jLabel24))
                .addGap(18, 18, 18)
                .addGroup(roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel12)
                    .addComponent(jLabel25))
                .addGap(18, 18, 18)
                .addGroup(roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel13)
                    .addComponent(jLabel26))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        roundedPanel3.setPreferredSize(new java.awt.Dimension(350, 510));

        javax.swing.GroupLayout roundedPanel2Layout = new javax.swing.GroupLayout(roundedPanel2);
        roundedPanel2.setLayout(roundedPanel2Layout);
        roundedPanel2Layout.setHorizontalGroup(
            roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(roundedPanel2Layout.createSequentialGroup()
                        .addComponent(jTextFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roundedPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        roundedPanel2Layout.setVerticalGroup(
            roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roundedPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(roundedPanel2Layout.createSequentialGroup()
                        .addGroup(roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        roundedPanel2.setPreferredSize(new java.awt.Dimension(1020, 518));

        javax.swing.GroupLayout roundedPanel1Layout = new javax.swing.GroupLayout(roundedPanel1);
        roundedPanel1.setLayout(roundedPanel1Layout);
        roundedPanel1Layout.setHorizontalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roundedPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.CENTER, roundedPanel1Layout.createSequentialGroup()
                .addGap(300, 300, 300)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        roundedPanel1Layout.setVerticalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(roundedPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        roundedPanel1.setPreferredSize(new java.awt.Dimension(1050, 590));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(roundedPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE)
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(roundedPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }

    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonSearch;
    private javax.swing.JTextField jTextFieldSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
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
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private RoundedPanel roundedPanel1;
    private RoundedPanel roundedPanel2;
    private RoundedPanel roundedPanel3;
}