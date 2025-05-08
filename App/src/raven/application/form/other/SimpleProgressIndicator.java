/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raven.application.form.other;

/**
 *
 * @author MINH HUY
 */

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;
import javax.swing.ListModel;
import javax.swing.border.EmptyBorder;

/**
 * A simplified progress indicator connected to a ListModel.
 */
public class SimpleProgressIndicator<E> extends JComponent {

    // Getters and Setters
    public ListModel<E> getModel() {
        return model;
    }

    public void setModel(ListModel<E> model) {
        this.model = model;
        repaint();
        revalidate();
    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
        repaint();
    }

    public Color getProgressColor() {
        return progressColor;
    }

    public void setProgressColor(Color progressColor) {
        this.progressColor = progressColor;
        repaint();
    }

    public int getProgressSize() {
        return progressSize;
    }

    public void setProgressSize(int progressSize) {
        this.progressSize = progressSize;
        repaint();
        revalidate();
    }

    // Fields
    private ListModel<E> model;
    private float progress = -1;
    private Color progressColor = new Color(63, 171, 222);
    private int progressSize = 35;

    public SimpleProgressIndicator() {
        setOpaque(true);
        setBackground(new Color(250, 250, 250));
        setForeground(new Color(200, 200, 200));
        setBorder(new EmptyBorder(5, 20, 5, 20));
        setLayout(new LayoutManager() {
            @Override
            public void addLayoutComponent(String name, Component comp) {}
            @Override
            public void removeLayoutComponent(Component comp) {}
            @Override
            public Dimension preferredLayoutSize(Container parent) {
                return getSize(parent);
            }
            @Override
            public Dimension minimumLayoutSize(Container parent) {
                return getSize(parent);
            }
            @Override
            public void layoutContainer(Container parent) {}
        });
    }

    private Dimension getSize(Container parent) {
        if (model != null) {
            int width = (getInsets().left + getInsets().right) + (progressSize * model.getSize());
            int height = (getInsets().top + getInsets().bottom) + progressSize;
            return new Dimension(width, height);
        }
        return new Dimension(100, 100);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (isOpaque()) {
            g.setColor(getBackground());
            g.fillRect(0, 0, getWidth(), getHeight());
        }
        if (model != null && model.getSize() > 0) {
            BufferedImage img = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = img.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int x = model.getSize() == 1 ? getWidth() / 2 - (progressSize / 2) : getInsets().left;
            int y = getInsets().top;
            int width = getWidth() - x * 2 - progressSize;
            int size = model.getSize() == 1 ? width : width / (model.getSize() - 1);

            for (int i = 0; i < model.getSize(); i++) {
                int sx = x + (i * size);
                Ellipse2D circle = new Ellipse2D.Double(sx, y, progressSize, progressSize);
                g2.setColor(i <= (int) progress ? progressColor : getForeground());
                g2.fill(circle);

                // Draw progress number
                g2.setFont(getFont());
                FontMetrics fm = g2.getFontMetrics();
                String text = (i + 1) + "";
                Rectangle2D textBounds = fm.getStringBounds(text, g2);
                double fx = (progressSize - textBounds.getWidth()) / 2;
                double fy = (progressSize - textBounds.getHeight()) / 2 + fm.getAscent();
                g2.setColor(Color.WHITE);
                g2.drawString(text, (int) (sx + fx), (int) (y + fy));
            }

            g2.dispose();
            g.drawImage(img, 0, 0, null);
        }
        super.paintComponent(g);
    }
}
