package raven.application.form.other;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

public class RoundedJScrollPane extends JScrollPane {

    public RoundedJScrollPane(Component view) {
        super(view);
        setOpaque(false);
        getViewport().setOpaque(false);
        setBorder(new RoundedBorder(15));
    }

    public RoundedJScrollPane() {
        super();
        setOpaque(false);
        getViewport().setOpaque(false);
        setBorder(new RoundedBorder(15));
    }

    // Bo góc viền
    private static class RoundedBorder implements Border {
        private final int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(radius + 1, radius + 1, radius + 1, radius + 1);
        }

        @Override
        public boolean isBorderOpaque() {
            return false;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.GRAY); // Màu viền
            g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
            g2.dispose();
        }
    }
}
