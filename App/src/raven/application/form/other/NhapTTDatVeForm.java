/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package raven.application.form.other;
import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Image;
import javax.swing.ImageIcon;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import net.miginfocom.swing.MigLayout;
import raven.application.Application;
import raven.application.form.other.component.PanelThanhToan;


/**
 *
 * @author DELL
 */
public class NhapTTDatVeForm extends javax.swing.JPanel {
        
    /**
     * Creates new form NhapTTDatVeForm
     */
    public NhapTTDatVeForm() {
        initComponents();
        init();
        
    }
    private void init() {
        
        setLayout(new MigLayout("align left top", "", ""));

        lbTTLienHe.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h2.font");
        lbTamTinh.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h2.font");
        lbTienIch.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h2.font");
        lbTTChuyenDi.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h2.font");
        lbGiaVe.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h3.font");
        lbTGDi.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h2.font");
        lbTGDen.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h2.font");
        lbBaoHiem3.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h3.font");
        lbBaoHiem4.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h3.font");
        panelTTLienHe.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        panelChuThich.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        panelTienIch.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        panelBaoHiem.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        panelTamTinh.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        panelTTChuyenDi.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        panelTTChuyenDiCon.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        
//      Set image
        ImageIcon BusImage = new ImageIcon(getClass().getResource("/raven/thanhtoan/icon/IBus.jpeg"));
        Image image = BusImage.getImage().getScaledInstance(88, 88, Image.SCALE_SMOOTH);
        lbImage.setIcon(new ImageIcon(image));

//      Thêm phần chữ mờ gọi ý cho người dùng nhập dữ liệu
        txtTenNguoiDi.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Tên người đi *");
        txtSDT.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Số điện thoại *");
        txtEmail.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Email để nhận thông tin đặt chỗ *");  

//      Set các icon         
        ImageIcon Busicon = new ImageIcon(getClass().getResource("/raven/thanhtoan/icon/bus.png"));
        Image scaledBusImage = Busicon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);  // hoặc 24x24
        ImageIcon resizedIconBusIcon = new ImageIcon(scaledBusImage);
        lbNgayThang.setIcon(resizedIconBusIcon);
        lbNgayThang.setText("T5, 08/05/2025");
        lbNgayThang.setIconTextGap(10);  // Khoảng cách giữa ảnh và text
        
        ImageIcon BaoHiemIcon = new ImageIcon(getClass().getResource("/raven/thanhtoan/icon/baohiem.png"));
        Image scaledBaoHiemImage = BaoHiemIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);  // hoặc 24x24
        ImageIcon resizedIconBaoHiemIcon = new ImageIcon(scaledBaoHiemImage);
        lbGiaBaoHiem.setIcon(resizedIconBaoHiemIcon);
        lbGiaBaoHiem.setText("Bảo hiểm chuyến đi( +20.000đ/ghế)");
        lbGiaBaoHiem.setIconTextGap(5);  // Khoảng cách giữa ảnh và text

        ImageIcon DiemDenIcon = new ImageIcon(getClass().getResource("/raven/thanhtoan/icon/diemden.png"));
        Image scaledDiemDenImage = DiemDenIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);  // hoặc 24x24
        ImageIcon resizedIconDiemDenIcon = new ImageIcon(scaledDiemDenImage);
        jb1.setIcon(resizedIconDiemDenIcon);
        
        ImageIcon DiemDiIcon = new ImageIcon(getClass().getResource("/raven/thanhtoan/icon/diemdi.png"));
        Image scaledDiemDiImage = DiemDiIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);  // hoặc 24x24
        ImageIcon resizedIconDiemDiIcon = new ImageIcon(scaledDiemDiImage);
        jb2.setIcon(resizedIconDiemDiIcon);
