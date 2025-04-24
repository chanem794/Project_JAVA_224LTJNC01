package raven.application;

import bll.*;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import raven.application.form.*;
import raven.toast.Notifications;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import raven.application.form.other.StationForm;

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
    private final UserService userService;

    public Application() {
        initComponents();
        setSize(new Dimension(1366, 768));
        setLocationRelativeTo(null);
        mainForm = new MainForm();
        loginForm = new LoginForm();
        registerForm = new RegisterForm();
        otpForm = new OTPForm();
        infoForm = new InfoForm();
        userService = new UserService();
        setContentPane(mainForm);
        //setContentPane(loginForm);
        getRootPane().putClientProperty(FlatClientProperties.FULL_WINDOW_CONTENT, true);
        Notifications.getInstance().setJFrame(this);
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

    public static UserService getUserService() {
        return app.userService;
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
        FlatMacDarkLaf.setup();
        java.awt.EventQueue.invokeLater(() -> {
            app = new Application();
            showForm(new StationForm());
            //  app.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            app.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
