package jarpg.game;


import jarpg.game.entity.player.Player;
import jarpg.game.gameobject.item.Item;
import jarpg.game.gameobject.item.ItemBuilder;

public class Game {

  private Player player;

  public Game(String[] args) throws InstantiationException {
    try {
      if (null != args && args.length > 0 && null != args[0]) {
        player = new Player(args[0]);

        Item book = new Item("Book");
        player.addItem(book);

        ItemBuilder ib = new ItemBuilder();
        ib.name("Gold bar").price(100);
        player.addItem(ib.build());
      }
    } catch (Exception e) {
      throw new InstantiationException("Failed to instantiate game");
    }
  }

  public Game() throws InstantiationException {
    this(new String[] { "NoName" });
  }

  public void play() {
    System.out.printf(
      "Player name: \"%s\", Player UUID: \"%s\"",
      player.getName(), player.getUUID()
    );
  }

}
