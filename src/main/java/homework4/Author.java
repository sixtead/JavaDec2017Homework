package homework4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Author {
    private Integer id;
    private String name;

    private static final String TABLE = "`authors`";

    Author(String name) {
        this.id = null;
        this.name = name;
    }

    private Author(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public static Author getById(int id) throws SQLException {
        ResultSet rSet = Author.select(id);
        if(rSet.first()) {
            return new Author(rSet.getInt(1), rSet.getString(2));
        } else {
            return null;
        }
    }

    public static Author getByName(String name) throws SQLException {
        ResultSet rSet = Author.select(name);
        if(rSet.first()) {
            return new Author(rSet.getInt(1), rSet.getString(2));
        } else {
            return null;
        }
    }

    private static ResultSet select(int id) throws SQLException {
        // Connection conn = DBConnector.getConnection();
        // PreparedStatement pStatement = conn.prepareStatement(
        //         "SELECT `id`, `name`\n" +
        //         "FROM `authors`\n" +
        //         "WHERE `id` = ?;"
        // );
        // pStatement.setInt(1, id);
        // return pStatement.executeQuery();
        String cols = "`id`, `name`";
        String conds = "`id` = " + id;
        return new DBQuery().select(TABLE, cols, conds);
    }

    private static ResultSet select(String name) throws SQLException {
        // Connection conn = DBConnector.getConnection();
        // PreparedStatement pStatement = conn.prepareStatement(
        //         "SELECT `id`, `name`\n" +
        //                 "FROM `authors`\n" +
        //                 "WHERE `name` = ?;"
        // );
        // pStatement.setString(1, name);
        // return pStatement.executeQuery();
        String cols = "`id`, `name`";
        String conds = "`name` LIKE '" + name + "'";
        return new DBQuery().select(TABLE, cols, conds);
    }

    public void save() throws SQLException {
        Author auth = Author.getByName(name);
        id = (auth != null) ? auth.getId() : Author.insert(name).getId();
    }

    private static Author insert(String name) throws SQLException {
        Connection conn = DBConnector.getConnection();
        PreparedStatement pStatement = conn.prepareStatement(
                "INSERT INTO `authors` (`name`)\n" +
                "VALUES (?);",
                Statement.RETURN_GENERATED_KEYS
        );
        pStatement.setString(1, name);
        pStatement.executeUpdate();

        ResultSet tableKeys = pStatement.getGeneratedKeys();
        tableKeys.next();

        return new Author(tableKeys.getInt(1), name);
    }

    public void rename(String name) throws SQLException {
        if(id != null) {
            this.name = update(name).getName();
        } else {
            System.out.println("No such entry in database");
        }
    }

    private Author update(String name) throws SQLException {
        Connection conn = DBConnector.getConnection();
        PreparedStatement pStatement = conn.prepareStatement(
                "UPDATE `authors`\n" +
                "SET `name` = ?\n" +
                "WHERE `id` = ?;"
        );
        pStatement.setString(1, name);
        pStatement.setInt(2, id);
        pStatement.executeUpdate();

        return new Author(id, name);
    }

    public void remove() {
//        check if author not used in any books
//        if so, print message with id's of these books
//        otherwise call private method delete
    }

    private void delete() throws SQLException {
        Connection conn = DBConnector.getConnection();
        PreparedStatement pStatement = conn.prepareStatement(
                "DELETE FROM `authors`\n" +
                "WHERE `id` = ?;"
        );
        pStatement.setInt(1, id);
        pStatement.executeUpdate();
    }
}