package homework1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class VertexTest {
  @Test
  @DisplayName("Test creation of Vertex with (1, 2) coordinates")
  void testCreation() {
    int x = 1;
    int y = 2;

    Vertex vert = new Vertex(x, y);

    assertEquals(1, vert.getX());
    assertEquals(2, vert.getY());
  }
}