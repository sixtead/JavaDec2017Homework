package homework4;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.SQLException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LibraryTest {
    @Test
    @DisplayName("test library constructor")
    void testConstructor() throws SQLException {
        assertNotNull(new Library());
    }
}