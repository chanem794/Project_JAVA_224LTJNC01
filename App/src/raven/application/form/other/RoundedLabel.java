package raven.application.form.other;

import javax.swing.JLabel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

public class RoundedLabel extends JLabel {
    private int cornerRadius = 30; // Bán kính bo góc mặc định

    public RoundedLabel() {
        super();
        setOpaque(false); // Đặt trong suốt để vẽ nền tùy chỉnh
    }

    public RoundedLabel(String text) {
        super(text);
        setOpaque(false); // Đặt trong suốt để vẽ nền tùy chỉnh
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Vẽ nền bo góc
        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius));

        // Gọi phương thức vẽ văn bản của JLabel
        super.paintComponent(g);

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