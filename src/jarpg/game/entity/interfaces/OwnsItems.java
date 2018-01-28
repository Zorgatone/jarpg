package jarpg.game.entity.interfaces;

import java.util.List;
import java.util.UUID;
import jarpg.game.gameobject.item.Item;

public interface OwnsItems {

  List<Item> getItems();

  boolean hasItems();
  int countItems();

  void addItem(Item item) throws Exception;
  void addItems(List<Item> items) throws Exception;

  void removeItem(Item item) throws Exception;
  void removeItems(List<Item> items) throws Exception;

  void removeItemID(UUID id) throws Exception;
  void removeItemIDs(List<UUID> ids) throws Exception;

}
