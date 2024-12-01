package edu.uab.model;

import java.math.BigDecimal;

public class Item extends Component {
  public Item(String name, BigDecimal price, Location location, Dimensions dimensions) {
    super(name, price, location, dimensions);
  }
}
