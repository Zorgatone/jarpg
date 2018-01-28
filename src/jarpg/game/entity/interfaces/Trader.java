package jarpg.game.entity.interfaces;

import jarpg.game.gameobject.interfaces.Sellable;
import jarpg.game.gameobject.item.Item;


public interface Trader extends OwnsItems, OwnsMoney {

  void sellItem(Item item) throws Exception;
  void buyItem(Item item) throws Exception;

  void sell(Sellable sellable) throws Exception;
  void buy(Sellable sellable) throws Exception;

}
