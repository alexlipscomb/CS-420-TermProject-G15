package edu.uab.model;

public class Location {
  private final double x;
  private final double y;

  public Location(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double getX() {
    return this.x;
  }

  public double getY() {
    return this.y;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || this.getClass() != o.getClass()) {
      return false;
    }

    Location location = (Location) o;
    return this.x == location.x && this.y == location.y;
  }

  @Override
  public String toString() {
    return "Location(" + "x=" + this.x + ", y=" + this.y + ")";
  }

  public double distanceTo(Location other) {
    if (other == null) {
      throw new IllegalArgumentException("Other location cannot be null");
    }

    double dx = this.x - other.x;
    double dy = this.y - other.y;

    return Math.sqrt((dx * dx) + (dy * dy));
  }
}
