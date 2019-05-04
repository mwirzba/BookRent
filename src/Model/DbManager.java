package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbManager {
    private static DbManager ourInstance = new DbManager();

    public Connection getConn() {
        return conn;
    }

    private Connection conn;

    public static DbManager getInstance() {
        return ourInstance;
    }

    private DbManager() {
        conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://" +
                    "DESKTOP-QCG1H25\\SQLEXPRESS;database=BookRentDatabase", "admin", "admin");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
