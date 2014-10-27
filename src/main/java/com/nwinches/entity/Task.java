package com.nwinches.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.searchbox.annotations.JestId;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a single item in the todo list. Used to represent the webservice, mongo, and elastic
 * search layers. These could be separated for cleanliness, but providing translation logic seems
 * overkill for this scale project.
 */
@Getter
@Setter
@Document(collection = "tasks")
public class Task {
  @Id
  @JestId
  private String id;
  private String title;
  private String body;
  private boolean complete;
}
