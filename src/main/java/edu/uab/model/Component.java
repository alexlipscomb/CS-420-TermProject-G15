package edu.uab.model;

import java.math.BigDecimal;

/**
 * Abstract base class representing a component on a farm. Components can have
 * a name, price, location, and dimensions.
 */
public abstract class Component {
  protected String name;
  protected BigDecimal price;
  protected Location location;
  protected Dimensions dimensions;

  /**
   * Constructs a new component with the given attributes.
   *
   * @param name       The name of the component.
   * @param price      The price of the component.
   * @param location   The location of the component.
   * @param dimensions The dimensions of the component.
   */
  public Component(String name, BigDecimal price, Location location, Dimensions dimensions) {
    this.name = name;
    this.price = price;
    this.location = location;
    this.dimensions = dimensions;
  }

  /**
   * Gets the name of the component.
   *
   * @return The name of the component.
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of the component.
   *
   * @param name The new name of the component.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the price of the component.
   *
   * @return The price of the component.
   */
  public BigDecimal getPrice() {
    return price;
  }

  /**
   * Sets the price of the component.
   *
   * @param price The new price of the component.
   */
  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  /**
   * Gets the location of the component.
   *
   * @return The location of the component.
   */
  public Location getLocation() {
    return location;
  }

  /**
   * Sets the location of the component.
   *
   * @param location The new location of the component.
   */
  public void setLocation(Location location) {
    this.location = location;
  }

  /**
   * Gets the dimensions of the component.
   *
   * @return The dimensions of the component.
   */
  public Dimensions getDimensions() {
    return dimensions;
  }

  /**
   * Sets the dimensions of the component.
   *
   * @param dimensions The new dimensions of the component.
   */
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
