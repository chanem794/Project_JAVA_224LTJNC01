/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package raven.application.form.other;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import net.miginfocom.swing.MigLayout;
import raven.application.Application;
import raven.application.form.other.component.PanelThanhToan;


public class NhapTTDatVeForm extends javax.swing.JPanel {
        

    public NhapTTDatVeForm() {
        initComponents();
        init();
        
    }
    private void init() {
        
        setLayout(new MigLayout("align left top", "", ""));
        
//      Set phần tiêu đề cho tất cả tiêu đề
        lbTTLienHe.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h2.font");
        lbTamTinh.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h2.font");
        lbTienIch.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h2.font");
        lbTTChuyenDi.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h2.font");
        lbGiaVe.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h2.font");
        lbTGDi.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h2.font");
        lbTGDen.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h2.font");
        lbBaoHiem3.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h3.font");
        lbBaoHiem4.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h3.font");
        cmdTiepTuc.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h3.font");
        
//      Set in đậm cho các chữ
        lbTenNhaXe.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:bold +1");
        lbDiaChiDi.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:bold +1");
        lbDiaChiDen.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:bold +1");
        lbNgayThang.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:bold +1");
        lbDieuKhoan.setText("<html>Bằng việc nhấn Tiếp tục, bạn đồng ý với <font color='#0055FF'><u><b>Chính sách bảo mật thanh toán</b></u></font> và <font color='#0055FF'><u><b>Quy chế</b></u></font></html>");
        cmdChiTiet.setText("<html><u><b>Chi tiết</b></u></html>");
        lbGiaBaoHiem.setText("<html><b><font color='#000000'>Bảo hiểm chuyến đi </font></b> ( +20.000đ/ghế)</html>");
        
//      Bo góc các panel
        panelTTLienHe.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        panelChuThich.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        panelTienIch.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        panelBaoHiem.putClientProperty(FlatClientProperties.STYLE, ""+ "arc:20;"+ "border:2,2,2,2,#27AE60,,20");
        panelChuThich.putClientProperty(FlatClientProperties.STYLE, ""+ "arc:20;"+ "border:2,2,2,2,#27AE60,,20");
        panelTamTinh.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        panelTTChuyenDi.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        panelTTChuyenDiCon.putClientProperty(FlatClientProperties.STYLE, ""+ "arc:20;"+ "border:2,2,2,2,#E0E0E0,,20");
        panelHeader.putClientProperty(FlatClientProperties.STYLE, ""+ "arc:20;"+ "border:2,2,2,2,#F1F7FD,,20");

//      Thêm phần chữ mờ gọi ý cho người dùng nhập dữ liệu và in đậm phần nhập vào
        txtTenNguoiDi.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Tên người đi *");
        txtTenNguoiDi.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:bold +1");
        txtSDT.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Số điện thoại *");
        txtSDT.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:bold +1");
        txtEmail.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Email để nhận thông tin đặt chỗ *");  
        txtEmail.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:bold +1");
        
//      Set image
        ImageIcon BusImage = new ImageIcon(getClass().getResource("/raven/thanhtoan/icon/xebuyt.jpg"));
        Image image = BusImage.getImage().getScaledInstance(88, 88, Image.SCALE_SMOOTH);
        lbImage.setIcon(new ImageIcon(image));
        
//      Set các icon  
        ImageIcon ChuThichIcon = new ImageIcon(getClass().getResource("/raven/thanhtoan/icon/baohiem.png"));
        Image scaledChuThichImage = ChuThichIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);  
        ImageIcon resizedIconChuThichIcon = new ImageIcon(scaledChuThichImage);
        lbChuThichTTLH.setIcon(resizedIconChuThichIcon);
        lbChuThichTTLH.setText("Số điện thoại và email được sử dụng để gửi thông tin đơn hàng và liên hệ khi cần thiết.");
        lbChuThichTTLH.setIconTextGap(5);// Khoảng cách giữa ảnh và text
        
//        ImageIcon Busicon = new ImageIcon(getClass().getResource("/raven/thanhtoan/icon/bus.png"));
//        Image scaledBusImage = Busicon.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH); 
//        ImageIcon resizedIconBusIcon = new ImageIcon(scaledBusImage);
//        lbNgayThang.setIcon(resizedIconBusIcon);
//        lbNgayThang.setText("T5, 08/05/2025");
//        lbNgayThang.setIconTextGap(10);  
        
//        ImageIcon BaoHiemIcon = new ImageIcon(getClass().getResource("/raven/thanhtoan/icon/baohiem2.png"));
//        Image scaledBaoHiemImage = BaoHiemIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);  
//        ImageIcon resizedIconBaoHiemIcon = new ImageIcon(scaledBaoHiemImage);
//        lbGiaBaoHiem.setIcon(resizedIconBaoHiemIcon);
//        lbGiaBaoHiem.setText("Bảo hiểm chuyến đi ( +20.000đ/ghế)");
//        lbGiaBaoHiem.setIconTextGap(5);  

//        ImageIcon DiemDenIcon = new ImageIcon(getClass().getResource("/raven/thanhtoan/icon/diemden.png"));
//        Image scaledDiemDenImage = DiemDenIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);  
//        ImageIcon resizedIconDiemDenIcon = new ImageIcon(scaledDiemDenImage);
//        jb1.setIcon(resizedIconDiemDenIcon);
//        
//        ImageIcon DiemDiIcon = new ImageIcon(getClass().getResource("/raven/thanhtoan/icon/diemdi.png"));
//        Image scaledDiemDiImage = DiemDiIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);  
//        ImageIcon resizedIconDiemDiIcon = new ImageIcon(scaledDiemDiImage);
//        jb2.setIcon(resizedIconDiemDiIcon);
        
