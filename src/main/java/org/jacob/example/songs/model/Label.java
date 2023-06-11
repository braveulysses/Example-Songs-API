package org.jacob.example.songs.model;

import java.util.List;
import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@EqualsAndHashCode
public class Label implements Model {

  @Getter @NonNull
  private final UUID id;

  @Getter @NonNull
  private final String name;

  @Getter @NonNull
  private final List<Band> roster;

  public Label(String name, List<Band> roster) {
    this.id = UUID.randomUUID();
    this.name = name;
    this.roster = roster;
  }
}
