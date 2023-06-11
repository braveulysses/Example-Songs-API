package org.jacob.example.songs.configuration;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.NoArgsConstructor;
import org.jacob.example.songs.model.Band;
import org.jacob.example.songs.model.Label;
import org.jacob.example.songs.model.Song;
import java.util.List;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.yaml.snakeyaml.Yaml;

@Configuration
public class SongsApiConfiguration {

  private static final Logger logger = LoggerFactory.getLogger(SongsApiConfiguration.class);

  private final List<Label> labels;
  private final List<Band> bands;
  private final List<Song> songs;

  public SongsApiConfiguration() {
    DataLoader dataLoader = new DataLoader();
    dataLoader.load();
    labels = dataLoader.getLabels();
    bands = dataLoader.getBands();
    songs = dataLoader.getSongs();
    logger.info("Configuration loaded");
  }

  @Bean
  public Labels labels() {
    return new Labels(labels);
  }

  @Bean
  public Bands bands() {
    return new Bands(bands);
  }

  @Bean Songs songs() {
    return new Songs(songs);
  }

  @NoArgsConstructor
  private static class DataLoader {

    private static final Logger logger = LoggerFactory.getLogger(DataLoader.class);

    @Getter
    private final List<Band> bands = new ArrayList<>();

    @Getter
    private final List<Label> labels = new ArrayList<>();

    @Getter
    private final List<Song> songs = new ArrayList<>();

    public void load() {
      loadBands();
      loadLabels();
      loadSongs();
    }

    private static InputStream readResource(String name) {
      return SongsApiConfiguration.class.getResourceAsStream(name);
    }

    @SuppressWarnings("unchecked")
    private void loadBands() {
      Yaml yaml = new Yaml();
      for (Object element : yaml.loadAll(readResource("/bands.yaml"))) {
        Map<String, Object> bandMap = (Map<String, Object>) element;
        Band band = new Band((String) bandMap.get("name"));
        bands.add(band);
        logger.info("Loaded band {}", band.getName());
      }
    }

    @SuppressWarnings("unchecked")
    private void loadLabels() {
      Yaml yaml = new Yaml();
      for (Object element : yaml.loadAll(readResource("/labels.yaml"))) {
        Map<String, Object> labelMap = (Map<String, Object>) element;
        String labelName = (String) labelMap.get("name");
        List<String> bandNames = (List<String>) labelMap.get("roster");
        Label label = new Label(
                labelName,
                bandNames.stream()
                        .map(this::toBandInstance)
                        .collect(Collectors.toList()));
        labels.add(label);
        logger.info("Loaded label {}", label.getName());
      }
    }

    @SuppressWarnings("unchecked")
    private void loadSongs() {
      Yaml yaml = new Yaml();
      for (Object element : yaml.loadAll(readResource("/songs.yaml"))) {
        Map<String, Object> songMap = (Map<String, Object>) element;
        String songName = (String) songMap.get("name");
        String bandName = (String) songMap.get("band");
        String lyrics = (String) songMap.get("lyrics");
        songs.add(new Song(songName, toBandInstance(bandName), lyrics));
      }
    }

    private Band toBandInstance(String name) {
      return bands.stream()
              .filter(band -> name.equals(band.getName()))
              .findFirst()
              .orElseThrow(() -> new IllegalArgumentException("unknown band " + name));
    }
  }
}
