package homework1;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HexTest {

  @Test
  @DisplayName("Hex draw test")
  void drawTest() {
    // Hex hex = new Hex(0, 0, 0);
    // System.out.println(hex.draw());
  }

  @Test
  @DisplayName("test straightDistance from {0, 0, 0} to {-1, 2, -1}")
  void testStraightDistance_0_0_0_to_m1_2_m1() {
    Hex hex1 = new Hex(0, 0, 0);
    Hex hex2 = new Hex(-1, 2, -1);
    assertEquals(2, hex1.straightDistance(hex2));
  }

  @Test
  @DisplayName("Test neighbors of Hex {0, 0, 0}")
  void testNeighbors_0_0_0() {
    Hex hex = new Hex(0, 0, 0);
    List<Hex> neighbors = hex.neighbors();
    assertAll("neigbors",
        () -> assertEquals(new Hex(-1, 0, 1), neighbors.get(0)),
        () -> assertEquals(new Hex(-1, 1, 0), neighbors.get(1)),
        () -> assertEquals(new Hex(0, 1, -1), neighbors.get(2)),
        () -> assertEquals(new Hex(1, 0, -1), neighbors.get(3)),
        () -> assertEquals(new Hex(1, -1, 0), neighbors.get(4)),
        () -> assertEquals(new Hex(0, -1, 1), neighbors.get(5))
      );
  }

}