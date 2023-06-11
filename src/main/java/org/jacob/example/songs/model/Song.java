package org.jacob.example.songs.model;

import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@EqualsAndHashCode
public class Song implements Model {

  @Getter @NonNull
  private final UUID id;

  @Getter @NonNull
  private final String name;

  @Getter @NonNull
  private final Band band;

  @Getter
  private final String lyrics;

  public Song(String name, Band band, String lyrics) {
    this.id = UUID.randomUUID();
    this.name = name;
    this.band = band;
    this.lyrics = lyrics;
  }
}
