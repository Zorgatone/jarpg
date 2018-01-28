package jarpg.game.entity.interfaces;

import jarpg.game.entity.Entity;


public interface Hostile {

  boolean isHostile(Entity entity);
  boolean isHostileToPlayers();

}
