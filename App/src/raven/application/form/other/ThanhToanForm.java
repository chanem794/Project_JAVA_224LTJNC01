/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package raven.application.form.other;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.ui.FlatUIUtils;
import dal.TTChuyenDiDAO;
import java.awt.Color;
import java.awt.Image;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;
import model.TTChuyenDi;
import net.miginfocom.swing.MigLayout;
import raven.application.Application;
import raven.application.form.other.component.PanelChiTiet;
import raven.application.form.other.component.PanelNhapTTDatVe;

public class ThanhToanForm extends javax.swing.JPanel {
    private PanelChiTiet PanelChiTiet;
    private JLayeredPane layeredPane;
    private StationForm previousForm; // Thêm biến này vào lớp
    private int maXe;
    private int basePrice;
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("E, dd/MM/yyyy",new Locale("vi", "VN"));
    private static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm");
    
    public ThanhToanForm(int maxe,String tenNguoiDi, String sdt, String email, StationForm previousForm) {
        maXe=maxe;
        initComponents();
        init();
        jbTen.setText(tenNguoiDi);
        jbSdt.setText(sdt);
        jbEmail.setText(email);
        this.previousForm = previousForm; // Lưu tham chiếu đến StationForm
}
    
    private void init() {
        TTChuyenDiDAO TTChuyenDiDAO = new TTChuyenDiDAO();
        TTChuyenDi xe = TTChuyenDiDAO.getTripDetails(maXe);
        if (xe != null) {
            lbTenNhaXe.setText(xe.getTenXe());
            lbTTXe.setText(xe.getLoaiXe());
            lbDiaChiDi.setText(xe.getDiemDi());
            lbDiaChiDen.setText(xe.getDiemDen());
            lbTGDi.setText(TIME_FORMAT.format(xe.getGioDi()));
            lbTGDen.setText(TIME_FORMAT.format(xe.getGioDen()));
            lbNgayThang.setText(DATE_FORMAT.format(xe.getNgayKhoiHanh()));
            lbGheIcon.setText("A1.1");
            lbNguoiIcon.setText("1");
            basePrice = xe.getGiaVe();
            updateBasePriceOnly(); 
        }
        setLayout(new MigLayout("align left top", "", ""));

//      Kích thước chữ cho các tiêu đề 
        lbTTLienHe.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h2.font");
        lbTongTien.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h2.font");
        lbPTThanhToan.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
        lbTTChuyenDi.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h2.font");
        lbGiaVe.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h2.font");
        lbTGDi.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h2.font");
        lbTGDen.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h2.font");
        cmdThanhToan.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h3.font");
        
        
        panelPTTT.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        panelTongTien.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        panelTTChuyenDi.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        panelTTChuyenDiCon.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        panelTTLienHe.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        panelTTChuyenDiCon.putClientProperty(FlatClientProperties.STYLE, ""+ "arc:20;"+ "border:2,2,2,2,#E0E0E0,,20");
        panelHeader.putClientProperty(FlatClientProperties.STYLE, ""+ "arc:20;"+ "border:2,2,2,2,#F1F7FD,,20");
        
        lb1.setText("<html><b>Thanh toán khi lên xe</b></html>");
        lb2.setText("<html><b>Thẻ ATM nội địa / Internet Banking</b></html>");
        lb3.setText("<html><b>Thanh toán VNPAY - QR</b></html>");
        lb4.setText("<html><b>Thanh toán qua Viettel Money</b></html>");
        lb5.setText("<html><b>Tại cửa hàng tiện lợi hoặc siêu thị</b></html>");
        cmdChinhSua.setText("<html><u><b>Chỉnh sửa</b></u></html>");
        
        //      Set in đậm cho các chữ
        lbTenNhaXe.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:bold +1");
        lbDiaChiDi.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:bold +1");
        lbDiaChiDen.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:bold +1");
        lbNgayThang.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:bold +1");
        lbDieuKhoan.setText("<html>Bằng việc nhấn nút Thanh toán, bạn đồng ý với <font color='#0055FF'><u><b>Chính sách bảo mật thanh toán </b></u></font></html>");
        cmdChiTiet.setText("<html><u><b>Chi tiết</b></u></html>");
//      set chỉ cho phép chọn 1 phương thức
        ButtonGroup group = new ButtonGroup();
        group.add(Rb1);
        group.add(Rb2);
        group.add(Rb3);
        group.add(Rb4);
        group.add(Rb5);
        
//      Thêm image vào panel Thong tin chuyen di
        ImageIcon icon = new ImageIcon(getClass().getResource("/raven/thanhtoan/icon/xebuyt.jpg"));
        Image image = icon.getImage().getScaledInstance(88, 88, Image.SCALE_SMOOTH);
        lbImage.setIcon(new ImageIcon(image));
        
//      Set icon bằng faltlat
        FlatSVGIcon Busicon = new FlatSVGIcon("raven/thanhtoan/icon/busbus.svg");
        Busicon.setColorFilter(new FlatSVGIcon.ColorFilter()
        .add(Color.decode("#969696"),
        FlatUIUtils.getUIColor("Menu.icon.lightColor", Color.red),
        FlatUIUtils.getUIColor("Menu.icon.darkColor", Color.red))
        );
        lb1.setIcon(Busicon);
        
        FlatSVGIcon atmicon = new FlatSVGIcon("raven/thanhtoan/icon/atm.svg");
        atmicon.setColorFilter(new FlatSVGIcon.ColorFilter()
        .add(Color.decode("#969696"),
        FlatUIUtils.getUIColor("Menu.icon.lightColor", Color.red),
        FlatUIUtils.getUIColor("Menu.icon.darkColor", Color.red))
        );
        lb2.setIcon(atmicon);
        
        FlatSVGIcon vnpayicon = new FlatSVGIcon("raven/thanhtoan/icon/vnpay.svg");
        vnpayicon.setColorFilter(new FlatSVGIcon.ColorFilter()
        .add(Color.decode("#969696"),
        FlatUIUtils.getUIColor("Menu.icon.lightColor", Color.red),
        FlatUIUtils.getUIColor("Menu.icon.darkColor", Color.red))
        );
        lb3.setIcon(vnpayicon);
        
        FlatSVGIcon viettelicon = new FlatSVGIcon("raven/thanhtoan/icon/viettel.svg");
        viettelicon.setColorFilter(new FlatSVGIcon.ColorFilter()
        .add(Color.decode("#969696"),
        FlatUIUtils.getUIColor("Menu.icon.lightColor", Color.red),
        FlatUIUtils.getUIColor("Menu.icon.darkColor", Color.red))
        );
        lb4.setIcon(viettelicon);
        
        FlatSVGIcon cuahangtlicon = new FlatSVGIcon("raven/thanhtoan/icon/cuahangtienloi.svg");
        cuahangtlicon.setColorFilter(new FlatSVGIcon.ColorFilter()
        .add(Color.decode("#969696"),
        FlatUIUtils.getUIColor("Menu.icon.lightColor", Color.red),
        FlatUIUtils.getUIColor("Menu.icon.darkColor", Color.red))
        );
        lb5.setIcon(cuahangtlicon);
        
        FlatSVGIcon BusBusicon = new FlatSVGIcon("raven/thanhtoan/icon/busbus.svg");
        BusBusicon.setColorFilter(new FlatSVGIcon.ColorFilter()
        .add(Color.decode("#969696"),
        FlatUIUtils.getUIColor("Menu.icon.lightColor", Color.red),
        FlatUIUtils.getUIColor("Menu.icon.darkColor", Color.red))
        );
        lbNgayThang.setIcon(BusBusicon);
        
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
        
        FlatSVGIcon thanhtoanicon = new FlatSVGIcon("raven/thanhtoan/icon/thanhtoan.svg");
        thanhtoanicon.setColorFilter(new FlatSVGIcon.ColorFilter()
        .add(Color.decode("#969696"),
        FlatUIUtils.getUIColor("Menu.icon.lightColor", Color.red),
        FlatUIUtils.getUIColor("Menu.icon.darkColor", Color.red))
        );
        cmdThanhToan.setIcon(thanhtoanicon);
        cmdThanhToan.setText("Thanh toán");
        
        ImageIcon SoNguoiIcon = new ImageIcon(getClass().getResource("/raven/thanhtoan/icon/nguoi.png"));
        Image scaledSoNguoiImage = SoNguoiIcon.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);  // hoặc 24x24
        ImageIcon resizedIconSoNguoiIcon = new ImageIcon(scaledSoNguoiImage);
        lbNguoiIcon.setIcon(resizedIconSoNguoiIcon);
        lbNguoiIcon.setText("1");
        
