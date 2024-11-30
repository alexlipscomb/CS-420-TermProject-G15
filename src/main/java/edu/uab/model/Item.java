package edu.uab.model;

import java.math.BigDecimal;

public interface Item {
  public void setName(String name);

  public void setPrice(BigDecimal price);

  public void setLocation(Location location);

  public void setDimensions(Dimensions dimensions);

  public void delete();

  public String getName();

  public BigDecimal getPrice();

  public Location getLocation();

  public Dimensions getDimensions();

}
