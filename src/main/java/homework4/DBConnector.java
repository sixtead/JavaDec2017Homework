package homework4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class DBConnector {

    private static Connection conn = null;

    static Connection getConnection()
    {
        if (conn != null) return conn;
        // get db, user, pass from settings file
        return getConnection("library");
    }

    private static Connection getConnection(String db) {
        try {
             conn = DriverManager.getConnection("jdbc:h2:./data/"+ db, "sa", "");
//            conn = DriverManager.getConnection("jdbc:h2:mem:"+ db + ";DB_CLOSE_DELAY=-1", "sa", "");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }
}
