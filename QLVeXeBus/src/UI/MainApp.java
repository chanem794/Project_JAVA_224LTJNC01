// UI: Main Application
package UI;

import javax.swing.*;
import java.awt.*;

public class MainApp extends JFrame {
    public MainApp() {
        setTitle("Nào Mình Cùng Đi Xe Bus");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add("Trang Chủ", new TrangChuUI());
        tabbedPane.add("Vé của tôi", new VeCuaToiUI());
        tabbedPane.add("Ưu đãi", new UuDaiUI());
        tabbedPane.add("Hỗ trợ", new HoTroUI());
        tabbedPane.add("Tài khoản", new TaiKhoanUI());

        add(tabbedPane);
    }

    public static void main(String[] args) {
        new MainApp().setVisible(true);
    }
}
