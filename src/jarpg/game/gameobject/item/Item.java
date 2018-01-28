package jarpg.game.gameobject.item;

import jarpg.game.gameobject.GameObject;
import jarpg.game.gameobject.interfaces.Sellable;
import jarpg.game.gameobject.interfaces.Valuable;
import java.util.UUID;

public class Item extends GameObject implements Valuable, Sellable {

  public static final int DEFAULT_PRICE = -1;

  protected int price = DEFAULT_PRICE;

  public Item() {
    super();
  }

  public Item(UUID id) {
    super(id);
  }

  public Item(String name) {
    super(name);
  }

  public Item(UUID id, String name) {
    super(id, name);
  }

  public Item(int price) {
    this();

    this.price = price;
  }

  public Item(String name, int price) {
    this(name);

    this.price = price;
  }

  public Item(UUID id, int price) {
    this(id);

    this.price = price;
  }

  public Item(UUID id, String name, int price) {
    this(id, name);

    this.price = price;
  }

  @Override
  public Number getPrice() {
    return price >= 0 ? price : Float.NaN;
  }

  @Override
  public boolean canBeSold() {
    double itemPrice = getPrice().doubleValue();
    return Double.isFinite(itemPrice) && itemPrice >= 0;
  }

}
