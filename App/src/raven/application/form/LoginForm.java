package raven.application.form;

import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;
import raven.application.Application;
import raven.toast.Notifications;

/**
 *
 * @author Raven
 */
public class LoginForm extends javax.swing.JPanel { 

    public LoginForm() {
        initComponents();
        init();
    }

    private void init() {
        setLayout(new MigLayout("al center center"));

        lbTitle.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
        
//        txtPass.putClientProperty(FlatClientProperties.STYLE, ""
//                + "showRevealButton:true;"
//                + "showCapsLock:true");
        cmdLogin.putClientProperty(FlatClientProperties.STYLE, ""
                + "borderWidth:0;"
                + "focusWidth:0");
        cmdRegister.putClientProperty(FlatClientProperties.STYLE, ""
                + "borderWidth:0;"
                + "focusWidth:0");
        txtSodienthoai.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Số Điện Thoại");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelLogin1 = new raven.application.form.PanelLogin();
        lbTitle = new javax.swing.JLabel();
        lbSodienthoai = new javax.swing.JLabel();
        txtSodienthoai = new javax.swing.JTextField();
        cmdLogin = new javax.swing.JButton();
        lbSodienthoai1 = new javax.swing.JLabel();
        cmdRegister = new javax.swing.JButton();

        lbTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitle.setText("Đăng nhập");
        panelLogin1.add(lbTitle);

        lbSodienthoai.setText("Số điện thoại");
        panelLogin1.add(lbSodienthoai);
        panelLogin1.add(txtSodienthoai);

        cmdLogin.setText("Tiếp tục");
        cmdLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdLoginActionPerformed(evt);
            }
        });
        panelLogin1.add(cmdLogin);

        lbSodienthoai1.setText("Bạn chưa có tài khoản?");
        panelLogin1.add(lbSodienthoai1);

        cmdRegister.setText("Đăng Ký");
        cmdRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdRegisterActionPerformed(evt);
            }
        });
        panelLogin1.add(cmdRegister);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(218, Short.MAX_VALUE)
                .addComponent(panelLogin1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(197, 197, 197))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(panelLogin1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(95, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmdLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdLoginActionPerformed
        String phone = txtSodienthoai.getText().trim();
        if (phone.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, "Vui lòng nhập số điện thoại");
            return;
        }
        Application.showOTPForm(this, false); // Luồng đăng nhập

    }//GEN-LAST:event_cmdLoginActionPerformed

    private void cmdRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdRegisterActionPerformed
        // TODO add your handling code here:
        Application.register();
    }//GEN-LAST:event_cmdRegisterActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdLogin;
    private javax.swing.JButton cmdRegister;
    private javax.swing.JLabel lbSodienthoai;
    private javax.swing.JLabel lbSodienthoai1;
    private javax.swing.JLabel lbTitle;
    private raven.application.form.PanelLogin panelLogin1;
    private javax.swing.JTextField txtSodienthoai;
    // End of variables declaration//GEN-END:variables
}
