package com.nwinches;

import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.nwinches.dao.InMemoryTaskDao;
import com.nwinches.dao.TaskDao;
import com.nwinches.entity.Task;

public class TaskActivity {
  
  private TaskDao taskDao = new InMemoryTaskDao();

  @GET
  @Path("task/{taskId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Task getTask(@PathParam("taskId") String taskId) {
    return taskDao.getTask(taskId);
  }
  
  @POST
  @Path("task")
  @Consumes(MediaType.TEXT_PLAIN)
  public void createTask(@QueryParam("task") Task task) {
    task.setId(UUID.randomUUID().toString());

    taskDao.saveTask(task);
  }
}
