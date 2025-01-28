package project;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DButil {
    private static Connection con;

    public static Connection getConnection() {
        try {
            if (con == null) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdb", "root", "**********");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL Driver not found: " + e);
        } catch (SQLException e) {
            System.out.println("Database connection error: " + e);
        }
        return con;
    }
}
