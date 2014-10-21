package com.nwinches.exception;

public class NoSuchTaskException extends Exception {
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
