package edu.uab.model;

import java.math.BigDecimal;

public interface Item {
  public String getName();

  public void setName(String name);

  public BigDecimal getPrice();

  public void setPrice(BigDecimal price);

  public Location getLocation();

  public void setLocation(Location location);

  public Dimensions getDimensions();

  public void setDimensions(Dimensions dimensions);

  // NOTE: Horrible design. Why should an Item be able to delete itself? This
  // should only be the responsibility of the container. This violates the
  // Interface Segregation Principle, and is bad:
  // https://en.wikipedia.org/wiki/Interface_segregation_principle
  // public void delete();
}
