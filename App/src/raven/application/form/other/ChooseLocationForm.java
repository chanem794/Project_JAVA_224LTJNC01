/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package raven.application.form.other;

import bll.TuyenService;
import bll.XeService;
import static com.formdev.flatlaf.FlatClientProperties.PLACEHOLDER_TEXT;
import com.formdev.flatlaf.FlatLaf;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.RoundRectangle2D;
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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import model.Tuyen;
import model.Xe;
import net.miginfocom.swing.MigLayout;
import raven.application.Application;
/**
 *
 * @author Admin
 */
public class ChooseLocationForm extends javax.swing.JPanel {

    /**
     * Creates new form ChooseLocationForm
     */
    public ChooseLocationForm() {
        initComponents();
        init();
        UIManager.addPropertyChangeListener(evt -> {
            if ("lookAndFeel".equals(evt.getPropertyName())) {
                updatePanelColors();
            }
        });
    }
    private void init() {
        setLayout(new MigLayout("al center center"));

        // Đọc dữ liệu từ file Tỉnh, Huyện.txt
        List<String> diemDiList = new ArrayList<>();
        List<String> diemDenList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("Tỉnh, Huyện.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) { // Bỏ qua các dòng trống
                    diemDiList.add(line);  // Thêm vào danh sách điểm đi
                    diemDenList.add(line); // Thêm vào danh sách điểm đến (cùng danh sách)
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            diemDiList.add("Error loading data from file");
            diemDenList.add("Error loading data from file");
        }

        // Thiết lập icon và thuộc tính cho jLabel1
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/raven/icon/png/iconbusfinal.png"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(115, 115, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);
        jLabel1.setIcon(resizedIcon);
        jLabel1.setIconTextGap(20);
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel1.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);

        // Thiết lập icon cho jLabel2
        ImageIcon tickIcon = new ImageIcon(getClass().getResource("/raven/icon/png/Flat_tick_icon.svg.png"));
        Image scaledTickImage = tickIcon.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon resizedTickIcon = new ImageIcon(scaledTickImage);
        jLabel2.setIcon(resizedTickIcon);
        jLabel2.setIconTextGap(10);
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        // Thiết lập icon cho jLabel5
        ImageIcon iconLabel5 = new ImageIcon(getClass().getResource("/raven/icon/png/circle.png"));
        Image scaledIcon5 = iconLabel5.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon5 = new ImageIcon(scaledIcon5);
        jLabel5.setIcon(resizedIcon5);
        jLabel5.setIconTextGap(5);
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel5.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);

        // Thiết lập icon cho jLabel6
        ImageIcon iconLabel6 = new ImageIcon(getClass().getResource("/raven/icon/png/location.png"));
        Image scaledIcon6 = iconLabel6.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon6 = new ImageIcon(scaledIcon6);
        jLabel6.setIcon(resizedIcon6);
        jLabel6.setIconTextGap(5);
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel6.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);

        // Thiết lập icon cho jLabel7
        ImageIcon iconLabel7 = new ImageIcon(getClass().getResource("/raven/icon/png/calendar.png"));
        Image scaledIcon7 = iconLabel7.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon7 = new ImageIcon(scaledIcon7);
        jLabel7.setIcon(resizedIcon7);
        jLabel7.setIconTextGap(5);
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel7.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);

        ImageIcon iconLabel9 = new ImageIcon(getClass().getResource("/raven/icon/png/tickets.png"));
        Image scaledIcon9 = iconLabel9.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon9 = new ImageIcon(scaledIcon9);
        jLabel9.setIcon(resizedIcon9);
        jLabel9.setIconTextGap(5);
        jLabel9.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel9.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);

        // Thiết lập placeholder và font size cho jTextField1
        jTextField1.setText("DD/MM/YYYY");
        jTextField1.setFont(new java.awt.Font("SansSerif", 1, 14));
        jTextField1.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (jTextField1.getText().equals("DD/MM/YYYY")) {
                    jTextField1.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (jTextField1.getText().isEmpty()) {
                    jTextField1.setText("DD/MM/YYYY");
                }
            }
        });

        // Liên kết DateChooser với jTextField1 (SỬA chỗ này)
        dateChooser1.setTextField(jTextField1);
        dateChooser1.setDateFormat(new java.text.SimpleDateFormat("dd-MM-yyyy")); // <- Sửa YYYY thành yyyy

        // Thiết lập placeholder và font size cho jTextField2
        jTextField2.setText("DD/MM/YYYY");
        jTextField2.setFont(new java.awt.Font("SansSerif", 1, 14));
        jTextField2.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (jTextField2.getText().equals("DD/MM/YYYY")) {
                    jTextField2.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (jTextField2.getText().isEmpty()) {
                    jTextField2.setText("DD/MM/YYYY");
                }
            }
        });

        // Tạo DateChooser mới cho jTextField2 và liên kết (SỬA chỗ này)
        com.raven.datechooser.DateChooser dateChooser2 = new com.raven.datechooser.DateChooser();
        dateChooser2.setTextField(jTextField2);
        dateChooser2.setDateFormat(new java.text.SimpleDateFormat("dd-MM-yyyy")); // <- Đảm bảo dùng yyyy

        // Cài đặt model cho jComboBox1 và jComboBox2 từ file
        jComboBox1.setModel(new DefaultComboBoxModel<>(diemDiList.toArray(new String[0])));
        jComboBox2.setModel(new DefaultComboBoxModel<>(diemDenList.toArray(new String[0])));

        // Đặt giá trị mặc định cho jComboBox1 và jComboBox2 (nếu danh sách không rỗng)
        if (!diemDiList.isEmpty()) {
            jComboBox1.setSelectedIndex(0); // Đặt địa điểm đi mặc định là mục đầu tiên
        }
        if (!diemDenList.isEmpty()) {
            jComboBox2.setSelectedIndex(0); // Đặt địa điểm đến mặc định là mục đầu tiên
        }

        // Gọi phương thức gợi ý với cải tiến
        setupAutoComplete(jComboBox1, diemDiList);
        setupAutoComplete(jComboBox2, diemDenList);

        // Cập nhật màu ban đầu
        updatePanelColors();
    }


