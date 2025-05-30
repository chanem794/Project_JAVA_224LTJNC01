package raven.application.form.other;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.*;
import java.awt.*;

public class PanelDataStation {

    private static final Color BORDER_COLOR = new Color(102, 255, 51);
    private static final Color TEXT_COLOR = new Color(102, 102, 102);
    private static final Font STATION_FONT = new Font("Segoe UI", Font.BOLD, 14);
    private static final String ICON_PATH = "/raven/icon/png/location.png";

    public static JPanel createStationPanel(String time, String station, String address) {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createLineBorder(BORDER_COLOR));

        JRadioButton radioButton = new JRadioButton();
        radioButton.putClientProperty("FlatLaf.style", ""
                + "background:#FFFFFF;"
                + "foreground:#102102;"
                + "selectionColor:#2196F3;"
                + "focusColor:#2196F3;"
                + "borderWidth:1;"
                + "arc:10");
        radioButton.setFocusable(true);
        radioButton.setEnabled(true);

        JLabel timeLabel = new JLabel(time);
        timeLabel.setForeground(TEXT_COLOR);
        JLabel stationLabel = new JLabel(station);
        stationLabel.setFont(STATION_FONT);
        stationLabel.setForeground(TEXT_COLOR);
        JLabel addressLabel = new JLabel(address);
        addressLabel.setForeground(TEXT_COLOR);

        JLabel mapIcon = new JLabel();
        ImageIcon icon = new ImageIcon(PanelDataStation.class.getResource(ICON_PATH));
        if (icon.getImage() != null) {
            mapIcon.setIcon(icon);
        } else {
            mapIcon.setText("Biểu tượng không tải được");
        }
        JLabel mapLabel = new JLabel("Bản đồ");
        mapLabel.setForeground(TEXT_COLOR);

        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(radioButton)
                                .addGap(18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(timeLabel, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(stationLabel, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(addressLabel, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(mapIcon)
                                        .addComponent(mapLabel, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(timeLabel, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(stationLabel, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(addressLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(radioButton, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(mapIcon, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(mapLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
                                .addGap(20))
        );
        return panel;
    }
}
