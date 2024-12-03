package edu.uab.controller.ItemContainerCommands;

import edu.uab.model.Dimensions;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.fxml.FXML;;

public class EditDimensionsModalController {
  @FXML
  private TextField lengthField;
  @FXML
  private TextField widthField;
  @FXML
  private TextField heightField;

  private Dimensions dimensions;

  public Dimensions getDimensions() {
    return this.dimensions;
  }

  public void setDimensions(Dimensions dimensions) {
    this.lengthField.setText(String.valueOf(dimensions.getLength()));
    this.widthField.setText(String.valueOf(dimensions.getWidth()));
    this.heightField.setText(String.valueOf(dimensions.getHeight()));
  }

  @FXML
  public void handleOk() {
    try {
      this.dimensions = new Dimensions(
          Double.parseDouble(this.lengthField.getText()),
          Double.parseDouble(this.widthField.getText()),
          Double.parseDouble(this.heightField.getText()));

      ((Stage) this.lengthField.getScene().getWindow()).close();
    } catch (Exception e) {
      this.showAlert("Invalid Input", "Please check all fields and try again.");
    }
  }

  @FXML
  public void handleCancel() {
    ((Stage) this.widthField.getScene().getWindow()).close();
  }

  // TODO: "Alertable" class
  private void showAlert(String title, String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle(title);
    alert.setContentText(message);
    alert.showAndWait();
  }
}
