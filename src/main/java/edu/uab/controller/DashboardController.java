package edu.uab.controller;

import java.io.IOException;

import edu.uab.controller.ItemContainerCommands.AddItemModalController;
import edu.uab.model.Component;
import edu.uab.model.Dashboard;
import edu.uab.model.Item;
import edu.uab.model.ItemContainer;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class DashboardController {
  @FXML
  private TreeView<Component> treeView;
  private Dashboard dashboard;

  @FXML
  public void initialize() {
    this.dashboard = Dashboard.getInstance();
    ItemContainer rootContainer = dashboard.getRootContainer();

    TreeItem<Component> rootNode = new TreeItem<>(rootContainer);
    treeView.setRoot(rootNode);
    treeView.setShowRoot(true);

    this.populateTree(rootNode, rootContainer);
  }

  private void populateTree(TreeItem<Component> treeItem, ItemContainer container) {
    for (Component component : container.getComponents()) {
      TreeItem<Component> childNode = new TreeItem<>(component);
      treeItem.getChildren().add(childNode);

      if (component instanceof ItemContainer) {
        this.populateTree(childNode, (ItemContainer) component);
      }
    }
  }

  @FXML
  public void handleAddItem() {
    TreeItem<Component> selectedNode = this.treeView.getSelectionModel().getSelectedItem();

    if (selectedNode != null) {
      Component selectedComponent = selectedNode.getValue();

      if (!(selectedComponent instanceof ItemContainer)) {
        this.showAlert("Invalid Selection", "Cannot add an item to a non-container", AlertType.ERROR);
        return;
      }
    }

    try {
      FXMLLoader loader = new FXMLLoader(
          this.getClass().getResource("/edu/uab/view/ItemContainerCommands/AddItemModal.fxml"));
      Parent modalRoot = loader.load();

      Stage modalStage = new Stage();
      modalStage.initModality(Modality.APPLICATION_MODAL);
      modalStage.setTitle("Add Item");
      modalStage.setScene(new Scene(modalRoot));
      modalStage.showAndWait();

      AddItemModalController modalController = loader.getController();
      Item newItem = modalController.getCreatedItem();

      if (newItem == null) {
        return;
      }

      if (selectedNode == null) {
        this.dashboard.getRootContainer().add(newItem);
        this.treeView.getRoot().getChildren().add(new TreeItem<>(newItem));
      } else {
        ItemContainer selectedContainer = (ItemContainer) selectedNode.getValue();
        selectedContainer.add(newItem);
        selectedNode.getChildren().add(new TreeItem<>(newItem));
      }
    } catch (IOException e) {
      e.printStackTrace();
      this.showAlert("Error", "An unexpected error occurred", AlertType.ERROR);
    }
  }

  @FXML
  public void handleDeleteItem() {
    TreeItem<Component> selectedNode = this.treeView.getSelectionModel().getSelectedItem();

    if (selectedNode == null) {
      this.showAlert("Nothing to delete", "There's nothing selected to delete.", AlertType.INFORMATION);
      return;
    }

    TreeItem<Component> parentNode = selectedNode.getParent();

    if (parentNode == null) {
      this.showAlert("Cannot delete root", "Cannot delete the root node.", AlertType.WARNING);
      return;
    }

    Component selectedComponent = selectedNode.getValue();
    ItemContainer parentContainer = (ItemContainer) parentNode.getValue();

    parentContainer.remove(selectedComponent);

    parentNode.getChildren().remove(selectedNode);

    this.showAlert("Success", selectedComponent.getName() + " has been deleted.", AlertType.INFORMATION);
  }

  public void handleEditComponent() {
    TreeItem<Component> selectedNode = this.treeView.getSelectionModel().getSelectedItem();

    if (selectedNode == null) {
      this.showAlert("No Selection", "No item is selected to edit!", AlertType.WARNING);
      return;
    }

    Component component = selectedNode.getValue();

    try {
      FXMLLoader loader = new FXMLLoader(
          this.getClass().getResource("/edu/uab/view/ComponentPropertiesModal.fxml"));
      Parent modalRoot = loader.load();

      ComponentPropertiesModalController modalController = loader.getController();

      modalController.setName(component.getName());
      modalController.setPrice(component.getPrice());
      modalController.setDimensions(component.getDimensions());
      modalController.setLocation(component.getLocation());

      Stage modalStage = new Stage();
      modalStage.initModality(Modality.APPLICATION_MODAL);
      modalStage.setTitle("Edit Component Properties");
      modalStage.setScene(new Scene(modalRoot));

      modalStage.showAndWait();

      if (modalController.isConfirmed()) {
        component.setName(modalController.getName());
        component.setPrice(modalController.getPrice());
        component.setDimensions(modalController.getDimensions());
        component.setLocation(modalController.getLocation());

        this.treeView.refresh();
      }
    } catch (Exception e) {
      e.printStackTrace();
      this.showAlert("Error", "An unexpected error occurred", AlertType.ERROR);
    }
  }

  private void showAlert(String title, String message, AlertType alertType) {
    Alert alert = new Alert(alertType);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
  }
}
