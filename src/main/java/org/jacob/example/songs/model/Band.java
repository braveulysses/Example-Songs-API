package org.jacob.example.songs.model;

import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class Band implements Model {

  @Getter @NonNull
  private final UUID id;

  @Getter @NonNull
  private final String name;

  public Band(String name) {
    this.id = UUID.randomUUID();
    this.name = name;
  }
}
