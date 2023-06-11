package org.jacob.example.songs.configuration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;
import org.jacob.example.songs.model.Band;
import org.jacob.example.songs.model.Song;
import org.junit.jupiter.api.Test;

public class SongsApiConfigurationTest {

  @Test
  public void getLabels() {
    SongsApiConfiguration config = new SongsApiConfiguration();
    Labels labels = config.labels();

    assertEquals(2, labels.getLabels().size());
    assertTrue(labels.getLabels().stream()
            .anyMatch(label -> label.getName().equals("SST Records")));
  }

  @Test
  public void getBands() {
    SongsApiConfiguration config = new SongsApiConfiguration();
    Bands bands = config.bands();

    assertEquals(3, bands.getBands().size());
    assertTrue(bands.getBands().stream()
            .anyMatch(band -> band.getName().equals("Hüsker Dü")));
  }

  @Test
  public void getSongs() {
    SongsApiConfiguration config = new SongsApiConfiguration();
    Songs songs = config.songs();

    assertEquals(8, songs.getSongs().size());
    assertTrue(songs.getSongs().stream()
            .anyMatch(song -> song.getName().equals("Transmission")));
  }

  @Test
  public void consistentBandInstances() {
    SongsApiConfiguration config = new SongsApiConfiguration();
    Bands bands = config.bands();
    Songs songs = config.songs();

    Optional<Band> bandOptional =
            bands.getBands().stream()
                    .filter(band -> band.getName().equals("Joy Division"))
                    .findFirst();
    assertTrue(bandOptional.isPresent());

    Optional<Song> songOptional =
            songs.getSongs().stream()
                    .filter(song -> song.getName().equals("Transmission"))
                    .findFirst();
    assertTrue(songOptional.isPresent());

    assertEquals(bandOptional.get(), songOptional.get().getBand());
  }
}
