package edu.uab.model;

public class Dashboard {
  private static Dashboard instance;
  private Component root; // TODO: Change to generic class/interface

  private Dashboard() {
    // root = new ItemContainer(); // TODO:
    // root.name = "Farm";
  }

  public static Dashboard getInstance() {
    if (instance == null) {
      instance = new Dashboard();
    }
    return instance;
  }

  // public ItemContainer getRoot() {
  // return root; // TODO
  // }
}
