package raven.application.form.other;

import bll.TuyenService;
import bll.XeService;
import com.formdev.flatlaf.FlatLaf;
import com.raven.datechooser.DateChooser;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import model.Tuyen;
import model.Xe;
import net.miginfocom.swing.MigLayout;
import raven.application.Application;
import raven.toast.Notifications;

/**
 *
 * @author Admin
 */
public class ChooseBusForm extends javax.swing.JPanel {
    private List<BusTicketForm> busTicketForms = new ArrayList<>();
    // Các biến để lưu giá trị từ ChooseLocationForm
    private String departureLocation; // Giá trị của jComboBox1 (Nơi xuất phát)
    private String destinationLocation; // Giá trị của jComboBox2 (Nơi đến)
    private String departureDate; // Giá trị của jTextField1 (Ngày đi)
    private String arrivalDate; // Giá trị của jTextField2 (Ngày đến)
    private int totalXe;
    private List<String> diemDiList = new ArrayList<>(); // Danh sách điểm đi
    private List<String> diemDenList = new ArrayList<>(); // Danh sách điểm đến
    private boolean isUpdating = false; // Biến cờ để tránh vòng lặp cập nhật
    private List<Xe> xeList; // Thêm biến thành viên xeList

    /**
     * Constructor
     */
    public ChooseBusForm(String departureLocation, String destinationLocation, String departureDate, List<Xe> xeList) {
        this.departureLocation = departureLocation;
        this.destinationLocation = destinationLocation;
        this.departureDate = departureDate;
        this.xeList = xeList; // Gán xeList từ tham số
        this.totalXe = xeList != null ? xeList.size() : 0; // Gán totalXe
        initComponents();
        init();
        UIManager.addPropertyChangeListener(evt -> {
            if ("lookAndFeel".equals(evt.getPropertyName())) {
                updatePanelColors();
            }
        });
        jLabel1.setText("Tổng số xe đã tìm thấy: " + totalXe);
    }

