package edu.uab.model;

import java.math.BigDecimal;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;

/**
 * Represents a drone within the system which can fly between locations and be
 * visually represented on a graphical interface.
 * This drone is a singleton and extends {@link Item}.
 */
public class Drone extends Item {
  private static Drone instance;
  private final ImageView droneView;
  private Location targetLocation;
  private boolean isFlying;
  private double speed = 150; // px per second

  /**
   * Constructs a new {@code Drone} associated with a given {@link CommandCenter}.
   * Initializes the drone's image and starting location.
   *
   * @param commandCenter The {@link CommandCenter} that the drone is controlled
   *                      from.
   */
  public Drone(CommandCenter commandCenter) {
    super("Drone", new BigDecimal(0), commandCenter.getLocation(), new Dimensions(10, 10, 0)); // Drone is 10x10 in size

    this.droneView = new ImageView(new Image(this.getClass().getResourceAsStream("/images/drone.png")));
    this.droneView.setFitWidth(50);
    this.droneView.setFitHeight(50);
    this.targetLocation = commandCenter.getLocation();
    this.isFlying = false;

    this.updateViewLocation();
  }

  /**
   * Retrieves the singleton instance of the {@code Drone}.
   * If the instance does not exist, it will be created.
   *
   * @param commandCenter the {@link CommandCenter} used to initialize the drone
   *                      if it doesn't exist
   * @return the singleton instance of the {@code Drone}
   */
  public static Drone getInstance(CommandCenter commandCenter) {
    if (Drone.instance == null) {
      Drone.instance = new Drone(commandCenter);
    }

    return Drone.instance;
  }

  /**
   * Gets the visual representation of the drone as an {@link ImageView}.
   *
   * @return the {@code ImageView} representing the drone
   */
  public ImageView getDroneView() {
    return this.droneView;
  }

  /**
   * Flies the drone to a specified location. Movements are animated, and the
   * drone's position is updated over time with a constant speed. If the drone is
   * already flying, this command is ignored.
   * 
   * @param target        The target {@link Location} to which the drone should
   *                      fly.
   * @param plotPane      The {@link Pane} where the drone is visually
   *                      represented.
   * @param commandCenter The {@link CommandCenter}.
   */
  public void flyTo(Location target, Pane plotPane, CommandCenter commandCenter) {
    if (this.isFlying) {
      return;
    }

    this.isFlying = true;
    this.targetLocation = target;

    new AnimationTimer() {
      private long lastUpdate = 0;

      @Override
      public void handle(long now) {
        if (lastUpdate == 0) {
          lastUpdate = now;
          return;
        }

        double elapsedSeconds = (now - lastUpdate) / 1_000_000_000.0;
        lastUpdate = now;

        double distance = location.distanceTo(targetLocation);

        // Handle deleted Items and Item Containers
        if (plotPane.getChildren().contains(droneView) && distance == 0) {
          targetLocation = commandCenter.getLocation();
        }

        // Move
        if (distance > 1) {
          double step = speed * elapsedSeconds;
          double dx = targetLocation.getX() - location.getX();
          double dy = targetLocation.getY() - location.getY();

          double stepX = step * dx / distance;
          double stepY = step * dy / distance;

          Location newLocation = new Location(
              location.getX() + stepX,
              location.getY() + stepY);

          setLocation(newLocation);
          updateViewLocation();
        } else {
          setLocation(targetLocation);
          updateViewLocation();
          isFlying = false;
          stop();
        }
      }

    }.start();
  }

  /**
   * Updates the drone's visual representation on the {@link Pane} to match its
   * current location.
   */
  public void updateViewLocation() {
    this.droneView.setLayoutX(this.getLocation().getX());
    this.droneView.setLayoutY(this.getLocation().getY());
  }

  /**
   * Checks if the drone is currently in flight.
   *
   * @return {@code true} if the drone is flying, {@code false} otherwise.
   */
  public boolean isFlying() {
    return this.isFlying;
  }
}
