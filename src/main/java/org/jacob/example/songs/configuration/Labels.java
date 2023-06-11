/*
 * Copyright (C) 2023 Ping Identity Corporation
 * All rights reserved.
 *
 * The contents of this file are the property of Ping Identity Corporation.
 * You may not copy or use this file, in either source code or executable
 * form, except in compliance with terms set by Ping Identity Corporation.
 * For further information please contact:
 *
 * Ping Identity Corporation
 * 1001 17th St Suite 100
 * Denver, CO 80202
 * 303.468.2900
 * http://www.pingidentity.com
 */

package org.jacob.example.songs.configuration;

import java.util.List;
import lombok.Getter;
import org.jacob.example.songs.model.Label;

public class Labels {

  @Getter
  private final List<Label> labels;

  public Labels(List<Label> labels) {
    this.labels = labels;
  }
}
