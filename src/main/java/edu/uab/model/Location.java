package edu.uab.model;

public class Location {
  private final int x;
  private final int y;

  public Location(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Location location = (Location) o;
    return this.x == location.x && this.y == location.y;

  }

  @Override
  public String toString() {
    return "Location(" + "x=" + this.x + ", y=" + this.y + ")";
  }
}
