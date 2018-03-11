package homework4;

import org.h2.jdbcx.JdbcConnectionPool;

class DBConnector {
    private final static String DB_CONNECTION = "jdbc:h2:./data/library";
    private final static String DB_USER = "sa";
    private final static String DB_PASS = "";

    static JdbcConnectionPool getConnectionPool() {
        JdbcConnectionPool cp = null;
        cp = JdbcConnectionPool.create(DB_CONNECTION, DB_USER, DB_PASS);
        return cp;
    }


////    private static Connection conn = null;
//    private Connection conn = null;
//
////    static Connection getConnection()
//    Connection getConnection() {
//        if (conn != null) return conn;
//        // get db, user, pass from settings file
//        return getConnection("library");
//    }
//
////    private static Connection getConnection(String db) {
//    private Connection getConnection(String db) {
//        try {
//             conn = DriverManager.getConnection("jdbc:h2:./data/"+ db, "sa", "");
////            conn = DriverManager.getConnection("jdbc:h2:mem:"+ db + ";DB_CLOSE_DELAY=-1", "sa", "");
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//
//        return conn;
//    }
}

//private static JdbcConnectionPool getConnectionPool() {
//    JdbcConnectionPool cp = null;
//    cp = JdbcConnectionPool.create(DB_CONNECTION, DB_USER, DB_PASSWORD);
//    return cp;
//}
//}