package com.nwinches.dao;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.powermock.api.easymock.PowerMock.replayAll;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.powermock.api.easymock.annotation.Mock;

import com.nwinches.AbstractTest;
import com.nwinches.entity.Task;
import com.nwinches.exception.NoSuchTaskException;

public class InMemoryTaskDaoTest extends AbstractTest {
  private static final String TASK_ID = "taskId";
  private static final String TITLE = "This is a Title";
  
  @Mock private Task task;
  
  private TaskStore taskStore;
  
  @Before
  public void setup() {
    taskStore = new InMemoryTaskStore();
  }

  @Test(expected = NoSuchTaskException.class)
  public void testGetTask_NoResults() throws Exception {
    replayAll();
    taskStore.getTask("non existant task");
  }

  @Test
  public void testExists_NoResults() throws Exception {
    replayAll();
    assertFalse(taskStore.exists("non existant task"));
  }
  
  @Test
  public void testSaveTaskAndGetTask() throws Exception {
    expect(task.getId()).andReturn(TASK_ID);
    
    replayAll();
    taskStore.saveTask(task);
    Task actualTask = taskStore.getTask(TASK_ID);
    
    assertTrue(taskStore.exists(TASK_ID));
    assertEquals(task, actualTask);
  }

  @Test
  public void testSaveTaskAndListTask() throws Exception {
    expect(task.getId()).andReturn(TASK_ID);
    
    replayAll();
    taskStore.saveTask(task);
    List<Task> actualTasks = taskStore.listTasks();
    
    assertEquals(1, actualTasks.size());
    assertTrue(actualTasks.contains(task));
  }
  
  @Test
  public void testSaveTaskAndSearchTask() throws Exception {
    expect(task.getId()).andReturn(TASK_ID);
    expect(task.getTitle()).andReturn(TITLE);
    
    replayAll();
    taskStore.saveTask(task);
    List<Task> actualTasks = taskStore.searchTasks("title");
    
    assertEquals(1, actualTasks.size());
    assertTrue(actualTasks.contains(task));
  }
  
  @Test
  public void testSaveTaskAndSearchTask_NoResults() throws Exception {
    expect(task.getId()).andReturn(TASK_ID);
    expect(task.getTitle()).andReturn(TITLE);
    expect(task.getBody()).andReturn(TITLE);
    
    replayAll();
    taskStore.saveTask(task);
    List<Task> actualTasks = taskStore.searchTasks("non existant search parameter");
    
    assertEquals(0, actualTasks.size());
  }
  
  @Test(expected = NoSuchTaskException.class)
  public void testDeleteTask() throws Exception {
    expect(task.getId()).andReturn(TASK_ID);
    
    replayAll();
    taskStore.saveTask(task);
    taskStore.deleteTask(TASK_ID);
    taskStore.getTask(TASK_ID);
  }
}
