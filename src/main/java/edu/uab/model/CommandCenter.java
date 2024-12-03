package edu.uab.model;

import java.math.BigDecimal;

public class CommandCenter extends ItemContainer {
  private static CommandCenter instance;

  private Drone drone;

  private CommandCenter() {
    super("Command Center", new BigDecimal(0), new Location(0, 0), new Dimensions(50, 50, 50));
    this.drone = new Drone(this);
  }

  public static CommandCenter getInstance() {
    if (CommandCenter.instance == null) {
      CommandCenter.instance = new CommandCenter();
    }

    return CommandCenter.instance;
  }

  public Drone getDrone() {
    return this.drone;
  }

  @Override
  public boolean add(Component component) {
    if (component instanceof CommandCenter) {
      throw new UnsupportedOperationException("A Command Center already exists.");
    }

    return super.add(component);
  }

  @Override
  public boolean remove(Component component) {
    if (component == this) {
      throw new UnsupportedOperationException("Command Center cannot be removed.");
    }

    return super.remove(component);
  }

  @Override
  public void setLocation(Location location) {
    super.setLocation(location);
    this.drone.setLocation(location);
  }
}
