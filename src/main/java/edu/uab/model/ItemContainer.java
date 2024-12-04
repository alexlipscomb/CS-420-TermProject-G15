package edu.uab.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a container that holds multiple components, such as {@link Item}s
 * or other containers and their subclasses.
 * Extends {@link Component}
 */
public class ItemContainer extends Component {
  private List<Component> components;

  /**
   * @param name       The name of the container
   * @param price      The price of the container
   * @param location   The location of the container
   * @param dimensions The dimensions of the container
   */
  public ItemContainer(String name, BigDecimal price, Location location, Dimensions dimensions) {
    super(name, price, location, dimensions);
    this.components = new ArrayList<>();
  }

  /**
   * Adds a component to this container.
   * 
   * @param component The component to be added.
   * @return {@code true} if the component was added successfully, {@code false}
   *         otherwise.
   */
  public boolean add(Component component) {
    if (!this.components.contains(component)) {
      return this.components.add(component);
    }

    return false;
  }

  /**
   * Removes a component from this container.
   *
   * @param component The component to remove.
   * @return {@code true} if the component was successfully removed, {@code false}
   *         otherwise.
   */
  public boolean remove(Component component) {
    return this.components.remove(component);
  }

  /**
   * Retrieves all components in this container.
   *
   * @return A list of components in this container.
   */
  public List<Component> getComponents() {
    return this.components;
  }
}
