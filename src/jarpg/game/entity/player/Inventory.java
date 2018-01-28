package jarpg.game.entity.player;

import jarpg.game.exceptions.DuplicateException;
import jarpg.game.exceptions.ExhaustedCapacity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import jarpg.game.entity.interfaces.OwnsItems;
import jarpg.game.exceptions.NotFoundException;
import jarpg.game.gameobject.item.Item;

public class Inventory implements OwnsItems {

  public static final int DEFAULT_MAX_ITEMS = 40;

  protected ArrayList<Item> items;
  protected int maxItems = DEFAULT_MAX_ITEMS;

  public Inventory() {
    this.items = new ArrayList<Item>();
  }

  public Inventory(int maxItems) throws InstantiationException {
    if (maxItems <= 0) {
      throw new InstantiationException("Maximum items must be greater than or equal to one");
    }

    this.maxItems = maxItems;
    this.items = new ArrayList<Item>(maxItems);
  }

  public Inventory(Item[] items) {
    this.items = new ArrayList<Item>(Arrays.asList(items));
  }

  public Inventory(List<Item> items) {
    this.items = new ArrayList<Item>(items);
  }

  public Inventory(int maxItems, Item[] items) {
    this.maxItems = maxItems;
    this.items = new ArrayList<Item>(Arrays.asList(items));
  }

  public Inventory(int maxItems, List<Item> items) {
    this.maxItems = maxItems;
    this.items = new ArrayList<Item>(items);
  }

  public boolean containsItem(Item item) {
    return items.contains(item);
  }

  public void setMaxItems(int maxItems) {
    this.maxItems = maxItems;
  }

  public int getMaxItems() {
    return this.maxItems;
  }

  public List<Item> getItems() {
    return items;
  }

  @Override
  public boolean hasItems() {
    return items.size() > 0;
  }

  @Override
  public int countItems() {
    return items.size();
  }

  @Override
  public void addItem(Item item) throws DuplicateException, ExhaustedCapacity {
    if (!containsItem(item)) {
      if (items.size() + 1 <= maxItems) {
        items.add(item);
      } else {
        throw new ExhaustedCapacity("Inventory is full");
      }
    } else {
      throw new DuplicateException("Item already in inventory");
    }
  }

  @Override
  public void addItems(List<Item> items) throws DuplicateException, ExhaustedCapacity {
    for (Item item : items) {
      addItem(item);
    }
  }

  @Override
  public void removeItem(Item item) throws NotFoundException {
    if (containsItem(item)) {
      items.remove(item);
    } else {
      throw new NotFoundException("Item not present in inventory");
    }
  }

  @Override
  public void removeItems(List<Item> items) throws NotFoundException {
    for (Item item : items) {
      removeItem(item);
    }
  }

  @Override
  public void removeItemID(UUID id) throws NotFoundException {
    for (int i = 0, len = items.size(); i < len; i++) {
      if (items.get(i).getUUID() == id) {
        items.remove(i);
        break;
      }
    }
  }

  @Override
  public void removeItemIDs(List<UUID> ids) throws NotFoundException {
    for (UUID id : ids) {
      removeItemID(id);
    }
  }

}
