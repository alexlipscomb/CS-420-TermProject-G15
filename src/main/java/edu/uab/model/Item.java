package edu.uab.model;

import java.math.BigDecimal;

/**
 * Represents an individual item with a name, price, location, and dimensions.
 * Extends {@link Component}.
 */
public class Item extends Component {
  /**
   * @param name       The name of the item
   * @param price      The price of the item
   * @param location   The location of the item
   * @param dimensions The dimensions of the item
   */
  public Item(String name, BigDecimal price, Location location, Dimensions dimensions) {
    super(name, price, location, dimensions);
  }
}
