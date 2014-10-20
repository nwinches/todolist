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

@Path("task/{taskId}")
public class TaskActivity {
  
  private TaskDao taskDao = new InMemoryTaskDao();

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String getTask(@PathParam("taskId") String taskId) {
    return taskDao.getTask(taskId).getTitle();
  }
  
  @POST
  @Consumes(MediaType.TEXT_PLAIN)
  public void saveTask(@PathParam("taskTitle") String taskTitle) {
    Task task = new Task();
    task.setId(UUID.randomUUID().toString());
    task.setTitle(taskTitle);
    task.setBody("TODO");

    taskDao.saveTask(task);
  }
}
