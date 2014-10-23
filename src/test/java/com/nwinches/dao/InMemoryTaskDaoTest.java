package com.nwinches.dao;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertEquals;
import static org.powermock.api.easymock.PowerMock.replayAll;
import static org.powermock.api.easymock.PowerMock.verifyAll;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.annotation.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import com.nwinches.entity.Task;
import com.nwinches.exception.NoSuchTaskException;

@RunWith(PowerMockRunner.class)
public class InMemoryTaskDaoTest {
  private static final String TASK_ID = "taskId";
  
  @Mock private Task task;
  
  private TaskStore taskDao = new InMemoryTaskStore();

  @Test(expected = NoSuchTaskException.class)
  public void testGetTask_NoResults() throws Exception {
    replayAll();
    taskDao.getTask("non existant task");
  }
  
  @Test
  public void testSaveTaskAndGetTask() throws Exception {
    expect(task.getId()).andReturn(TASK_ID);
    
    replayAll();
    taskDao.saveTask(task);
    Task actualTask = taskDao.getTask(TASK_ID);
    
    assertEquals(task, actualTask);
  }
  
  @Test(expected = NoSuchTaskException.class)
  public void testDeleteTask() throws Exception {
    expect(task.getId()).andReturn(TASK_ID);
    
    replayAll();
    taskDao.saveTask(task);
    taskDao.deleteTask(TASK_ID);
    taskDao.getTask(TASK_ID);
  }
  
  @After
  public void verify() {
    verifyAll();
  }
}
