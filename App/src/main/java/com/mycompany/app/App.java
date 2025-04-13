
package com.mycompany.app;

import connectiondata.CONNECTIONSQLSERVER;
import java.sql.Connection;

public class App {
    public static void main(String[] args) {
        Connection conn = CONNECTIONSQLSERVER.getConnection();  // Sửa đúng kiểu
        if (conn != null) {
            System.out.println("✅ Database đang hoạt động.");
        } else {
            System.out.println("❌ Không thể kết nối đến database.");
        }
    }
}
