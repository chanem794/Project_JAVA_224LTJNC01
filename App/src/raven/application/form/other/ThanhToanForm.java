/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package raven.application.form.other;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Color;
import java.awt.Image;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author DELL
 */
public class ThanhToanForm extends javax.swing.JPanel {

    /**
     * Creates new form ThanhToanForm
     */
    public ThanhToanForm() {
        initComponents();
        init();
    }
    
    
    private void init() {
        setLayout(new MigLayout("align left top", "", ""));

//      Kích thước chữ cho các tiêu đề 
        lbTTLienHe.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h2.font");
        lbTongTien.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h2.font");
        lbPTThanhToan.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h2.font");
        lbTTChuyenDi.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h2.font");
        lbGiaVe.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h3.font");
        lbTGDi.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h2.font");
        lbTGDen.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h2.font");
        panelPTTT.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        panelTongTien.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        panelTTChuyenDi.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        panelTTChuyenDiCon.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        panelTTLienHe.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        
        
//      set chỉ cho phép chọn 1 phương thức
        ButtonGroup group = new ButtonGroup();
        group.add(Rb1);
        group.add(Rb2);
        group.add(Rb3);
        group.add(Rb4);
        group.add(Rb5);
        
//      Thêm image vào panel Thong tin chuyen di
        ImageIcon icon = new ImageIcon(getClass().getResource("/raven/thanhtoan/icon/IBus.jpeg"));
        Image image = icon.getImage().getScaledInstance(88, 88, Image.SCALE_SMOOTH);
        lbImage.setIcon(new ImageIcon(image));
        
//      Set các icon        
        ImageIcon XeBusicon = new ImageIcon(getClass().getResource("/raven/thanhtoan/icon/xebus.png"));
        Image scaledXeBusImage = XeBusicon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);  // hoặc 24x24
        ImageIcon resizedIconXeBusIcon = new ImageIcon(scaledXeBusImage);
        lb1.setIcon(resizedIconXeBusIcon);
        lb1.setText("Thanh toán khi lên xe");
        lb1.setIconTextGap(10);
        
