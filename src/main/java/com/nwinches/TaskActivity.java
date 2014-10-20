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

@Path("task")
public class TaskActivity {
  
  private TaskDao taskDao = new InMemoryTaskDao();

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Task createDummyTask() {
    Task task = new Task();
    task.setId(UUID.randomUUID().toString());
    task.setTitle("dummy task");
    task.setBody("body");
    taskDao.saveTask(task);
    
    return task;
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Task getTask(String taskId) {
    return taskDao.getTask(taskId);
  }
  
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public void createTask(Task task) {
    task.setId(UUID.randomUUID().toString());

    taskDao.saveTask(task);
  }
}
