package homework4;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class AuthorTest {

    @BeforeAll
    static void setUp() {
        Connection conn = DBConnector.getConnection();
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate(
                    "CREATE TABLE `authors` (" +
                            "	`id` INT NOT NULL AUTO_INCREMENT," +
                            "	`name` varchar(100) NOT NULL UNIQUE," +
                            "	PRIMARY KEY (`id`)" +
                            ");"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("test public constructor")
    void testPublicConstructor() {
        Author author = new Author("О.Р. Лапонина");
        assertNotNull(author);
        assertNull(author.getId());
        assertEquals("О.Р. Лапонина", author.getName());
    }

    @Test
    @DisplayName("test save method")
    void testSave() {
        Author author = new Author("Dave Thomas");
        assertNull(author.getId());

        try {
            author.save();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        assertNotNull(author.getId());
    }

    @Test
    @DisplayName("test getByName static method")
    void testGetByName() {
        Author author = new Author("John Savage");
        try {
            author.save();
            Author authorFromDB = Author.getByName("John Savage");
            assertNotNull(authorFromDB);
            assertEquals(author.getId(), authorFromDB.getId());
            assertEquals(author.getName(), authorFromDB.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("test getByName with non-existing author")
    void testGetByNameNonExistingAuthor() {
        try {
            Author author = Author.getByName("Philip K. Dick");
            assertNull(author);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("test rename method")
    void testRename() {
        String oldName = "Ray Bradbury";
        String newName = "George Orwell";
        Author author = new Author(oldName);
        try {
            author.save();
            assertNotNull(author.getId());
            int id = author.getId();
            assertEquals(oldName, author.getName());
            author.rename(newName);
            assertEquals(id, (int)author.getId());
            assertNotEquals(oldName, author.getName());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}