package com.nwinches.exception;

import javax.ws.rs.NotAllowedException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class TaskModificationNotAllowedException extends NotAllowedException {
  private static final Response ALREADY_EXISTS = 
      Response.status(Status.FORBIDDEN).entity("Task with that id already exists").build();
  public TaskModificationNotAllowedException() {
    super(ALREADY_EXISTS);
  }

  public TaskModificationNotAllowedException(String message) {
    super(Response.status(Status.FORBIDDEN).entity(message).build());
  }

  public TaskModificationNotAllowedException(Throwable cause) {
    super(ALREADY_EXISTS, cause);
  }

  public TaskModificationNotAllowedException(String message, Throwable cause) {
    super(Response.status(Status.FORBIDDEN).entity(message).build(), cause);
  }
}
