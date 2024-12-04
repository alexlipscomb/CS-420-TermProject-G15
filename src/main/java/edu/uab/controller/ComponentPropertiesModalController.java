package edu.uab.controller;

import java.math.BigDecimal;
import edu.uab.model.Dimensions;
import edu.uab.model.Location;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller for the "Edit Component Properties" modal dialog, allowing the
 * user
 * to modify component attributes like name, price, dimensions, and location.
 */
public class ComponentPropertiesModalController {
  @FXML
  private TextField nameField;
  @FXML
  private TextField priceField;

  @FXML
  private TextField lengthField;
  @FXML
  private TextField widthField;
  @FXML
  private TextField heightField;

  @FXML
  private TextField xField;
  @FXML
  private TextField yField;

  private String name;
  private BigDecimal price;
  private Dimensions dimensions;
  private Location location;

  private boolean confirmed = false;

  /**
   * Handles applying the changes made by the user, validating input and
   * updating the component attributes.
   */
  @FXML
  public void handleApply() {
    try {
      this.name = this.nameField.getText();
      this.price = new BigDecimal(this.priceField.getText());
      this.location = new Location(
          Double.parseDouble(this.xField.getText()),
          Double.parseDouble(this.yField.getText()));
      this.dimensions = new Dimensions(
          Double.parseDouble(this.lengthField.getText()),
          Double.parseDouble(this.widthField.getText()),
          Double.parseDouble(this.heightField.getText()));

      this.confirmed = true;
      ((Stage) this.nameField.getScene().getWindow()).close();
    } catch (Exception a) {
      this.showAlert("Invalid Input", "Please check all fields and try again.");
    }
  }

  /**
   * Cancels the editing operation without saving changes.
   */
  public void handleCancel() {
    this.confirmed = false;
    ((Stage) this.nameField.getScene().getWindow()).close();
  }

  /**
   * Returns whether the changes were confirmed by the user.
   *
   * @return {@code true} if the user confirmed changes, {@false} otherwise.
   */
  public boolean isConfirmed() {
    return this.confirmed;
  }

  /**
   * Gets the name of the component.
   * 
   * @return The name of the component.
   */
  public String getName() {
    return this.name;
  }

  /**
   * Sets the name of the component.
   * 
   * @param name The name of the component
   */
  public void setName(String name) {
    this.name = name;
    if (this.nameField != null) {
      this.nameField.setText(name);
    }
  }

  /**
   * Gets the price of the component.
   *
   * @return The price of the component
   */
  public BigDecimal getPrice() {
    return this.price;
  }

  /**
   * Sets the price of the component.
   * 
   * @param price The price of the component.
   */
  public void setPrice(BigDecimal price) {
    this.price = price;
    if (this.price != null) {
      this.priceField.setText(price.toString());
    }
  }

  /**
   * Gets the dimensions of the component.
   *
   * @return The dimensions of the component.
   */
  public Dimensions getDimensions() {
    return this.dimensions;
  }

  /**
   * Sets the dimensions of the component.
   * 
   * @param dimensions The dimensions of the component.
   */
  public void setDimensions(Dimensions dimensions) {
    this.dimensions = dimensions;

    if (this.lengthField != null) {
      this.lengthField.setText(String.valueOf(dimensions.getLength()));
    }

    if (this.widthField != null) {
      this.widthField.setText(String.valueOf(dimensions.getWidth()));
    }

    if (this.heightField != null) {
      this.heightField.setText(String.valueOf(dimensions.getHeight()));
    }
  }

  /**
   * Gets the location of the component.
   *
   * @return The location of the component.
   */
  public Location getLocation() {
    return this.location;
  }

  public void setLocation(Location location) {
    this.location = location;

    if (this.xField != null) {
      this.xField.setText(String.valueOf(location.getX()));
    }

    if (this.yField != null) {
      this.yField.setText(String.valueOf(location.getY()));
    }
  }

  /**
   * Displays an error alert with the given title and message.
   *
   * @param title   The title of the alert.
   * @param message The message to display.
   */
  private void showAlert(String title, String message) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle(title);
    alert.setContentText(message);
    alert.showAndWait();
  }
}
