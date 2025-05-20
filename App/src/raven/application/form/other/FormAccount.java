package raven.application.form.other;

import bll.NguoiDungService;
import com.formdev.flatlaf.FlatClientProperties;
import com.raven.datechooser.DateChooser;
import java.sql.SQLException;
import model.NguoiDung;
import net.miginfocom.swing.MigLayout;
import raven.application.Application;
import raven.toast.Notifications;

/**
 *
 * @author Raven
 */ 
public class FormAccount extends javax.swing.JPanel {
    private DateChooser chDate = new DateChooser();
    private final NguoiDungService nguoiDungService;

    public FormAccount() {
        initComponents();
        init();
        nguoiDungService = Application.getNguoiDungService();
        loadUserData();
    }
    private void init() {
        setLayout(new MigLayout("al center center"));

        lbTitle.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
        
//        txtPass.putClientProperty(FlatClientProperties.STYLE, ""
//                + "showRevealButton:true;"
//                + "showCapsLock:true");
        
        cmdLuu.putClientProperty(FlatClientProperties.STYLE, ""
                + "borderWidth:0;"
                + "focusWidth:0");
        txtHovaten.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Họ và tên");
        txtNgaySinh.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Ngày sinh (DD/MM/YYYY)");
        txtEmail.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "");
        chDate.setTextField(txtNgaySinh); // Liên kết DateChooser với txtNgaySinh
        chDate.setDateFormat(new java.text.SimpleDateFormat("dd/MM/yyyy")); // Định dạng ngày
    }
    private void loadUserData() {
        NguoiDung nguoiDung = Application.getCurrentUser();
        if (nguoiDung == null) {
            Notifications.getInstance().show(Notifications.Type.WARNING, "Không tìm thấy thông tin người dùng. Vui lòng đăng nhập lại.");
            System.out.println("Error: Current user is null");
            Application.logout();
            return;
        }

        // Log để gỡ lỗi
        System.out.println("Mã người dùng: " + nguoiDung.getMaNguoiDung());
        System.out.println("Email từ nguoiDung: " + nguoiDung.getEmail());
        System.out.println("Tên người dùng từ nguoiDung: " + nguoiDung.getTenNguoiDung());
        System.out.println("Ngày sinh từ nguoiDung: " + nguoiDung.getNgaySinh());

        // Cập nhật các trường thông tin
        txtEmail.setText(nguoiDung.getEmail() != null ? nguoiDung.getEmail() : "");
        txtHovaten.setText(nguoiDung.getTenNguoiDung() != null ? nguoiDung.getTenNguoiDung() : "");
        if (nguoiDung.getNgaySinh() != null) {
            chDate.setSelectedDate(nguoiDung.getNgaySinh());
        } else {
            chDate.setSelectedDate(new java.util.Date()); // Sử dụng ngày hiện tại
        }

        // Kiểm tra mã người dùng và cập nhật jLabel2
        String maNguoiDung = nguoiDung.getMaNguoiDung();
        if (maNguoiDung != null && maNguoiDung.length() >= 2) {
            String prefix = maNguoiDung.substring(0, 2).toUpperCase();
            if ("AD".equals(prefix)) {
                jLabel2.setText("Admin");
            } else if ("LX".equals(prefix)) {
                jLabel2.setText("Người lái xe");
            } else {
                jLabel2.setText("Khách hàng"); // Trường hợp mặc định
            }
        } else {
            jLabel2.setText("Khách hàng"); // Nếu mã người dùng không hợp lệ
        }
}

    private void saveUserData() {
        String email = txtEmail.getText();
        String tenNguoiDung = txtHovaten.getText().trim();
        java.util.Date ngaySinh = chDate.getSelectedDate();

        try {
            if (tenNguoiDung.isEmpty()) {
                Notifications.getInstance().show(Notifications.Type.WARNING, "Họ và tên không được để trống");
                return;
            }

            boolean success = nguoiDungService.updateUserInfo(email, tenNguoiDung, ngaySinh);
            if (success) {
                Notifications.getInstance().show(Notifications.Type.SUCCESS, "Cập nhật thông tin thành công");
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR, "Cập nhật thông tin thất bại");
            }
        } catch (SQLException e) {
            Notifications.getInstance().show(Notifications.Type.ERROR, "Lỗi khi cập nhật thông tin");
            e.printStackTrace();
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelAccount1 = new raven.application.form.other.component.PanelAccount();
        lbTitle = new javax.swing.JLabel();
        lbHovaten = new javax.swing.JLabel();
        txtHovaten = new javax.swing.JTextField();
        lbEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lbNgaySinh = new javax.swing.JLabel();
        txtNgaySinh = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cmdLuu = new javax.swing.JButton();

        lbTitle.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lbTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitle.setText("Thông tin tài khoản");
        panelAccount1.add(lbTitle);

        lbHovaten.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lbHovaten.setText("Họ và tên");
        panelAccount1.add(lbHovaten);
        panelAccount1.add(txtHovaten);

        lbEmail.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lbEmail.setText("Email");
        panelAccount1.add(lbEmail);

        txtEmail.setEditable(false);
        panelAccount1.add(txtEmail);

        lbNgaySinh.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lbNgaySinh.setText("Ngày sinh");
        panelAccount1.add(lbNgaySinh);
        panelAccount1.add(txtNgaySinh);

        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel1.setText("Loại tài khoản");
        panelAccount1.add(jLabel1);

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel2.setText("jLabel2");
        panelAccount1.add(jLabel2);

        cmdLuu.setText("Lưu");
        cmdLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdLuuActionPerformed(evt);
            }
        });
        panelAccount1.add(cmdLuu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(652, 652, 652)
                .addComponent(panelAccount1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelAccount1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 9, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    private void cmdLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdLuuActionPerformed
            //Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Hello sample message");
        saveUserData();
    }//GEN-LAST:event_cmdLuuActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdLuu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lbEmail;
    private javax.swing.JLabel lbHovaten;
    private javax.swing.JLabel lbNgaySinh;
    private javax.swing.JLabel lbTitle;
    private raven.application.form.other.component.PanelAccount panelAccount1;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHovaten;
    private javax.swing.JTextField txtNgaySinh;
    // End of variables declaration//GEN-END:variables
}
