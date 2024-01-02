package action;

import java.sql.Connection;
import java.sql.DriverManager;

public class Db {
    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database", "your_username", "your_password");
        } catch (Exception e) {
            System.out.println("Exception Error in Db.getConnection: " + e.getMessage());
        }
        return con;
    }
}
