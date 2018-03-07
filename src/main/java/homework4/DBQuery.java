package homework4;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class DBQuery {

    int insert(String table, String columns, String values) throws SQLException {
        Connection conn = DBConnector.getConnection();
        Statement statement = conn.createStatement();
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO ").append(table).append(" (").append(columns).append(") ");
        sql.append("VALUES ").append("(").append(values).append(")").append(";");
        return statement.executeUpdate(sql.toString());
    }

    ResultSet select(String table, String columns, String conditions) throws SQLException {
        Connection conn = DBConnector.getConnection();
        Statement statement = conn.createStatement();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ").append(columns).append(" ");
        sql.append("FROM ").append(table).append(" ");
        sql.append("WHERE ").append(conditions).append(";");
        return statement.executeQuery(sql.toString());
    }

    int update(String table, String columnsValues, String conditions) throws SQLException {
        Connection conn = DBConnector.getConnection();
        Statement statement = conn.createStatement();
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE ").append(table).append(" ");
        sql.append("SET" ).append(columnsValues).append(" ");
        sql.append("WHERE ").append(conditions).append(";");
        return statement.executeUpdate(sql.toString());
    }

    int delete(String table, String conditions) throws SQLException {
        Connection conn = DBConnector.getConnection();
        Statement statement = conn.createStatement();
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM ").append(table).append(" ");
        sql.append("WHERE ").append(conditions).append(";");
        return statement.executeUpdate(sql.toString());
    }
}