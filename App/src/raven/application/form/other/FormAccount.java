package raven.application.form.other;

import com.formdev.flatlaf.FlatClientProperties;
import com.raven.datechooser.DateChooser;
import net.miginfocom.swing.MigLayout;
import raven.toast.Notifications;

/**
 *
 * @author Raven
 */
public class FormAccount extends javax.swing.JPanel {
    private DateChooser chDate = new DateChooser();
    public FormAccount() {
        initComponents();
        lbTitle.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
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
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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

        lbHovaten.setText("Họ và tên");

        lbEmail.setText("Email");

        lbNgaySinh.setText("Ngày sinh");

        cmdLuu.setText("Lưu");
        cmdLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdLuuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbHovaten, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHovaten, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 404, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 170, Short.MAX_VALUE)
                .addComponent(lbTitle)
                .addGap(20, 20, 20)
                .addComponent(lbHovaten)
                .addGap(7, 7, 7)
                .addComponent(txtHovaten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(lbEmail)
                .addGap(7, 7, 7)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbNgaySinh)
                .addGap(18, 18, 18)
                .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cmdLuu)
                .addGap(0, 132, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmdLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdLuuActionPerformed
            //Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Hello sample message");
        
    }//GEN-LAST:event_cmdLuuActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdLuu;
    private javax.swing.JLabel lbEmail;
    private javax.swing.JLabel lbHovaten;
    private javax.swing.JLabel lbNgaySinh;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHovaten;
    private javax.swing.JTextField txtNgaySinh;
    // End of variables declaration//GEN-END:variables
}
