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
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
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
    private JSpinner jSpinner6; // Thêm JSpinner cho giờ đi
    private JSpinner jSpinner7;
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

        // Tải danh sách điểm đi và điểm đến từ cơ sở dữ liệu
        List<String> diemDiList = new ArrayList<>();
        List<String> diemDenList = new ArrayList<>();
        try {
            diemDiList = tuyenService.getAllDiemDi();
            diemDenList = tuyenService.getAllDiemDen();
        } catch (SQLException e) {
            e.printStackTrace();
            diemDiList.add("Lỗi tải dữ liệu");
            diemDenList.add("Lỗi tải dữ liệu");
        }

        // Cài đặt model cho jComboBox1 và jComboBox2
        jComboBox1.setModel(new DefaultComboBoxModel<>(diemDiList.toArray(new String[0])));
        jComboBox2.setModel(new DefaultComboBoxModel<>(diemDenList.toArray(new String[0])));
        if (!diemDiList.isEmpty()) jComboBox1.setSelectedIndex(0);
        if (!diemDenList.isEmpty()) jComboBox2.setSelectedIndex(0);

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

        // Ẩn jTextField6 và jTextField7, thay bằng JSpinner
        jTextField6.setVisible(false);
        jTextField7.setVisible(false);

        // Cấu hình JSpinner cho giờ đi (jSpinner6)
        SpinnerDateModel dateModel6 = new SpinnerDateModel();
        jSpinner6 = new JSpinner(dateModel6);
        JSpinner.DateEditor timeEditor6 = new JSpinner.DateEditor(jSpinner6, "HH:mm");
        jSpinner6.setEditor(timeEditor6);
        jSpinner6.setValue(new Date()); // Giá trị mặc định

        // Cấu hình JSpinner cho giờ đến (jSpinner7)
        SpinnerDateModel dateModel7 = new SpinnerDateModel();
        jSpinner7 = new JSpinner(dateModel7);
        JSpinner.DateEditor timeEditor7 = new JSpinner.DateEditor(jSpinner7, "HH:mm");
        jSpinner7.setEditor(timeEditor7);
        jSpinner7.setValue(new Date()); // Giá trị mặc định

        // Thêm JSpinner vào jPanel2
        jPanel2.add(jSpinner6, "cell 1 5, width 137px");
        jPanel2.add(jSpinner7, "cell 1 6, width 137px");
    }
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
                SwingUtilities.invokeLater(() -> {
                    String input = textField.getText();
                    if (input.isEmpty()) {
                        comboBox.setModel(new DefaultComboBoxModel<>(items.toArray(new String[0])));
                        comboBox.setPopupVisible(false);
                        comboBox.setSelectedIndex(-1);
                        return;
                    }
                    List<String> matchedItems = new ArrayList<>();
                    for (String item : items) {
                        if (item.toLowerCase().contains(input.toLowerCase())) {
                            matchedItems.add(item);
                        }
                    }
                    comboBox.setModel(new DefaultComboBoxModel<>(matchedItems.toArray(new String[0])));
                    comboBox.setSelectedItem(input);
                    comboBox.setPopupVisible(!input.isEmpty() && !matchedItems.isEmpty());
                });
            }
        });
        comboBox.addActionListener(e -> {
            if (comboBox.getSelectedItem() != null) {
                textField.setText(comboBox.getSelectedItem().toString());
                comboBox.setPopupVisible(false);
            }
        });
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                comboBox.setPopupVisible(false);
            }
        });
    }
    private void updatePanelColors() {
    if (FlatLaf.isLafDark()) {

        roundedPanel1.setBackground(new Color(49, 62, 74, 255));
        roundedPanel4.setBackground(new Color(49, 62, 74, 255));
        roundedPanel5.setBackground(new Color(49, 62, 74, 255));
        roundedPanel7.setBackground(new Color(49, 62, 74, 255));
    } else {
 
        roundedPanel1.setBackground(new Color(255, 255, 255));
        roundedPanel4.setBackground(new Color(255, 255, 255));
        roundedPanel5.setBackground(new Color(255, 255, 255));
        roundedPanel7.setBackground(new Color(255, 255, 255));
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(roundedPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedPanel6Layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(roundedPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(roundedPanel6Layout.createSequentialGroup()
                        .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(27, 27, 27)
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
                                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))))
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

        jLabel28.setText("jLabel28");

        jLabel29.setText("jLabel29");

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
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel17)
                                        .addComponent(jLabel16))))
                            .addComponent(jTextField3)
                            .addComponent(jTextField6)
                            .addComponent(jTextField4)
                            .addComponent(jTextField7)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 183, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout roundedPanel2Layout = new javax.swing.GroupLayout(roundedPanel2);
        roundedPanel2.setLayout(roundedPanel2Layout);
        roundedPanel2Layout.setHorizontalGroup(
            roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel2Layout.createSequentialGroup()
                .addComponent(roundedPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(roundedPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 8, Short.MAX_VALUE)))
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
    
    private int generateNewMaXe() {
        try {
            int maxMaXe = xeService.getMaxMaXe(); // Giả định cần thêm getMaxMaXe trong XeService
            return maxMaXe + 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return (int) (System.currentTimeMillis() % 10000); // Fallback, tạo mã tạm thời
        }
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    String tenXe = jTextField3.getText().trim();
        String loaiXe = jTextField4.getText().trim();
        String ngayKhoiHanhStr = jTextField5.getText().trim();
        Date gioDiDate = (Date) jSpinner6.getValue(); // Lấy giá trị từ JSpinner
        Date gioDenDate = (Date) jSpinner7.getValue(); // Lấy giá trị từ JSpinner
        String soGheStr = jTextField8.getText().trim();
        String giaVeStr = jTextField9.getText().trim();

        // Kiểm tra ràng buộc
        if (tenXe.isEmpty() || loaiXe.isEmpty() || ngayKhoiHanhStr.equals("DD/MM/YYYY") || ngayKhoiHanhStr.isEmpty() ||
            soGheStr.isEmpty() || giaVeStr.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Vui lòng điền đầy đủ thông tin!");
            return;
        }

        Date ngayKhoiHanh = null;
        if (!ngayKhoiHanhStr.equals("DD/MM/YYYY")) {
            try {
                ngayKhoiHanh = new SimpleDateFormat("dd-MM-yyyy").parse(ngayKhoiHanhStr);
            } catch (ParseException e) {
                Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Định dạng ngày không hợp lệ!");
                return;
            }
        }

        java.sql.Time gioDi = new java.sql.Time(gioDiDate.getTime());
        java.sql.Time gioDen = new java.sql.Time(gioDenDate.getTime());

        int soGhe;
        int giaVe;
        try {
            soGhe = Integer.parseInt(soGheStr);
            giaVe = (int) Double.parseDouble(giaVeStr); // Ép kiểu double thành int
            if (soGhe <= 0 || giaVe <= 0) {
                Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Số ghế và giá vé phải lớn hơn 0!");
                return;
            }
        } catch (NumberFormatException e) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Số ghế và giá vé phải là số!");
            return;
        }

        if (selectedMaTuyen == null) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Vui lòng tìm kiếm tuyến trước!");
            return;
        }

        // Tạo và lưu xe mới
        Xe xe = new Xe();
        xe.setTenXe(tenXe);
        xe.setLoaiXe(loaiXe);
        xe.setMaTuyen(selectedMaTuyen);
        xe.setNgayKhoiHanh(new java.sql.Date(ngayKhoiHanh != null ? ngayKhoiHanh.getTime() : 0));
        xe.setGioDi(gioDi);
        xe.setGioDen(gioDen);
        xe.setSoGhe(soGhe);
        xe.setGheConTrong(soGhe); // Mặc định GheConTrong bằng SoGhe
        xe.setGiaVe(giaVe);
        xe.setMaXe(generateNewMaXe()); // Sử dụng logic tạo mã xe mới

        try {
            xeService.createXe(xe); // Thay addXe bằng createXe
            Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, "Đăng ký chuyến xe thành công!");
            // Reset form
            jTextField3.setText("");
            jTextField4.setText("");
            jTextField5.setText("DD/MM/YYYY");
            jSpinner6.setValue(new Date()); // Reset JSpinner
            jSpinner7.setValue(new Date()); // Reset JSpinner
            jTextField8.setText("");
            jTextField9.setText("");
            jLabel6.setText("Điểm đi: Chưa chọn");
            jLabel7.setText("Điểm đến: Chưa chọn");
            jLabel29.setText("Mã tuyến: Chưa chọn");
            selectedMaTuyen = null;
        } catch (SQLException e) {
            e.printStackTrace();
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Lỗi khi lưu chuyến xe!");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String diemDi = (String) jComboBox1.getSelectedItem();
    String diemDen = (String) jComboBox2.getSelectedItem();

    if (diemDi == null || diemDen == null || diemDi.equals("Lỗi tải dữ liệu") || diemDen.equals("Lỗi tải dữ liệu")) {
        Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Vui lòng chọn điểm đi và điểm đến hợp lệ!");
        return;
    }

    try {
        // Tìm tuyến dựa trên DiemDi và DiemDen
        List<Tuyen> tuyenList = tuyenService.getAllTuyen();
        Tuyen selectedTuyen = null;
        for (Tuyen tuyen : tuyenList) {
            if (tuyen.getDiemDi().equals(diemDi) && tuyen.getDiemDen().equals(diemDen)) {
                selectedTuyen = tuyen;
                break;
            }
        }
        if (selectedTuyen != null) {
            selectedMaTuyen = selectedTuyen.getMaTuyen();
            jLabel6.setText("Điểm đi: " + diemDi);
            jLabel7.setText("Điểm đến: " + diemDen);
            jLabel29.setText("Mã tuyến: " + selectedMaTuyen);
            Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, "Tìm tuyến thành công!");
        } else {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Không tìm thấy tuyến!");
            selectedMaTuyen = null;
            jLabel6.setText("Điểm đi: Chưa chọn");
            jLabel7.setText("Điểm đến: Chưa chọn");
            jLabel29.setText("Mã tuyến: Chưa chọn");
        }
    } catch (SQLException e) {
        e.printStackTrace();
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
