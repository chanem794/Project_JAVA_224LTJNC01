package UI;

import javax.swing.*;
import java.awt.*;

public class DangNhapUI extends JDialog {
    public DangNhapUI(JFrame parent) {
        setSize(800, 550);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());
        setResizable(false);


        JLabel imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.CENTER);
        ImageIcon icon = new ImageIcon(getClass().getResource("/Resource/DangNhap.jpg"));
        Image scaledImage = icon.getImage().getScaledInstance(330, 550, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(scaledImage));
        add(imageLabel, BorderLayout.WEST);


        Dimension fullWidthButtonSize = new Dimension(340, 45);
        Dimension inputFieldSize = new Dimension(340, 40);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        JLabel lblTitle = new JLabel("Đăng Nhập");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitle.setForeground(Color.BLUE);
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblTitle);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        JPanel phonePanel = new JPanel(new BorderLayout(5, 5));
        phonePanel.setMaximumSize(inputFieldSize);
        String[] quocGia = {"+84 (VNM)", "+1 (USA)", "+81 (JPN)", "+44 (GBR)", "+33 (FRA)", "+49 (DEU)", "+86 (CHN)", "+65 (SGP)"};
        JComboBox<String> cboMa = new JComboBox<>(quocGia);
        JTextField txtSDT = new JTextField();
        txtSDT.setPreferredSize(inputFieldSize);
        txtSDT.setMaximumSize(inputFieldSize);
        phonePanel.add(cboMa, BorderLayout.WEST);
        phonePanel.add(txtSDT, BorderLayout.CENTER);
        mainPanel.add(phonePanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JPanel matKhauPanel = new JPanel(new BorderLayout(5, 5));
        matKhauPanel.setMaximumSize(inputFieldSize);
        JLabel lblMatKhau = new JLabel("  Mật khẩu:        ");
        JPasswordField txtMatKhau = new JPasswordField();
        txtMatKhau.setFont(new Font("Arial", Font.PLAIN, 14));
        txtMatKhau.setEchoChar('*');
        txtMatKhau.setPreferredSize(inputFieldSize);
        txtMatKhau.setMaximumSize(inputFieldSize);
        matKhauPanel.add(lblMatKhau, BorderLayout.WEST);
        matKhauPanel.add(txtMatKhau, BorderLayout.CENTER);
        mainPanel.add(matKhauPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));


        JButton btnDangNhap = new JButton("Đăng nhập");
        btnDangNhap.setBackground(Color.RED);
        btnDangNhap.setForeground(Color.WHITE);
        btnDangNhap.setFont(new Font("Arial", Font.BOLD, 16));
        btnDangNhap.setFocusPainted(false);
        btnDangNhap.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnDangNhap.setPreferredSize(fullWidthButtonSize);
        btnDangNhap.setMaximumSize(fullWidthButtonSize);
        mainPanel.add(btnDangNhap);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        JLabel lblHoac = new JLabel("hoặc", JLabel.CENTER);
        lblHoac.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblHoac);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton btnZalo = new JButton("Nhận OTP trên Zalo");
        btnZalo.setBackground(Color.BLUE);
        btnZalo.setForeground(Color.WHITE);
        btnZalo.setFont(new Font("Arial", Font.BOLD, 16));
        btnZalo.setFocusPainted(false);
        btnZalo.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnZalo.setPreferredSize(fullWidthButtonSize);
        btnZalo.setMaximumSize(fullWidthButtonSize);
        mainPanel.add(btnZalo);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton btnGoogle = new JButton("Đăng nhập với Google");
        ImageIcon googleIcon = new ImageIcon(getClass().getResource("/Resource/Google.png"));
        Image scaledGoogle = googleIcon.getImage().getScaledInstance(34, 24, Image.SCALE_SMOOTH);
        btnGoogle.setIcon(new ImageIcon(scaledGoogle));
        btnGoogle.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnGoogle.setPreferredSize(fullWidthButtonSize);
        btnGoogle.setMaximumSize(fullWidthButtonSize);
        btnGoogle.setFocusPainted(false);
        mainPanel.add(btnGoogle);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton btnApple = new JButton("Đăng nhập với Apple");
        ImageIcon appleIcon = new ImageIcon(getClass().getResource("/Resource/Apple.png"));
        Image scaledApple = appleIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        btnApple.setIcon(new ImageIcon(scaledApple));
        btnApple.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnApple.setPreferredSize(fullWidthButtonSize);
        btnApple.setMaximumSize(fullWidthButtonSize);
        btnApple.setFocusPainted(false);
        mainPanel.add(btnApple);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        JLabel lblGioiThieu = new JLabel("Bạn có mã giới thiệu không?");
        lblGioiThieu.setForeground(Color.BLUE);
        lblGioiThieu.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblGioiThieu);
        mainPanel.add(Box.createVerticalGlue());

        JPanel termsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        termsPanel.add(new JLabel("Bằng cách 'đăng nhập', tôi đồng ý"));
        JLabel lbl1 = new JLabel("Điều khoản & Điều kiện");
        lbl1.setForeground(Color.BLUE);
        JLabel lbl2 = new JLabel("Chính sách bảo mật");
        lbl2.setForeground(Color.BLUE);
        termsPanel.add(lbl1);
        termsPanel.add(lbl2);
        mainPanel.add(termsPanel);

        add(mainPanel, BorderLayout.CENTER);
    }
}
