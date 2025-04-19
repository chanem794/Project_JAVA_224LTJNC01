package UI;

import javax.swing.*;
import java.awt.*;

public class TaiKhoanUI extends JPanel {
    public TaiKhoanUI() {
        setLayout(new BorderLayout(10, 10));

        JLabel lblTieuDe = new JLabel("Đăng nhập để quản lý vé của bạn", JLabel.CENTER);
        lblTieuDe.setFont(new Font("Arial", Font.BOLD, 20));
        add(lblTieuDe, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JButton btnDangNhap = new JButton("Đăng nhập");
        btnDangNhap.setBackground(Color.RED);
        btnDangNhap.setForeground(Color.WHITE);
        btnDangNhap.setFont(new Font("Arial", Font.BOLD, 16));
        centerPanel.add(btnDangNhap);

        JLabel lblDangKy = new JLabel("Bạn chưa có tài khoản? ");
        JButton btnDangKy = new JButton("Đăng ký");
        btnDangKy.setForeground(Color.BLUE);
        btnDangKy.setBorderPainted(false);
        btnDangKy.setContentAreaFilled(false);
        JPanel dangKyPanel = new JPanel();
        dangKyPanel.add(lblDangKy);
        dangKyPanel.add(btnDangKy);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        centerPanel.add(dangKyPanel);

        // Thông tin chi tiết
        JLabel lblThongTin = new JLabel("Thông tin chi tiết của tôi");
        lblThongTin.setFont(new Font("Arial", Font.BOLD, 16));
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        centerPanel.add(lblThongTin);
        centerPanel.add(taoButtonRow("Mã vé"));
        centerPanel.add(taoButtonRow("Thông tin cá nhân"));
        centerPanel.add(taoButtonRow("Hành khách"));

        // Thanh toán
        JLabel lblThanhToan = new JLabel("Thanh toán");
        lblThanhToan.setFont(new Font("Arial", Font.BOLD, 16));
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        centerPanel.add(lblThanhToan);
        centerPanel.add(taoButtonRow("Ví TTBus"));

        // Nhiều hơn
        JLabel lblMore = new JLabel("Nhiều hơn");
        lblMore.setFont(new Font("Arial", Font.BOLD, 16));
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        centerPanel.add(lblMore);
        centerPanel.add(taoButtonRow("Ưu đãi"));
        centerPanel.add(taoButtonRow("Giới thiệu"));
        centerPanel.add(taoButtonRow("Biết về Đặt Vé Bus"));
        centerPanel.add(taoButtonRow("Đánh giá ứng dụng"));
        centerPanel.add(taoButtonRow("Hỗ trợ"));
        centerPanel.add(taoButtonRow("Cài đặt tài khoản"));

        // Sở thích
        JLabel lblSothich = new JLabel("Sở thích");
        lblSothich.setFont(new Font("Arial", Font.BOLD, 16));
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        centerPanel.add(lblSothich);
        centerPanel.add(taoButtonRow("Quốc gia: Việt Nam"));
        centerPanel.add(taoButtonRow("Tiền tệ: VND"));
        centerPanel.add(taoButtonRow("Ngôn ngữ: Tiếng Việt"));

        add(new JScrollPane(centerPanel), BorderLayout.CENTER);
        
        btnDangNhap.addActionListener(e -> {
        DangNhapUI dialog = new DangNhapUI((JFrame) SwingUtilities.getWindowAncestor(this));
        dialog.setVisible(true);
        });

        btnDangKy.addActionListener(e -> {
        DangKyUI dialog = new DangKyUI((JFrame) SwingUtilities.getWindowAncestor(this));
        dialog.setVisible(true);
        });

    }

    private JPanel taoButtonRow(String text) {
        JPanel row = new JPanel(new BorderLayout());
        JLabel label = new JLabel(text);
        JButton btnChiTiet = new JButton(">>>");
        row.add(label, BorderLayout.WEST);
        row.add(btnChiTiet, BorderLayout.EAST);
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        return row;
    }
    
}