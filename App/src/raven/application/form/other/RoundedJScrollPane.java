package raven.application.form.other;

import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Rectangle;

public class RoundedJScrollPane extends JScrollPane {
    private int cornerRadius = 30; // Bán kính bo góc, đồng bộ với RoundedPanel
    private boolean isMouseOver = false;

    public RoundedJScrollPane() {
        super();
        setOpaque(false); // Đặt trong suốt để vẽ nền tùy chỉnh
        getViewport().setOpaque(false); // Đảm bảo viewport cũng trong suốt
        setBorder(new RoundedBorder(cornerRadius)); // Sử dụng RoundedBorder để vẽ viền bo góc
        initMouseListener();
    }

    public RoundedJScrollPane(Component view) {
        super(view);
        setOpaque(false);
        getViewport().setOpaque(false);
        setBorder(new RoundedBorder(cornerRadius));
        initMouseListener();
    }

    private void initMouseListener() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                isMouseOver = true;
                setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // Hiển thị thanh cuộn khi chuột vào
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isMouseOver = false;
                setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS); // Ẩn thanh cuộn khi chuột ra
                repaint();
            }
        });

        // Đảm bảo thanh cuộn ẩn ban đầu
        setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_NEVER);
        setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);

        // Tùy chỉnh giao diện thanh cuộn dọc
        getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(150, 150, 150, 180); // Màu của thanh kéo (thumb), có độ trong suốt
                this.trackColor = new Color(200, 200, 200, 50); // Màu của đường ray (track), nhạt và trong suốt
            }

            @Override
            protected void paintTrack(Graphics g, javax.swing.JComponent c, Rectangle trackBounds) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(trackColor);
                g2.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
                g2.dispose();
            }

            @Override
            protected void paintThumb(Graphics g, javax.swing.JComponent c, Rectangle thumbBounds) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(thumbColor);
                g2.fillRoundRect(thumbBounds.x + 2, thumbBounds.y, thumbBounds.width - 4, thumbBounds.height, 8, 8);
                g2.dispose();
            }

            @Override
            protected javax.swing.JButton createDecreaseButton(int orientation) {
                return createZeroButton(); // Ẩn nút tăng (mũi tên lên)
            }

            @Override
            protected javax.swing.JButton createIncreaseButton(int orientation) {
                return createZeroButton(); // Ẩn nút giảm (mũi tên xuống)
            }

            private javax.swing.JButton createZeroButton() {
                javax.swing.JButton button = new javax.swing.JButton();
                button.setPreferredSize(new java.awt.Dimension(0, 0));
                button.setMinimumSize(new java.awt.Dimension(0, 0));
                button.setMaximumSize(new java.awt.Dimension(0, 0));
                return button;
            }
        });

        // Đặt chiều rộng thanh cuộn
        getVerticalScrollBar().setPreferredSize(new java.awt.Dimension(8, Integer.MAX_VALUE)); // Chiều rộng 8px
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
        RoundRectangle2D roundRect = new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);
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

        // Cắt vùng vẽ của các thành phần con (viewport và thanh cuộn) thành hình bo góc
        RoundRectangle2D roundRect = new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);
        g2.setClip(roundRect);

        // Vẽ các thành phần con
        super.paintChildren(g2);

        g2.dispose();
    }

    public int getCornerRadius() {
        return cornerRadius;
    }

    public void setCornerRadius(int cornerRadius) {
        this.cornerRadius = cornerRadius;
        setBorder(new RoundedBorder(cornerRadius)); // Cập nhật viền với bán kính mới
        repaint();
    }

    // Lớp nội tại để vẽ viền bo góc
    private static class RoundedBorder implements Border {
        private final int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(2, 2, 2, 2); // Giữ khoảng cách nhỏ 2px
        }

        @Override
        public boolean isBorderOpaque() {
            return false;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(c.getBackground().darker()); // Viền có màu tối hơn nền một chút
            g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
            g2.dispose();
        }
    }
}