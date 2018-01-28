package jarpg.game.entity.player;

import jarpg.game.entity.Entity;
import jarpg.game.entity.interfaces.Combat;
import jarpg.game.entity.interfaces.Trader;
import jarpg.game.exceptions.DuplicateException;
import jarpg.game.exceptions.ExhaustedCapacity;
import jarpg.game.exceptions.InvalidTrade;
import jarpg.game.exceptions.NotFoundException;
import jarpg.game.gameobject.interfaces.Sellable;
import jarpg.game.gameobject.item.Item;
import jarpg.game.interfaces.Damageable;
import jarpg.game.interfaces.Living;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class Player extends Entity implements Combat, Damageable, Living, Trader {

  public static final int START_HP = 200;
  public static final int START_ARMOR = 1;
  public static final String DEFAULT_NAME = "<Player.Unknown-Name>";
  public static final int START_MONEY = 50;

  protected int baseHp = START_HP;
  protected int baseArmor = START_ARMOR;
  protected int money = START_MONEY;
  protected Inventory inventory = new Inventory();
  protected ArrayList<Sellable> sellables = new ArrayList<Sellable>();

  public Player(String name) {
    super(name);
  }

  public Player(UUID id, String name) {
    super(id, name);
  }

  public Player() {
    this(DEFAULT_NAME);
  }

  public Player(UUID id) {
    this(id, DEFAULT_NAME);
  }

  @Override
  public int getHP() {
    return baseHp;
  }

  @Override
  public int getArmor() {
    return baseArmor;
  }

  @Override
  public void takeDamage(int hp) {
    this.baseHp -= hp;
  }

  @Override
  public void restoreDamage(int hp) {
    this.baseHp += hp;
  }

  @Override
  public boolean isAlive() {
    return this.baseHp > 0;
  }

  @Override
  public List<Item> getItems() {
    return inventory.getItems();
  }

  @Override
  public boolean hasItems() {
    return inventory.hasItems();
  }

  @Override
  public int countItems() {
    return inventory.countItems();
  }

  @Override
  public void addItem(Item item) throws DuplicateException, ExhaustedCapacity {
    inventory.addItem(item);
  }

  @Override
  public void addItems(List<Item> items) throws DuplicateException, ExhaustedCapacity {
    inventory.addItems(items);
  }

  @Override
  public void removeItem(Item item) throws Exception {
    inventory.removeItem(item);
  }

  @Override
  public void removeItems(List<Item> items) throws Exception {
    inventory.removeItems(items);
  }

  @Override
  public void removeItemID(UUID id) throws Exception {
    inventory.removeItemID(id);
  }

  @Override
  public void removeItemIDs(List<UUID> ids) throws Exception {
    inventory.removeItemIDs(ids);
  }

  @Override
  public boolean hasMoney() {
    return money > 0;
  }

  @Override
  public int getMoney() {
    return money;
  }

  @Override
  public void addMoney(int money) {
    this.money += money;
  }

  @Override
  public void removeMoney(int money) {
    this.money -= money;
  }

  @Override
  public void sellItem(Item item) throws InvalidTrade, NotFoundException {
    Double price = item.getPrice().doubleValue();

    if (!(
      item.canBeSold() &&
      Double.isFinite(price) &&
      inventory.containsItem(item)
    )) {
      throw new InvalidTrade("Cannot sell item: " + item.getUUID());
    }

    inventory.removeItem(item);
    money += price;
  }

  @Override
  public void buyItem(Item item) throws InvalidTrade {
    if (null == item) {
      throw new InvalidTrade("Cannot buy null item.");
    }

    Double price = item.getPrice().doubleValue();

    if (!(
      item.canBeSold() &&
      Double.isFinite(price) &&
      money >= price &&
      inventory.countItems() + 1 < inventory.getMaxItems()
    ) || inventory.containsItem(item)) {
      throw new InvalidTrade("Cannot buy item: " + item.getUUID());
    }

    money -= price;

    try {
      inventory.addItem(item);
    } catch (DuplicateException e) {
      money += price;
      throw new InvalidTrade("Cannot buy duplicate item: " + item.getUUID());
    } catch (ExhaustedCapacity e) {
      money += price;
      throw new InvalidTrade("Cannot buy more items with full inventory");
    }
  }

  @Override
  public void sell(Sellable sellable) throws InvalidTrade, NotFoundException {
    if (sellable instanceof Item) {
      sellItem((Item) sellable);
    } else {
      if (null == sellable) {
        throw new InvalidTrade("Cannot sell null sellable.");
      }

      Double price = sellable.getPrice().doubleValue();

      if (!(
        sellable.canBeSold() &&
        Double.isFinite(price) &&
        sellables.contains(sellable)
      )) {
        throw new InvalidTrade("Cannot sell sellable: " + sellable.getUUID());
      }

      sellables.remove(sellable);
      money += price;
    }
  }

  @Override
  public void buy(Sellable sellable) throws InvalidTrade {
    if (sellable instanceof Item) {
      buyItem((Item) sellable);
    } else {
      if (null == sellable) {
        throw new InvalidTrade("Cannot buy null item.");
      }

      Double price = sellable.getPrice().doubleValue();

      if (!(
        sellable.canBeSold() &&
        Double.isFinite(price) &&
        money >= price
      )) {
        throw new InvalidTrade("Cannot buy item: " + sellable.getUUID());
      }

      money -= price;
      sellables.add(sellable);
    }
  }
}
