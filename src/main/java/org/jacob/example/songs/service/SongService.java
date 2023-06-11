package org.jacob.example.songs.service;

import org.jacob.example.songs.configuration.Songs;
import org.jacob.example.songs.model.Song;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SongService {

  private final Songs songs;

  @Autowired
  public SongService(Songs songs) {
    this.songs = songs;
  }

  public List<Song> getAllSongs() {
    return songs.getSongs();
  }

  public Optional<Song> getSongById(String id) {
    return songs.getSongs().stream()
            .filter(song -> id.equals(song.getId().toString()))
            .findFirst();
  }

  public Optional<Song> getSongByName(String name) {
    return songs.getSongs().stream()
            .filter(song -> name.equalsIgnoreCase(song.getName()))
            .findFirst();
  }
}
