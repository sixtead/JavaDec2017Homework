package homework4;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TitleTest {

    @BeforeAll
    static void setUp() {
        Title.drop();
        Title.migrate();
    }

    @AfterAll
    static void tearDown() {
        Title.drop();
    }

    @Test
    @DisplayName("test public constructor")
    void testPublicConstructor() {
        Title title = new Title("Java for beginners");
        assertNotNull(title);
        assertNull(title.getId());
        assertEquals("Java for beginners", title.getName());
    }

    @Test
    @DisplayName("test save method")
    void testSave() {
        Title title = new Title("Java Core");
        assertNull(title.getId());
        title.save();
        assertNotNull(title.getId());
    }

    @Test
    @DisplayName("test getById static method")
    void testGetById() {
        Title title = Title.getById(1);
        assertEquals(1, (int) title.getId());
    }

    @Test
    @DisplayName("test getByName static method")
    void testGetByName() {
        Title title = new Title("Learning Java");
        title.save();
        Title titleFromDB = Title.getByName("Learning Java");
        assertNotNull(titleFromDB);
        assertEquals(title.getId(), titleFromDB.getId());
        assertEquals(title.getName(), titleFromDB.getName());
    }

    @Test
    @DisplayName("test getByName with non-existing title")
    void testGetByNameNonExistingTitle() {
        Title title = Title.getByName("Ruby");
        assertNull(title);
    }

    @Test
    @DisplayName("test rename method")
    void testRename() {
        String oldName = "Advanced Java";
        String newName = "Advanced Java II";
        Title title = new Title(oldName);
        title.save();
//        System.out.println("ID = " + title.getId());
//        System.out.println("NAME = " + title.getName());
        assertNotNull(title.getId());
        int id = title.getId();
        assertEquals(oldName, title.getName());
        title.rename(newName);
        assertEquals(id, (int) title.getId());
        assertNotEquals(oldName, title.getName());
    }

    @Test
    @DisplayName("test remove method")
    void testRemove() {
        Title title = Title.getById(1);
        assertNotNull(title);
        title.remove();
        assertNull(title.getId());
        assertNull(title.getName());
//        System.out.println("SHOULD FAIL ALREADY");
        title = Title.getById(1);
        assertNull(title);
    }
}