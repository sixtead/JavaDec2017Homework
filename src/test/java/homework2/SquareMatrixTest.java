package homework2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SquareMatrixTest {

    @Test
    @DisplayName("test matrix creation with odd matrix size")
    void testCreationOdd() {
        SquareMatrix matrix = new SquareMatrix(3);
        assertAll(
                () -> assertEquals(1, matrix.get(0, 0)),
                () -> assertEquals(2, matrix.get(0, 1)),
                () -> assertEquals(3, matrix.get(0, 2)),
                () -> assertEquals(4, matrix.get(1, 0)),
                () -> assertEquals(5, matrix.get(1, 1)),
                () -> assertEquals(6, matrix.get(1, 2)),
                () -> assertEquals(7, matrix.get(2, 0)),
                () -> assertEquals(8, matrix.get(2, 1)),
                () -> assertEquals(9, matrix.get(2, 2))
        );
    }

    @Test
    @DisplayName("test matrix creation with even matrix size")
    void testCreationEven() {
        SquareMatrix matrix = new SquareMatrix(4);
        assertAll(
                () -> assertEquals(1, matrix.get(0, 0)),
                () -> assertEquals(2, matrix.get(0, 1)),
                () -> assertEquals(3, matrix.get(0, 2)),
                () -> assertEquals(4, matrix.get(0, 3)),
                () -> assertEquals(5, matrix.get(1, 0)),
                () -> assertEquals(6, matrix.get(1, 1)),
                () -> assertEquals(7, matrix.get(1, 2)),
                () -> assertEquals(8, matrix.get(1, 3)),
                () -> assertEquals(9, matrix.get(2, 0)),
                () -> assertEquals(10, matrix.get(2, 1)),
                () -> assertEquals(11, matrix.get(2, 2)),
                () -> assertEquals(12, matrix.get(2, 3)),
                () -> assertEquals(13, matrix.get(3, 0)),
                () -> assertEquals(14, matrix.get(3, 1)),
                () -> assertEquals(15, matrix.get(3, 2)),
                () -> assertEquals(16, matrix.get(3, 3))
        );
    }

    @Test
    @DisplayName("test toString() method with odd matrix size")
    void testToStringOdd() {
        SquareMatrix matrix = new SquareMatrix(3);
        assertEquals("1 2 3\n4 5 6\n7 8 9\n", matrix.toString());
    }

    @Test
    @DisplayName("test toString() method with even matrix size")
    void testToStringEven() {
        SquareMatrix matrix = new SquareMatrix(4);
        assertEquals("1 2 3 4\n5 6 7 8\n9 10 11 12\n13 14 15 16\n", matrix.toString());
    }

    @Test
    @DisplayName("test toStringSpiral() method with odd matrix size")
    void testToStringSpiralOdd() {
        SquareMatrix matrix = new SquareMatrix(3);
        assertEquals("5 4 7 8 9 6 3 2 1", matrix.toStringSpiral());
    }

    @Test
    @DisplayName("test toStringSpiral() method with even matrix size")
    void testToStringSpiralEven() {
        SquareMatrix matrix = new SquareMatrix(4);
        assertEquals("7 6 10 11 12 8 4 3 2 1 5 9 13 14 15 16", matrix.toStringSpiral());
    }
}