        ImageIcon SoNguoiIcon = new ImageIcon(getClass().getResource("/raven/thanhtoan/icon/nguoi.png"));
        Image scaledSoNguoiImage = SoNguoiIcon.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);  
        ImageIcon resizedIconSoNguoiIcon = new ImageIcon(scaledSoNguoiImage);
        lbNguoiIcon.setIcon(resizedIconSoNguoiIcon);
        lbNguoiIcon.setText("1");
        lbNguoiIcon.setIconTextGap(3);  
        
        ImageIcon SoGheIcon = new ImageIcon(getClass().getResource("/raven/thanhtoan/icon/ghe.png"));
        Image scaledSoGheImage = SoGheIcon.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);  
        ImageIcon resizedIconSoGheIcon = new ImageIcon(scaledSoGheImage);
        lbGheIcon.setIcon(resizedIconSoGheIcon);
        lbGheIcon.setText("A1.1");
        lbGheIcon.setIconTextGap(3);   
        
//      Set icon bằng flatlaf
        FlatSVGIcon KhienBaoHiemicon = new FlatSVGIcon("raven/thanhtoan/icon/khienBaoHiem.svg");
        KhienBaoHiemicon.setColorFilter(new FlatSVGIcon.ColorFilter()
        .add(Color.decode("#969696"),
        FlatUIUtils.getUIColor("Menu.icon.lightColor", Color.red),
        FlatUIUtils.getUIColor("Menu.icon.darkColor", Color.red))
        );
        lbGiaBaoHiem.setIcon(KhienBaoHiemicon);

        FlatSVGIcon BusBusicon = new FlatSVGIcon("raven/thanhtoan/icon/busbus.svg");
        BusBusicon.setColorFilter(new FlatSVGIcon.ColorFilter()
        .add(Color.decode("#969696"),
        FlatUIUtils.getUIColor("Menu.icon.lightColor", Color.red),
        FlatUIUtils.getUIColor("Menu.icon.darkColor", Color.red))
        );
        lbNgayThang.setIcon(BusBusicon);
        lbNgayThang.setText("T5, 08/05/2025");
        
        FlatSVGIcon cmdQLicon = new FlatSVGIcon("raven/thanhtoan/icon/quayLai.svg");
        cmdQLicon.setColorFilter(new FlatSVGIcon.ColorFilter()
        .add(Color.decode("#969696"),
        FlatUIUtils.getUIColor("Menu.icon.lightColor", Color.red),
        FlatUIUtils.getUIColor("Menu.icon.darkColor", Color.red))
        );
        cmdQuayLai.setIcon(cmdQLicon);
        cmdQuayLai.setText("Quay lại");
        
        FlatSVGIcon DiemDiicon = new FlatSVGIcon("raven/thanhtoan/icon/diemdi.svg",20, 20);
        DiemDiicon.setColorFilter(new FlatSVGIcon.ColorFilter()
        .add(Color.decode("#969696"),
        FlatUIUtils.getUIColor("Menu.icon.lightColor", Color.red),
        FlatUIUtils.getUIColor("Menu.icon.darkColor", Color.red))
        );
        jb2.setIcon(DiemDiicon);
        
        FlatSVGIcon DeimDenicon = new FlatSVGIcon("raven/thanhtoan/icon/diemden.svg",20,20);
        DeimDenicon.setColorFilter(new FlatSVGIcon.ColorFilter()
        .add(Color.decode("#969696"),
        FlatUIUtils.getUIColor("Menu.icon.lightColor", Color.red),
        FlatUIUtils.getUIColor("Menu.icon.darkColor", Color.red))
        );
        jb1.setIcon(DeimDenicon);
                
