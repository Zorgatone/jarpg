package jarpg.game.entity.interfaces;

import jarpg.game.entity.Entity;

public interface Friendly {

  boolean isFriendly(Entity entity);
  boolean isFriendlyToPlayers();

}
