package edu.uab.model;

import java.math.BigDecimal;

// An implementation of an Item
public abstract class ItemImpl implements Item {
  private String name;
  private BigDecimal price;
  private Location location;
  private Dimensions dimensions;

  public ItemImpl(String name, BigDecimal price, Location location, Dimensions dimensions) {
    this.name = name;
    this.price = price;
    this.location = location;
    this.dimensions = dimensions;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  @Override
  public void setLocation(Location location) {
    this.location = location;
  }

  @Override
  public void setDimensions(Dimensions dimensions) {
    this.dimensions = dimensions;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public BigDecimal getPrice() {
    return this.price;
  }

  @Override
  public Location getLocation() {
    return location;
  }

  @Override
  public Dimensions getDimensions() {
    return dimensions;
  }

}
