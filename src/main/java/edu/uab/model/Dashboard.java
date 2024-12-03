package edu.uab.model;

import java.util.ArrayList;
import java.util.List;

public class Dashboard {
  private static Dashboard instance;

  private ItemContainer rootContainer;

  private Dashboard() {
    this.rootContainer = new ItemContainer("Farm", null, new Location(0, 0), new Dimensions(800, 600, 0));
  }

  public static Dashboard getInstance() {
    if (Dashboard.instance == null) {
      Dashboard.instance = new Dashboard();
    }

    return Dashboard.instance;
  }

  public ItemContainer getRootContainer() {
    return rootContainer;
  }

  public void addItemToRoot(Component item) {
    rootContainer.add(item);
  }

  public List<Component> getAllComponents() {
    List<Component> components = new ArrayList<>();
    collectComponents(rootContainer, components);
    return components;
  }

  private void collectComponents(ItemContainer container, List<Component> components) {
    for (Component component : container.getComponents()) {
      components.add(component);
      if (component instanceof ItemContainer) {
        collectComponents((ItemContainer) component, components);
      }
    }
  }
}
