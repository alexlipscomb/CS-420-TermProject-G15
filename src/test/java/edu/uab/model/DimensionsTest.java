package edu.uab.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DimensionsTest {
  @Test
  void testValidDimensions() {
    double length = 10.0;
    double width = 5.0;
    double height = 2.0;

    Dimensions dimensions = new Dimensions(length, width, height);

    assertEquals(length, dimensions.getLength());
    assertEquals(width, dimensions.getWidth());
    assertEquals(height, dimensions.getHeight());
  }

  @Test
  void testNegativeDimensionsException() {
    assertThrows(IllegalArgumentException.class, () -> new Dimensions(-1, -10, -100));
  }

  @Test
  void testDimensionsEquality() {
    Dimensions dimensions1 = new Dimensions(10, 20, 5);
    Dimensions dimensions2 = new Dimensions(10, 20, 5);

    assertEquals(dimensions1, dimensions2);
  }

  @Test
  void testInvalidDimensions() {
    assertThrows(IllegalArgumentException.class, () -> new Dimensions(-10, 20, 5));
  }
}
