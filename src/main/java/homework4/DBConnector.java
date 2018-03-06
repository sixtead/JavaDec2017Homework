package homework4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private static Connection conn = null;

    public static Connection getConnection()
    {
        if (conn != null) return conn;
        // get db, user, pass from settings file
        return getConnection("library");
    }

    private static Connection getConnection(String db) {
//        jdbc:h2:mem:
        try {
            conn = DriverManager.getConnection("jdbc:h2:mem:"+ db + ";DB_CLOSE_DELAY=-1", "sa", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }
}
