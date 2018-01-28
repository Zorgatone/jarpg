package jarpg.game.gameobject;

import jarpg.game.interfaces.Identifiable;
import java.util.UUID;

public abstract class GameObject implements Identifiable {

  public static final String DEFAULT_NAME = "<GameObject.Unknown-Name>";

  protected UUID id = UUID.randomUUID();
  protected String name = DEFAULT_NAME;

  public GameObject(UUID id) {
    this.id = id;
  }

  public GameObject() {
    this(UUID.randomUUID());
  }

  public GameObject(UUID id, String name) {
    this(id);

    this.name = name;
  }

  public GameObject(String name) {
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
