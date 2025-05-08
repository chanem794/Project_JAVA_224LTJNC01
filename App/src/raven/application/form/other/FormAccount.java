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
        String email = Application.getCurrentEmail();
        if (email != null) {
            try {
                NguoiDung nguoiDung = nguoiDungService.getUserByEmail(email);
                if (nguoiDung != null) {
                    txtEmail.setText(nguoiDung.getEmail());
                    txtHovaten.setText(nguoiDung.getTenNguoiDung() != null ? nguoiDung.getTenNguoiDung() : "");
                    if (nguoiDung.getNgaySinh() != null) {
                        chDate.setSelectedDate(nguoiDung.getNgaySinh());
                    }
                }
            } catch (SQLException e) {
                Notifications.getInstance().show(Notifications.Type.ERROR, "Lỗi khi nạp thông tin người dùng");
                e.printStackTrace();
            }
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
        cmdLuu = new javax.swing.JButton();

        lbTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitle.setText("Thông tin tài khoản");
        panelAccount1.add(lbTitle);

        lbHovaten.setText("Họ và tên");
        panelAccount1.add(lbHovaten);
        panelAccount1.add(txtHovaten);

        lbEmail.setText("Email");
        panelAccount1.add(lbEmail);

        txtEmail.setEditable(false);
        panelAccount1.add(txtEmail);

        lbNgaySinh.setText("Ngày sinh");
        panelAccount1.add(lbNgaySinh);
        panelAccount1.add(txtNgaySinh);

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
                .addGap(188, 188, 188)
                .addComponent(panelAccount1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(panelAccount1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    private void cmdLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdLuuActionPerformed
            //Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Hello sample message");
        saveUserData();
    }//GEN-LAST:event_cmdLuuActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdLuu;
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
