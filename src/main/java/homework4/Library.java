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
//        Connection conn = DBConnector.getConnection();
//        Statement query = conn.createStatement();
//
//        query.executeUpdate(
//            "CREATE TABLE `books` (" +
//            "   `id` INT NOT NULL AUTO_INCREMENT," +
//            "   `isbn` varchar(13) NOT NULL UNIQUE," +
//            "   `author_id` INT NOT NULL," +
//            "   `title_id` INT NOT NULL," +
//            "   PRIMARY KEY (`id`)" +
//            ");"
//        );
//        query.executeUpdate(
//            "CREATE TABLE `authors` (" +
//            "	`id` INT NOT NULL AUTO_INCREMENT," +
//            "	`name` varchar(100) NOT NULL UNIQUE," +
//            "	PRIMARY KEY (`id`)" +
//            ");"
//        );
//        query.executeUpdate(
//            "CREATE TABLE `titles` (" +
//            	"`id` INT NOT NULL AUTO_INCREMENT," +
//            	"`name` varchar(100) NOT NULL UNIQUE," +
//            	"PRIMARY KEY (`id`)" +
//            ");"
//        );
//        query.executeUpdate(
//            "ALTER TABLE `books`" +
//            "    ADD CONSTRAINT `books_fk0`" +
//            "        FOREIGN KEY (`author_id`) REFERENCES `authors`(`id`);"
//        );
//        query.executeUpdate(
//            "ALTER TABLE `books`" +
//            "   ADD CONSTRAINT `books_fk1`" +
//            "       FOREIGN KEY (`title_id`) REFERENCES `titles`(`id`);"
//        );
//
    }

    public static void main(String[] args) {
//        try {
//            Library lib = new Library();
//            lib.init();
//            System.out.println(lib.addAuthor("Ewan"));
//            System.out.println(lib.addAuthor("McGregor"));
//            System.out.println(lib.getAuthor("Ewan"));
//            System.out.println(lib.getAuthor("McGregor"));
//            System.out.println(Author.getByName("Ewan"));
//		} catch (SQLException e) {
//			System.out.println(e.getMessage());
//        }
    }
}
