package raven.application.form.other;

import com.formdev.flatlaf.FlatLaf;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Admin
 */
public class ChooseBusForm extends javax.swing.JPanel {

    // Các biến để lưu giá trị từ ChooseLocationForm
    private String departureLocation; // Giá trị của jComboBox1 (Nơi xuất phát)
    private String destinationLocation; // Giá trị của jComboBox2 (Nơi đến)
    private String departureDate; // Giá trị của jTextField1 (Ngày đi)
    private String arrivalDate; // Giá trị của jTextField2 (Ngày đến)

    private List<String> diemDiList = new ArrayList<>(); // Danh sách điểm đi
    private List<String> diemDenList = new ArrayList<>(); // Danh sách điểm đến
    private boolean isUpdating = false; // Biến cờ để tránh vòng lặp cập nhật

    /**
     * Constructor
     */
    public ChooseBusForm(String departureLocation, String destinationLocation, String departureDate, String arrivalDate) {
        this.departureLocation = departureLocation;
        this.destinationLocation = destinationLocation;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
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

        // Cài đặt model cho jComboBox1 và jComboBox2 từ file
        jComboBox1.setModel(new DefaultComboBoxModel<>(diemDiList.toArray(new String[0])));
        jComboBox2.setModel(new DefaultComboBoxModel<>(diemDenList.toArray(new String[0])));

        // Đặt giá trị đã chọn từ ChooseLocationForm
        if (departureLocation != null && !departureLocation.isEmpty()) {
            jComboBox1.setSelectedItem(departureLocation);
        }
        if (destinationLocation != null && !destinationLocation.isEmpty()) {
            jComboBox2.setSelectedItem(destinationLocation);
        }

        // Icon jLabel5
        ImageIcon iconLabel5 = new ImageIcon(getClass().getResource("/raven/icon/png/circle.png"));
        Image scaledIcon5 = iconLabel5.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
        jLabel5.setIcon(new ImageIcon(scaledIcon5));
        jLabel5.setIconTextGap(5);
        jLabel5.setHorizontalTextPosition(SwingConstants.RIGHT);
        jLabel5.setVerticalTextPosition(SwingConstants.CENTER);

        // Icon jLabel6
        ImageIcon iconLabel6 = new ImageIcon(getClass().getResource("/raven/icon/png/location.png"));
        Image scaledIcon6 = iconLabel6.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
        jLabel6.setIcon(new ImageIcon(scaledIcon6));
        jLabel6.setIconTextGap(5);
        jLabel6.setHorizontalTextPosition(SwingConstants.RIGHT);
        jLabel6.setVerticalTextPosition(SwingConstants.CENTER);

        // Icon jLabel7
        ImageIcon iconLabel7 = new ImageIcon(getClass().getResource("/raven/icon/png/calendar.png"));
        Image scaledIcon7 = iconLabel7.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
        jLabel7.setIcon(new ImageIcon(scaledIcon7));
        jLabel7.setIconTextGap(5);
        jLabel7.setHorizontalTextPosition(SwingConstants.RIGHT);
        jLabel7.setVerticalTextPosition(SwingConstants.CENTER);

        // DateChooser cho jTextField1
        com.raven.datechooser.DateChooser dateChooser1 = new com.raven.datechooser.DateChooser();
        dateChooser1.setTextField(jTextField1);
        dateChooser1.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));

        // DateChooser cho jTextField2
        com.raven.datechooser.DateChooser dateChooser2 = new com.raven.datechooser.DateChooser();
        dateChooser2.setTextField(jTextField2);
        dateChooser2.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));

        // jTextField1
        jTextField1.setFont(new java.awt.Font("Segoe UI", 1, 14));
        if (departureDate == null || departureDate.isEmpty() || departureDate.equals("DD/MM/YYYY")) {
            jTextField1.setText("DD/MM/YYYY");
        } else {
            jTextField1.setText(departureDate);
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                java.util.Date date = sdf.parse(departureDate);
                dateChooser1.setSelectedDate(date);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

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

        jTextField1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                dateChooser1.showPopup();
            }
        });

        jTextField1.addPropertyChangeListener("text", evt -> {
            dateChooser1.hidePopup();
        });

        // jTextField2
        jTextField2.setFont(new java.awt.Font("SansSerif", 1, 14));
        if (arrivalDate == null || arrivalDate.isEmpty() || arrivalDate.equals("DD/MM/YYYY")) {
            jTextField2.setText("DD/MM/YYYY");
        } else {
            jTextField2.setText(arrivalDate);
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                java.util.Date date = sdf.parse(arrivalDate);
                dateChooser2.setSelectedDate(date);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

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

        jTextField2.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                dateChooser2.showPopup();
            }
        });

        jTextField2.addPropertyChangeListener("text", evt -> {
            dateChooser2.hidePopup();
        });

        // Thiết lập gợi ý cho jComboBox1 và jComboBox2
        setupAutoComplete(jComboBox1, diemDiList);
        setupAutoComplete(jComboBox2, diemDenList);

        // Cập nhật màu nền
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

    private void setupAutoComplete(javax.swing.JComboBox<String> comboBox, List<String> items) {
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
                        comboBox.setSelectedItem(input); // Giữ nguyên nội dung bạn đang gõ
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

        jPanel1 = new javax.swing.JPanel();
        roundedPanel2 = new raven.application.form.other.RoundedPanel();
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
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        roundedPanel3 = new raven.application.form.other.RoundedPanel();
        jRadioButton1 = new javax.swing.JRadioButton();

        roundedPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jButton1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jButton1.setText("Tìm kiếm ");

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

        jTextField1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
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

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Chọn ngày đến");

        javax.swing.GroupLayout roundedPanel1Layout = new javax.swing.GroupLayout(roundedPanel1);
        roundedPanel1.setLayout(roundedPanel1Layout);
        roundedPanel1Layout.setHorizontalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(roundedPanel1Layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedPanel1Layout.createSequentialGroup()
                        .addComponent(jComboBox2, 0, 182, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundedPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel9))
                .addGap(22, 22, 22))
        );
        roundedPanel1Layout.setVerticalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel8)
                        .addComponent(jLabel9))
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout roundedPanel2Layout = new javax.swing.GroupLayout(roundedPanel2);
        roundedPanel2.setLayout(roundedPanel2Layout);
        roundedPanel2Layout.setHorizontalGroup(
            roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(roundedPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        roundedPanel2Layout.setVerticalGroup(
            roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(roundedPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jLabel2.setText("jLabel2");

        jButton2.setText("jButton2");

        javax.swing.GroupLayout roundedPanel3Layout = new javax.swing.GroupLayout(roundedPanel3);
        roundedPanel3.setLayout(roundedPanel3Layout);
        roundedPanel3Layout.setHorizontalGroup(
            roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        roundedPanel3Layout.setVerticalGroup(
            roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jRadioButton1.setText("jRadioButton1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roundedPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(roundedPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(583, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 293, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private raven.application.form.other.RoundedPanel roundedPanel1;
    private raven.application.form.other.RoundedPanel roundedPanel2;
    private raven.application.form.other.RoundedPanel roundedPanel3;
    // End of variables declaration//GEN-END:variables
}
