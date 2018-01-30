package homework1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RectangleTest {

  @Test
  @DisplayName("Test with {0, 0, 1, 1}")
  void test_0_0_1_1() {
    Rectangle rec = new Rectangle(0, 0, 1, 1);
    assertEquals(1, rec.edgeLength(rec.getVertexA(), rec.getVertexD()));
    assertEquals(1, rec.edgeLength(rec.getVertexA(), rec.getVertexB()));
    assertEquals(1, rec.area());
  }

  @Test
  @DisplayName("Test with {-5, -10, -5, 0}")
  void test_m5_m10_m5_0() {
    Rectangle rec = new Rectangle(-5, -10, -5, 0);
    assertEquals(0, rec.edgeLength(rec.getVertexA(), rec.getVertexD()));
    assertEquals(10, rec.edgeLength(rec.getVertexA(), rec.getVertexB()));
    assertEquals(0, rec.area());
  }

  @Test
  @DisplayName("Test with {-3, 6, -7, 2}")
  void test_m3_6_m7_2() {
    Rectangle rec = new Rectangle(-3, 6, -7, 2);
    assertEquals(4, rec.edgeLength(rec.getVertexA(), rec.getVertexD()));
    assertEquals(4, rec.edgeLength(rec.getVertexA(), rec.getVertexB()));
    assertEquals(16, rec.area());
  }

  @Test
  @DisplayName("Test with {-3, -4, -1, 5}")
  void test_m3_m4_m1_5() {
    Rectangle rec = new Rectangle(-3, -4, -1, 5);
    assertEquals(2, rec.edgeLength(rec.getVertexA(), rec.getVertexD()));
    assertEquals(9, rec.edgeLength(rec.getVertexA(), rec.getVertexB()));
    assertEquals(18, rec.area());
  }

  @Test
  @DisplayName("Test with {-2, 0, -1, 8}")
  void test_m2_0_m1_8() {
    Rectangle rec = new Rectangle(-2, 0, -1, 8);
    assertEquals(1, rec.edgeLength(rec.getVertexA(), rec.getVertexD()));
    assertEquals(8, rec.edgeLength(rec.getVertexA(), rec.getVertexB()));
    assertEquals(8, rec.area());
  }

  @Test
  @DisplayName("Test with {1, 9, 8, -7}")
  void test_1_9_8_m7() {
    Rectangle rec = new Rectangle(1, 9, 8, -7);
    assertEquals(7, rec.edgeLength(rec.getVertexA(), rec.getVertexD()));
    assertEquals(16, rec.edgeLength(rec.getVertexA(), rec.getVertexB()));
    assertEquals(112, rec.area());
  }

  @Test
  @DisplayName("Test with {4, -10, 6, -8}")
  void test_4_m10_6_m8() {
    Rectangle rec = new Rectangle(4, -10, 6, -8);
    assertEquals(2, rec.edgeLength(rec.getVertexA(), rec.getVertexD()));
    assertEquals(2, rec.edgeLength(rec.getVertexA(), rec.getVertexB()));
    assertEquals(4, rec.area());
  }

  @Test
  @DisplayName("Test with {6, 5, -1, -7}")
  void test_6_5_m1_m7() {
    Rectangle rec = new Rectangle(6, 5, -1, -7);
    assertEquals(7, rec.edgeLength(rec.getVertexA(), rec.getVertexD()));
    assertEquals(12, rec.edgeLength(rec.getVertexA(), rec.getVertexB()));
    assertEquals(84, rec.area());
  }

  @Test
  @DisplayName("Test with {2, 3, 4, 4}")
  void test_2_3_4_4() {
    Rectangle rec = new Rectangle(2, 3, 4, 4);
    assertEquals(2, rec.edgeLength(rec.getVertexA(), rec.getVertexD()));
    assertEquals(1, rec.edgeLength(rec.getVertexA(), rec.getVertexB()));
    assertEquals(2, rec.area());
  }

  @Test
  @DisplayName("Test with {8, 7, 6, 6}")
  void test_8_7_6_6() {
    Rectangle rec = new Rectangle(8, 7, 6, 6);
    assertEquals(2, rec.edgeLength(rec.getVertexA(), rec.getVertexD()));
    assertEquals(1, rec.edgeLength(rec.getVertexA(), rec.getVertexB()));
    assertEquals(2, rec.area());
  }

  @Test
  @DisplayName("Test with {4, 7, 4, -1}")
  void test_4_7_4_m1() {
    Rectangle rec = new Rectangle(4, 7, 4, -1);
    assertEquals(0, rec.edgeLength(rec.getVertexA(), rec.getVertexD()));
    assertEquals(8, rec.edgeLength(rec.getVertexA(), rec.getVertexB()));
    assertEquals(0, rec.area());
  }

  @Test
  @DisplayName("Test with {-5, -5, 5, 5} and {-8, -4, -6, 3}")
  void test_m5_m5_m5_m5_and_m8_m4_m6_3() {
    Rectangle rec1 = new Rectangle(-5, -5, 5, 5);
    Rectangle rec2 = new Rectangle(-8, -4, -6, 3);
    assertEquals(0, rec1.intersection(rec2).area());
  }

  @Test
  @DisplayName("Test with {-5, -5, 5, 5} and {-7, -7, -4, 4}")
  void test_m5_m5_m5_m5_and_m7_m7_m4_4() {
    Rectangle rec1 = new Rectangle(-5, -5, 5, 5);
    Rectangle rec2 = new Rectangle(-7, -7, -4, 4);
    assertEquals(9, rec1.intersection(rec2).area());
  }

  @Test
  @DisplayName("Test with {-5, -5, 5, 5} and {-7, -3, -2, 2}")
  void test_m5_m5_m5_m5_and_m7_m3_m2_2() {
    Rectangle rec1 = new Rectangle(-5, -5, 5, 5);
    Rectangle rec2 = new Rectangle(-7, -3, -2, 2);
    assertEquals(15, rec1.intersection(rec2).area());
  }

  @Test
  @DisplayName("Test with {-5, -5, 5, 5} and {-7, 2, -2, 7}")
  void test_m5_m5_m5_m5_and_m7_2_m2_7() {
    Rectangle rec1 = new Rectangle(-5, -5, 5, 5);
    Rectangle rec2 = new Rectangle(-7, 2, -2, 7);
    assertEquals(9, rec1.intersection(rec2).area());
  }

  @Test
  @DisplayName("Test with {-5, -5, 5, 5} and {-2, -6, 2, 7}")
  void test_m5_m5_m5_m5_and_m2_m6_2_7() {
    Rectangle rec1 = new Rectangle(-5, -5, 5, 5);
    Rectangle rec2 = new Rectangle(-2, -6, 2, 7);
    assertEquals(40, rec1.intersection(rec2).area());
  }

  @Test
  @DisplayName("Test with {-5, -5, 5, 5} and {4, 4, 5, 5}")
  void test_m5_m5_m5_m5_and_4_4_5_5() {
    Rectangle rec1 = new Rectangle(-5, -5, 5, 5);
    Rectangle rec2 = new Rectangle(4, 4, 5, 5);
    assertEquals(1, rec1.intersection(rec2).area());
  }

  @Test
  @DisplayName("Test with {-5, -5, 5, 5} and {-2, -3, 2, 2}")
  void test_m5_m5_m5_m5_and_m2_m3_2_2() {
    Rectangle rec1 = new Rectangle(-5, -5, 5, 5);
    Rectangle rec2 = new Rectangle(-2, -3, 2, 2);
    assertEquals(20, rec1.intersection(rec2).area());
  }

  @Test
  @DisplayName("Test with {-5, -5, 5, 5} and {6, 6, 8, -9}")
  void test_m5_m5_m5_m5_and_6_6_8_m9() {
    Rectangle rec1 = new Rectangle(-5, -5, 5, 5);
    Rectangle rec2 = new Rectangle(6, 6, 8, -9);
    assertEquals(0, rec1.intersection(rec2).area());
  }

  // @Test
  // @DisplayName("Test with {-5, -5, 5, 5} and {-4, -10, -7, 3}")
  // void test_m5_m5_m5_m5_and_m4_m10_m7_3() {
  //   Rectangle rec1 = new Rectangle(-5, -5, 5, 5);
  //   Rectangle rec2 = new Rectangle(-4, -10, -7, 3);
  //   assertEquals(0, rec1.intersection(rec2).area());
  // }

  // @Test
  // @DisplayName("Test with {-5, -5, 5, 5} and {5, 0, -5, 0}")
  // void test_m5_m5_m5_m5_and_5_0_m5_0() {
  //   Rectangle rec1 = new Rectangle(-5, -5, 5, 5);
  //   Rectangle rec2 = new Rectangle(5, 0, -5, 0);
  //   assertEquals(0, rec1.intersection(rec2).area());
  // }

  @Test
  @DisplayName("Test with {-5, -5, 5, 5} and {-4, 3, 5, -8}")
  void test__m5_m5_m5_m5_and_m4_3_5_m8() {
    Rectangle rec1 = new Rectangle(-5, -5, 5, 5);
    Rectangle rec2 = new Rectangle(-4, 3, 5, -8);
    assertEquals(72, rec1.intersection(rec2).area());
  }
}