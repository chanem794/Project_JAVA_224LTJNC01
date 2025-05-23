/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package raven.application.form.other;

import bll.TuyenService;
import bll.XeService;
import com.formdev.flatlaf.FlatLaf;
import com.raven.datechooser.DateChooser;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPopupMenu;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import model.Tuyen;
import model.Xe;
import net.miginfocom.swing.MigLayout;
import raven.toast.Notifications;

/**
 *
 * @author Admin
 */
public class RegisterForTheTripForm extends javax.swing.JPanel {
    private TuyenService tuyenService;
    private XeService xeService;
    private DateChooser dateChooser5;
    private Integer selectedMaTuyen = null;
    
    /**
     * Creates new form RegisterForTheTripForm
     */
    public RegisterForTheTripForm() {
        initComponents();
        init();
        updatePanelColors();
    }
    private void init() {
        setLayout(new MigLayout("al center center"));
        tuyenService = new TuyenService();
        xeService = new XeService();

        // Thiết lập icon và thuộc tính
        ImageIcon iconLabel3 = new ImageIcon(getClass().getResource("/raven/icon/png/exclamation.png"));
        Image scaledIcon3 = iconLabel3.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        jLabel3.setIcon(new ImageIcon(scaledIcon3));
        jLabel3.setIconTextGap(5);
        jLabel3.setHorizontalTextPosition(SwingConstants.RIGHT);
        jLabel3.setVerticalTextPosition(SwingConstants.CENTER);

        ImageIcon iconLabel7 = new ImageIcon(getClass().getResource("/raven/icon/png/destination.png"));
        Image scaledIcon7 = iconLabel7.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        jLabel7.setIcon(new ImageIcon(scaledIcon7));
        jLabel7.setIconTextGap(5);
        jLabel7.setHorizontalTextPosition(SwingConstants.RIGHT);
        jLabel7.setVerticalTextPosition(SwingConstants.CENTER);

        ImageIcon iconLabel9 = new ImageIcon(getClass().getResource("/raven/icon/png/getHigh.png"));
        Image scaledIcon9 = iconLabel9.getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH);
        jLabel9.setIcon(new ImageIcon(scaledIcon9));
        jLabel9.setIconTextGap(5);
        jLabel9.setHorizontalTextPosition(SwingConstants.RIGHT);
        jLabel9.setVerticalTextPosition(SwingConstants.CENTER);

