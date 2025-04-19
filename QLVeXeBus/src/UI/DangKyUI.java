package UI;

import javax.swing.*;
import java.awt.*;

public class DangKyUI extends JDialog {
    public DangKyUI(JFrame parent) {

        setSize(800, 600);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());
        setResizable(false);

        JLabel imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.CENTER);
        ImageIcon icon = new ImageIcon(getClass().getResource("/Resource/DangNhap.jpg"));
        Image scaledImage = icon.getImage().getScaledInstance(380, 600, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(scaledImage));
        add(imageLabel, BorderLayout.WEST);

        Dimension fullWidthButtonSize = new Dimension(340, 45);
        Dimension inputFieldSize = new Dimension(340, 40);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        JLabel lblTitle = new JLabel("Đăng Ký");
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
        JTextField txtMatKhau = new JTextField();
        txtMatKhau.setFont(new Font("Arial", Font.PLAIN, 14));
        txtMatKhau.setPreferredSize(inputFieldSize);
        txtMatKhau.setMaximumSize(inputFieldSize);
        matKhauPanel.add(lblMatKhau, BorderLayout.WEST);
        matKhauPanel.add(txtMatKhau, BorderLayout.CENTER);
        mainPanel.add(matKhauPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JPanel nhapLaiPanel = new JPanel(new BorderLayout(5, 5));
        nhapLaiPanel.setMaximumSize(inputFieldSize);
        JLabel lblNhapLai = new JLabel("  Nhập lại MK:   ");
        JTextField txtNhapLai = new JTextField();
        txtNhapLai.setFont(new Font("Arial", Font.PLAIN, 14));
        txtNhapLai.setPreferredSize(inputFieldSize);
        txtNhapLai.setMaximumSize(inputFieldSize);
        nhapLaiPanel.add(lblNhapLai, BorderLayout.WEST);
        nhapLaiPanel.add(txtNhapLai, BorderLayout.CENTER);
        mainPanel.add(nhapLaiPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton btnDangKy = new JButton("Đăng ký");
        btnDangKy.setBackground(Color.RED);
        btnDangKy.setForeground(Color.WHITE);
        btnDangKy.setFont(new Font("Arial", Font.BOLD, 16));
        btnDangKy.setFocusPainted(false);
        btnDangKy.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnDangKy.setPreferredSize(fullWidthButtonSize);
        btnDangKy.setMaximumSize(fullWidthButtonSize);
        mainPanel.add(btnDangKy);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        JLabel lblHoac = new JLabel("hoặc", JLabel.CENTER);
        lblHoac.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblHoac);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton btnZalo = new JButton("Đăng ký với Zalo");
        btnZalo.setBackground(Color.BLUE);
        btnZalo.setForeground(Color.WHITE);
        btnZalo.setFont(new Font("Arial", Font.BOLD, 16));
        btnZalo.setFocusPainted(false);
        btnZalo.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnZalo.setPreferredSize(fullWidthButtonSize);
        btnZalo.setMaximumSize(fullWidthButtonSize);
        mainPanel.add(btnZalo);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton btnGoogle = new JButton("Đăng ký với Google");
        ImageIcon googleIcon = new ImageIcon(getClass().getResource("/Resource/Google.png"));
        Image scaledGoogle = googleIcon.getImage().getScaledInstance(34, 24, Image.SCALE_SMOOTH);
        btnGoogle.setIcon(new ImageIcon(scaledGoogle));
        btnGoogle.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnGoogle.setPreferredSize(fullWidthButtonSize);
        btnGoogle.setMaximumSize(fullWidthButtonSize);
        btnGoogle.setFocusPainted(false);
        mainPanel.add(btnGoogle);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton btnApple = new JButton("Đăng ký với Apple");
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
        termsPanel.add(new JLabel("Bằng cách 'đăng ký', tôi đồng ý"));
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
