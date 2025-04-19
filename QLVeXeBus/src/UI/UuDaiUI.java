package UI;

import javax.swing.*;
import java.awt.*;

public class UuDaiUI extends JPanel {
    public UuDaiUI() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);

        // Header ưu đãi
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(154, 86, 243));
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblUuDai = new JLabel("🎟️ Ưu đãi", JLabel.LEFT);
        lblUuDai.setFont(new Font("Arial", Font.BOLD, 26));
        lblUuDai.setForeground(Color.WHITE);

        JLabel lblMoTa = new JLabel("Nhận giá tốt nhất", JLabel.LEFT);
        lblMoTa.setFont(new Font("Arial", Font.PLAIN, 18));
        lblMoTa.setForeground(Color.WHITE);

        JLabel lblNhaXe = new JLabel("20+ nhà xe", JLabel.LEFT);
        lblNhaXe.setFont(new Font("Arial", Font.ITALIC, 14));
        lblNhaXe.setForeground(Color.WHITE);

        headerPanel.add(lblUuDai);
        headerPanel.add(lblMoTa);
        headerPanel.add(lblNhaXe);

        contentPanel.add(headerPanel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Nhóm ưu đãi 1
        contentPanel.add(taoNhomUuDai("Các tuyến đường hàng đầu quanh Hồ Chí Minh", new String[][] {
                {"47%", "Hồ Chí Minh → Đà Lạt", "dalat.png"},
                {"34%", "Đà Lạt → Hồ Chí Minh", "hochiminh.png"},
        }));

        // Nhóm ưu đãi 2
        contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        contentPanel.add(taoNhomUuDai("Các tuyến đường hàng đầu quanh Hà Nội", new String[][] {
                {"6%", "Hồ Chí Minh → Sóc Trăng", "soctrang.png"},
                {"10%", "Thanh Hóa → Hà Nội", "hanoi.png"},
        }));

        add(new JScrollPane(contentPanel), BorderLayout.CENTER);
    }

    private JPanel taoNhomUuDai(String tieuDe, String[][] uuDaiData) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel lblTieuDe = new JLabel(tieuDe);
        lblTieuDe.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(lblTieuDe);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        JPanel uuDaiRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        uuDaiRow.setBackground(Color.WHITE);

        for (String[] uu : uuDaiData) {
            String tile = uu[0];
            String chuyen = uu[1];
            String imageFile = uu[2];

            JPanel card = new JPanel();
            card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
            card.setPreferredSize(new Dimension(200, 200));
            card.setBackground(new Color(245, 245, 245));
            card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

            JLabel lblTile = new JLabel("Lên đến " + tile + " tắt");
            lblTile.setFont(new Font("Arial", Font.BOLD, 14));
            lblTile.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel lblChuyen = new JLabel(chuyen);
            lblChuyen.setFont(new Font("Arial", Font.PLAIN, 13));
            lblChuyen.setAlignmentX(Component.CENTER_ALIGNMENT);

            ImageIcon icon = new ImageIcon(imageFile);
            Image scaledImage = icon.getImage().getScaledInstance(180, 100, Image.SCALE_SMOOTH);
            JLabel lblImage = new JLabel(new ImageIcon(scaledImage));
            lblImage.setAlignmentX(Component.CENTER_ALIGNMENT);

            card.add(Box.createRigidArea(new Dimension(0, 5)));
            card.add(lblTile);
            card.add(lblChuyen);
            card.add(lblImage);

            uuDaiRow.add(card);
        }

        panel.add(uuDaiRow);
        return panel;
    }
}
