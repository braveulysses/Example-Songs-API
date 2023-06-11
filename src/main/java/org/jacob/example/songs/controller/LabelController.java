package org.jacob.example.songs.controller;

import org.jacob.example.songs.model.Label;
import org.jacob.example.songs.model.ListResponse;
import org.jacob.example.songs.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class LabelController {

  private final LabelService labelService;

  @Autowired
  public LabelController(LabelService labelService) {
    this.labelService = labelService;
  }

  @RequestMapping(
          path = "/labels",
          method = RequestMethod.GET
  )
  public @ResponseBody ListResponse<Label> getLabels() {
    return new ListResponse<>(labelService.getAllLabels());
  }

  @RequestMapping(
          path = "/labels/{id}",
          method = RequestMethod.GET
  )
  public @ResponseBody Label getLabel(@PathVariable("id") String id) {
    return labelService.getLabelById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "label not found"));
  }
}
