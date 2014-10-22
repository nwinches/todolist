package com.nwinches.heroku;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

import com.nwinches.TaskActivity;
import com.nwinches.dao.InMemoryTaskDao;

/**
 * This class launches the web application in an embedded Jetty container. This is the entry point
 * to your application. The Java command that is used for launching should fire this main method.
 */
public class Main extends ResourceConfig {

  public Main() {
    register(RequestContextFilter.class);
    register(TaskActivity.class);
    register(InMemoryTaskDao.class);
  }
}
