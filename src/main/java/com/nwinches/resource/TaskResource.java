package com.nwinches.resource;

import java.util.List;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Setter;

import com.nwinches.dao.TaskStore;
import com.nwinches.entity.Task;
import com.nwinches.exception.NoSuchTaskException;

/**
 * Basic class for handling RESTful HTTP requests regarding tasks.
 */
@Path("tasks")
public class TaskResource {
  private static final Response ALREADY_EXISTS = 
      Response.status(Status.FORBIDDEN).entity("Task with that id already exists").build();
  
  @Autowired
  private TaskStore taskStore;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Task> listTasks(@QueryParam("search") String search) {
    if (search == null || search.trim().isEmpty()) {
      return taskStore.listTasks();
    } else {
      return taskStore.searchTasks(search);
    }
  }

  @GET
  @Path("/{taskId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Task getTask(@PathParam("taskId") String taskId) {
    return taskStore.getTask(taskId);
  }
  
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public void createTask(Task task) {
    if (task.getId() == null) {
      task.setId(UUID.randomUUID().toString());
    } else {
      Task other = taskStore.getTask(task.getId());
      if (other != null) {
        throw new ForbiddenException(ALREADY_EXISTS);
      }
    }

    taskStore.saveTask(task);
  }
  
  @PUT
  @Path("/{taskId}")
  @Consumes(MediaType.APPLICATION_JSON)
  public void updateTask(@PathParam("taskId") String taskId, Task task) {
    if (task.getId() == null) {
      task.setId(taskId);
    }
    
    taskStore.saveTask(task);
  }
  
  @DELETE
  @Consumes(MediaType.TEXT_PLAIN)
  public void deleteTask(String taskId) {
    taskStore.deleteTask(taskId);
  }
}
