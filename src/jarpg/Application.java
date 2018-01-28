package jarpg;

import jarpg.game.Game;


public class Application {

  private String[] args;

  public Application(String[] args) {
    this.args = args;
  }

  public void run() throws Exception {
    Game game = new Game(args);
    game.play();
  }

}