// Phương thức cập nhật màu nền của roundedPanel2 dựa trên theme
private void updatePanelColors() {
    if (FlatLaf.isLafDark()) {
        roundedPanel2.setBackground(new Color(79, 92, 104, 255));
        roundedPanel1.setBackground(new Color(49, 62, 74, 255));
        jButton1.setBackground(new Color(79, 92, 104, 255));
        // Màu xám đậm cho dark mode
    } else {
        roundedPanel2.setBackground(new Color(230, 230, 230));
        roundedPanel1.setBackground(new Color(255, 255, 255));
        jButton1.setBackground(new Color(255, 149, 0));
    }
}

private boolean isUpdating = false; // Biến cờ để tránh vòng lặp cập nhật

private void setupAutoComplete(JComboBox<String> comboBox, List<String> items) {
    comboBox.setEditable(true);
    JTextField textField = (JTextField) comboBox.getEditor().getEditorComponent();

    // Đặt giá trị ban đầu từ mục được chọn (địa điểm mặc định)
    if (comboBox.getSelectedItem() != null) {
        textField.setText(comboBox.getSelectedItem().toString());
    }

    textField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
        @Override
        public void insertUpdate(javax.swing.event.DocumentEvent e) {
            updateSuggestions();
        }

        @Override
        public void removeUpdate(javax.swing.event.DocumentEvent e) {
            updateSuggestions();
        }

        @Override
        public void changedUpdate(javax.swing.event.DocumentEvent e) {
            updateSuggestions();
        }

        private void updateSuggestions() {
            if (isUpdating) return; // Tránh vòng lặp cập nhật
            isUpdating = true;

            SwingUtilities.invokeLater(() -> {
                try {
                    String input = textField.getText();
                    List<String> matchedItems = new ArrayList<>();
                    List<String> unmatchedItems = new ArrayList<>();

                    // Nếu không có đầu vào, hiển thị toàn bộ danh sách và ẩn popup, không khôi phục văn bản
                    if (input.isEmpty()) {
                        comboBox.setModel(new DefaultComboBoxModel<>(items.toArray(new String[0])));
                        comboBox.setPopupVisible(false);
                        comboBox.setSelectedIndex(-1); // Bỏ chọn mục hiện tại
                        isUpdating = false;
                        return;
                    }

                    // Phân loại các mục: phù hợp và không phù hợp
                    for (String item : items) {
                        if (normalizeString(item).toLowerCase().contains(normalizeString(input).toLowerCase())) {
                            matchedItems.add(item);
                        } else {
                            unmatchedItems.add(item);
                        }
                    }

                    // Kết hợp danh sách: các mục phù hợp lên trên, các mục không phù hợp xuống dưới
                    List<String> allItems = new ArrayList<>();
                    allItems.addAll(matchedItems);
                    allItems.addAll(unmatchedItems);

                    // Cập nhật model của JComboBox
                    comboBox.setModel(new DefaultComboBoxModel<>(allItems.toArray(new String[0])));
                    comboBox.setSelectedItem(input); // giữ nguyên nội dung bạn đang gõ
                    comboBox.showPopup();
                    // Chỉ hiển thị popup nếu có nội dung nhập vào và danh sách có mục phù hợp
                    if (!input.isEmpty() && !matchedItems.isEmpty()) {
                        comboBox.setPopupVisible(true);
                    } else {
                        comboBox.setPopupVisible(false);
                    }
                    
                } finally {
                    isUpdating = false; // Đặt lại cờ sau khi cập nhật xong
                }
            });
        }
    });

    // Đảm bảo khi chọn một mục, văn bản được cập nhật mà không gây vòng lặp
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

    // Đảm bảo popup không hiển thị khi mất focus
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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooser1 = new com.raven.datechooser.DateChooser();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        roundedPanel2 = new raven.application.form.other.RoundedPanel();
        jLabel2 = new javax.swing.JLabel();
        roundedPanel1 = new raven.application.form.other.RoundedPanel();
        jButton1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        roundedPanel3 = new raven.application.form.other.RoundedPanel();

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 30)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("VEXERE - Vé Xe Buýt Cho Mọi Nhà");
        jLabel1.setToolTipText("");

        roundedPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Vui lòng chọn địa điểm xuất phát và địa điểm đến ");

        jButton1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jButton1.setText("Tìm kiếm ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Nơi xuất phát");
        jLabel3.setToolTipText("");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Nơi đến:");

        jTextField1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jTextField1.setText("DD/MM/YYYY");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Chọn ngày đi:");

        jTextField2.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jTextField2.setText("DD/MM/YYYY");
        jTextField2.setToolTipText("");
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Chọn ngày đến dự kiến:");

        javax.swing.GroupLayout roundedPanel1Layout = new javax.swing.GroupLayout(roundedPanel1);
        roundedPanel1.setLayout(roundedPanel1Layout);
        roundedPanel1Layout.setHorizontalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(roundedPanel1Layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(roundedPanel1Layout.createSequentialGroup()
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(roundedPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))))
        );
        roundedPanel1Layout.setVerticalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel1Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout roundedPanel2Layout = new javax.swing.GroupLayout(roundedPanel2);
        roundedPanel2.setLayout(roundedPanel2Layout);
        roundedPanel2Layout.setHorizontalGroup(
            roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel2Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 611, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(182, 182, 182))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel2Layout.createSequentialGroup()
                        .addComponent(roundedPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))))
        );
        roundedPanel2Layout.setVerticalGroup(
            roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(roundedPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout roundedPanel3Layout = new javax.swing.GroupLayout(roundedPanel3);
        roundedPanel3.setLayout(roundedPanel3Layout);
        roundedPanel3Layout.setHorizontalGroup(
            roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        roundedPanel3Layout.setVerticalGroup(
            roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1149, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(377, 377, 377)
                                .addComponent(roundedPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(roundedPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roundedPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(322, 322, 322)
                .addComponent(roundedPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(175, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(962, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(844, 844, 844))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    String departureLocation = jComboBox1.getSelectedItem().toString();
    String destinationLocation = jComboBox2.getSelectedItem().toString();
    String departureDateStr = jTextField1.getText();
    String arrivalDateStr = jTextField2.getText();

    // Chuyển đổi departureDateStr sang Date
    Date departureDate = null;
    if (!"DD/MM/YYYY".equals(departureDateStr)) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            departureDate = sdf.parse(departureDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            departureDate = new Date(); // Gán giá trị mặc định nếu parse thất bại
        }
    } else {
        // Nếu không nhập ngày, có thể bỏ qua lọc theo ngày hoặc gán ngày hiện tại
        departureDate = new Date(); // Hoặc để null nếu không muốn lọc
    }

    // Lấy MaTuyen từ danh sách getAllTuyen()
    int maTuyen = -1;
    try {
        TuyenService tuyenService = new TuyenService();
        List<Tuyen> tuyenList = tuyenService.getAllTuyen();
        for (Tuyen tuyen : tuyenList) {
            if (tuyen.getDiemDi().equals(departureLocation) && tuyen.getDiemDen().equals(destinationLocation)) {
                maTuyen = tuyen.getMaTuyen();
                break;
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    // Lấy danh sách xe dựa trên MaTuyen
    List<Xe> xeList = new ArrayList<>();
    if (maTuyen != -1) {
        try {
            XeService xeService = new XeService();
            xeList = xeService.getXeByMaTuyen(maTuyen);
            // Lọc thêm theo ngày nếu có departureDate hợp lệ
            // Lọc thêm theo ngày nếu có departureDate hợp lệ
            if (departureDate != null) {
                java.sql.Date sqlDepartureDate = new java.sql.Date(departureDate.getTime());
                java.sql.Date finalSqlDepartureDate = sqlDepartureDate; // Biến final để dùng trong lambda
                xeList.removeIf(xe -> xe.getNgayKhoiHanh() == null || !xe.getNgayKhoiHanh().equals(finalSqlDepartureDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    int totalXe = xeList.size();
    // Truyền giá trị sang ChooseBusForm
    Application.showForm(new ChooseBusForm(departureLocation, destinationLocation, departureDateStr, arrivalDateStr, totalXe));
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.datechooser.DateChooser dateChooser1;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private raven.application.form.other.RoundedPanel roundedPanel1;
    private raven.application.form.other.RoundedPanel roundedPanel2;
    private raven.application.form.other.RoundedPanel roundedPanel3;
    // End of variables declaration//GEN-END:variables


}
