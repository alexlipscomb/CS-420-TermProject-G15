package edu.uab.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton class representing the dashboard of a farm management system. This
 * class provides a root container for all other components to be added to, such
 * as anything subclassing {@link Component},
 */
public class Dashboard {
  private static Dashboard instance;

  private ItemContainer rootContainer;

  private Dashboard() {
    this.rootContainer = new ItemContainer("Farm", null, new Location(0, 0), new Dimensions(800, 600, 0));
  }

  /**
   * Returns the singleton instance of the Dashboard.
   *
   * @return The singleton instance of the Dashboard.
   */
  public static Dashboard getInstance() {
    if (Dashboard.instance == null) {
      Dashboard.instance = new Dashboard();
    }

    return Dashboard.instance;
  }

  /**
   * Returns the root container of the dashboard.
   *
   * @return The root container of the dashboard.
   */
  public ItemContainer getRootContainer() {
    return rootContainer;
  }

  /**
   * Adds a component to the root container.
   *
   * @param item The component to be added to the root container.
   */
  public void addItemToRoot(Component item) {
    rootContainer.add(item);
  }

  /**
   * Returns a flat list of all components in the dashboard, including nested
   * ones.
   *
   * @return A list of all components in the dashboard.
   */
  public List<Component> getAllComponents() {
    List<Component> components = new ArrayList<>();
    collectComponents(rootContainer, components);
    return components;
  }

  /**
   * Recursively collects components from a given container and its children.
   *
   * @param container  The container to collect components from.
   * @param components The list to add collected components to.
   */
  private void collectComponents(ItemContainer container, List<Component> components) {
    for (Component component : container.getComponents()) {
      components.add(component);
      if (component instanceof ItemContainer) {
        collectComponents((ItemContainer) component, components);
      }
    }
  }
}
