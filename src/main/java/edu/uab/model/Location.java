package edu.uab.model;

/**
 * Represents a 2D location with x and y coordinates.
 */
public class Location {
  private final double x;
  private final double y;

  /**
   * Constructs a new {@code Location} with the specified coordinates.
   * 
   * @param x The x-coordinate.
   * @param y The y-coordinate.
   */
  public Location(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Gets the x-coordinate of this location.
   * 
   * @return The x-coordinate.
   */
  public double getX() {
    return this.x;
  }

  /**
   * Gets the y-coordinate of this location.
   * 
   * @return The y-coordinate.
   */
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

  /**
   * Calculates the distance between this location and another location.
   *
   * @param other The other location.
   * @return The distance between the two locations.
   * @throws IllegalArgumentException if {@code other} is {@code null}.
   */
  public double distanceTo(Location other) {
    if (other == null) {
      throw new IllegalArgumentException("Other location cannot be null");
    }

    double dx = this.x - other.x;
    double dy = this.y - other.y;

    return Math.sqrt((dx * dx) + (dy * dy));
  }
}
