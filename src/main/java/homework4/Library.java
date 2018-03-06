package homework4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Library {

    Library() {}

    public void init() throws SQLException {
        Connection conn = DBConnector.getConnection();
        Statement query = conn.createStatement();

        query.executeUpdate(
            "CREATE TABLE `books` (" +
            "   `id` INT NOT NULL AUTO_INCREMENT," +
            "   `isbn` varchar(13) NOT NULL UNIQUE," +
            "   `author_id` INT NOT NULL," +
            "   `title_id` INT NOT NULL," +
            "   PRIMARY KEY (`id`)" +
            ");"
        );
        query.executeUpdate(
            "CREATE TABLE `authors` (" +
            "	`id` INT NOT NULL AUTO_INCREMENT," +
            "	`name` varchar(100) NOT NULL UNIQUE," +
            "	PRIMARY KEY (`id`)" +
            ");"
        );
        query.executeUpdate(
            "CREATE TABLE `titles` (" +
            	"`id` INT NOT NULL AUTO_INCREMENT," +
            	"`name` varchar(100) NOT NULL UNIQUE," +
            	"PRIMARY KEY (`id`)" +
            ");"
        );
        query.executeUpdate(
            "ALTER TABLE `books`" +
            "    ADD CONSTRAINT `books_fk0`" +
            "        FOREIGN KEY (`author_id`) REFERENCES `authors`(`id`);"
        );
        query.executeUpdate(
            "ALTER TABLE `books`" +
            "   ADD CONSTRAINT `books_fk1`" +
            "       FOREIGN KEY (`title_id`) REFERENCES `titles`(`id`);"
        );

    }

    public int addAuthor(String name) throws SQLException {
        Connection conn = DBConnector.getConnection();
        PreparedStatement query = conn.prepareStatement(
            "INSERT INTO `authors` (`name`)\n" +
            "VALUES (?);",
            Statement.RETURN_GENERATED_KEYS
        );
        query.setString(1, name);
        query.executeUpdate();

        ResultSet tableKeys = query.getGeneratedKeys();
        tableKeys.next();

        return tableKeys.getInt(1);
    }

    public int getAuthor(String name) throws SQLException {
        Connection conn = DBConnector.getConnection();
        PreparedStatement query = conn.prepareStatement(
            "SELECT `id`\n" +
            "FROM `authors`\n" +
            "WHERE `name` = ?;"
        );
        query.setString(1, name);
        ResultSet result = query.executeQuery();

        return result.first() ? result.getInt(1) : -1;
    }

    public static void main(String[] args) {
        try {
            Library lib = new Library();
            lib.init();
            System.out.println(lib.addAuthor("Ewan"));
            System.out.println(lib.addAuthor("McGregor"));
            System.out.println(lib.getAuthor("Ewan"));
            System.out.println(lib.getAuthor("McGregor"));
            System.out.println(Author.getByName("Ewan"));
		} catch (SQLException e) {
			e.printStackTrace();
        }
    }
}
