package jarpg.game.gameobject.interfaces;

/**
 * Something you might be able to ATTEMPT to sell
 */
public interface Sellable extends Valuable {

  boolean canBeSold();

}
