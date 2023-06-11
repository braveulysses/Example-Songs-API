package org.jacob.example.songs.service;

import java.util.List;
import java.util.Optional;
import org.jacob.example.songs.configuration.Bands;
import org.jacob.example.songs.model.Band;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BandService {

  private final Bands bands;

  @Autowired
  public BandService(Bands bands) {
    this.bands = bands;
  }

  public List<Band> getAllBands() {
    return bands.getBands();
  }

  public Optional<Band> getBandById(String id) {
    return bands.getBands().stream()
            .filter(band -> id.equals(band.getId().toString()))
            .findFirst();
  }

  public Optional<Band> getBandByName(String name) {
    return bands.getBands().stream()
            .filter(band -> name.equalsIgnoreCase(band.getName()))
            .findFirst();
  }
}
