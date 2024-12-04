package edu.uab.model;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ItemContainerTest {

  @Test
  void testAddAndRemoveComponents() {
    ItemContainer container = new ItemContainer("Container", new BigDecimal(500), new Location(100, 100),
        new Dimensions(20, 20, 10));
    Item item = new Item("Test Item", new BigDecimal(100), new Location(110, 110), new Dimensions(10, 10, 5));

    assertTrue(container.add(item));
    assertEquals(1, container.getComponents().size());
    assertEquals(item, container.getComponents().get(0));

    assertTrue(container.remove(item));
    assertTrue(container.getComponents().isEmpty());
  }
}
