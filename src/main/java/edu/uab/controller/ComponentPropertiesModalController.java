package edu.uab.controller;

import java.math.BigDecimal;
import edu.uab.model.Dimensions;
import edu.uab.model.Location;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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

  @FXML
  public void handleApply() {
    try {
      this.name = this.nameField.getText();
      this.price = new BigDecimal(this.priceField.getText());
      this.location = new Location(
          Integer.parseInt(this.xField.getText()),
          Integer.parseInt(this.yField.getText()));
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

  public void handleCancel() {
    this.confirmed = false;
    ((Stage) this.nameField.getScene().getWindow()).close();
  }

  private void showAlert(String title, String message) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle(title);
    alert.setContentText(message);
    alert.showAndWait();
  }

  public boolean isConfirmed() {
    return this.confirmed;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
    if (this.nameField != null) {
      this.nameField.setText(name);
    }
  }

  public BigDecimal getPrice() {
    return this.price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
    if (this.price != null) {
      this.priceField.setText(price.toString());
    }
  }

  public Dimensions getDimensions() {
    return this.dimensions;
  }

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
}
