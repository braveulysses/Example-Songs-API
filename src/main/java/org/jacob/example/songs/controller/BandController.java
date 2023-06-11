package org.jacob.example.songs.controller;

import org.jacob.example.songs.model.Band;
import org.jacob.example.songs.model.ListResponse;
import org.jacob.example.songs.service.BandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class BandController {

  private final BandService bandService;

  @Autowired
  public BandController(BandService bandService) {
    this.bandService = bandService;
  }

  @RequestMapping(
          path = "/bands",
          method = RequestMethod.GET
  )
  public @ResponseBody ListResponse<Band> getBands() {
    return new ListResponse<>(bandService.getAllBands());
  }

  @RequestMapping(
          path = "/bands/{id}",
          method = RequestMethod.GET
  )
  public @ResponseBody Band getBand(@PathVariable("id") String id) {
    return bandService.getBandById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "band not found"));
  }
}
