package UI;

import javax.swing.*;
import java.awt.*;

public class HoTroUI extends JPanel {
    public HoTroUI() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);

        // Panel chính giữa
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        mainPanel.setBackground(Color.WHITE);

        // Tiêu đề
        JLabel lblTieuDe = new JLabel("Trợ giúp");
        lblTieuDe.setFont(new Font("Arial", Font.BOLD, 24));
        lblTieuDe.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(lblTieuDe);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Ảnh minh họa
        ImageIcon image = new ImageIcon("support.png"); // ảnh cần lưu cùng thư mục với project
        JLabel imgLabel = new JLabel(image);
        imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(imgLabel);

        // Mô tả
        JLabel lblMoTa = new JLabel("Để quản lý đặt chỗ liền mạch và hỗ trợ khách hàng", JLabel.CENTER);
        lblMoTa.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblMoTa.setFont(new Font("Arial", Font.PLAIN, 14));
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(lblMoTa);

        // Nút đăng nhập
        JButton btnDangNhap = new JButton("Đăng nhập vào tài khoản của bạn");
        btnDangNhap.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnDangNhap.setBackground(Color.RED);
        btnDangNhap.setForeground(Color.WHITE);
        btnDangNhap.setFocusPainted(false);
        btnDangNhap.setFont(new Font("Arial", Font.BOLD, 16));
        btnDangNhap.setMaximumSize(new Dimension(300, 40));
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        mainPanel.add(btnDangNhap);

        // Câu hỏi thường gặp
        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        mainPanel.add(taoMuc("📄 Câu hỏi thường gặp", "Đọc tất cả các câu hỏi thường gặp"));

        // Chủ đề khác
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(taoMuc("📄 Chủ đề khác", "Duyệt các chủ đề khác"));

        add(new JScrollPane(mainPanel), BorderLayout.CENTER);
        
        btnDangNhap.addActionListener(e -> {
        DangNhapUI dialog = new DangNhapUI((JFrame) SwingUtilities.getWindowAncestor(this));
        dialog.setVisible(true);
        });
    }

    private JPanel taoMuc(String tieuDe, String noiDung) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        JLabel lblTieuDe = new JLabel(tieuDe);
        lblTieuDe.setFont(new Font("Arial", Font.BOLD, 16));

        JButton btn = new JButton(noiDung);
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setBorder(BorderFactory.createEmptyBorder());
        btn.setHorizontalAlignment(SwingConstants.RIGHT);
        btn.setFont(new Font("Arial", Font.PLAIN, 14));

        panel.add(lblTieuDe, BorderLayout.NORTH);
        panel.add(btn, BorderLayout.SOUTH);

        return panel;
    }
}
