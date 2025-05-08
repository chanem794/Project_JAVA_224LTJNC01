package raven.application.form.other;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

public class RoundedPanelNew extends JPanel {
    private int cornerRadius = 30; // Bán kính bo góc

    public RoundedPanelNew() {
        setOpaque(false); // Đặt trong suốt để vẽ nền tùy chỉnh
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Che phủ vùng ngoài bo góc với màu của container cha
        g2.setColor(getParent() != null ? getParent().getBackground() : getBackground());
        g2.fillRect(0, 0, getWidth(), getHeight());

        // Tạo hình bo góc để cắt vùng vẽ
        RoundRectangle2D roundRect = new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
        g2.setClip(roundRect);

        // Vẽ nền bo góc với màu hiện tại
        g2.setColor(getBackground());
        g2.fill(roundRect);

        g2.dispose();
    }

    @Override
    protected void paintChildren(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Cắt vùng vẽ của các thành phần con thành hình bo góc
        RoundRectangle2D roundRect = new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
        g2.setClip(roundRect);

        // Vẽ các thành phần con (như JTextField, JLabel)
        super.paintChildren(g2);

        g2.dispose();
    }

    public int getCornerRadius() {
        return cornerRadius;
    }

    public void setCornerRadius(int cornerRadius) {
        this.cornerRadius = cornerRadius;
        repaint();
    }
}