        ImageIcon iconLabel11 = new ImageIcon(getClass().getResource("/raven/icon/png/record-button.png"));
        Image scaledIcon11 = iconLabel11.getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH);
        jLabel11.setIcon(new ImageIcon(scaledIcon11));
        jLabel11.setIconTextGap(5);
        jLabel11.setHorizontalTextPosition(SwingConstants.RIGHT);
        jLabel11.setVerticalTextPosition(SwingConstants.CENTER);

        ImageIcon iconLabel23 = new ImageIcon(getClass().getResource("/raven/icon/png/exclamation.png"));
        Image scaledIcon23 = iconLabel23.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        jLabel23.setIcon(new ImageIcon(scaledIcon23));
        jLabel23.setIconTextGap(5);
        jLabel23.setHorizontalTextPosition(SwingConstants.RIGHT);
        jLabel23.setVerticalTextPosition(SwingConstants.CENTER);

        // Thêm ảnh vào jPanel1
        jPanel1.setLayout(new java.awt.BorderLayout());
        JLabel imageLabel1 = new JLabel();
        ImageIcon iconPanel1 = new ImageIcon(getClass().getResource("/raven/icon/png/t1.png"));
        Image scaledImage1 = iconPanel1.getImage().getScaledInstance(193, 287, Image.SCALE_SMOOTH);
        imageLabel1.setIcon(new ImageIcon(scaledImage1));
        imageLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jPanel1.add(imageLabel1, java.awt.BorderLayout.CENTER);

        // Thêm ảnh vào jPanel3
        jPanel3.setLayout(new java.awt.BorderLayout());
        JLabel imageLabel3 = new JLabel();
        ImageIcon iconPanel3 = new ImageIcon(getClass().getResource("/raven/icon/png/images.jpg"));
        Image scaledImage3 = iconPanel3.getImage().getScaledInstance(193, 183, Image.SCALE_SMOOTH);
        imageLabel3.setIcon(new ImageIcon(scaledImage3));
        imageLabel3.setHorizontalAlignment(SwingConstants.CENTER);
        jPanel3.add(imageLabel3, java.awt.BorderLayout.CENTER);

        // Đọc dữ liệu từ file "Tỉnh, Huyện.txt" để làm gợi ý
        List<String> diemDiList = new ArrayList<>();
        List<String> diemDenList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("Tỉnh, Huyện.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    diemDiList.add(line);
                    diemDenList.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            diemDiList.add("Error loading data from file");
            diemDenList.add("Error loading data from file");
        }

        // Cài đặt model cho jComboBox1 và jComboBox2
        jComboBox1.setModel(new DefaultComboBoxModel<>(diemDiList.toArray(new String[0])));
        jComboBox2.setModel(new DefaultComboBoxModel<>(diemDenList.toArray(new String[0])));
        if (!diemDiList.isEmpty()) jComboBox1.setSelectedIndex(0);
        if (!diemDenList.isEmpty()) jComboBox2.setSelectedIndex(0);

        // Thiết lập kích thước cố định cho jComboBox1 và jComboBox2
        jComboBox1.setMinimumSize(new java.awt.Dimension(154, 41));
        jComboBox1.setPreferredSize(new java.awt.Dimension(154, 41));
        jComboBox1.setMaximumSize(new java.awt.Dimension(154, 41));
        jComboBox2.setMinimumSize(new java.awt.Dimension(154, 41));
        jComboBox2.setPreferredSize(new java.awt.Dimension(154, 41));
        jComboBox2.setMaximumSize(new java.awt.Dimension(154, 41));

        // Thiết lập gợi ý tự động
        setupAutoComplete(jComboBox1, diemDiList);
        setupAutoComplete(jComboBox2, diemDenList);

        // Thiết lập DateChooser cho jTextField5
        dateChooser5 = new DateChooser();
        jTextField5.setText("DD/MM/YYYY");
        jTextField5.setFont(new java.awt.Font("SansSerif", 1, 14));
        dateChooser5.setTextField(jTextField5);
        dateChooser5.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
        jTextField5.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (jTextField5.getText().equals("DD/MM/YYYY")) {
                    jTextField5.setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (jTextField5.getText().isEmpty()) {
                    jTextField5.setText("DD/MM/YYYY");
                }
            }
        });

        // Thiết lập placeholder và bộ chọn giờ cho jTextField6 (Giờ đi)
        jTextField6.setText("Nhập thời gian đi");
        jTextField6.setForeground(Color.GRAY);
        setupTimePicker(jTextField6, "Giờ đi");

        // Thiết lập placeholder và bộ chọn giờ cho jTextField7 (Giờ đến)
        jTextField7.setText("Nhập thời gian đến");
        jTextField7.setForeground(Color.GRAY);
        setupTimePicker(jTextField7, "Giờ đến");

        // Đảm bảo jTextField6 và jTextField7 hiển thị
        jTextField6.setVisible(true);
        jTextField7.setVisible(true);

        // Đặt giá trị mặc định cho jLabel16 và jLabel17
        jLabel16.setText("");
        jLabel17.setText("");

        // Thiết lập placeholder cho các trường nhập liệu khác
        jTextField3.setText("Nhập tên xe");
        jTextField3.setForeground(Color.GRAY);
        jTextField3.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (jTextField3.getText().equals("Nhập tên xe")) {
                    jTextField3.setText("");
                    jTextField3.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (jTextField3.getText().isEmpty()) {
                    jTextField3.setText("Nhập tên xe");
                    jTextField3.setForeground(Color.GRAY);
                }
            }
        });

        jTextField4.setText("Nhập loại xe");
        jTextField4.setForeground(Color.GRAY);
        jTextField4.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (jTextField4.getText().equals("Nhập loại xe")) {
                    jTextField4.setText("");
                    jTextField4.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (jTextField4.getText().isEmpty()) {
                    jTextField4.setText("Nhập loại xe");
                    jTextField4.setForeground(Color.GRAY);
                }
            }
        });

        jTextField8.setText("Nhập số ghế");
        jTextField8.setForeground(Color.GRAY);
        jTextField8.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (jTextField8.getText().equals("Nhập số ghế")) {
                    jTextField8.setText("");
                    jTextField8.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (jTextField8.getText().isEmpty()) {
                    jTextField8.setText("Nhập số ghế");
                    jTextField8.setForeground(Color.GRAY);
                }
            }
        });

        jTextField9.setText("Nhập giá vé");
        jTextField9.setForeground(Color.GRAY);
        jTextField9.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (jTextField9.getText().equals("Nhập giá vé")) {
                    jTextField9.setText("");
                    jTextField9.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (jTextField9.getText().isEmpty()) {
                    jTextField9.setText("Nhập giá vé");
                    jTextField9.setForeground(Color.GRAY);
                }
            }
        });

        // Thêm lắng nghe sự kiện thay đổi giao diện
        UIManager.addPropertyChangeListener(evt -> {
            if ("lookAndFeel".equals(evt.getPropertyName())) {
                updatePanelColors();
            }
        });
    }

    private void updatePanelColors() {
        if (FlatLaf.isLafDark()) {
            // Màu xám cho dark mode
            roundedPanel1.setBackground(new Color(79, 92, 104, 255));
            roundedPanel2.setBackground(new Color(49, 62, 74, 255));
            roundedPanel3.setBackground(new Color(51, 62, 74, 255));
            roundedPanel4.setBackground(new Color(49, 62, 74, 255));
            roundedPanel5.setBackground(new Color(49, 62, 74, 255));
            roundedPanel6.setBackground(new Color(79, 92, 104, 255));
            roundedPanel7.setBackground(new Color(49, 62, 74, 255));
            jPanel2.setBackground(new Color(79, 92, 104, 255));
            jButton1.setBackground(new Color(49, 62, 74, 255));
            jButton2.setBackground(new Color(79, 92, 104, 255));
        } else {
            // Màu trắng cho light mode
            roundedPanel1.setBackground(new Color(255, 255, 255));
            roundedPanel2.setBackground(new Color(255, 255, 255));
            roundedPanel3.setBackground(new Color(255, 255, 255));
            roundedPanel4.setBackground(new Color(255, 255, 255));
            roundedPanel5.setBackground(new Color(230, 230, 230));
            roundedPanel6.setBackground(new Color(255, 255, 255));
            roundedPanel7.setBackground(new Color(255, 255, 255));
            jPanel2.setBackground(new Color(255, 255, 255));
            jButton1.setBackground(new Color(255, 149, 0));
            jButton2.setBackground(new Color(36, 116, 229, 255));
        }
        // Cập nhật giao diện
        revalidate();
        repaint();
    }

    private boolean isUpdating = false; // Biến cờ để tránh vòng lặp cập nhật

    private void setupAutoComplete(JComboBox<String> comboBox, List<String> items) {
        comboBox.setEditable(true);
        JTextField textField = (JTextField) comboBox.getEditor().getEditorComponent();
        if (comboBox.getSelectedItem() != null) {
            textField.setText(comboBox.getSelectedItem().toString());
        }

        textField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) { updateSuggestions(); }
            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) { updateSuggestions(); }
            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) { updateSuggestions(); }

            private void updateSuggestions() {
                if (isUpdating) return; // Tránh vòng lặp cập nhật
                isUpdating = true;

                SwingUtilities.invokeLater(() -> {
                    try {
                        String input = textField.getText();
                        if (input.isEmpty()) {
                            comboBox.setModel(new DefaultComboBoxModel<>(items.toArray(new String[0])));
                            comboBox.setPopupVisible(false);
                            comboBox.setSelectedIndex(-1);
                            return;
                        }
                        List<String> matchedItems = new ArrayList<>();
                        for (String item : items) {
                            if (normalizeString(item).toLowerCase().contains(normalizeString(input).toLowerCase())) {
                                matchedItems.add(item);
                            }
                        }
                        comboBox.setModel(new DefaultComboBoxModel<>(matchedItems.toArray(new String[0])));
                        comboBox.setSelectedItem(input); // Giữ nguyên nội dung đang nhập
                        comboBox.setPopupVisible(!input.isEmpty() && !matchedItems.isEmpty());
                    } finally {
                        isUpdating = false; // Đặt lại cờ sau khi hoàn tất
                    }
                });
            }
        });

        comboBox.addActionListener(e -> {
            if (!isUpdating && comboBox.getSelectedItem() != null) {
                isUpdating = true;
                try {
                    textField.setText(comboBox.getSelectedItem().toString());
                    comboBox.setPopupVisible(false);
                } finally {
                    isUpdating = false;
                }
            }
        });

        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                comboBox.setPopupVisible(false);
            }
        });
    }

    private String normalizeString(String str) {
        if (str == null) return "";
        String normalized = java.text.Normalizer.normalize(str, java.text.Normalizer.Form.NFD);
        return normalized.replaceAll("\\p{M}", "").replace("đ", "d").replace("Đ", "D");
}
    private void setupTimePicker(JTextField textField, String title) {
        JPopupMenu popup = new JPopupMenu();
        SpinnerDateModel model = new SpinnerDateModel();
        JSpinner spinner = new JSpinner(model);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "HH:mm");
        spinner.setEditor(editor);
        spinner.setValue(new Date()); // Giá trị mặc định là thời gian hiện tại
        popup.add(spinner);

        textField.setEditable(false); // Không cho phép nhập tay
        textField.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (textField.getText().equals("Nhập thời gian đi") || textField.getText().equals("Nhập thời gian đến")) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
                popup.show(textField, 0, textField.getHeight());
            }
        });

        // Xử lý thay đổi thời gian
        spinner.addChangeListener(e -> {
            Date selectedTime = (Date) spinner.getValue();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            String timeStr = sdf.format(selectedTime);

            if (textField == jTextField6) { // Giờ đi
                jTextField6.setText(timeStr);
                // Kiểm tra và cập nhật giờ đến nếu cần
                if (!jTextField7.getText().isEmpty() && !jTextField7.getText().equals("Nhập thời gian đến")) {
                    try {
                        Date currentArrival = sdf.parse(jTextField7.getText());
                        Date currentDeparture = sdf.parse(timeStr);
                        long diff = currentArrival.getTime() - currentDeparture.getTime();
                        if (diff < 30 * 60 * 1000) { // Ít hơn 30 phút
                            Date newArrival = new Date(currentDeparture.getTime() + 30 * 60 * 1000);
                            jTextField7.setText(sdf.format(newArrival));
                        }
                    } catch (ParseException ex) {
                        Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Lỗi xử lý thời gian!");
                    }
                }
            } else if (textField == jTextField7) { // Giờ đến
                if (!jTextField6.getText().isEmpty() && !jTextField6.getText().equals("Nhập thời gian đi")) {
                    try {
                        Date departureTime = sdf.parse(jTextField6.getText());
                        Date arrivalTime = sdf.parse(timeStr);
                        long diff = arrivalTime.getTime() - departureTime.getTime();
                        if (diff < 30 * 60 * 1000) { // Ít hơn 30 phút
                            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Giờ đến phải sau giờ đi ít nhất 30 phút!");
                            Date newArrival = new Date(departureTime.getTime() + 30 * 60 * 1000);
                            jTextField7.setText(sdf.format(newArrival));
                            return;
                        }
                    } catch (ParseException ex) {
                        Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Lỗi xử lý thời gian!");
                    }
                }
                jTextField7.setText(timeStr);
            }
            popup.setVisible(false);
        });

        // Xử lý khi mất focus
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    if (textField == jTextField6) {
                        textField.setText("Nhập thời gian đi");
                    } else if (textField == jTextField7) {
                        textField.setText("Nhập thời gian đến");
                    }
                    textField.setForeground(Color.GRAY);
                }
            }
        });
    }
    
    private int generateNewMaXe() throws SQLException {
        int maxMaXe = xeService.getMaxMaXe();
        return maxMaXe + 1;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundedPanel1 = new raven.application.form.other.RoundedPanel();
        jLabel2 = new javax.swing.JLabel();
        roundedPanel3 = new raven.application.form.other.RoundedPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        roundedPanel2 = new raven.application.form.other.RoundedPanel();
        jPanel1 = new javax.swing.JPanel();
        roundedPanel4 = new raven.application.form.other.RoundedPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        roundedPanel5 = new raven.application.form.other.RoundedPanel();
        roundedPanel6 = new raven.application.form.other.RoundedPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        roundedPanel7 = new raven.application.form.other.RoundedPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jRadioButton1 = new javax.swing.JRadioButton();
        jButton2 = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        jLabel2.setText("Đăng Ký Chuyến Xe");

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel1.setText("Vui lòng điền ĐẦY ĐỦ và CHÍNH XÁC thông tin bên dưới:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 193, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 287, Short.MAX_VALUE)
        );

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel5.setText("chuyến xe của BẠN");

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel4.setText(" Hình ảnh minh họa cho");

        javax.swing.GroupLayout roundedPanel4Layout = new javax.swing.GroupLayout(roundedPanel4);
        roundedPanel4.setLayout(roundedPanel4Layout);
        roundedPanel4Layout.setHorizontalGroup(
            roundedPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel4Layout.createSequentialGroup()
                .addGroup(roundedPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4))
                    .addGroup(roundedPanel4Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel5)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        roundedPanel4Layout.setVerticalGroup(
            roundedPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel6.setText("Tìm kiếm tuyến:");

        jLabel8.setText("Điểm đi:");

        jLabel10.setText("Điểm đến:");

        jButton1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jButton1.setText("Tìm Kiếm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout roundedPanel6Layout = new javax.swing.GroupLayout(roundedPanel6);
        roundedPanel6.setLayout(roundedPanel6Layout);
        roundedPanel6Layout.setHorizontalGroup(
            roundedPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel6Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundedPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addGroup(roundedPanel6Layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(roundedPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(roundedPanel6Layout.createSequentialGroup()
                        .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))))
        );
        roundedPanel6Layout.setVerticalGroup(
            roundedPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundedPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(roundedPanel6Layout.createSequentialGroup()
                        .addGroup(roundedPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10))
                        .addGroup(roundedPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(roundedPanel6Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(roundedPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(roundedPanel6Layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addGroup(roundedPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel6Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(roundedPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(roundedPanel6Layout.createSequentialGroup()
                        .addGroup(roundedPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jLabel12.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel12.setText("Nhập tên xe:");

        jLabel13.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel13.setText("Nhập loại xe:");

        jLabel14.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel14.setText("Điểm đi:");

        jLabel15.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel15.setText("Điểm đến:");

        jTextField3.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jTextField3.setText("jTextField3");

        jTextField4.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jTextField4.setText("jTextField4");

        jLabel16.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel16.setText("jLabel16");

        jLabel17.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel17.setText("jLabel17");

        jLabel18.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel18.setText("Ngày khởi hành:");

        jLabel19.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel19.setText("Giờ đi:");

        jLabel20.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel20.setText("Giờ đến:");

        jTextField5.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jTextField5.setText("jTextField5");

        jTextField6.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jTextField6.setText("jTextField6");

        jTextField7.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jTextField7.setText("jTextField7");

        jLabel21.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel21.setText("Số ghế:");

        jTextField8.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jTextField8.setText("jTextField8");
        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel22.setText("Đăng ký tiền vé:");

        jLabel24.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel24.setText("Lưu ý:");

        jLabel25.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel25.setText("Không được bán vé sai ");

        jLabel26.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel26.setText("lệch so với giá niêm yết");

        jLabel27.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel27.setText("Nhập giá vé:");

        jTextField9.setFont(new java.awt.Font("SansSerif", 1, 22)); // NOI18N
        jTextField9.setText("jTextField9");

        jRadioButton1.setFont(new java.awt.Font("SansSerif", 3, 12)); // NOI18N
        jRadioButton1.setText("Tôi cam kết đã kiểm tra chính xác thông tin");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jButton2.setText("Đăng Ký Chuyến Xe");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel28.setText("Mã tuyến:");

        javax.swing.GroupLayout roundedPanel7Layout = new javax.swing.GroupLayout(roundedPanel7);
        roundedPanel7.setLayout(roundedPanel7Layout);
        roundedPanel7Layout.setHorizontalGroup(
            roundedPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel7Layout.createSequentialGroup()
                .addGroup(roundedPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedPanel7Layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(jLabel22))
                    .addGroup(roundedPanel7Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(roundedPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton1)
                            .addGroup(roundedPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel26)
                                .addGroup(roundedPanel7Layout.createSequentialGroup()
                                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel25))
                                .addComponent(jLabel27)
                                .addComponent(jTextField9))
                            .addComponent(jButton2)
                            .addGroup(roundedPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel28)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel29)))))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        roundedPanel7Layout.setVerticalGroup(
            roundedPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundedPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel26)
                .addGap(40, 40, 40)
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(roundedPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jLabel29))
                .addGap(32, 32, 32)
                .addComponent(jRadioButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel15)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14)
                            .addComponent(jLabel19)
                            .addComponent(jLabel20)
                            .addComponent(jLabel21))
                        .addGap(101, 101, 101)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField3)
                            .addComponent(jTextField6)
                            .addComponent(jTextField4)
                            .addComponent(jTextField7)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17)
                                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16)))))
                    .addComponent(jLabel18))
                .addGap(18, 18, 18)
                .addComponent(roundedPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roundedPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(jLabel16))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jLabel17))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout roundedPanel5Layout = new javax.swing.GroupLayout(roundedPanel5);
        roundedPanel5.setLayout(roundedPanel5Layout);
        roundedPanel5Layout.setHorizontalGroup(
            roundedPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel5Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(roundedPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(roundedPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        roundedPanel5Layout.setVerticalGroup(
            roundedPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel5Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(roundedPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 193, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout roundedPanel2Layout = new javax.swing.GroupLayout(roundedPanel2);
        roundedPanel2.setLayout(roundedPanel2Layout);
        roundedPanel2Layout.setHorizontalGroup(
            roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel2Layout.createSequentialGroup()
                .addComponent(roundedPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundedPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        roundedPanel2Layout.setVerticalGroup(
            roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roundedPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(roundedPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundedPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout roundedPanel3Layout = new javax.swing.GroupLayout(roundedPanel3);
        roundedPanel3.setLayout(roundedPanel3Layout);
        roundedPanel3Layout.setHorizontalGroup(
            roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(roundedPanel3Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(roundedPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(roundedPanel3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        roundedPanel3Layout.setVerticalGroup(
            roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roundedPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout roundedPanel1Layout = new javax.swing.GroupLayout(roundedPanel1);
        roundedPanel1.setLayout(roundedPanel1Layout);
        roundedPanel1Layout.setHorizontalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roundedPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(372, 372, 372))
        );
        roundedPanel1Layout.setVerticalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(roundedPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(200, 200, 200)
                .addComponent(roundedPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(293, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundedPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed

        // Không cần xử lý logic tạo mới ở đây, chỉ để xác nhận
        if (!jRadioButton1.isSelected()) return;
        Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_RIGHT, "Vui lòng nhấn 'Đăng Ký Chuyến Xe' để hoàn tất!");

    }//GEN-LAST:event_jRadioButton1ActionPerformed
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            // Lấy dữ liệu từ các trường nhập liệu
            String tenXe = jTextField3.getText().trim();
            String loaiXe = jTextField4.getText().trim();
            String ngayKhoiHanhStr = jTextField5.getText().trim();
            String gioDiStr = jTextField6.getText().trim();
            String gioDenStr = jTextField7.getText().trim();
            String soGheStr = jTextField8.getText().trim();
            String giaVeStr = jTextField9.getText().trim();

            // Kiểm tra dữ liệu đầu vào
            if (tenXe.equals("Nhập tên xe") || loaiXe.equals("Nhập loại xe") || ngayKhoiHanhStr.equals("DD/MM/YYYY") || 
                gioDiStr.equals("Nhập thời gian đi") || gioDenStr.equals("Nhập thời gian đến") || soGheStr.equals("Nhập số ghế") || giaVeStr.equals("Nhập giá vé")) {
                Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Vui lòng điền đầy đủ thông tin!");
                return;
            }
            if (selectedMaTuyen == null) {
                Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Vui lòng tìm tuyến trước khi đăng ký!");
                return;
            }

            // Chuyển đổi dữ liệu
            Date ngayKhoiHanh = null;
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                ngayKhoiHanh = sdf.parse(ngayKhoiHanhStr);
            } catch (ParseException e) {
                Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Ngày khởi hành không hợp lệ!");
                return;
            }

            java.sql.Time gioDi = null;
            java.sql.Time gioDen = null;
            try {
                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
                Date gioDiDate = timeFormat.parse(gioDiStr);
                Date gioDenDate = timeFormat.parse(gioDenStr);
                gioDi = new java.sql.Time(gioDiDate.getTime());
                gioDen = new java.sql.Time(gioDenDate.getTime());
            } catch (ParseException e) {
                Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Giờ đi hoặc giờ đến không hợp lệ!");
                return;
            }

            int soGhe;
            int giaVe;
            try {
                soGhe = Integer.parseInt(soGheStr);
                giaVe = Integer.parseInt(giaVeStr);
                if (soGhe <= 0 || giaVe <= 0) {
                    Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Số ghế và giá vé phải lớn hơn 0!");
                    return;
                }
            } catch (NumberFormatException e) {
                Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Số ghế hoặc giá vé không hợp lệ!");
                return;
            }

            // Tạo đối tượng Xe
            Xe xe = new Xe();
            xe.setTenXe(tenXe);
            xe.setLoaiXe(loaiXe);
            xe.setDiemDi(jComboBox1.getSelectedItem().toString());
            xe.setDiemDen(jComboBox2.getSelectedItem().toString());
            xe.setMaTuyen(selectedMaTuyen);
            xe.setNgayKhoiHanh(new java.sql.Date(ngayKhoiHanh.getTime()));
            xe.setGioDi(gioDi);
            xe.setGioDen(gioDen);
            xe.setSoGhe(soGhe);
            xe.setGheConTrong(soGhe);
            xe.setGiaVe(giaVe);
            xe.setMaXe(generateNewMaXe());

            // Lưu vào cơ sở dữ liệu
            System.out.println("Đang lưu xe: MaXe=" + xe.getMaXe() + ", MaTuyen=" + xe.getMaTuyen() + ", DiemDi=" + xe.getDiemDi() + ", DiemDen=" + xe.getDiemDen());
            xeService.createXe(xe);
            Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, "Đăng ký chuyến xe thành công!");

            // Reset form
            jTextField3.setText("Nhập tên xe");
            jTextField3.setForeground(Color.GRAY);
            jTextField4.setText("Nhập loại xe");
            jTextField4.setForeground(Color.GRAY);
            jTextField5.setText("DD/MM/YYYY");
            jTextField6.setText("Nhập thời gian đi");
            jTextField6.setForeground(Color.GRAY);
            jTextField7.setText("Nhập thời gian đến");
            jTextField7.setForeground(Color.GRAY);
            jTextField8.setText("Nhập số ghế");
            jTextField8.setForeground(Color.GRAY);
            jTextField9.setText("Nhập giá vé");
            jTextField9.setForeground(Color.GRAY);
            jComboBox1.setSelectedIndex(0);
            jComboBox2.setSelectedIndex(0);
            jLabel16.setText("");
            jLabel17.setText("");
            selectedMaTuyen = null;

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Lỗi SQL: " + e.getMessage());
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Lỗi khi lưu chuyến xe: " + e.getMessage());
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String diemDi = jComboBox1.getSelectedItem() != null ? jComboBox1.getSelectedItem().toString() : "";
        String diemDen = jComboBox2.getSelectedItem() != null ? jComboBox2.getSelectedItem().toString() : "";
        try {
            List<Tuyen> tuyenList = tuyenService.getAllTuyen();
            selectedMaTuyen = null;
            for (Tuyen tuyen : tuyenList) {
                if (tuyen.getDiemDi().equals(diemDi) && tuyen.getDiemDen().equals(diemDen)) {
                    selectedMaTuyen = tuyen.getMaTuyen();
                    break;
                }
            }
            if (selectedMaTuyen != null) {
                jLabel16.setText(diemDi); // Hiển thị điểm đi
                jLabel17.setText(diemDen); // Hiển thị điểm đến
                jLabel29.setText(String.valueOf(selectedMaTuyen)); // Hiển thị mã tuyến
                Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, "Tìm thấy tuyến: " + diemDi + " -> " + diemDen);
            } else {
                jLabel16.setText("");
                jLabel17.setText("");
                jLabel29.setText(""); // Đặt lại mã tuyến nếu không tìm thấy
                Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Không tìm thấy tuyến phù hợp!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            jLabel16.setText("");
            jLabel17.setText("");
            jLabel29.setText(""); // Đặt lại mã tuyến nếu có lỗi
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Lỗi khi tìm tuyến!");
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
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
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private raven.application.form.other.RoundedPanel roundedPanel1;
    private raven.application.form.other.RoundedPanel roundedPanel2;
    private raven.application.form.other.RoundedPanel roundedPanel3;
    private raven.application.form.other.RoundedPanel roundedPanel4;
    private raven.application.form.other.RoundedPanel roundedPanel5;
    private raven.application.form.other.RoundedPanel roundedPanel6;
    private raven.application.form.other.RoundedPanel roundedPanel7;
    // End of variables declaration//GEN-END:variables
}
