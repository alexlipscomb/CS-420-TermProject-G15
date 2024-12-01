package edu.uab.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ItemContainer extends Component {
  private List<Component> components;

  public ItemContainer(String name, BigDecimal price, Location location, Dimensions dimensions) {
    super(name, price, location, dimensions);
    this.components = new ArrayList<>();
  }

  public boolean add(Component component) {
    if (!this.components.contains(component)) {
      return this.components.add(component);
    }

    return false;
  }

  public boolean remove(Component component) {
    return this.components.remove(component);
  }

  public List<Component> getComponents() {
    return this.components;
  }
}
