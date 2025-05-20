package raven.application;

import bll.*;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import raven.application.form.*;
import raven.toast.Notifications;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.SQLException;
import raven.application.form.other.StationForm;
import model.NguoiDung; // Thêm import này
public class Application extends javax.swing.JFrame { 
    private static Application app;
    private final MainForm mainForm;
    private final LoginForm loginForm;
    private final RegisterForm registerForm;
    private final OTPForm otpForm;
    private final InfoForm infoForm;
    private Container previousForm;
    private boolean isRegisterFlow;
    private String currentEmail;
    private final NguoiDungService nguoidungService;
    private NguoiDung currentUser; // Thêm thuộc tính để lưu người dùng hiện tại
    public Application() {
        initComponents();
        setSize(new Dimension(1366, 768));
        setLocationRelativeTo(null);
        mainForm = new MainForm();
        loginForm = new LoginForm();
        registerForm = new RegisterForm();
        otpForm = new OTPForm();
        infoForm = new InfoForm();
        nguoidungService = new NguoiDungService();
        //setContentPane(mainForm);
        setContentPane(loginForm);
        getRootPane().putClientProperty(FlatClientProperties.FULL_WINDOW_CONTENT, true);
        Notifications.getInstance().setJFrame(this);
    }
    // Thêm phương thức getCurrentUser()
    public static NguoiDung getCurrentUser() {
        if (app.currentUser != null) {
            try {
                // Tải lại thông tin người dùng từ cơ sở dữ liệu
                app.currentUser = app.nguoidungService.getUserByEmail(app.currentUser.getEmail());
            } catch (SQLException e) {
                e.printStackTrace();
                Notifications.getInstance().show(Notifications.Type.ERROR, "Lỗi khi tải thông tin người dùng");
            }
        }
        return app.currentUser;
}

    // Thêm phương thức setCurrentUser() để cập nhật người dùng sau khi đăng nhập
    public static void setCurrentUser(NguoiDung user) {
        app.currentUser = user;
    }
    public static void showForm(Component component) {
        component.applyComponentOrientation(app.getComponentOrientation());
        app.mainForm.showForm(component);
    }

    public static void login() {
        FlatAnimatedLafChange.showSnapshot();
        app.setContentPane(app.mainForm);
        app.mainForm.applyComponentOrientation(app.getComponentOrientation());
        setSelectedMenu(0, 0);
        app.mainForm.hideMenu();
        SwingUtilities.updateComponentTreeUI(app.mainForm);
        FlatAnimatedLafChange.hideSnapshotWithAnimation();
        app.previousForm = null;
        app.currentEmail = null;
    }

    public static void register() {
        FlatAnimatedLafChange.showSnapshot();
        app.setContentPane(app.registerForm);
        app.registerForm.applyComponentOrientation(app.getComponentOrientation());
        SwingUtilities.updateComponentTreeUI(app.registerForm);
        FlatAnimatedLafChange.hideSnapshotWithAnimation();
        app.previousForm = null;
        app.currentEmail = null;
    }

    public static void logout() {
        FlatAnimatedLafChange.showSnapshot();
        app.setContentPane(app.loginForm);
        app.loginForm.applyComponentOrientation(app.getComponentOrientation());
        SwingUtilities.updateComponentTreeUI(app.loginForm);
        FlatAnimatedLafChange.hideSnapshotWithAnimation();
        app.previousForm = null;
        app.currentEmail = null;
        app.currentUser = null; // Xóa thông tin người dùng khi đăng xuất
    }

    public static boolean isRegisterFlow() {
        return app.isRegisterFlow;
    }

    public static void showOTPForm(Container currentForm, boolean isRegisterFlow, String email) {
        FlatAnimatedLafChange.showSnapshot();
        app.previousForm = currentForm;
        app.isRegisterFlow = isRegisterFlow;
        app.currentEmail = email;
        app.setContentPane(app.otpForm);
        app.otpForm.applyComponentOrientation(app.getComponentOrientation());
        SwingUtilities.updateComponentTreeUI(app.otpForm);
        FlatAnimatedLafChange.hideSnapshotWithAnimation();
    }

    public static void showInfoForm() {
        FlatAnimatedLafChange.showSnapshot();
        app.setContentPane(app.infoForm);
        app.infoForm.applyComponentOrientation(app.getComponentOrientation());
        SwingUtilities.updateComponentTreeUI(app.infoForm);
        FlatAnimatedLafChange.hideSnapshotWithAnimation();
    }

    public static void backToPreviousForm() {
        if (app.previousForm != null) {
            FlatAnimatedLafChange.showSnapshot();
            app.setContentPane(app.previousForm);
            app.previousForm.applyComponentOrientation(app.getComponentOrientation());
            SwingUtilities.updateComponentTreeUI(app.previousForm);
            FlatAnimatedLafChange.hideSnapshotWithAnimation();
        }
    }

    public static String getCurrentEmail() {
        return app.currentEmail;
    }

    public static NguoiDungService getNguoiDungService() {
        return app.nguoidungService;
    }

    public static void setSelectedMenu(int index, int subIndex) {
        app.mainForm.setSelectedMenu(index, subIndex);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 719, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 521, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("raven.theme");
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        FlatMacLightLaf.setup();
        java.awt.EventQueue.invokeLater(() -> {
            app = new Application();
            app.setVisible(true);

            // 👉 Hiển thị ChooseLocationForm lúc khởi động
            
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
