package edu.uab.model;

public class Dimensions {
  private double length;
  private double width;
  private double height;

  public Dimensions(double length, double width, double height) {
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
}
