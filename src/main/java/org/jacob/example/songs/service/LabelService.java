package org.jacob.example.songs.service;

import java.util.List;
import java.util.Optional;
import org.jacob.example.songs.configuration.Labels;
import org.jacob.example.songs.model.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LabelService {

  private final Labels labels;

  @Autowired
  public LabelService(Labels labels) {
    this.labels = labels;
  }

  public List<Label> getAllLabels() {
    return labels.getLabels();
  }

  public Optional<Label> getLabelById(String id) {
    return labels.getLabels().stream()
            .filter(label -> id.equals(label.getId().toString()))
            .findFirst();
  }

  public Optional<Label> getLabelByName(String name) {
    return labels.getLabels().stream()
            .filter(label -> name.equalsIgnoreCase(label.getName()))
            .findFirst();
  }
}
