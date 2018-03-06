package homework4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Author {
    private static Connection conn = null;
    private Integer id;
    private String name;

    private static Connection connect() throws SQLException {
		return DriverManager.getConnection("jdbc:h2:mem:", "sa", "");
	};

    Author(Integer id, String name) throws SQLException {
        this.id = id;
        this.name = name;
    }

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
    }
    
    public static Author getByName(String name) throws SQLException {
        conn = connect();
        Integer id;
        PreparedStatement pStatement = conn.prepareStatement(
            "SELECT `id`\n" +
            "FROM `authors`\n" +
            "WHERE `name` = ?;"
        );
        pStatement.setString(1, name);
        ResultSet rSet = pStatement.executeQuery();
        conn.close();

        id = rSet.first() ? rSet.getInt(1) : null;
        return (id != null) ? new Author(id, name) : null;
    }
}