package UI;

import Business_Logic_Layer.TinhBLL;
import Business_Logic_Layer.TrangChuBLL;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class TrangChuUI extends JPanel {
    private JComboBox<String> cboTu, cboDen;
    private JTextField txtNgay;
    private JTextArea areaKetQua;
    private TinhBLL tinhBLL;
    private TrangChuBLL trangChuBLL;

    public TrangChuUI() {
        setLayout(new BorderLayout(10, 10));
        tinhBLL = new TinhBLL();
        trangChuBLL = new TrangChuBLL();

        // Panel chọn điểm đi/đến và ngày
        JPanel panelChon = new JPanel();
        panelChon.setLayout(new BoxLayout(panelChon, BoxLayout.Y_AXIS));

        JLabel lblTitle = new JLabel("Vé xe", JLabel.LEFT);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        panelChon.add(lblTitle);

        JLabel lblSubtitle = new JLabel("Ưu đãi & giảm giá hấp dẫn");
        lblSubtitle.setFont(new Font("Arial", Font.PLAIN, 14));
        panelChon.add(lblSubtitle);

        // Lấy danh sách tỉnh
        ArrayList<String> dsTinh = tinhBLL.getDanhSachTinh();
        String[] tinhArray = dsTinh.toArray(new String[0]);

        JPanel tuDenPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        tuDenPanel.add(new JLabel("Từ:"));
        cboTu = new JComboBox<>(tinhArray);
        tuDenPanel.add(cboTu);

        tuDenPanel.add(new JLabel("Đến:"));
        cboDen = new JComboBox<>(tinhArray);
        tuDenPanel.add(cboDen);
        panelChon.add(tuDenPanel);

        JPanel ngayPanel = new JPanel();
        txtNgay = new JTextField(10);
        txtNgay.setText(LocalDate.now().toString());
        JButton btnHomNay = new JButton("Hôm nay");
        JButton btnNgayMai = new JButton("Ngày mai");

        btnHomNay.addActionListener(e -> txtNgay.setText(LocalDate.now().toString()));
        btnNgayMai.addActionListener(e -> txtNgay.setText(LocalDate.now().plusDays(1).toString()));

        ngayPanel.add(new JLabel("Ngày hành trình:"));
        ngayPanel.add(txtNgay);
        ngayPanel.add(btnHomNay);
        ngayPanel.add(btnNgayMai);
        panelChon.add(ngayPanel);

        JButton btnTimKiem = new JButton("\uD83D\uDD0D Tìm kiếm xe");
        btnTimKiem.setBackground(Color.RED);
        btnTimKiem.setForeground(Color.WHITE);
        btnTimKiem.setFont(new Font("Arial", Font.BOLD, 16));
        panelChon.add(Box.createRigidArea(new Dimension(0, 10)));
        panelChon.add(btnTimKiem);

        add(panelChon, BorderLayout.NORTH);

        // Panel kết quả
        JPanel uuDaiPanel = new JPanel(new BorderLayout());
        JLabel lblUuDai = new JLabel("\uD83C\uDF1F Kết quả tìm kiếm", JLabel.LEFT);
        lblUuDai.setFont(new Font("Arial", Font.BOLD, 18));
        uuDaiPanel.add(lblUuDai, BorderLayout.NORTH);

        areaKetQua = new JTextArea();
        areaKetQua.setEditable(false);
        uuDaiPanel.add(new JScrollPane(areaKetQua), BorderLayout.CENTER);

        add(uuDaiPanel, BorderLayout.CENTER);

        // Sự kiện tìm kiếm
        btnTimKiem.addActionListener(e -> timKiemChuyenDi());
    }

    private void timKiemChuyenDi() {
        String tu = (String) cboTu.getSelectedItem();
        String den = (String) cboDen.getSelectedItem();
        String ngay = txtNgay.getText();

        // Gọi BLL để tìm chuyến đi
        ArrayList<String> dsChuyenDi = trangChuBLL.timKiemChuyenDi(tu, den, ngay);
        areaKetQua.setText("");
        if (dsChuyenDi.isEmpty()) {
            areaKetQua.append("Không tìm thấy chuyến đi phù hợp.\n");
        } else {
            for (String chuyen : dsChuyenDi) {
                areaKetQua.append("- " + chuyen + "\n");
            }
        }
    }
}