package edu.uab.model;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

  @Test
  void testItemCreation() {
    Location location = new Location(100, 200);
    Dimensions dimensions = new Dimensions(10, 20, 5);
    Item item = new Item("Test Item", new BigDecimal(100), location, dimensions);

    assertEquals("Test Item", item.getName());
    assertEquals(new BigDecimal(100), item.getPrice());
    assertEquals(location, item.getLocation());
    assertEquals(dimensions, item.getDimensions());
  }
}
