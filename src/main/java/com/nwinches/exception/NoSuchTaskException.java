package com.nwinches.exception;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class NoSuchTaskException extends NotFoundException {
  public NoSuchTaskException() {
    super(Response.status(Status.NOT_FOUND).build());
  }

  public NoSuchTaskException(String message) {
    super(Response.status(Status.NOT_FOUND).entity(message).build());
  }

  public NoSuchTaskException(Throwable cause) {
    super(Response.status(Status.NOT_FOUND).build(), cause);
  }

  public NoSuchTaskException(String message, Throwable cause) {
    super(Response.status(Status.NOT_FOUND).entity(message).build(), cause);
  }
}
