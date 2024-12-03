package edu.uab.controller;

import edu.uab.model.Location;
import edu.uab.model.Component;
import edu.uab.model.Dimensions;
import edu.uab.model.Item;
import edu.uab.model.ItemContainer;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.math.BigDecimal;

public class AddItemModalController {

  @FXML
  private TextField nameField;
  @FXML
  private TextField priceField;
  @FXML
  private TextField xField;
  @FXML
  private TextField yField;
  @FXML
  private TextField lengthField;
  @FXML
  private TextField widthField;
  @FXML
  private TextField heightField;
  @FXML
  private CheckBox isContainerCheckBox;

  private Component createdComponent;

  @FXML
  public void handleAdd() {
    try {
      String name = nameField.getText();
      BigDecimal price = new BigDecimal(priceField.getText());
      Location location = new Location(
          Double.parseDouble(xField.getText()),
          Double.parseDouble(yField.getText()));
      Dimensions dimensions = new Dimensions(
          Double.parseDouble(lengthField.getText()),
          Double.parseDouble(widthField.getText()),
          Double.parseDouble(heightField.getText()));

      if (this.isContainerCheckBox.isSelected()) {
        this.createdComponent = new ItemContainer(name, price, location, dimensions);
      } else {
        this.createdComponent = new Item(name, price, location, dimensions);
      }

      ((Stage) this.nameField.getScene().getWindow()).close();
    } catch (Exception e) {
      this.showAlert("Invalid Input", "Please check all fields and try again.");
    }
  }

  @FXML
  public void handleCancel() {
    ((Stage) this.nameField.getScene().getWindow()).close();
  }

  public Component getCreatedComponent() {
    return this.createdComponent;
  }

  private void showAlert(String title, String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle(title);
    alert.setContentText(message);
    alert.showAndWait();
  }
}
