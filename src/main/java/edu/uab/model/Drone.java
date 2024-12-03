package edu.uab.model;

import java.math.BigDecimal;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;

public class Drone extends Item {
  private static Drone instance;
  private final ImageView droneView;
  private Location targetLocation;
  private boolean isFlying;
  private double speed = 150; // px per second

  public Drone(CommandCenter commandCenter) {
    super("Drone", new BigDecimal(0), commandCenter.getLocation(), new Dimensions(10, 10, 0)); // Drone is 10x10 in size

    this.droneView = new ImageView(new Image(this.getClass().getResourceAsStream("/images/drone.png")));
    this.droneView.setFitWidth(50);
    this.droneView.setFitHeight(50);
    this.targetLocation = commandCenter.getLocation();
    this.isFlying = false;

    this.updateViewLocation();
  }

  public static Drone getInstance(CommandCenter commandCenter) {
    if (Drone.instance == null) {
      Drone.instance = new Drone(commandCenter);
    }

    return Drone.instance;
  }

  public ImageView getDroneView() {
    return this.droneView;
  }

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

  public void updateViewLocation() {
    this.droneView.setLayoutX(this.getLocation().getX());
    this.droneView.setLayoutY(this.getLocation().getY());
  }

  public boolean isFlying() {
    return this.isFlying;
  }
}
