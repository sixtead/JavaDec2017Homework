package homework1;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class HexGrid {
  private Set<Hex> grid;

  HexGrid() {}

  HexGrid(int radius) {
    this.grid = new HashSet<>();

    if(radius >= 0) {
      for(int x = -radius; x <= radius; x++) {
        for(int y = -radius; y <= radius; y++) {
          int z = 0 - x - y;
          if(Math.abs(z) <= radius) grid.add(new Hex(x, y, z));
        }
      }
    }
  }

  public int size() {
    return grid.size();
  }

  public boolean add(Hex hex) {
    return grid.add(hex);
  }

  public boolean remove(Hex hex) {
    return grid.remove(hex);
  }

  public boolean contains(Hex hex) {
    return grid.contains(hex);
  }

  public String toString() {
    String result = "";

    for(Hex hex: grid) {
      result += hex.toString() + ", ";
    }

    return result.endsWith(", ")
        ? result.substring(0, result.length() - 2)
        : result;
  }

  public int straightDistance(Hex hex1, Hex hex2) {
    return (grid.contains(hex1) && grid.contains(hex2))
        ? hex1.straightDistance(hex2)
        : -1;
  }

  public List<Hex> straightPath(Hex hex1, Hex hex2) {
    List<Hex> path = new LinkedList<>();
    int distance = hex1.straightDistance(hex2);

    if(grid.contains(hex1) && grid.contains(hex2)) {
      for(int i = 0; i <= distance; i++) {
        float pathPart = (i == 0) ? 0 : 1F / distance * i;
        Hex hexToAdd = Hex.cubeLerp(hex1, hex2, pathPart);
        if(grid.contains(hexToAdd)) {
          path.add(hexToAdd);
        } else {
          break;
        }
      }
    }

    return path;
  }

}