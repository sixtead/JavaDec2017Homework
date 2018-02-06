package homework1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HexGridTest {

  @Test
  @DisplayName("Test HexGrid creation with radius = 0")
  void testCreation_radius0() {
    HexGrid grid = new HexGrid(0);

    assertNotNull(grid);
    assertEquals(1, grid.size());
    assertTrue(grid.contains(new Hex(0, 0, 0)));
    assertFalse(grid.contains(new Hex(-1, 1, 0)));
  }

  @Test
  @DisplayName("Test HexGrid creation with radius = 1")
  void testCreation_radius1() {
    HexGrid grid = new HexGrid(1);

    assertNotNull(grid);
    assertEquals(7, grid.size());
    assertTrue(grid.contains(new Hex(0, 0, 0)));
    assertTrue(grid.contains(new Hex(-1, 0, 1)));
    assertTrue(grid.contains(new Hex(-1, 1, 0)));
    assertTrue(grid.contains(new Hex(0, -1, 1)));
    assertTrue(grid.contains(new Hex(0, 1, -1)));
    assertTrue(grid.contains(new Hex(1, -1, 0)));
    assertTrue(grid.contains(new Hex(1, 0, -1)));

    assertFalse(grid.contains(new Hex(-2, 0, 2)));
  }

  @Test
  @DisplayName("Test HexGrid creation with radius = 2")
  void testCreation_radius2() {
    HexGrid grid = new HexGrid(2);

    assertNotNull(grid);
    assertEquals(19, grid.size());
    assertTrue(grid.contains(new Hex(0, 0, 0)));
    assertTrue(grid.contains(new Hex(-1, 0, 1)));
    assertTrue(grid.contains(new Hex(-1, 1, 0)));
    assertTrue(grid.contains(new Hex(0, -1, 1)));
    assertTrue(grid.contains(new Hex(0, 1, -1)));
    assertTrue(grid.contains(new Hex(1, -1, 0)));
    assertTrue(grid.contains(new Hex(1, 0, -1)));
    assertTrue(grid.contains(new Hex(-2, 0, 2)));
    assertTrue(grid.contains(new Hex(-2, 1, 1)));
    assertTrue(grid.contains(new Hex(-2, 2, 0)));
    assertTrue(grid.contains(new Hex(-1, -1, 2)));
    assertTrue(grid.contains(new Hex(-1, 2, -1)));
    assertTrue(grid.contains(new Hex(0, -2, 2)));
    assertTrue(grid.contains(new Hex(0, 2, -2)));
    assertTrue(grid.contains(new Hex(1, 1, -2)));
    assertTrue(grid.contains(new Hex(1, -2, 1)));
    assertTrue(grid.contains(new Hex(2, 0, -2)));
    assertTrue(grid.contains(new Hex(2, -1, -1)));
    assertTrue(grid.contains(new Hex(2, -2, 0)));

    assertFalse(grid.contains(new Hex(-1, 3, -2)));
  }

  @Test
  @DisplayName("Test adding and removing hexes")
  void testAddingRemoving() {
    HexGrid grid = new HexGrid(2);

    assertTrue(grid.contains(new Hex(0, 0, 0)));
    grid.remove(new Hex(0, 0, 0));
    assertFalse(grid.contains(new Hex(0, 0, 0)));
    grid.add(new Hex(0, 0, 0));
    assertTrue(grid.contains(new Hex(0, 0, 0)));
  }

  @Test
  @DisplayName("Test straightDistance on Grid radius 3 from {0, 0, 0} to {-3, 5, -2}")
  void testStraightDistanceOnGrid_r3_0_0_0_to_m3_5_m2() {
    HexGrid grid = new HexGrid(3);
    Hex hex1 = new Hex(0, 0, 0);
    Hex hex2 = new Hex(-3, 5, -2);
    assertNotEquals(5, grid.straightDistance(hex1, hex2));
    assertEquals(-1, grid.straightDistance(hex1, hex2));
    List<Hex> path = grid.straightPath(hex1, hex2);
    assertEquals(0, path.size());
  }

  @Test
  @DisplayName("Test straightDistance on Grid radius 5 from {0, 0, 0} to {-3, 5, -2}")
  void testStraightDistanceOnGrid_r5_0_0_0_to_m3_5_m2() {
    HexGrid grid = new HexGrid(5);
    Hex hex1 = new Hex(0, 0, 0);
    Hex hex2 = new Hex(-3, 5, -2);
    assertEquals(5, grid.straightDistance(hex1, hex2));
    List<Hex> path = grid.straightPath(hex1, hex2);
    List<Hex> expected = new LinkedList<>();
    expected.add(new Hex(0, 0, 0));
    expected.add(new Hex(-1, 1, 0));
    expected.add(new Hex(-1, 2, -1));
    expected.add(new Hex(-2, 3, -1));
    expected.add(new Hex(-2, 4, -2));
    expected.add(new Hex(-3, 5, -2));
    assertEquals(expected.size(), path.size());
    for(int i = 0; i < path.size(); i++) {
      assertEquals(expected.get(i), path.get(i));
    }
  }
}