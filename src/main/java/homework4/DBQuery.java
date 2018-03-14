package homework4;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import org.h2.jdbcx.JdbcConnectionPool;

class DBQuery {

    static int insert(String table, String columns, String values, Map<Integer, Object> parameters) throws SQLException {
        JdbcConnectionPool cp = DBConnector.getConnectionPool();
        Connection conn = cp.getConnection();
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
        int rowsCount = pStatement.executeUpdate();
        pStatement.close();
        conn.close();
        cp.dispose();
        return rowsCount;
    }

    static ResultSet select(String table, String columns, String conditions, Map<Integer, Object> parameters) throws SQLException {
        JdbcConnectionPool cp = DBConnector.getConnectionPool();
        Connection conn = cp.getConnection();
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
        ResultSet resultSet = pStatement.executeQuery();
        CachedRowSet crs = RowSetProvider.newFactory().createCachedRowSet();
        crs.populate(resultSet);
        conn.close();
        cp.dispose();
        return crs;
    }

    static int update(String table, String columnsValues, String conditions, Map<Integer, Object> parameters) throws SQLException {
        JdbcConnectionPool cp = DBConnector.getConnectionPool();
        Connection conn = cp.getConnection();
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
        int rowsCount = pStatement.executeUpdate();
        conn.close();
        cp.dispose();
        return rowsCount;
    }

    static int delete(String table, String conditions, Map<Integer, Object> parameters) throws SQLException {
        JdbcConnectionPool cp = DBConnector.getConnectionPool();
        Connection conn = cp.getConnection();
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
        int rowsCount = pStatement.executeUpdate();
        conn.close();
        cp.dispose();
        return rowsCount;
    }

    static void create(String table, String columns) {
        try {
            JdbcConnectionPool cp = DBConnector.getConnectionPool();
            Connection conn = cp.getConnection();
            Statement statement = conn.createStatement();
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS " + table + "(" +
                    "`id` INT NOT NULL AUTO_INCREMENT," +
                    columns +
                    ",PRIMARY KEY (`id`));"
            );
            conn.close();
            cp.dispose();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    static void addFK(String table, String fkName, String field, String referenceTable, String referenceField) {
        try {
            JdbcConnectionPool cp = DBConnector.getConnectionPool();
            Connection conn = cp.getConnection();
            Statement statement = conn.createStatement();
            statement.executeUpdate(
                    "ALTER TABLE " + table +
                    "ADD CONSTRAINT `" + fkName + "`" +
                    "FOREIGN KEY (`" + field + "`) " +
                    "REFERENCES `" + referenceTable + "`(`" + referenceField + "`);"
            );
            conn.close();
            cp.dispose();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    static void drop(String table) {
        try {
            JdbcConnectionPool cp = DBConnector.getConnectionPool();
            Connection conn = cp.getConnection();
            Statement statement = conn.createStatement();
            statement.executeUpdate(
                    "DROP TABLE IF EXISTS" + table + ";"
            );
            conn.close();
            cp.dispose();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}