package jarpg.game.entity.interfaces;

public interface OwnsMoney {

  boolean hasMoney();
  int getMoney();

  void addMoney(int money);
  void removeMoney(int money);

}
