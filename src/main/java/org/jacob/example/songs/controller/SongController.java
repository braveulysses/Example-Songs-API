package org.jacob.example.songs.controller;

import org.jacob.example.songs.model.ListResponse;
import org.jacob.example.songs.model.Song;
import org.jacob.example.songs.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class SongController {

  private final SongService songService;

  @Autowired
  public SongController(SongService songService) {
    this.songService = songService;
  }

  @RequestMapping(
          path = "/songs",
          method = RequestMethod.GET
  )
  public @ResponseBody ListResponse<Song> getSongs() {
    return new ListResponse<>(songService.getAllSongs());
  }

  @RequestMapping(
          path = "/songs/{id}",
          method = RequestMethod.GET
  )
  public @ResponseBody Song getSong(@PathVariable("id") String id) {
    return songService.getSongById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "song not found"));
  }
}
