package homework1;

class Rectangle {
  private Vertex vertexA;
  private Vertex vertexB;
  private Vertex vertexC;
  private Vertex vertexD;
  
  Rectangle() {}
  
  Rectangle(int x1, int y1, int x2, int y2) {
    this.vertexA = new Vertex(Math.min(x1, x2), Math.min(y1, y2));
    this.vertexB = new Vertex(Math.min(x1, x2), Math.max(y1, y2));
    this.vertexC = new Vertex(Math.max(x1, x2), Math.max(y1, y2));
    this.vertexD = new Vertex(Math.max(x1, x2), Math.min(y1, y2));
  }
  
  public Vertex getVertexA() {
    return vertexA;
  }
  
  public Vertex getVertexB() {
    return vertexB;
  }
  
  public Vertex getVertexC() {
    return vertexC;
  }
  
  public Vertex getVertexD() {
    return vertexD;
  }

  public double edgeLength(Vertex vert1, Vertex vert2) {
    return Math.sqrt(
        Math.pow(vert1.getX() - vert2.getX(), 2)
        + Math.pow(vert1.getY() - vert2.getY(), 2)
      );
  }

  public double area() {
    return edgeLength(vertexA, vertexB) * edgeLength(vertexA, vertexD);
  }

  public Rectangle intersection(Rectangle rec) {
    int minX = Math.min(this.getVertexA().getX(), rec.getVertexA().getX());
    int maxX = Math.max(this.getVertexD().getX(), rec.getVertexD().getX());
    Integer leftmostX = null;
    Integer rightmostX = null;
    int minY = Math.min(this.getVertexA().getY(), rec.getVertexA().getY());
    int maxY = Math.max(this.getVertexB().getY(), rec.getVertexB().getY());
    Integer bottomY = null;
    Integer topY = null;

    for(int x = minX; x <= maxX; x++) {
      if(x >= this.getVertexA().getX() && x <= this.getVertexD().getX()
          && x >= rec.getVertexA().getX() && x <= rec.getVertexD().getX()) {
        leftmostX = leftmostX != null ? Math.min(leftmostX, x) : x;
        rightmostX = rightmostX != null ? Math.max(rightmostX, x) : x;
      }
    }

    if(leftmostX == null || rightmostX == null) return new Rectangle(0, 0, 0, 0);

    for(int y = minY; y <= maxY; y++) {
      if(y >= this.getVertexA().getY() && y <= this.getVertexB().getY()
          && y >= rec.getVertexA().getY() && y <= rec.getVertexB().getY()) {
        bottomY = bottomY != null ? Math.min(bottomY, y) : y;
        topY = topY != null ? Math.max(topY, y) : y;
      }
    }

    return bottomY == null || topY == null
        ? new Rectangle(0, 0, 0, 0)
        : new Rectangle(leftmostX, bottomY, rightmostX, topY);
  }

}