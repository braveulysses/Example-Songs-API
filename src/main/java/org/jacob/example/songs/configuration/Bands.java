package org.jacob.example.songs.configuration;

import java.util.List;
import lombok.Getter;
import org.jacob.example.songs.model.Band;

public class Bands {

  @Getter
  private final List<Band> bands;

  public Bands(List<Band> bands) {
    this.bands = bands;
  }
}