//      Set lại các đoạn bị mất chữ 
        lbFooter.setText("<html><div style='text-align: center;'>Bạn sẽ sớm nhận được biển số xe, số điện thoại tài xế và dể dàng thay đổi điển đón trả sau khi đặt</div></html>");

}
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelNhapTTDatVe = new javax.swing.JPanel();
        panelTTLienHe = new javax.swing.JPanel();
        lbTTLienHe = new javax.swing.JLabel();
        txtTenNguoiDi = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        cbSDT = new javax.swing.JComboBox<>();
        txtEmail = new javax.swing.JTextField();
        panelChuThich = new javax.swing.JPanel();
        lbChuThichTTLH = new javax.swing.JLabel();
        panelTTChuyenDi = new javax.swing.JPanel();
        lbTTChuyenDi = new javax.swing.JLabel();
        panelTTChuyenDiCon = new javax.swing.JPanel();
        lbNgayThang = new javax.swing.JLabel();
        cmdChiTiet = new javax.swing.JButton();
        lbImage = new javax.swing.JLabel();
        lbTenNhaXe = new javax.swing.JLabel();
        lbTTXe = new javax.swing.JLabel();
        lbIconXe = new javax.swing.JLabel();
        lbTGDi = new javax.swing.JLabel();
        lbTGDen = new javax.swing.JLabel();
        lbDiaChiDen = new javax.swing.JLabel();
        lbDiaChiDi = new javax.swing.JLabel();
        lbDiaChiDiCuThe = new javax.swing.JLabel();
        lbDiaChiDenCuThe = new javax.swing.JLabel();
        jb1 = new javax.swing.JLabel();
        jb2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        panelButton = new javax.swing.JPanel();
        cmdTiepTuc = new javax.swing.JButton();
        lbFooter = new javax.swing.JLabel();
        lbDieuKhoan = new javax.swing.JLabel();
        panelTienIch = new javax.swing.JPanel();
        lbTienIch = new javax.swing.JLabel();
        panelBaoHiem = new javax.swing.JPanel();
        lbBaoHiem3 = new javax.swing.JLabel();
        lbBaoHiem4 = new javax.swing.JLabel();
        lbMoTaBaoHiem3 = new javax.swing.JLabel();
        lbMoTaBaoHiem4 = new javax.swing.JLabel();
        lbBaoHiem2 = new javax.swing.JLabel();
        lbGiaBaoHiem = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        panelTamTinh = new javax.swing.JPanel();
        lbTamTinh = new javax.swing.JLabel();
        lbGiaVe = new javax.swing.JLabel();
        cmdQuayLai = new javax.swing.JButton();

        panelNhapTTDatVe.setPreferredSize(new java.awt.Dimension(1000, 768));

        panelTTLienHe.setBackground(new java.awt.Color(153, 153, 153));

        lbTTLienHe.setText("Thông tin liên hệ");

        txtTenNguoiDi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenNguoiDiActionPerformed(evt);
            }
        });

        cbSDT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "VN +84", "IN +91", "CN +86", "MM +95", "MO +835", "KR +82", "JP +81", "TH +66", "AU +61" }));

        panelChuThich.setBackground(new java.awt.Color(204, 255, 255));
        panelChuThich.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 5, 0, 0, new java.awt.Color(0, 204, 204)));
        panelChuThich.setForeground(java.awt.Color.white);
        panelChuThich.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lbChuThichTTLH.setForeground(new java.awt.Color(0, 0, 0));
        lbChuThichTTLH.setText("Số điện thoại và email được sử dụng để gửi thông tin đơn hàng và liên hệ khi cần thiết.");

        javax.swing.GroupLayout panelChuThichLayout = new javax.swing.GroupLayout(panelChuThich);
        panelChuThich.setLayout(panelChuThichLayout);
        panelChuThichLayout.setHorizontalGroup(
            panelChuThichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChuThichLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(lbChuThichTTLH, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(86, Short.MAX_VALUE))
        );
        panelChuThichLayout.setVerticalGroup(
            panelChuThichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChuThichLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lbChuThichTTLH)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelTTLienHeLayout = new javax.swing.GroupLayout(panelTTLienHe);
        panelTTLienHe.setLayout(panelTTLienHeLayout);
        panelTTLienHeLayout.setHorizontalGroup(
            panelTTLienHeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTTLienHeLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(panelTTLienHeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelTTLienHeLayout.createSequentialGroup()
                        .addComponent(cbSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtSDT))
                    .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTTLienHe, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelChuThich, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTenNguoiDi))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        panelTTLienHeLayout.setVerticalGroup(
            panelTTLienHeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTTLienHeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTTLienHe, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTenNguoiDi, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelTTLienHeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelChuThich, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        panelTTChuyenDi.setBackground(new java.awt.Color(153, 153, 153));

        lbTTChuyenDi.setText("Thông tin chuyến đi");

        lbNgayThang.setText("CN, 19/06/2025");

        cmdChiTiet.setText("Chi tiết");

        lbTenNhaXe.setText("Đình Nhân");

        lbTTXe.setText("Limousine 32 phòng (WC)");

        lbIconXe.setText("Icon");

        lbTGDi.setText("16:30");

        lbTGDen.setText("10:20");

        lbDiaChiDen.setText("VP An Sương");

        lbDiaChiDi.setText("VP Đà Nẵng");

        lbDiaChiDiCuThe.setText("Địa chỉ đi cụ thể");

        lbDiaChiDenCuThe.setText("Địa chỉ đến cụ thể");

        jb1.setText("jLabel1");

        jb2.setText("jLabel2");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout panelTTChuyenDiConLayout = new javax.swing.GroupLayout(panelTTChuyenDiCon);
        panelTTChuyenDiCon.setLayout(panelTTChuyenDiConLayout);
        panelTTChuyenDiConLayout.setHorizontalGroup(
            panelTTChuyenDiConLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTTChuyenDiConLayout.createSequentialGroup()
                .addGroup(panelTTChuyenDiConLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTTChuyenDiConLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelTTChuyenDiConLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelTTChuyenDiConLayout.createSequentialGroup()
                                .addComponent(lbImage, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelTTChuyenDiConLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbIconXe, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panelTTChuyenDiConLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lbTTXe, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                                        .addComponent(lbTenNhaXe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(panelTTChuyenDiConLayout.createSequentialGroup()
                                .addComponent(lbNgayThang, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cmdChiTiet)
                                .addGap(29, 29, 29))))
                    .addGroup(panelTTChuyenDiConLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(panelTTChuyenDiConLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbTGDi, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                            .addComponent(lbTGDen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelTTChuyenDiConLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jb1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jb2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelTTChuyenDiConLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbDiaChiDenCuThe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelTTChuyenDiConLayout.createSequentialGroup()
                                .addGroup(panelTTChuyenDiConLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbDiaChiDiCuThe, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbDiaChiDi, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbDiaChiDen, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        panelTTChuyenDiConLayout.setVerticalGroup(
            panelTTChuyenDiConLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTTChuyenDiConLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTTChuyenDiConLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNgayThang)
                    .addComponent(cmdChiTiet))
                .addGap(6, 6, 6)
                .addGroup(panelTTChuyenDiConLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbImage, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelTTChuyenDiConLayout.createSequentialGroup()
                        .addComponent(lbTenNhaXe)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbTTXe)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbIconXe)))
                .addGap(18, 18, 18)
                .addGroup(panelTTChuyenDiConLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTTChuyenDiConLayout.createSequentialGroup()
                        .addGroup(panelTTChuyenDiConLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbDiaChiDi)
                            .addComponent(jb1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelTTChuyenDiConLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbDiaChiDiCuThe)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lbTGDi, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelTTChuyenDiConLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jb2)
                    .addComponent(lbDiaChiDen)
                    .addComponent(lbTGDen, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbDiaChiDenCuThe)
                .addGap(40, 40, 40))
        );

        javax.swing.GroupLayout panelTTChuyenDiLayout = new javax.swing.GroupLayout(panelTTChuyenDi);
        panelTTChuyenDi.setLayout(panelTTChuyenDiLayout);
        panelTTChuyenDiLayout.setHorizontalGroup(
            panelTTChuyenDiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTTChuyenDiLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(panelTTChuyenDiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelTTChuyenDiCon, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTTChuyenDi, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        panelTTChuyenDiLayout.setVerticalGroup(
            panelTTChuyenDiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTTChuyenDiLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(lbTTChuyenDi, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelTTChuyenDiCon, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        cmdTiepTuc.setBackground(new java.awt.Color(0, 204, 204));
        cmdTiepTuc.setForeground(java.awt.Color.white);
        cmdTiepTuc.setText("Tiếp tục");
        cmdTiepTuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdTiepTucActionPerformed(evt);
            }
        });

        lbFooter.setText("Bạn sẽ sớm nhận được biển số xe, số điện thoại tài xế và dể dàng thay đổi điển đón trả sau khi đặt");

        lbDieuKhoan.setText("Bằng việc nhấn Tiếp tục, bạn đồng ý với Chính sách bảo mật thanh toán và Quy chế");

        javax.swing.GroupLayout panelButtonLayout = new javax.swing.GroupLayout(panelButton);
        panelButton.setLayout(panelButtonLayout);
        panelButtonLayout.setHorizontalGroup(
            panelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelButtonLayout.createSequentialGroup()
                .addGroup(panelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelButtonLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(cmdTiepTuc, javax.swing.GroupLayout.PREFERRED_SIZE, 694, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(lbFooter, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelButtonLayout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(lbDieuKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        panelButtonLayout.setVerticalGroup(
            panelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelButtonLayout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(panelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbFooter, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdTiepTuc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbDieuKhoan)
                .addGap(12, 12, 12))
        );

        panelTienIch.setBackground(new java.awt.Color(153, 153, 153));

        lbTienIch.setText("Tiện ích");

        panelBaoHiem.setBackground(new java.awt.Color(204, 204, 204));

        lbBaoHiem3.setText("Bảo hiểm tai nạn");

        lbBaoHiem4.setText("Chính sách Hoàn Hủy chuyến đi");

        lbMoTaBaoHiem3.setText("Quyền lợi bảo hiểm lên đến 400 triệu đồng khi xảy ra tai nạn");

        lbMoTaBaoHiem4.setText("Hoàn trả lại 100% tiền vé thực tế nếu chuyến đi bị hủy bởi các lý do khách quan hoặc bất khả kháng về vấn đề sk");

        javax.swing.GroupLayout panelBaoHiemLayout = new javax.swing.GroupLayout(panelBaoHiem);
        panelBaoHiem.setLayout(panelBaoHiemLayout);
        panelBaoHiemLayout.setHorizontalGroup(
            panelBaoHiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBaoHiemLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBaoHiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBaoHiemLayout.createSequentialGroup()
                        .addComponent(lbBaoHiem3, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelBaoHiemLayout.createSequentialGroup()
                        .addGroup(panelBaoHiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbMoTaBaoHiem3, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbBaoHiem4, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbMoTaBaoHiem4))
                        .addContainerGap(9, Short.MAX_VALUE))))
        );
        panelBaoHiemLayout.setVerticalGroup(
            panelBaoHiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBaoHiemLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbBaoHiem3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbMoTaBaoHiem3)
                .addGap(23, 23, 23)
                .addComponent(lbBaoHiem4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbMoTaBaoHiem4)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        lbBaoHiem2.setText("Được bồi thường lên đến 400.000.000đ/ghế");

        lbGiaBaoHiem.setText("Bảo hiểm chuyến đi( +20.000đ/ghế)");

        jCheckBox1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jCheckBox1.setMaximumSize(new java.awt.Dimension(28, 28));
        jCheckBox1.setMinimumSize(new java.awt.Dimension(28, 28));

        javax.swing.GroupLayout panelTienIchLayout = new javax.swing.GroupLayout(panelTienIch);
        panelTienIch.setLayout(panelTienIchLayout);
        panelTienIchLayout.setHorizontalGroup(
            panelTienIchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTienIchLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(panelTienIchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTienIchLayout.createSequentialGroup()
                        .addGroup(panelTienIchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelBaoHiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbTienIch, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(24, Short.MAX_VALUE))
                    .addGroup(panelTienIchLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(panelTienIchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelTienIchLayout.createSequentialGroup()
                                .addComponent(lbBaoHiem2, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(panelTienIchLayout.createSequentialGroup()
                                .addComponent(lbGiaBaoHiem, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35))))))
        );
        panelTienIchLayout.setVerticalGroup(
            panelTienIchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTienIchLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(lbTienIch, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTienIchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbGiaBaoHiem)
                    .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbBaoHiem2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelBaoHiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(17, 17, 17))
        );

        panelTamTinh.setBackground(new java.awt.Color(153, 153, 153));

        lbTamTinh.setText("Tạm Tính");

        lbGiaVe.setForeground(new java.awt.Color(0, 204, 204));
        lbGiaVe.setText("550.000đ");

        javax.swing.GroupLayout panelTamTinhLayout = new javax.swing.GroupLayout(panelTamTinh);
        panelTamTinh.setLayout(panelTamTinhLayout);
        panelTamTinhLayout.setHorizontalGroup(
            panelTamTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTamTinhLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lbTamTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbGiaVe, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        panelTamTinhLayout.setVerticalGroup(
            panelTamTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTamTinhLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTamTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbTamTinh, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(lbGiaVe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        cmdQuayLai.setText("< Quay lại");

        javax.swing.GroupLayout panelNhapTTDatVeLayout = new javax.swing.GroupLayout(panelNhapTTDatVe);
        panelNhapTTDatVe.setLayout(panelNhapTTDatVeLayout);
        panelNhapTTDatVeLayout.setHorizontalGroup(
            panelNhapTTDatVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNhapTTDatVeLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(panelNhapTTDatVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelNhapTTDatVeLayout.createSequentialGroup()
                        .addComponent(cmdQuayLai)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelNhapTTDatVeLayout.createSequentialGroup()
                        .addGroup(panelNhapTTDatVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panelTTLienHe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelTienIch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(panelNhapTTDatVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panelTamTinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelTTChuyenDi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(14, 14, 14))))
            .addComponent(panelButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelNhapTTDatVeLayout.setVerticalGroup(
            panelNhapTTDatVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNhapTTDatVeLayout.createSequentialGroup()
                .addComponent(cmdQuayLai)
                .addGap(12, 12, 12)
                .addGroup(panelNhapTTDatVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelNhapTTDatVeLayout.createSequentialGroup()
                        .addComponent(panelTamTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(panelTTChuyenDi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelNhapTTDatVeLayout.createSequentialGroup()
                        .addComponent(panelTTLienHe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(panelTienIch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(panelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelNhapTTDatVe, javax.swing.GroupLayout.DEFAULT_SIZE, 1064, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelNhapTTDatVe, javax.swing.GroupLayout.PREFERRED_SIZE, 752, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmdTiepTucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdTiepTucActionPerformed
        Application.showForm(new PanelThanhToan());
    }//GEN-LAST:event_cmdTiepTucActionPerformed

    private void txtTenNguoiDiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenNguoiDiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenNguoiDiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbSDT;
    private javax.swing.JButton cmdChiTiet;
    private javax.swing.JButton cmdQuayLai;
    private javax.swing.JButton cmdTiepTuc;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel jb1;
    private javax.swing.JLabel jb2;
    private javax.swing.JLabel lbBaoHiem2;
    private javax.swing.JLabel lbBaoHiem3;
    private javax.swing.JLabel lbBaoHiem4;
    private javax.swing.JLabel lbChuThichTTLH;
    private javax.swing.JLabel lbDiaChiDen;
    private javax.swing.JLabel lbDiaChiDenCuThe;
    private javax.swing.JLabel lbDiaChiDi;
    private javax.swing.JLabel lbDiaChiDiCuThe;
    private javax.swing.JLabel lbDieuKhoan;
    private javax.swing.JLabel lbFooter;
    private javax.swing.JLabel lbGiaBaoHiem;
    private javax.swing.JLabel lbGiaVe;
    private javax.swing.JLabel lbIconXe;
    private javax.swing.JLabel lbImage;
    private javax.swing.JLabel lbMoTaBaoHiem3;
    private javax.swing.JLabel lbMoTaBaoHiem4;
    private javax.swing.JLabel lbNgayThang;
    private javax.swing.JLabel lbTGDen;
    private javax.swing.JLabel lbTGDi;
    private javax.swing.JLabel lbTTChuyenDi;
    private javax.swing.JLabel lbTTLienHe;
    private javax.swing.JLabel lbTTXe;
    private javax.swing.JLabel lbTamTinh;
    private javax.swing.JLabel lbTenNhaXe;
    private javax.swing.JLabel lbTienIch;
    private javax.swing.JPanel panelBaoHiem;
    private javax.swing.JPanel panelButton;
    private javax.swing.JPanel panelChuThich;
    private javax.swing.JPanel panelNhapTTDatVe;
    private javax.swing.JPanel panelTTChuyenDi;
    private javax.swing.JPanel panelTTChuyenDiCon;
    private javax.swing.JPanel panelTTLienHe;
    private javax.swing.JPanel panelTamTinh;
    private javax.swing.JPanel panelTienIch;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenNguoiDi;
    // End of variables declaration//GEN-END:variables
}