//      Set lại các đoạn bị mất chữ 
        lbFooter.setText("<html><div style='text-align: center;'>Bạn sẽ sớm nhận được biển số xe, số điện thoại tài xế và dể dàng thay đổi điển đón trả sau khi đặt</div></html>");
        lbMoTaBaoHiem4.setText("<html><div style='text-align: left;'>Hoàn trả 100% tiền vé thực tế nếu chuyến đi bị hủy do các lý do khách quan hoặc bất khả kháng liên quan<br> đến vấn đề sức khỏe, bao gồm các trường hợp như bệnh nặng được xác nhận bằng giấy tờ y tế, tình trạng<br> khẩn cấp sức khỏe của hành khách, hoặc các hướng dẫn y tế công cộng.</div></html>");
        lbMoTaBaoHiem3.setText("<html><div style='text-align: left;'>Quyền lợi bảo hiểm lên đến 400 triệu đồng trong trường hợp xảy ra tai nạn, bao gồm các chi phí y tế, điều<br> trị hoặc bồi thường thiệt hại được xác nhận theo quy định của hợp đồng bảo hiểm.</div></html>");

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
        CbBaoHiem = new javax.swing.JCheckBox();
        cmdQuayLai = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        panelTamTinh = new javax.swing.JPanel();
        lbTamTinh = new javax.swing.JLabel();
        lbGiaVe = new javax.swing.JLabel();
        panelTTChuyenDi = new javax.swing.JPanel();
        lbTTChuyenDi = new javax.swing.JLabel();
        panelTTChuyenDiCon = new javax.swing.JPanel();
        lbImage = new javax.swing.JLabel();
        lbTenNhaXe = new javax.swing.JLabel();
        lbTTXe = new javax.swing.JLabel();
        lbTGDi = new javax.swing.JLabel();
        lbTGDen = new javax.swing.JLabel();
        lbDiaChiDen = new javax.swing.JLabel();
        lbDiaChiDi = new javax.swing.JLabel();
        lbDiaChiDiCuThe = new javax.swing.JLabel();
        lbDiaChiDenCuThe = new javax.swing.JLabel();
        jb1 = new javax.swing.JLabel();
        jb2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lbNguoiIcon = new javax.swing.JLabel();
        lbGheIcon = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        panelHeader = new javax.swing.JPanel();
        cmdChiTiet = new javax.swing.JButton();
        lbNgayThang = new javax.swing.JLabel();

        panelNhapTTDatVe.setPreferredSize(new java.awt.Dimension(1000, 768));

        panelTTLienHe.setBackground(new java.awt.Color(235, 235, 235));

        lbTTLienHe.setForeground(new java.awt.Color(0, 0, 0));
        lbTTLienHe.setText("Thông tin liên hệ");

        txtTenNguoiDi.setForeground(new java.awt.Color(0, 0, 0));
        txtTenNguoiDi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenNguoiDiActionPerformed(evt);
            }
        });

        txtSDT.setForeground(new java.awt.Color(0, 0, 0));

        cbSDT.setForeground(new java.awt.Color(0, 0, 0));
        cbSDT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "VN +84", "IN +91", "CN +86", "MM +95", "MO +835", "KR +82", "JP +81", "TH +66", "AU +61" }));

        txtEmail.setForeground(new java.awt.Color(0, 0, 0));

        panelChuThich.setBackground(new java.awt.Color(204, 255, 204));
        panelChuThich.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 102), 2));
        panelChuThich.setForeground(new java.awt.Color(0, 0, 0));
        panelChuThich.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lbChuThichTTLH.setText("Số điện thoại và email được sử dụng để gửi thông tin đơn hàng và liên hệ khi cần thiết.");

        javax.swing.GroupLayout panelChuThichLayout = new javax.swing.GroupLayout(panelChuThich);
        panelChuThich.setLayout(panelChuThichLayout);
        panelChuThichLayout.setHorizontalGroup(
            panelChuThichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChuThichLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lbChuThichTTLH, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(79, Short.MAX_VALUE))
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
                .addGroup(panelTTLienHeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelTTLienHeLayout.createSequentialGroup()
                        .addComponent(cbSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtSDT))
                    .addComponent(lbTTLienHe, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail)
                    .addComponent(panelChuThich, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTenNguoiDi))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        cmdTiepTuc.setBackground(new java.awt.Color(255, 204, 0));
        cmdTiepTuc.setForeground(new java.awt.Color(0, 0, 0));
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
                        .addComponent(cmdTiepTuc, javax.swing.GroupLayout.PREFERRED_SIZE, 659, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbFooter, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelButtonLayout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(lbDieuKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(68, Short.MAX_VALUE))
        );
        panelButtonLayout.setVerticalGroup(
            panelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelButtonLayout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(panelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdTiepTuc, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbFooter, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbDieuKhoan)
                .addGap(12, 12, 12))
        );

        panelTienIch.setBackground(new java.awt.Color(235, 235, 235));

        lbTienIch.setForeground(new java.awt.Color(0, 0, 0));
        lbTienIch.setText("Tiện ích");

        panelBaoHiem.setBackground(new java.awt.Color(235, 235, 235));
        panelBaoHiem.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 51), 2));

        lbBaoHiem3.setForeground(new java.awt.Color(0, 0, 0));
        lbBaoHiem3.setText("Bảo hiểm tai nạn");

        lbBaoHiem4.setForeground(new java.awt.Color(0, 0, 0));
        lbBaoHiem4.setText("Chính sách Hoàn Hủy chuyến đi");

        lbMoTaBaoHiem3.setBackground(new java.awt.Color(235, 235, 235));
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
                        .addGroup(panelBaoHiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbBaoHiem4, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbMoTaBaoHiem4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbMoTaBaoHiem3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(19, Short.MAX_VALUE))))
        );
        panelBaoHiemLayout.setVerticalGroup(
            panelBaoHiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBaoHiemLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbBaoHiem3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbMoTaBaoHiem3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbBaoHiem4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbMoTaBaoHiem4, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                .addContainerGap())
        );

        lbBaoHiem2.setText("Được bồi thường lên đến 400.000.000đ/ghế");

        lbGiaBaoHiem.setText("Bảo hiểm chuyến đi( +20.000đ/ghế)");

        CbBaoHiem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        CbBaoHiem.setMaximumSize(new java.awt.Dimension(28, 28));
        CbBaoHiem.setMinimumSize(new java.awt.Dimension(28, 28));

        javax.swing.GroupLayout panelTienIchLayout = new javax.swing.GroupLayout(panelTienIch);
        panelTienIch.setLayout(panelTienIchLayout);
        panelTienIchLayout.setHorizontalGroup(
            panelTienIchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTienIchLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(panelTienIchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTienIchLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lbBaoHiem2, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelTienIchLayout.createSequentialGroup()
                        .addComponent(lbGiaBaoHiem, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 360, Short.MAX_VALUE))))
            .addGroup(panelTienIchLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(panelTienIchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(CbBaoHiem, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelTienIchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lbTienIch, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(panelBaoHiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        panelTienIchLayout.setVerticalGroup(
            panelTienIchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTienIchLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTienIch, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTienIchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbGiaBaoHiem)
                    .addComponent(CbBaoHiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbBaoHiem2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBaoHiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(17, 17, 17))
        );

        cmdQuayLai.setForeground(new java.awt.Color(0, 51, 255));
        cmdQuayLai.setText("Quay lại");

        panelTamTinh.setBackground(new java.awt.Color(235, 235, 235));

        lbTamTinh.setForeground(new java.awt.Color(0, 0, 0));
        lbTamTinh.setText("Tạm Tính");

        lbGiaVe.setForeground(new java.awt.Color(0, 0, 0));
        lbGiaVe.setText("550.000đ");

        javax.swing.GroupLayout panelTamTinhLayout = new javax.swing.GroupLayout(panelTamTinh);
        panelTamTinh.setLayout(panelTamTinhLayout);
        panelTamTinhLayout.setHorizontalGroup(
            panelTamTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTamTinhLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lbTamTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 156, Short.MAX_VALUE)
                .addComponent(lbGiaVe, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        panelTTChuyenDi.setBackground(new java.awt.Color(235, 235, 235));

        lbTTChuyenDi.setForeground(new java.awt.Color(0, 0, 0));
        lbTTChuyenDi.setText("Thông tin chuyến đi");

        panelTTChuyenDiCon.setBackground(new java.awt.Color(235, 235, 235));

        lbTenNhaXe.setForeground(new java.awt.Color(0, 0, 0));
        lbTenNhaXe.setText("Đình Nhân");

        lbTTXe.setText("Limousine 32 phòng (WC)");

        lbTGDi.setForeground(new java.awt.Color(0, 0, 0));
        lbTGDi.setText("16:30");

        lbTGDen.setForeground(new java.awt.Color(0, 0, 0));
        lbTGDen.setText("10:20");

        lbDiaChiDen.setForeground(new java.awt.Color(0, 0, 0));
        lbDiaChiDen.setText("VP An Sương");

        lbDiaChiDi.setForeground(new java.awt.Color(0, 0, 0));
        lbDiaChiDi.setText("VP Đà Nẵng");

        lbDiaChiDiCuThe.setText("Địa chỉ đi cụ thể");

        lbDiaChiDenCuThe.setText("Địa chỉ đến cụ thể");

        jb1.setText("jLabel1");

        jb2.setText("jLabel2");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        lbNguoiIcon.setText("Iconnguoi");

        lbGheIcon.setText("iconghe");

        panelHeader.setBackground(new java.awt.Color(241, 247, 253));
        panelHeader.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        cmdChiTiet.setForeground(new java.awt.Color(0, 51, 255));
        cmdChiTiet.setText("Chi tiết");
        cmdChiTiet.setBorder(null);
        cmdChiTiet.setContentAreaFilled(false);
        cmdChiTiet.setFocusPainted(false);

        lbNgayThang.setForeground(new java.awt.Color(0, 0, 0));
        lbNgayThang.setText("CN, 19/06/2025");

        javax.swing.GroupLayout panelHeaderLayout = new javax.swing.GroupLayout(panelHeader);
        panelHeader.setLayout(panelHeaderLayout);
        panelHeaderLayout.setHorizontalGroup(
            panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelHeaderLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lbNgayThang, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cmdChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        panelHeaderLayout.setVerticalGroup(
            panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdChiTiet)
                    .addComponent(lbNgayThang))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelTTChuyenDiConLayout = new javax.swing.GroupLayout(panelTTChuyenDiCon);
        panelTTChuyenDiCon.setLayout(panelTTChuyenDiConLayout);
        panelTTChuyenDiConLayout.setHorizontalGroup(
            panelTTChuyenDiConLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTTChuyenDiConLayout.createSequentialGroup()
                .addGroup(panelTTChuyenDiConLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(panelTTChuyenDiConLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelTTChuyenDiConLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelTTChuyenDiConLayout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(lbImage, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelTTChuyenDiConLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelTTChuyenDiConLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lbTTXe, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                                        .addComponent(lbTenNhaXe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(panelTTChuyenDiConLayout.createSequentialGroup()
                                        .addComponent(lbNguoiIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lbGheIcon))))
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(panelHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelTTChuyenDiConLayout.setVerticalGroup(
            panelTTChuyenDiConLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTTChuyenDiConLayout.createSequentialGroup()
                .addComponent(panelHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTTChuyenDiConLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbImage, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelTTChuyenDiConLayout.createSequentialGroup()
                        .addComponent(lbTenNhaXe)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbTTXe)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelTTChuyenDiConLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbNguoiIcon)
                            .addComponent(lbGheIcon))))
                .addGap(8, 8, 8)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelTamTinh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelTTChuyenDi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(panelTamTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(panelTTChuyenDi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout panelNhapTTDatVeLayout = new javax.swing.GroupLayout(panelNhapTTDatVe);
        panelNhapTTDatVe.setLayout(panelNhapTTDatVeLayout);
        panelNhapTTDatVeLayout.setHorizontalGroup(
            panelNhapTTDatVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNhapTTDatVeLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(panelNhapTTDatVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmdQuayLai)
                    .addGroup(panelNhapTTDatVeLayout.createSequentialGroup()
                        .addGroup(panelNhapTTDatVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panelTTLienHe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelTienIch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(panelButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelNhapTTDatVeLayout.setVerticalGroup(
            panelNhapTTDatVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNhapTTDatVeLayout.createSequentialGroup()
                .addComponent(cmdQuayLai)
                .addGap(12, 12, 12)
                .addGroup(panelNhapTTDatVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelNhapTTDatVeLayout.createSequentialGroup()
                        .addComponent(panelTTLienHe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(panelTienIch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(panelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelNhapTTDatVe, javax.swing.GroupLayout.DEFAULT_SIZE, 1096, Short.MAX_VALUE)
                .addContainerGap())
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
    private javax.swing.JCheckBox CbBaoHiem;
    private javax.swing.JComboBox<String> cbSDT;
    private javax.swing.JButton cmdChiTiet;
    private javax.swing.JButton cmdQuayLai;
    private javax.swing.JButton cmdTiepTuc;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
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
    private javax.swing.JLabel lbGheIcon;
    private javax.swing.JLabel lbGiaBaoHiem;
    private javax.swing.JLabel lbGiaVe;
    private javax.swing.JLabel lbImage;
    private javax.swing.JLabel lbMoTaBaoHiem3;
    private javax.swing.JLabel lbMoTaBaoHiem4;
    private javax.swing.JLabel lbNgayThang;
    private javax.swing.JLabel lbNguoiIcon;
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
    private javax.swing.JPanel panelHeader;
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
