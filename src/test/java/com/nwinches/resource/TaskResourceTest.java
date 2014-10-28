package com.nwinches.resource;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.powermock.api.easymock.PowerMock.replayAll;

import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.Test;
import org.powermock.api.easymock.annotation.Mock;

import com.google.common.collect.ImmutableList;
import com.nwinches.AbstractTest;
import com.nwinches.business.TaskHandler;
import com.nwinches.entity.Task;

public class TaskResourceTest extends AbstractTest {
  private static final String TASK_ID = "taskId";
  private static final String SEARCH = "search";
  private static final String NOTIFY_NUMBER = "+1234567890";

  @Mock
  private Task task;
  @Mock private TaskHandler taskHandler;
  private TaskResource resource;
  
  @Before
  public void setup() {
    resource = new TaskResource();
    resource.setTaskHandler(taskHandler);
  }
  
  @Test
  public void testListTasks() {
    expect(taskHandler.listTasks()).andReturn(ImmutableList.of(task));
    
    replayAll();
    List<Task> actual = resource.listTasks(null);
    assertEquals(1, actual.size());
    assertTrue(actual.contains(task));
  }
  
  public void testSearchTasks() {
    expect(taskHandler.searchTasks(SEARCH)).andReturn(ImmutableList.of(task));
    
    replayAll();
    List<Task> actual = resource.listTasks(SEARCH);
    assertEquals(1, actual.size());
    assertTrue(actual.contains(task));
  }

  @Test
  public void testGetTask() {
    expect(taskHandler.getTask(TASK_ID)).andReturn(task);
    
    replayAll();
    Task actual = resource.getTask(TASK_ID);
    assertEquals(task, actual);
  }

  @Test
  public void testUpdateTask_noId() {
    expect(task.getId()).andReturn(null);
    task.setId(TASK_ID);
    taskHandler.updateTask(task, NOTIFY_NUMBER);
    
    replayAll();
    Response actual = resource.updateTask(TASK_ID, NOTIFY_NUMBER, task);
    assertEquals(Status.CREATED.getStatusCode(), actual.getStatus());
    assertEquals(task, actual.getEntity());
  }

  @Test
  public void testUpdateTask_idSupplied() {
    expect(task.getId()).andReturn(TASK_ID);
    taskHandler.updateTask(task, NOTIFY_NUMBER);
    
    replayAll();
    Response actual = resource.updateTask(TASK_ID, NOTIFY_NUMBER, task);
    assertEquals(Status.CREATED.getStatusCode(), actual.getStatus());
    assertEquals(task, actual.getEntity());
  }

  @Test
  public void testDeleteTask() {
    taskHandler.deleteTask(TASK_ID);
    
    replayAll();
    resource.deleteTask(TASK_ID);
    // nothing to test, just verify mocks
  }
}