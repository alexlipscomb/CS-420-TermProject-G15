package edu.uab.model;

/**
 * Represents the dimensions of a component in 3D space with length, width, and
 * height.
 */
public class Dimensions {
  private double length;
  private double width;
  private double height;

  /**
   * Constructs a new {@code Dimensions} object with the specified length, width,
   * and height.
   *
   * @param length The length of the dimensions (non-negative)
   * @param width  The width of the dimensions (non-negative)
   * @param height The length of the dimensions (non-negative)
   */
  public Dimensions(double length, double width, double height) {
    if (length < 0 || width < 0 || height < 0) {
      throw new IllegalArgumentException("Dimensions must be non-negative");
    }

    this.length = length;
    this.width = width;
    this.height = height;
  }

  /**
   * Gets the length component of this dimension.
   * 
   * @return The length.
   */
  public double getLength() {
    return this.length;
  }

  /**
   * Gets the width component of this dimension.
   * 
   * @return The width.
   */
  public double getWidth() {
    return this.width;
  }

  /**
   * Gets the height component of this dimension.
   *
   * @return The height.
   */
  public double getHeight() {
    return this.height;
  }

  @Override
  public String toString() {
    return "Dimensions("
        + "length=" + this.length
        + ", width=" + this.width
        + ", height=" + this.height
        + ")";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || this.getClass() != o.getClass()) {
      return false;
    }

    Dimensions dimensions = (Dimensions) o;
    return this.length == dimensions.length && this.width == dimensions.width && this.height == dimensions.height;
  }
}
