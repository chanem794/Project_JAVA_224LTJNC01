package connectiondata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CONNECTIONSQLSERVER {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyVeXeBuyt;encrypt=true;trustServerCertificate=true";
    private static final String USER = "sa";
    private static final String PASSWORD = "123";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Kết nối SQL Server thành công!");
        } catch (ClassNotFoundException e) {
            System.out.println("❌ Lỗi: Không tìm thấy driver SQL Server!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("❌ Lỗi kết nối SQL Server: " + e.getMessage());
            e.printStackTrace();
        }
        return conn;
    }
}