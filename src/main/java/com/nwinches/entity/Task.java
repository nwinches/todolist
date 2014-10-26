package com.nwinches.entity;

import io.searchbox.annotations.JestId;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Task {
  @JestId
  private String id;
  private String title;
  private String body;
  private boolean complete;
}
