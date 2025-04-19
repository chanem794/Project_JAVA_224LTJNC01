package UI;

import Data_Access_Layer.VeCuaToiDAO;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VeCuaToiUI extends JPanel {
    private JTextArea area;
    private JTextField txtMaKH;

    public VeCuaToiUI() {
        setLayout(new BorderLayout());
        txtMaKH = new JTextField(10);
        JButton btnXem = new JButton("Xem vé");
        area = new JTextArea();
        area.setEditable(false);

        JPanel top = new JPanel();
        top.add(new JLabel("Mã KH:"));
        top.add(txtMaKH);
        top.add(btnXem);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(area), BorderLayout.CENTER);

        btnXem.addActionListener(e -> {
            String ma = txtMaKH.getText();
            area.setText("");
            ArrayList<String> dsVe = VeCuaToiDAO.layVeTheoKhachHang(ma);
            for (String ve : dsVe) {
                area.append(ve + "\n");
            }
        });
    }
}