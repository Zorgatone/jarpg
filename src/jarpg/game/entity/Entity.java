package jarpg.game.entity;

import jarpg.game.interfaces.Identifiable;
import java.util.UUID;

public abstract class Entity implements Identifiable {

  public static final String DEFAULT_NAME = "<Entity.Unknown-Name>";

  protected UUID id = UUID.randomUUID();
  protected String name = DEFAULT_NAME;

  public Entity(UUID id) {
    this.id = id;
  }

  public Entity() {
    this(UUID.randomUUID());
  }

  public Entity(UUID id, String name) {
    this(id);

    this.name = name;
  }

  public Entity(String name) {
    this(UUID.randomUUID(), name);
  }

  public UUID getUUID() {
    return id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

}
