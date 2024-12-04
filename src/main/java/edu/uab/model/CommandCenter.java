package edu.uab.model;

import java.math.BigDecimal;

/**
 * Singleton class representing the command center in the farm. The command
 * center
 * houses a drone and cannot be removed or duplicated.
 */
public class CommandCenter extends ItemContainer {
  private static CommandCenter instance;

  private Drone drone;

  private CommandCenter() {
    super("Command Center", new BigDecimal(0), new Location(0, 0), new Dimensions(50, 50, 50));
    this.drone = new Drone(this);
  }

  /**
   * Returns the singleton instance of the Command Center.
   *
   * @return The singleton instance of the Command Center.
   */
  public static CommandCenter getInstance() {
    if (CommandCenter.instance == null) {
      CommandCenter.instance = new CommandCenter();
    }

    return CommandCenter.instance;
  }

  /**
   * Returns the drone associated with the command center.
   *
   * @return The drone associated with the command center.
   */
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
