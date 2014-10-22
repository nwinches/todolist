package com.nwinches.exception;

import javax.ws.rs.NotFoundException;

public class NoSuchTaskException extends NotFoundException {
  public NoSuchTaskException() {
    super();
  }

  public NoSuchTaskException(String message) {
    super(message);
  }

  public NoSuchTaskException(Throwable cause) {
    super(cause);
  }

  public NoSuchTaskException(String message, Throwable cause) {
    super(message, cause);
  }
}
