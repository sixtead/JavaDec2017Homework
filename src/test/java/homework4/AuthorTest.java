package homework4;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthorTest {

    @BeforeAll
    static void setUp() {
        Author.drop();
        Author.migrate();
    }

    @AfterAll
    static void tearDown() {
        Author.drop();
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
        author.save();
        assertNotNull(author.getId());
    }

    @Test
    @DisplayName("test getById static method")
    void testGetById() {
        Author author = Author.getById(1);
        assertEquals(1, (int) author.getId());
    }

    @Test
    @DisplayName("test getByName static method")
    void testGetByName() {
        Author author = new Author("John Savage");
        author.save();
        Author authorFromDB = Author.getByName("John Savage");
        assertNotNull(authorFromDB);
        assertEquals(author.getId(), authorFromDB.getId());
        assertEquals(author.getName(), authorFromDB.getName());
    }

    @Test
    @DisplayName("test getByName with non-existing author")
    void testGetByNameNonExistingAuthor() {
        Author author = Author.getByName("Philip K. Dick");
        assertNull(author);
    }

    @Test
    @DisplayName("test rename method")
    void testRename() {
        String oldName = "Ray Bradbury";
        String newName = "George Orwell";
        Author author = new Author(oldName);
        author.save();
//        System.out.println("ID = " + author.getId());
//        System.out.println("NAME = " + author.getName());
        assertNotNull(author.getId());
        int id = author.getId();
        assertEquals(oldName, author.getName());
        author.rename(newName);
        assertEquals(id, (int) author.getId());
        assertNotEquals(oldName, author.getName());
    }

    @Test
    @DisplayName("test remove method")
    void testRemove() {
        Author author = Author.getById(1);
        assertNotNull(author);
        author.remove();
        assertNull(author.getId());
        assertNull(author.getName());
//        System.out.println("SHOULD FAIL ALREADY");
        author = Author.getById(1);
        assertNull(author);
    }
}