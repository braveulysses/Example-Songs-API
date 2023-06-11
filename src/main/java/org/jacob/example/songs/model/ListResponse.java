package org.jacob.example.songs.model;

import java.util.List;
import lombok.Getter;

public class ListResponse<T extends Model> {

  @Getter
  private final int size;

  @Getter
  private final List<T> items;

  public ListResponse(List<T> items) {
    this.size = items.size();
    this.items = items;
  }
}
