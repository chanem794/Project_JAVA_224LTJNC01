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
        chDate.setTextField(txtNgaySinh); // Liên kết DateChooser với txtNgaySinh
        chDate.setDateFormat(new java.text.SimpleDateFormat("dd/MM/yyyy")); // Định dạng ngày
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelAccount1 = new raven.application.form.other.component.PanelAccount();
        lbTitle = new javax.swing.JLabel();
        lbHovaten = new javax.swing.JLabel();
        txtNgaySinh = new javax.swing.JTextField();
        lbNgaySinh = new javax.swing.JLabel();
        txtHovaten = new javax.swing.JTextField();
        cmdLuu = new javax.swing.JButton();

        lbTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitle.setText("Thông tin tài khoản");
        panelAccount1.add(lbTitle);

        lbHovaten.setText("Họ và tên");
        panelAccount1.add(lbHovaten);
        panelAccount1.add(txtNgaySinh);

        lbNgaySinh.setText("Ngày sinh");
        panelAccount1.add(lbNgaySinh);
        panelAccount1.add(txtHovaten);

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(162, Short.MAX_VALUE)
                .addComponent(panelAccount1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(157, 157, 157))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addComponent(panelAccount1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(132, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmdLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdLuuActionPerformed
            //Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Hello sample message");
        
    }//GEN-LAST:event_cmdLuuActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdLuu;
    private javax.swing.JLabel lbHovaten;
    private javax.swing.JLabel lbNgaySinh;
    private javax.swing.JLabel lbTitle;
    private raven.application.form.other.component.PanelAccount panelAccount1;
    private javax.swing.JTextField txtHovaten;
    private javax.swing.JTextField txtNgaySinh;
    // End of variables declaration//GEN-END:variables
}
