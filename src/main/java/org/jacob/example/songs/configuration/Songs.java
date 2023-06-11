package org.jacob.example.songs.configuration;

import java.util.List;
import lombok.Getter;
import org.jacob.example.songs.model.Song;

public class Songs {

  @Getter
  private final List<Song> songs;

  public Songs(List<Song> songs) {
    this.songs = songs;
  }
}
