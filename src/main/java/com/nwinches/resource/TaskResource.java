package com.nwinches.resource;

import java.util.List;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Setter;

import com.nwinches.dao.TaskDao;
import com.nwinches.entity.Task;
import com.nwinches.exception.NoSuchTaskException;

/**
 * Basic class for handling RESTful HTTP requests regarding tasks.
 */
@Path("task")
public class TaskResource {
  
  @Autowired
  private TaskDao taskDao;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Task> createDummyTask() {
    Task task = new Task();
    task.setId(UUID.randomUUID().toString());
    task.setTitle("dummy task");
    task.setBody("body");
    taskDao.saveTask(task);
    
    return taskDao.listTasks();
  }

  @GET
  @Path("/{taskId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Task getTask(@PathParam("taskId") String taskId) {
    try {
      return taskDao.getTask(taskId);
    } catch (NoSuchTaskException e) {
      Response.status(Status.NOT_FOUND).build().close();
      return null;
    }
  }
  
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public void createTask(Task task) {
    task.setId(UUID.randomUUID().toString());

    taskDao.saveTask(task);
  }
  
  @DELETE
  @Consumes(MediaType.TEXT_PLAIN)
  public void deleteTask(String taskId) {
    taskDao.deleteTask(taskId);
  }
}
