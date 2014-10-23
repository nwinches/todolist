package com.nwinches.heroku;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

import com.nwinches.dao.InMemoryTaskStore;
import com.nwinches.resource.TaskResource;

/**
 * "Entry point" that is required in order to integrated jersey with Spring.
 */
public class Main extends ResourceConfig {

  /**
   * JAX-RS requires registering classes in order to use them in Spring.
   */
  public Main() {
    register(RequestContextFilter.class);
    register(TaskResource.class);
    register(InMemoryTaskStore.class);
  }
}
