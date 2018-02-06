package homework1;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class Hex {
  private int x;
  private int y;
  private int z;

  Hex() {}
  Hex(int x, int y, int z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public int getZ() {
    return z;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y, z);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) return false;
    if (obj == this) return true;
    if (!(obj instanceof Hex))return false;

    Hex hex = (Hex) obj;

    return (this.x == hex.x)
        && (this.y == hex.y)
        && (this.z == hex.z);
  }

  @Override
  public String toString() {
    return "[" + x + ", " + y + ", " + z + "]";
  }

  public int straightDistance(Hex targetHex) {
    return (Math.abs(this.x - targetHex.x)
        + Math.abs(this.y - targetHex.y)
        + Math.abs(this.z - targetHex.z)) / 2;
  }

  public List<Hex> neighbors() {
    List<Hex> neighbors = new ArrayList<>();
    neighbors.add(new Hex(x - 1, y + 0, z + 1));
    neighbors.add(new Hex(x - 1, y + 1, z + 0));
    neighbors.add(new Hex(x + 0, y + 1, z - 1));
    neighbors.add(new Hex(x + 1, y + 0, z - 1));
    neighbors.add(new Hex(x + 1, y - 1, z + 0));
    neighbors.add(new Hex(x + 0, y - 1, z + 1));

    return neighbors;
  }

  // linearInterpolation
  private static float lerp(int start, int end, float pathPart) {
    return start + pathPart * (end - start);
  }

  static Hex cubeLerp(Hex start, Hex end, float pathPart) {
    return new Hex(Math.round(Hex.lerp(start.getX(), end.getX(), pathPart)),
        Math.round(Hex.lerp(start.getY(), end.getY(), pathPart)),
        Math.round(Hex.lerp(start.getZ(), end.getZ(), pathPart)));
  }

  public String draw() {
    return
      "   -----\n" +
      " /   " + x +"   \\\n" +
      "/    " + y + "    \\\n" +
      "\\    " + z + "    /\n" +
      " \\       /\n" +
      "   -----";
  }
}