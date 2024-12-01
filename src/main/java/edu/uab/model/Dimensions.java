package edu.uab.model;

public class Dimensions {
  private double length;
  private double width;
  private double height;

  public Dimensions(double length, double width, double height) {
    if (length < 0 || width < 0 || height < 0) {
      throw new IllegalArgumentException("Dimensions must be non-negative");
    }

    this.length = length;
    this.width = width;
    this.height = height;
  }

  public double getLength() {
    return this.length;
  }

  public double getWidth() {
    return this.width;
  }

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
}
