package com.nwinches.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Task {
  private String id;
  private String title;
  private String body;
  private boolean complete;
}