        ImageIcon ATMicon = new ImageIcon(getClass().getResource("/raven/thanhtoan/icon/atm.png"));
        Image scaledATMImage = ATMicon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);  // hoặc 24x24
        ImageIcon resizedIATMIcon = new ImageIcon(scaledATMImage);
        lb2.setIcon(resizedIATMIcon);
        lb2.setText("Thẻ ATM nội địa / Internet Banking");
        lb2.setIconTextGap(10);
        
        ImageIcon VNPAYicon = new ImageIcon(getClass().getResource("/raven/thanhtoan/icon/vnpay.png"));
        Image scaledVNPAYImage = VNPAYicon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);  // hoặc 24x24
        ImageIcon resizedIVNPAYIcon = new ImageIcon(scaledVNPAYImage);
        lb3.setIcon(resizedIVNPAYIcon);
        lb3.setText("Thanh toán VNPAY - QR");
        lb3.setIconTextGap(10);
        
        ImageIcon Viettelicon = new ImageIcon(getClass().getResource("/raven/thanhtoan/icon/viettel.png"));
        Image scaledViettelImage = Viettelicon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);  // hoặc 24x24
        ImageIcon resizedIViettelIcon = new ImageIcon(scaledViettelImage);
        lb4.setIcon(resizedIViettelIcon);
        lb4.setText("Thanh toán qua Viettel Money");
        lb4.setIconTextGap(10);
        
        ImageIcon CuaHangicon = new ImageIcon(getClass().getResource("/raven/thanhtoan/icon/cuahang.png"));
        Image scaledCuaHangImage = CuaHangicon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);  // hoặc 24x24
        ImageIcon resizedICuaHangIcon = new ImageIcon(scaledCuaHangImage);
        lb5.setIcon(resizedICuaHangIcon);
        lb5.setText("Tại cửa hàng tiện lợi hoặc siêu thị");
        lb5.setIconTextGap(10);
        
        ImageIcon ThanhToanicon = new ImageIcon(getClass().getResource("/raven/thanhtoan/icon/thanhtoan.png"));
        Image scaledThanhToanImage = ThanhToanicon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);  // hoặc 24x24
        ImageIcon resizedIThanhToanIcon = new ImageIcon(scaledThanhToanImage);
        cmdTiepTuc.setIcon(resizedIThanhToanIcon);
        cmdTiepTuc.setText("Thanh toán");
        cmdTiepTuc.setIconTextGap(10);

        ImageIcon Busicon = new ImageIcon(getClass().getResource("/raven/thanhtoan/icon/bus.png"));
        Image scaledBusImage = Busicon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);  // hoặc 24x24
        ImageIcon resizedIconBusIcon = new ImageIcon(scaledBusImage);
        lbNgayThang.setIcon(resizedIconBusIcon);
        lbNgayThang.setText("T5, 08/05/2025");
        lbNgayThang.setIconTextGap(10);  // Khoảng cách giữa ảnh và text
        

        ImageIcon DiemDenIcon = new ImageIcon(getClass().getResource("/raven/thanhtoan/icon/diemden.png"));
        Image scaledDiemDenImage = DiemDenIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);  // hoặc 24x24
        ImageIcon resizedIconDiemDenIcon = new ImageIcon(scaledDiemDenImage);
        jb1.setIcon(resizedIconDiemDenIcon);
        
        ImageIcon DiemDiIcon = new ImageIcon(getClass().getResource("/raven/thanhtoan/icon/diemdi.png"));
        Image scaledDiemDiImage = DiemDiIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);  // hoặc 24x24
        ImageIcon resizedIconDiemDiIcon = new ImageIcon(scaledDiemDiImage);
        jb2.setIcon(resizedIconDiemDiIcon);
        
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
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        panelButton = new javax.swing.JPanel();
        cmdTiepTuc = new javax.swing.JButton();
        lbFooter = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
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
        jSeparator8 = new javax.swing.JSeparator();

        setPreferredSize(new java.awt.Dimension(1000, 768));

        panelTongTien.setBackground(new java.awt.Color(153, 153, 153));

        lbTongTien.setText("Tổng tiền");

        lbGiaVe.setText("550.000đ");

        javax.swing.GroupLayout panelTongTienLayout = new javax.swing.GroupLayout(panelTongTien);
        panelTongTien.setLayout(panelTongTienLayout);
        panelTongTienLayout.setHorizontalGroup(
            panelTongTienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTongTienLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lbTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbGiaVe, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        panelPTTT.setBackground(new java.awt.Color(153, 153, 153));

        jPanel5.setBackground(new java.awt.Color(153, 153, 153));

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

        lbPTThanhToan.setText("Phương thức thanh toán");

        jLabel2.setText("Vui lòng chọn phương thức thanh toán phù hợp với bạn để đặt vé:");

        jPanel6.setBackground(new java.awt.Color(153, 153, 153));

        Rb2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Rb2ActionPerformed(evt);
            }
        });

        jLabel20.setText("Bạn có thể thanh toán cho tài xế khi lên xe");

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

        jPanel7.setBackground(new java.awt.Color(153, 153, 153));

        Rb3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Rb3ActionPerformed(evt);
            }
        });

        jLabel21.setText("Bạn có thể thanh toán cho tài xế khi lên xe");

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

        jPanel8.setBackground(new java.awt.Color(153, 153, 153));

        Rb4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Rb4ActionPerformed(evt);
            }
        });

        jLabel22.setText("Bạn có thể thanh toán cho tài xế khi lên xe");

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

        jPanel9.setBackground(new java.awt.Color(153, 153, 153));

        Rb5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Rb5ActionPerformed(evt);
            }
        });

        jLabel23.setText("Bạn có thể thanh toán cho tài xế khi lên xe");

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
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 656, Short.MAX_VALUE)
                    .addGroup(panelPTTTLayout.createSequentialGroup()
                        .addGroup(panelPTTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbPTThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 656, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 656, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 656, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 656, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelPTTTLayout.setVerticalGroup(
            panelPTTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPTTTLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbPTThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        panelTTLienHe.setBackground(new java.awt.Color(153, 153, 153));

        lbTTLienHe.setText("Thông tin liên hệ");

        jButton2.setText("Chỉnh sửa");

        jLabel3.setText("Hành khách");

        jLabel4.setText("Số điện thoại");

        jLabel5.setText("Email");

        jLabel6.setText("jLabel6");

        jLabel7.setText("jLabel7");

        jLabel8.setText("jLabel8");

        javax.swing.GroupLayout panelTTLienHeLayout = new javax.swing.GroupLayout(panelTTLienHe);
        panelTTLienHe.setLayout(panelTTLienHeLayout);
        panelTTLienHeLayout.setHorizontalGroup(
            panelTTLienHeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTTLienHeLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(panelTTLienHeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTTLienHeLayout.createSequentialGroup()
                        .addComponent(lbTTLienHe, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addGroup(panelTTLienHeLayout.createSequentialGroup()
                        .addGroup(panelTTLienHeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelTTLienHeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelTTLienHeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(14, 14, 14))
        );
        panelTTLienHeLayout.setVerticalGroup(
            panelTTLienHeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTTLienHeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTTLienHeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTTLienHe, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTTLienHeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTTLienHeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTTLienHeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        cmdTiepTuc.setBackground(new java.awt.Color(0, 204, 204));
        cmdTiepTuc.setForeground(java.awt.Color.white);
        cmdTiepTuc.setText("Thanh Toán");

        lbFooter.setText("Bạn sẽ sớm nhận được biển số xe, số điện thoại tài xế và dể dàng thay đổi điển đón trả sau khi đặt");

        jLabel13.setText("Bằng việc nhấn nút Thanh toán, bạn đồng ý với Chính sách bảo mật thanh toán ");

        javax.swing.GroupLayout panelButtonLayout = new javax.swing.GroupLayout(panelButton);
        panelButton.setLayout(panelButtonLayout);
        panelButtonLayout.setHorizontalGroup(
            panelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelButtonLayout.createSequentialGroup()
                .addGroup(panelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelButtonLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(cmdTiepTuc, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(lbFooter, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(panelButtonLayout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelButtonLayout.setVerticalGroup(
            panelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelButtonLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(panelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmdTiepTuc, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbFooter, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        jSeparator8.setOrientation(javax.swing.SwingConstants.VERTICAL);

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
                            .addComponent(jSeparator8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                            .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addGroup(panelThanhToan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(panelTongTien, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelTTLienHe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelTTChuyenDi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelThanhToan1Layout.setVerticalGroup(
            panelThanhToan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelThanhToan1Layout.createSequentialGroup()
                .addGroup(panelThanhToan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelThanhToan1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelTTChuyenDi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelTTLienHe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68))
                    .addGroup(panelThanhToan1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(panelPTTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)))
                .addComponent(panelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton Rb1;
    private javax.swing.JRadioButton Rb2;
    private javax.swing.JRadioButton Rb3;
    private javax.swing.JRadioButton Rb4;
    private javax.swing.JRadioButton Rb5;
    private javax.swing.JButton cmdChiTiet;
    private javax.swing.JButton cmdTiepTuc;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JLabel jb1;
    private javax.swing.JLabel jb2;
    private javax.swing.JLabel lb1;
    private javax.swing.JLabel lb2;
    private javax.swing.JLabel lb3;
    private javax.swing.JLabel lb4;
    private javax.swing.JLabel lb5;
    private javax.swing.JLabel lbDiaChiDen;
    private javax.swing.JLabel lbDiaChiDenCuThe;
    private javax.swing.JLabel lbDiaChiDi;
    private javax.swing.JLabel lbDiaChiDiCuThe;
    private javax.swing.JLabel lbFooter;
    private javax.swing.JLabel lbGiaVe;
    private javax.swing.JLabel lbIconXe;
    private javax.swing.JLabel lbImage;
    private javax.swing.JLabel lbNgayThang;
    private javax.swing.JLabel lbPTThanhToan;
    private javax.swing.JLabel lbTGDen;
    private javax.swing.JLabel lbTGDi;
    private javax.swing.JLabel lbTTChuyenDi;
    private javax.swing.JLabel lbTTLienHe;
    private javax.swing.JLabel lbTTXe;
    private javax.swing.JLabel lbTenNhaXe;
    private javax.swing.JLabel lbTongTien;
    private javax.swing.JPanel panelButton;
    private javax.swing.JPanel panelPTTT;
    private javax.swing.JPanel panelTTChuyenDi;
    private javax.swing.JPanel panelTTChuyenDiCon;
    private javax.swing.JPanel panelTTLienHe;
    private javax.swing.JPanel panelThanhToan1;
    private javax.swing.JPanel panelTongTien;
    // End of variables declaration//GEN-END:variables
}