    private void init() {
        setLayout(new MigLayout("al center center"));

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

        jComboBox1.setModel(new DefaultComboBoxModel<>(diemDiList.toArray(new String[0])));
        jComboBox2.setModel(new DefaultComboBoxModel<>(diemDenList.toArray(new String[0])));

        if (departureLocation != null && !departureLocation.isEmpty()) {
            jComboBox1.setSelectedItem(departureLocation);
        }
        if (destinationLocation != null && !destinationLocation.isEmpty()) {
            jComboBox2.setSelectedItem(destinationLocation);
        }

        ImageIcon iconLabel5 = new ImageIcon(getClass().getResource("/raven/icon/png/circle.png"));
        Image scaledIcon5 = iconLabel5.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
        jLabel5.setIcon(new ImageIcon(scaledIcon5));
        jLabel5.setIconTextGap(5);
        jLabel5.setHorizontalTextPosition(SwingConstants.RIGHT);
        jLabel5.setVerticalTextPosition(SwingConstants.CENTER);

        ImageIcon iconLabel6 = new ImageIcon(getClass().getResource("/raven/icon/png/location.png"));
        Image scaledIcon6 = iconLabel6.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
        jLabel6.setIcon(new ImageIcon(scaledIcon6));
        jLabel6.setIconTextGap(5);
        jLabel6.setHorizontalTextPosition(SwingConstants.RIGHT);
        jLabel6.setVerticalTextPosition(SwingConstants.CENTER);

        ImageIcon iconLabel7 = new ImageIcon(getClass().getResource("/raven/icon/png/calendar.png"));
        Image scaledIcon7 = iconLabel7.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
        jLabel7.setIcon(new ImageIcon(scaledIcon7));
        jLabel7.setIconTextGap(5);
        jLabel7.setHorizontalTextPosition(SwingConstants.RIGHT);
        jLabel7.setVerticalTextPosition(SwingConstants.CENTER);

        ImageIcon iconLabel9 = new ImageIcon(getClass().getResource("/raven/icon/png/bus.png"));
        Image scaledIcon9 = iconLabel9.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
        jLabel9.setIcon(new ImageIcon(scaledIcon9));
        jLabel9.setIconTextGap(5);
        jLabel9.setHorizontalTextPosition(SwingConstants.RIGHT);
        jLabel9.setVerticalTextPosition(SwingConstants.CENTER);

        ImageIcon iconLabel11 = new ImageIcon(getClass().getResource("/raven/icon/png/filter.png"));
        Image scaledIcon11 = iconLabel11.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
        jLabel11.setIcon(new ImageIcon(scaledIcon11));
        jLabel11.setIconTextGap(5);
        jLabel11.setHorizontalTextPosition(SwingConstants.RIGHT);
        jLabel11.setVerticalTextPosition(SwingConstants.CENTER);

        DateChooser dateChooser1 = new com.raven.datechooser.DateChooser();
        dateChooser1.setTextField(jTextField1);
        dateChooser1.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));

        jTextField1.setFont(new java.awt.Font("Segoe UI", 1, 14));
        if (departureDate == null || departureDate.isEmpty() || departureDate.equals("DD-MM-YYYY")) {
            jTextField1.setText("DD-MM-YYYY");
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
                if (jTextField1.getText().equals("DD-MM-YYYY")) {
                    jTextField1.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (jTextField1.getText().isEmpty()) {
                    jTextField1.setText("DD-MM-YYYY");
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
        setupAutoComplete(jComboBox1, diemDiList);
        setupAutoComplete(jComboBox2, diemDenList);

        // Hiển thị BusTicketForm trực tiếp khi khởi tạo
        displayBusTicketForms();

        updatePanelColors();
    }

    // Thêm phương thức để hiển thị BusTicketForm
    void displayBusTicketForms() {
        // Kiểm tra nếu xeList rỗng
        if (xeList == null || xeList.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Không có chuyến xe nào để hiển thị!");
            return;
        }

        // Tạo hoặc cập nhật ticketContainer
        javax.swing.JPanel ticketContainer = new javax.swing.JPanel();
        ticketContainer.setLayout(new MigLayout("wrap 1, gap 10, insets 10, align center", "[600px]", "[]"));
        ticketContainer.setOpaque(false);

        // Tạo một panel con để chứa jLabel9 và jLabel1 trên cùng một hàng
        javax.swing.JPanel labelPanel = new javax.swing.JPanel();
        labelPanel.setLayout(new MigLayout("insets 0, gap 5", "[][]", "[]"));
        labelPanel.setOpaque(false);

        // Thêm jLabel9 (ảnh) và jLabel1 (text) vào labelPanel
        labelPanel.add(jLabel9, "align left");
        labelPanel.add(jLabel1, "align left");

        // Thêm labelPanel vào ticketContainer, căn giữa
        ticketContainer.add(labelPanel, "align center, wrap");

        // Xóa danh sách BusTicketForm cũ trước khi tạo mới
        busTicketForms.clear();

        // Thêm BusTicketForm với thông tin từ danh sách xeList
        int displayCount = Math.min(xeList.size(), 300); // Hiển thị tối đa 300 xe
        for (int i = 0; i < displayCount; i++) {
            BusTicketForm busTicketForm = new BusTicketForm(xeList.get(i)); // Truyền đối tượng Xe vào BusTicketForm
            busTicketForm.setPreferredSize(new java.awt.Dimension(650, 250));
            busTicketForm.setMaximumSize(new java.awt.Dimension(650, 250));
            busTicketForm.setMinimumSize(new java.awt.Dimension(650, 250));
            // Đặt màu nền thủ công dựa trên theme hiện tại
            boolean isDarkMode = FlatLaf.isLafDark();
            if (isDarkMode) {
                busTicketForm.setBackground(new Color(79, 92, 104, 255));
            } else {
                busTicketForm.setBackground(new Color(255, 255, 255));
            }
            ticketContainer.add(busTicketForm, "align center");
            busTicketForms.add(busTicketForm);
        }

        // Thêm ticketContainer vào roundedPanel4
        roundedPanel4.removeAll();
        roundedPanel4.setLayout(new java.awt.BorderLayout());
        roundedPanel4.add(ticketContainer, java.awt.BorderLayout.CENTER);

        // Đảm bảo không có thanh cuộn ngang
        roundedJScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Làm mới giao diện để hiển thị thanh cuộn
        roundedPanel4.revalidate();
        roundedPanel4.repaint();
        roundedJScrollPane1.revalidate();
        roundedJScrollPane1.repaint();
    }


    // Phương thức cập nhật màu nền của roundedPanel2 dựa trên theme
    private void updatePanelColors() {
        if (FlatLaf.isLafDark()) {
            roundedPanel2.setBackground(new Color(79, 92, 104, 255));
            roundedPanel1.setBackground(new Color(49, 62, 74, 255));
            jButton1.setBackground(new Color(79, 92, 104, 255));
            roundedPanel4.setBackground(new Color(79, 92, 104, 255));
            roundedPanel7.setBackground(new Color(79, 92, 104, 255));
            // Cập nhật màu nền cho tất cả BusTicketForm
            for (BusTicketForm busTicketForm : busTicketForms) {
                busTicketForm.setBackground(new Color(79, 92, 104, 255));
            }
        } else {
            roundedPanel2.setBackground(new Color(230, 230, 230));
            roundedPanel1.setBackground(new Color(255, 255, 255));
            jButton1.setBackground(new Color(255, 149, 0));
            roundedPanel4.setBackground(new Color(255, 255, 255));
            roundedPanel7.setBackground(new Color(255, 255, 255));
            // Cập nhật màu nền cho tất cả BusTicketForm
            for (BusTicketForm busTicketForm : busTicketForms) {
                busTicketForm.setBackground(new Color(255, 255, 255));
            }
        }
        // Làm mới giao diện sau khi thay đổi theme
        roundedPanel4.revalidate();
        roundedPanel4.repaint();
        roundedJScrollPane1.revalidate();
        roundedJScrollPane1.repaint();
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
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        roundedPanel3 = new raven.application.form.other.RoundedPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        roundedJScrollPane3 = new raven.application.form.other.RoundedJScrollPane();
        roundedPanel7 = new raven.application.form.other.RoundedPanel();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jLabel10 = new javax.swing.JLabel();
        jRadioButton5 = new javax.swing.JRadioButton();
        jRadioButton6 = new javax.swing.JRadioButton();
        jRadioButton7 = new javax.swing.JRadioButton();
        jRadioButton8 = new javax.swing.JRadioButton();
        jRadioButton9 = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        roundedJScrollPane1 = new raven.application.form.other.RoundedJScrollPane();
        roundedPanel4 = new raven.application.form.other.RoundedPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        roundedPanel2.setBackground(new java.awt.Color(204, 204, 204));

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

        jTextField1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField1.setText("DD/MM/YYYY");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Chọn ngày đi:");

        javax.swing.GroupLayout roundedPanel1Layout = new javax.swing.GroupLayout(roundedPanel1);
        roundedPanel1.setLayout(roundedPanel1Layout);
        roundedPanel1Layout.setHorizontalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(64, 64, 64))
                    .addGroup(roundedPanel1Layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedPanel1Layout.createSequentialGroup()
                        .addComponent(jComboBox2, 0, 182, Short.MAX_VALUE)
                        .addGap(37, 37, 37)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundedPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(246, 246, 246))
                    .addGroup(roundedPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53))))
        );
        roundedPanel1Layout.setVerticalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jLabel8)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addContainerGap(19, Short.MAX_VALUE))
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

        jRadioButton2.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        jRadioButton2.setText("Mặc định\n");

        jRadioButton3.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        jRadioButton3.setText("Giờ đi sớm nhất");

        jRadioButton4.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        jRadioButton4.setText("Giờ đi muộn nhất\n");

        jLabel10.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel10.setText("Sắp xếp theo");

        jRadioButton5.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        jRadioButton5.setText("Giá tăng dần");

        jRadioButton6.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        jRadioButton6.setText("Giá giảm dần");

        jRadioButton7.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        jRadioButton7.setText("Giá rẻ nhất ");

        jRadioButton8.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        jRadioButton8.setText("Giá đắt nhất");

        jRadioButton9.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        jRadioButton9.setText("Nhiều chỗ trống nhất");

        javax.swing.GroupLayout roundedPanel7Layout = new javax.swing.GroupLayout(roundedPanel7);
        roundedPanel7.setLayout(roundedPanel7Layout);
        roundedPanel7Layout.setHorizontalGroup(
            roundedPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel7Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(roundedPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton6)
                    .addComponent(jRadioButton4)
                    .addComponent(jRadioButton3)
                    .addComponent(jRadioButton2)
                    .addComponent(jRadioButton7)
                    .addComponent(jRadioButton5)
                    .addComponent(jRadioButton8)
                    .addComponent(jRadioButton9))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel7Layout.createSequentialGroup()
                .addContainerGap(65, Short.MAX_VALUE)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addGap(88, 88, 88))
        );
        roundedPanel7Layout.setVerticalGroup(
            roundedPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel7Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(roundedPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addComponent(jRadioButton2)
                .addGap(30, 30, 30)
                .addComponent(jRadioButton3)
                .addGap(30, 30, 30)
                .addComponent(jRadioButton4)
                .addGap(30, 30, 30)
                .addComponent(jRadioButton5)
                .addGap(30, 30, 30)
                .addComponent(jRadioButton6)
                .addGap(30, 30, 30)
                .addComponent(jRadioButton7)
                .addGap(30, 30, 30)
                .addComponent(jRadioButton8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(jRadioButton9)
                .addGap(28, 28, 28))
        );

        roundedJScrollPane3.setViewportView(roundedPanel7);

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout roundedPanel4Layout = new javax.swing.GroupLayout(roundedPanel4);
        roundedPanel4.setLayout(roundedPanel4Layout);
        roundedPanel4Layout.setHorizontalGroup(
            roundedPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel4Layout.createSequentialGroup()
                .addGap(226, 226, 226)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap(438, Short.MAX_VALUE))
        );
        roundedPanel4Layout.setVerticalGroup(
            roundedPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundedPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(440, Short.MAX_VALUE))
        );

        roundedJScrollPane1.setViewportView(roundedPanel4);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(roundedJScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(roundedJScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(roundedPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(roundedPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(roundedJScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
                    .addComponent(roundedJScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 17, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
            String departureLocation = jComboBox1.getSelectedItem().toString();
        String destinationLocation = jComboBox2.getSelectedItem().toString();
        String departureDateStr = jTextField1.getText();

        // Chuyển đổi departureDateStr sang Date
        Date departureDate = null;
        if (!"DD-MM-YYYY".equals(departureDateStr)) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                departureDate = sdf.parse(departureDateStr);
            } catch (ParseException e) {
                e.printStackTrace();
                departureDate = new Date(); // Giá trị mặc định nếu lỗi
            }
        } else {
            departureDate = new Date(); // Gán ngày hiện tại nếu không nhập
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
                if (departureDate != null) {
                    java.sql.Date sqlDepartureDate = new java.sql.Date(departureDate.getTime());
                    xeList.removeIf(xe -> xe.getNgayKhoiHanh() == null || !xe.getNgayKhoiHanh().equals(sqlDepartureDate));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        int totalXe = xeList.size();
        // Kiểm tra nếu không tìm thấy chuyến xe nào
        if (totalXe == 0) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Không có chuyến xe nào, xin vui lòng chọn lại!");
            return; // Dừng việc cập nhật giao diện
        } else {
            Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, "Tìm chuyến thành công!");
        }

        // Cập nhật label với tổng số xe
        jLabel1.setText("Tổng số xe đã tìm thấy: " + totalXe);

        // Tạo hoặc cập nhật ticketContainer
        javax.swing.JPanel ticketContainer = new javax.swing.JPanel();
        ticketContainer.setLayout(new MigLayout("wrap 1, gap 10, insets 10, align center", "[600px]", "[]"));
        ticketContainer.setOpaque(false);

        // Tạo một panel con để chứa jLabel9 và jLabel1 trên cùng một hàng
        javax.swing.JPanel labelPanel = new javax.swing.JPanel();
        labelPanel.setLayout(new MigLayout("insets 0, gap 5", "[][]", "[]"));
        labelPanel.setOpaque(false);

        // Thêm jLabel9 (ảnh) và jLabel1 (text) vào labelPanel
        labelPanel.add(jLabel9, "align left");
        labelPanel.add(jLabel1, "align left");

        // Thêm labelPanel vào ticketContainer, căn giữa
        ticketContainer.add(labelPanel, "align center, wrap");

        // Xóa danh sách BusTicketForm cũ trước khi tạo mới
        busTicketForms.clear();

        // Thêm BusTicketForm với thông tin từ danh sách xeList
        int displayCount = Math.min(xeList.size(), 300); // Hiển thị tối đa 300 xe
        for (int i = 0; i < displayCount; i++) {
            BusTicketForm busTicketForm = new BusTicketForm(xeList.get(i)); // Truyền đối tượng Xe vào BusTicketForm
            busTicketForm.setPreferredSize(new java.awt.Dimension(650, 250));
            busTicketForm.setMaximumSize(new java.awt.Dimension(650, 250));
            busTicketForm.setMinimumSize(new java.awt.Dimension(650, 250));
            // Đặt màu nền thủ công dựa trên theme hiện tại
            boolean isDarkMode = FlatLaf.isLafDark();
            System.out.println("Dark mode detected when creating BusTicketForm: " + isDarkMode); // Debug
            if (isDarkMode) {
                busTicketForm.setBackground(new Color(79, 92, 104, 255));
            } else {
                busTicketForm.setBackground(new Color(255, 255, 255));
            }
            ticketContainer.add(busTicketForm, "align center");
            // Lưu BusTicketForm vào danh sách
            busTicketForms.add(busTicketForm);
        }

        // Thêm ticketContainer vào roundedPanel4
        roundedPanel4.removeAll(); // Xóa nội dung cũ
        roundedPanel4.setLayout(new java.awt.BorderLayout());
        roundedPanel4.add(ticketContainer, java.awt.BorderLayout.CENTER);

        // Đảm bảo không có thanh cuộn ngang
        roundedJScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Làm mới giao diện để hiển thị thanh cuộn
        roundedPanel4.revalidate();
        roundedPanel4.repaint();
        roundedJScrollPane1.revalidate();
        roundedJScrollPane1.repaint();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JRadioButton jRadioButton7;
    private javax.swing.JRadioButton jRadioButton8;
    private javax.swing.JRadioButton jRadioButton9;
    private javax.swing.JTextField jTextField1;
    private raven.application.form.other.RoundedJScrollPane roundedJScrollPane1;
    private raven.application.form.other.RoundedJScrollPane roundedJScrollPane3;
    private raven.application.form.other.RoundedPanel roundedPanel1;
    private raven.application.form.other.RoundedPanel roundedPanel2;
    private raven.application.form.other.RoundedPanel roundedPanel3;
    private raven.application.form.other.RoundedPanel roundedPanel4;
    private raven.application.form.other.RoundedPanel roundedPanel7;
    // End of variables declaration//GEN-END:variables

    // Getter cho các giá trị đã lưu
    public String getDepartureLocation() {
        return departureLocation;
    }

    public String getDestinationLocation() {
        return destinationLocation;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    // Getter cho các thành phần giao diện
    public JComboBox<String> getJComboBox1() {
        return jComboBox1;
    }

    public JComboBox<String> getJComboBox2() {
        return jComboBox2;
    }

    public JTextField getJTextField1() {
        return jTextField1;
    }

    // Phương thức displayBusTicketForms() đã có sẵn
}
