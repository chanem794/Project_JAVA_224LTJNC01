package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Manages SQL Server database connection.
 */
public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection connection;

    private DatabaseConnection() {
        try {
            String driverClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // ✅ sửa ở đây
            String serverName = "Dean";
            String databaseName = "QuanLyVeXeBuyt";
            String userName = "sa";
            String password = "12345";
            String driverURL = "jdbc:sqlserver://" + serverName + ":1433;databaseName=" + databaseName +
                    ";user=" + userName + ";password=" + password + ";encrypt=false;IntegratedSecurity=false";
            Class.forName(driverClass);
            this.connection = DriverManager.getConnection(driverURL);
            System.out.println("Database connection successful");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}