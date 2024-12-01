package edu.uab.model;

import java.util.ArrayList;

public class ItemContainer {
  private ArrayList<Item> items;
  private ArrayList<ItemContainer> itemContainers;

  public ItemContainer() {
  }

  public boolean addItem(Item item) {
    return this.items.add(item);
  }

  public boolean addItemContainer(ItemContainer itemContainer) {
    return this.itemContainers.add(itemContainer);
  }

}
