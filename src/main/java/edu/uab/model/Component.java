package edu.uab.model;

import java.math.BigDecimal;

public abstract class Component {
  protected String name;
  protected BigDecimal price;
  protected Location location;
  protected Dimensions dimensions;

  public Component(String name, BigDecimal price, Location location, Dimensions dimensions) {
    this.name = name;
    this.price = price;
    this.location = location;
    this.dimensions = dimensions;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  public Dimensions getDimensions() {
    return dimensions;
  }

  public void setDimensions(Dimensions dimensions) {
    this.dimensions = dimensions;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || this.getClass() != o.getClass()) {
      return false;
    }

    Component other = (Component) o;
    return name.equals(other.name)
        && price.equals(other.price)
        && location.equals(other.location)
        && dimensions.equals(other.dimensions);
  }

  @Override
  public String toString() {
    return this.name;
  }
}
