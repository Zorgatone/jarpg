package jarpg.game.gameobject.item;

import java.util.UUID;

public class ItemBuilder {

  private UUID _id = null;
  private String _name = null;
  private double _price = Double.NaN;

  public ItemBuilder(UUID _id) {
    id(_id);
  }

  public ItemBuilder() {
    //
  }

  public ItemBuilder id(UUID id) {
    if (null != id) {
      _id = id;
    }

    return this;
  }

  public ItemBuilder name(String name) {
    if (null != name) {
      _name = name;
    }

    return this;
  }

  public ItemBuilder price(double price) {
    if (Double.isFinite(price) && price > 0) {
      _price = price;
    }

    return this;
  }

  public Item build() {
    boolean validId = null != _id;
    boolean validName = null != _name;
    boolean validPrice = Double.isFinite(_price) && _price > 0;

    if (validId && validName && validPrice) {
      return new Item(_id, _name, (int)_price);
    }

    if (validId && validName) {
      return new Item(_id, _name);
    }

    if (validId && validPrice) {
      return new Item(_id, (int)_price);
    }

    if (validName && validPrice) {
      return new Item(_name, (int)_price);
    }

    if (validId) {
      return new Item(_id);
    }

    if (validName) {
      return new Item(_name);
    }

    if (validPrice) {
      return new Item((int)_price);
    }

    return new Item();
  }

}