        ImageIcon SoGheIcon = new ImageIcon(getClass().getResource("/raven/thanhtoan/icon/ghe.png"));
        Image scaledSoGheImage = SoGheIcon.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);  // hoặc 24x24
        ImageIcon resizedIconSoGheIcon = new ImageIcon(scaledSoGheImage);
        lbGheIcon.setIcon(resizedIconSoGheIcon);
        lbGheIcon.setText("A1.1");
        
        lbFooter.setText("<html><div style='text-align: center;'>Bạn sẽ sớm nhận được biển số xe, số điện thoại tài xế và dể dàng thay đổi điển đón trả sau khi đặt</div></html>");
        //      Set Panel khi nhấn button chi tiết
        
        layeredPane = new JLayeredPane();
        layeredPane.setLayout(null);


        this.removeAll(); 
        panelThanhToan1.setBounds(0, 0, 1096, 752);
        layeredPane.add(panelThanhToan1, JLayeredPane.DEFAULT_LAYER);

        // Thêm layeredPane vào NhapTTDatVeForm
        this.setLayout(new java.awt.BorderLayout());
        this.add(layeredPane, java.awt.BorderLayout.CENTER);

        // Khởi tạo và thêm PanelChiTiet
        PanelChiTiet = new PanelChiTiet(maXe);
        PanelChiTiet.setVisible(false);
        int formWidth = 1096;
        int formHeight = 752;
        int detailWidth = 390; 
        int detailHeight = 620;
        PanelChiTiet.setBounds(formWidth - detailWidth - 10, 22, detailWidth, detailHeight);
        layeredPane.add(PanelChiTiet, JLayeredPane.PALETTE_LAYER);

        cmdChiTiet.addActionListener(e -> {
            PanelChiTiet.setVisible(true);
        });

        PanelChiTiet.getBtnClose().addActionListener(e -> {
            PanelChiTiet.setVisible(false);
        });

        this.revalidate();
        this.repaint();
        
        cmdThanhToan.addActionListener(new java.awt.event.ActionListener() {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent evt) {
        // Kiểm tra xem có phương thức thanh toán nào được chọn hay không
            if (!Rb1.isSelected() && !Rb2.isSelected() && !Rb3.isSelected() && !Rb4.isSelected() && !Rb5.isSelected()) {
            // Hiển thị thông báo lỗi nếu chưa chọn phương thức
                javax.swing.JOptionPane.showMessageDialog(ThanhToanForm.this, 
                "Vui lòng chọn một phương thức thanh toán trước khi tiếp tục!", 
                "Lỗi", 
                javax.swing.JOptionPane.ERROR_MESSAGE);
        } else {
            // Xử lý logic thanh toán nếu đã chọn phương thức (có thể thêm code xử lý ở đây)
            javax.swing.JOptionPane.showMessageDialog(ThanhToanForm.this, 
                "Thanh toán thành công!", 
                "Thông báo", 
                javax.swing.JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
   }
    private void updateBasePriceOnly() {
        lbGiaVe.setText(String.format("%,dđ", basePrice));
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator4 = new javax.swing.JSeparator();
        panelThanhToan1 = new javax.swing.JPanel();
        panelTongTien = new javax.swing.JPanel();
        lbTongTien = new javax.swing.JLabel();
        lbGiaVe = new javax.swing.JLabel();
        panelPTTT = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        Rb1 = new javax.swing.JRadioButton();
        jLabel19 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        lb1 = new javax.swing.JLabel();
        lbPTThanhToan = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        Rb2 = new javax.swing.JRadioButton();
        jLabel20 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        lb2 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        Rb3 = new javax.swing.JRadioButton();
        jLabel21 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        lb3 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        Rb4 = new javax.swing.JRadioButton();
        jLabel22 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        lb4 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        Rb5 = new javax.swing.JRadioButton();
        jLabel23 = new javax.swing.JLabel();
        jSeparator11 = new javax.swing.JSeparator();
        lb5 = new javax.swing.JLabel();
        panelTTLienHe = new javax.swing.JPanel();
        lbTTLienHe = new javax.swing.JLabel();
        cmdChinhSua = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jbTen = new javax.swing.JLabel();
        jbSdt = new javax.swing.JLabel();
        jbEmail = new javax.swing.JLabel();
        panelButton = new javax.swing.JPanel();
        cmdThanhToan = new javax.swing.JButton();
        lbFooter = new javax.swing.JLabel();
        lbDieuKhoan = new javax.swing.JLabel();
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

        setPreferredSize(new java.awt.Dimension(1000, 768));

        panelTongTien.setBackground(new java.awt.Color(235, 235, 235));

        lbTongTien.setForeground(new java.awt.Color(0, 0, 0));
        lbTongTien.setText("Tổng tiền");

        lbGiaVe.setForeground(new java.awt.Color(0, 0, 0));
        lbGiaVe.setText("550.000đ");

        javax.swing.GroupLayout panelTongTienLayout = new javax.swing.GroupLayout(panelTongTien);
        panelTongTien.setLayout(panelTongTienLayout);
        panelTongTienLayout.setHorizontalGroup(
            panelTongTienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTongTienLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lbTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbGiaVe, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        panelTongTienLayout.setVerticalGroup(
            panelTongTienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTongTienLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTongTienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(lbGiaVe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        panelPTTT.setBackground(new java.awt.Color(235, 235, 235));
        panelPTTT.setFocusTraversalPolicyProvider(true);

        jPanel5.setBackground(new java.awt.Color(235, 235, 235));

        Rb1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Rb1ActionPerformed(evt);
            }
        });

        jLabel19.setText("Bạn có thể thanh toán cho tài xế khi lên xe");

        lb1.setText("Thanh toán khi lên xe");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 632, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(Rb1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 654, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Rb1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19)
                .addGap(23, 23, 23))
        );

        lbPTThanhToan.setForeground(new java.awt.Color(0, 0, 0));
        lbPTThanhToan.setText("Phương thức thanh toán");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Vui lòng chọn phương thức thanh toán phù hợp với bạn để đặt vé:");

        jPanel6.setBackground(new java.awt.Color(235, 235, 235));

        Rb2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Rb2ActionPerformed(evt);
            }
        });

        jLabel20.setText("Tài khoản phải có đăng kí Internet banking");

        lb2.setText("Thanh toán khi lên xe");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 632, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(Rb2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb2, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 653, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Rb2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20)
                .addGap(23, 23, 23))
        );

        jPanel7.setBackground(new java.awt.Color(235, 235, 235));

        Rb3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Rb3ActionPerformed(evt);
            }
        });

        jLabel21.setText("Thiết bị cần cài đặt Ứng dụng ngân hàng (Mobile Banking) hoặc Ví VNPAY");

        lb3.setText("Thanh toán khi lên xe");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 632, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(Rb3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb3, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 652, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Rb3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21)
                .addGap(23, 23, 23))
        );

        jPanel8.setBackground(new java.awt.Color(235, 235, 235));

        Rb4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Rb4ActionPerformed(evt);
            }
        });

        jLabel22.setText("Bạn cần có tài khoản Viettel Money hoặc có cài đặt ứng dụng Viettel Money");

        lb4.setText("Thanh toán khi lên xe");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 632, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(Rb4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb4, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 652, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Rb4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22)
                .addGap(23, 23, 23))
        );

        jPanel9.setBackground(new java.awt.Color(235, 235, 235));

        Rb5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Rb5ActionPerformed(evt);
            }
        });

        jLabel23.setText("Bạn có thể thanh toán tại các cửa hàng tiện lợi, Viettel post hoặc siêu thị");

        lb5.setText("Thanh toán khi lên xe");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 632, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(Rb5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb5, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 653, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Rb5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel23)
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout panelPTTTLayout = new javax.swing.GroupLayout(panelPTTT);
        panelPTTT.setLayout(panelPTTTLayout);
        panelPTTTLayout.setHorizontalGroup(
            panelPTTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPTTTLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPTTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 656, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 656, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 656, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 656, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 656, Short.MAX_VALUE)
                    .addGroup(panelPTTTLayout.createSequentialGroup()
                        .addComponent(lbPTThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(panelPTTTLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelPTTTLayout.setVerticalGroup(
            panelPTTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPTTTLayout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(lbPTThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(26, 26, 26)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelTTLienHe.setBackground(new java.awt.Color(235, 235, 235));

        lbTTLienHe.setForeground(new java.awt.Color(0, 0, 0));
        lbTTLienHe.setText("Thông tin liên hệ");

        cmdChinhSua.setForeground(new java.awt.Color(0, 51, 255));
        cmdChinhSua.setText("Chỉnh sửa");
        cmdChinhSua.setBorder(null);
        cmdChinhSua.setContentAreaFilled(false);
        cmdChinhSua.setFocusPainted(false);
        cmdChinhSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdChinhSuaActionPerformed(evt);
            }
        });

        jLabel3.setText("Hành khách");

        jLabel4.setText("Số điện thoại");

        jLabel5.setText("Email");

        jbTen.setForeground(new java.awt.Color(0, 0, 0));
        jbTen.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jbTen.setText("Lê Duy Quốc..");

        jbSdt.setForeground(new java.awt.Color(0, 0, 0));
        jbSdt.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jbSdt.setText("000000001");

        jbEmail.setForeground(new java.awt.Color(0, 0, 0));
        jbEmail.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jbEmail.setText("leduyquoc2giaml.com");

        javax.swing.GroupLayout panelTTLienHeLayout = new javax.swing.GroupLayout(panelTTLienHe);
        panelTTLienHe.setLayout(panelTTLienHeLayout);
        panelTTLienHeLayout.setHorizontalGroup(
            panelTTLienHeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTTLienHeLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(panelTTLienHeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTTLienHeLayout.createSequentialGroup()
                        .addGroup(panelTTLienHeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelTTLienHeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                        .addGroup(panelTTLienHeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbSdt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbTen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelTTLienHeLayout.createSequentialGroup()
                        .addComponent(lbTTLienHe, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmdChinhSua, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)))
                .addGap(14, 14, 14))
        );
        panelTTLienHeLayout.setVerticalGroup(
            panelTTLienHeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTTLienHeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTTLienHeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTTLienHe, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdChinhSua))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTTLienHeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jbTen))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTTLienHeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jbSdt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTTLienHeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jbEmail))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        cmdThanhToan.setBackground(new java.awt.Color(255, 204, 0));
        cmdThanhToan.setForeground(new java.awt.Color(0, 0, 0));
        cmdThanhToan.setText("Thanh Toán");
        cmdThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdThanhToanActionPerformed(evt);
            }
        });

        lbFooter.setText("Bạn sẽ sớm nhận được biển số xe, số điện thoại tài xế và dể dàng thay đổi điển đón trả sau khi đặt");

        lbDieuKhoan.setText("Bằng việc nhấn nút Thanh toán, bạn đồng ý với Chính sách bảo mật thanh toán ");

        javax.swing.GroupLayout panelButtonLayout = new javax.swing.GroupLayout(panelButton);
        panelButton.setLayout(panelButtonLayout);
        panelButtonLayout.setHorizontalGroup(
            panelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelButtonLayout.createSequentialGroup()
                .addGroup(panelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelButtonLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(cmdThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 671, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbFooter, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(panelButtonLayout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addComponent(lbDieuKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelButtonLayout.setVerticalGroup(
            panelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelButtonLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(panelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmdThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbFooter, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbDieuKhoan)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        lbDiaChiDiCuThe.setText("Tất cả các trạm đón trả khách");

        lbDiaChiDenCuThe.setText("Tất cả các trạm đón trả khách");

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
                .addComponent(lbNgayThang, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                .addComponent(cmdChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
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
                        .addGroup(panelTTChuyenDiConLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(panelHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
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

        javax.swing.GroupLayout panelThanhToan1Layout = new javax.swing.GroupLayout(panelThanhToan1);
        panelThanhToan1.setLayout(panelThanhToan1Layout);
        panelThanhToan1Layout.setHorizontalGroup(
            panelThanhToan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelThanhToan1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(panelPTTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelThanhToan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelTTChuyenDi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelTTLienHe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelThanhToan1Layout.setVerticalGroup(
            panelThanhToan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelThanhToan1Layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addGroup(panelThanhToan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelThanhToan1Layout.createSequentialGroup()
                        .addComponent(panelTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelTTChuyenDi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelTTLienHe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72))
                    .addComponent(panelPTTT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(panelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelThanhToan1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelThanhToan1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void Rb1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Rb1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Rb1ActionPerformed

    private void Rb2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Rb2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Rb2ActionPerformed

    private void Rb3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Rb3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Rb3ActionPerformed

    private void Rb4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Rb4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Rb4ActionPerformed

    private void Rb5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Rb5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Rb5ActionPerformed

    private void cmdThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdThanhToanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmdThanhToanActionPerformed

    private void cmdChinhSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdChinhSuaActionPerformed
        if (previousForm != null) {
            Application.showForm(new PanelNhapTTDatVe(maXe, previousForm));

    }
    }//GEN-LAST:event_cmdChinhSuaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton Rb1;
    private javax.swing.JRadioButton Rb2;
    private javax.swing.JRadioButton Rb3;
    private javax.swing.JRadioButton Rb4;
    private javax.swing.JRadioButton Rb5;
    private javax.swing.JButton cmdChiTiet;
    private javax.swing.JButton cmdChinhSua;
    private javax.swing.JButton cmdThanhToan;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JLabel jb1;
    private javax.swing.JLabel jb2;
    private javax.swing.JLabel jbEmail;
    private javax.swing.JLabel jbSdt;
    private javax.swing.JLabel jbTen;
    private javax.swing.JLabel lb1;
    private javax.swing.JLabel lb2;
    private javax.swing.JLabel lb3;
    private javax.swing.JLabel lb4;
    private javax.swing.JLabel lb5;
    private javax.swing.JLabel lbDiaChiDen;
    private javax.swing.JLabel lbDiaChiDenCuThe;
    private javax.swing.JLabel lbDiaChiDi;
    private javax.swing.JLabel lbDiaChiDiCuThe;
    private javax.swing.JLabel lbDieuKhoan;
    private javax.swing.JLabel lbFooter;
    private javax.swing.JLabel lbGheIcon;
    private javax.swing.JLabel lbGiaVe;
    private javax.swing.JLabel lbImage;
    private javax.swing.JLabel lbNgayThang;
    private javax.swing.JLabel lbNguoiIcon;
    private javax.swing.JLabel lbPTThanhToan;
    private javax.swing.JLabel lbTGDen;
    private javax.swing.JLabel lbTGDi;
    private javax.swing.JLabel lbTTChuyenDi;
    private javax.swing.JLabel lbTTLienHe;
    private javax.swing.JLabel lbTTXe;
    private javax.swing.JLabel lbTenNhaXe;
    private javax.swing.JLabel lbTongTien;
    private javax.swing.JPanel panelButton;
    private javax.swing.JPanel panelHeader;
    private javax.swing.JPanel panelPTTT;
    private javax.swing.JPanel panelTTChuyenDi;
    private javax.swing.JPanel panelTTChuyenDiCon;
    private javax.swing.JPanel panelTTLienHe;
    private javax.swing.JPanel panelThanhToan1;
    private javax.swing.JPanel panelTongTien;
    // End of variables declaration//GEN-END:variables
}
