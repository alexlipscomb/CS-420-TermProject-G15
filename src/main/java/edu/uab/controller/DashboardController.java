package edu.uab.controller;

import java.io.IOException;
import java.util.List;

import edu.uab.model.CommandCenter;
import edu.uab.model.Component;
import edu.uab.model.Dashboard;
import java.util.HashMap;
import java.util.Map;
import edu.uab.model.Dimensions;
import edu.uab.model.Drone;
import edu.uab.model.ItemContainer;
import edu.uab.model.Location;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class DashboardController {
  @FXML
  private TreeView<Component> treeView;
  @FXML
  Pane plotPane;
  private Dashboard dashboard;
  private Map<Component, Rectangle> componentRectangleMap = new HashMap<>();
  private Rectangle highlightedRectangle = null;
  private CommandCenter commandCenter;

  @FXML
  public void initialize() {
    this.dashboard = Dashboard.getInstance();
    this.commandCenter = CommandCenter.getInstance();

    ItemContainer rootContainer = this.dashboard.getRootContainer();
    if (!rootContainer.getComponents().contains(commandCenter)) {
      rootContainer.add(commandCenter);
    }

    TreeItem<Component> rootNode = new TreeItem<>(rootContainer);
    this.treeView.setRoot(rootNode);
    this.treeView.setShowRoot(true);

    this.populateTree(rootNode, rootContainer);

    this.drawPlot();

    Drone drone = commandCenter.getDrone();
    if (!this.plotPane.getChildren().contains(drone.getDroneView())) {
      this.plotPane.getChildren().add(drone.getDroneView());
    }
    drone.updateViewLocation();

    // Highlight selected rectangle
    treeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
      Component selectedComponent = newValue != null ? newValue.getValue() : null;
      if (this.highlightedRectangle != null) {
        this.highlightedRectangle.setStroke(Color.BLACK);
      }

      if (selectedComponent != null) {
        Rectangle rectangle = this.componentRectangleMap.get(selectedComponent);
        if (rectangle != null) {
          rectangle.setStroke(Color.RED);
          this.highlightedRectangle = rectangle;
        }
      }
    });
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

  private void drawPlot() {
    this.plotPane.getChildren().removeIf(node -> !(node instanceof ImageView));
    List<Component> components = this.dashboard.getAllComponents();

    for (Component component : components) {
      drawComponent(component);
    }

    if (!this.plotPane.getChildren().contains(commandCenter.getDrone().getDroneView())) {
      this.plotPane.getChildren().add(commandCenter.getDrone().getDroneView());
    }
  }

  private void drawComponent(Component component) {
    if (component == null || component.getLocation() == null || component.getDimensions() == null) {
      return;
    }

    Location location = component.getLocation();
    Dimensions dimensions = component.getDimensions();

    // TODO: Make constants
    double farmLength = 800.0;
    double farmWidth = 600.0;

    double xScale = this.plotPane.getPrefWidth() / farmLength;
    double yScale = this.plotPane.getPrefHeight() / farmWidth;

    double x = location.getX() * xScale;
    double y = location.getY() * yScale;
    double width = dimensions.getLength() * xScale;
    double height = dimensions.getWidth() * yScale;

    Rectangle rectangle = new Rectangle(x, y, width, height);
    rectangle.setStroke(Color.BLACK);
    rectangle.setFill(Color.TRANSPARENT);
    rectangle.setStrokeWidth(1);

    Tooltip tooltip = new Tooltip(component.getName());
    Tooltip.install(rectangle, tooltip);

    this.componentRectangleMap.put(component, rectangle);
    this.plotPane.getChildren().add(rectangle);

    Text nameText = new Text(component.getName());
    nameText.setX(x - 10);
    nameText.setY(y - 10);
    nameText.setFill(Color.BLACK);

    this.plotPane.getChildren().add(nameText);

    if (component instanceof ItemContainer) {
      ItemContainer container = (ItemContainer) component;
      for (Component child : container.getComponents()) {
        this.drawComponent(child);
      }
    }
  }

  @FXML
  public void handleAddComponent() {
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
          this.getClass().getResource("/edu/uab/view/AddItemModal.fxml"));
      Parent modalRoot = loader.load();

      Stage modalStage = new Stage();
      modalStage.initModality(Modality.APPLICATION_MODAL);
      modalStage.setTitle("Add Item");
      modalStage.setScene(new Scene(modalRoot));
      modalStage.showAndWait();

      AddItemModalController modalController = loader.getController();
      Component newComponent = modalController.getCreatedComponent();

      if (newComponent == null) {
        return;
      }

      if (selectedNode == null) {
        this.dashboard.getRootContainer().add(newComponent);
        this.treeView.getRoot().getChildren().add(new TreeItem<>(newComponent));
      } else {
        ItemContainer selectedContainer = (ItemContainer) selectedNode.getValue();
        selectedContainer.add(newComponent);
        selectedNode.getChildren().add(new TreeItem<>(newComponent));
      }
      this.drawPlot();
    } catch (IOException e) {
      e.printStackTrace();
      this.showAlert("Error", "An unexpected error occurred", AlertType.ERROR);
    }
  }

  @FXML
  public void handleDeleteComponent() {
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

    this.drawPlot();

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

      this.drawPlot();
    } catch (Exception e) {
      e.printStackTrace();
      this.showAlert("Error", "An unexpected error occurred", AlertType.ERROR);
    }
  }

  @FXML
  public void handleFlyToSelected() {
    TreeItem<Component> selectedNode = this.treeView.getSelectionModel().getSelectedItem();

    if (selectedNode == null) {
      this.showAlert("No Selection", "Please select an item or container to fly the drone to.", AlertType.WARNING);
      return;
    }

    Component selectedComponent = selectedNode.getValue();

    if (selectedComponent.getLocation() == null) {
      this.showAlert("Invalid Target", "The selected component does not have a vlaid location", AlertType.ERROR);
      ;
      return;
    }

    this.commandCenter.getDrone().flyTo(selectedComponent.getLocation(), this.plotPane, this.commandCenter);
  }

  @FXML
  public void handleFarmScan() {
    Drone drone = this.commandCenter.getDrone();
    List<Component> components = this.dashboard.getAllComponents();

    components.removeIf(component -> component.getLocation() == null);

    if (components.isEmpty()) {
      this.showAlert("No Components", "No valid components to scane", AlertType.INFORMATION);
      return;
    }

    components.sort((c1, c2) -> {
      double dist1 = drone.getLocation().distanceTo(c1.getLocation());
      double dist2 = drone.getLocation().distanceTo(c2.getLocation());
      return Double.compare(dist1, dist2);
    });

    this.flyToComponentsInOrder(drone, components, 0);
  }

  private void flyToComponentsInOrder(Drone drone, List<Component> components, int index) {
    if (index >= components.size()) {
      drone.flyTo(this.commandCenter.getLocation(), this.plotPane,
          this.commandCenter);
      return;
    }

    Component component = components.get(index);

    if (component.getLocation() != null) {
      drone.flyTo(component.getLocation(), this.plotPane, this.commandCenter);
    }

    new AnimationTimer() {
      @Override
      public void handle(long now) {
        if (!drone.isFlying()) {
          stop();
          flyToComponentsInOrder(drone, components, index + 1);
        }
      }
    }.start();
  }

  private void showAlert(String title, String message, AlertType alertType) {
    Alert alert = new Alert(alertType);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
  }
}
