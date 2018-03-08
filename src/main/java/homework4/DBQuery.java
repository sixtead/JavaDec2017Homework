package homework4;

import java.sql.*;
import java.util.Map;

class DBQuery {

    static int insert(String table, String columns, String values, Map<Integer, Object> parameters) throws SQLException {
        Connection conn = DBConnector.getConnection();
        String sql = "INSERT INTO " + table + " (" + columns + ") " +
                     "VALUES " + "(" + values + ")" + ";";
        PreparedStatement pStatement = conn.prepareStatement(sql);

        for(Map.Entry<Integer, Object> param : parameters.entrySet()) {
            Integer key = param.getKey();
            Object value = param.getValue();
            if (value instanceof Integer) {
                pStatement.setInt(key, (int) value);
            } else if (value instanceof String) {
                pStatement.setString(key, (String) value);
            }
        }
//        conn.close();
        return pStatement.executeUpdate();
    }

    static ResultSet select(String table, String columns, String conditions, Map<Integer, Object> parameters) throws SQLException {
        Connection conn = DBConnector.getConnection();
        String sql = "SELECT " + columns + " " +
                     "FROM " + table + " " +
                     "WHERE " + conditions + ";";
        PreparedStatement pStatement = conn.prepareStatement(sql);

        for(Map.Entry<Integer, Object> param : parameters.entrySet()) {
            Integer key = param.getKey();
            Object value = param.getValue();
            if (value instanceof Integer) {
                pStatement.setInt(key, (int) value);
            } else if (value instanceof String) {
                pStatement.setString(key, (String) value);
            }
        }
        return pStatement.executeQuery();
    }

    static int update(String table, String columnsValues, String conditions, Map<Integer, Object> parameters) throws SQLException {
        Connection conn = DBConnector.getConnection();
        String sql = "UPDATE " + table + " " +
                     "SET " + columnsValues + " " +
                     "WHERE " + conditions + ";";
        PreparedStatement pStatement = conn.prepareStatement(sql);

        for(Map.Entry<Integer, Object> param : parameters.entrySet()) {
            Integer key = param.getKey();
            Object value = param.getValue();
            if (value instanceof Integer) {
                pStatement.setInt(key, (int) value);
            } else if (value instanceof String) {
                pStatement.setString(key, (String) value);
            }
        }
        return pStatement.executeUpdate();
    }

    static int delete(String table, String conditions, Map<Integer, Object> parameters) throws SQLException {
        Connection conn = DBConnector.getConnection();
        String sql = "DELETE FROM " + table + " " +
                     "WHERE " + conditions + ";";
        PreparedStatement pStatement = conn.prepareStatement(sql);

        for(Map.Entry<Integer, Object> param : parameters.entrySet()) {
            Integer key = param.getKey();
            Object value = param.getValue();
            if (value instanceof Integer) {
                pStatement.setInt(key, (int) value);
            } else if (value instanceof String) {
                pStatement.setString(key, (String) value);
            }
        }
        return pStatement.executeUpdate();
    }

    static void create(String table, String columns) {
        Connection conn = DBConnector.getConnection();
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS " + table + "(" +
                    "`id` INT NOT NULL AUTO_INCREMENT," +
                    columns +
                    ",PRIMARY KEY (`id`));"
            );
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    static void drop(String table) {
        Connection conn = DBConnector.getConnection();
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate(
                    "DROP TABLE IF EXISTS" + table + ";"
            );
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}