package com.nwinches.dao;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.powermock.api.easymock.PowerMock.replayAll;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.powermock.api.easymock.annotation.Mock;

import com.google.common.collect.ImmutableList;
import com.nwinches.AbstractTest;
import com.nwinches.dao.springdata.MongoTaskRepository;
import com.nwinches.entity.Task;
import com.nwinches.exception.NoSuchTaskException;

public class MongoTaskStoreTest extends AbstractTest {
  private static final String TASK_ID = "taskId";

  @Mock
  private Task task;
  @Mock
  private MongoTaskRepository repository;

  private MongoTaskStore taskStore;

  @Before
  public void setup() {
    taskStore = new MongoTaskStore();
    taskStore.setRepository(repository);
  }

  @Test(expected = NoSuchTaskException.class)
  public void testGetTask_NoResults() throws Exception {
    expect(repository.findById(TASK_ID)).andReturn(null);
    replayAll();
    taskStore.getTask(TASK_ID);
  }

  @Test
  public void testExists_NoResults() throws Exception {
    expect(repository.exists(TASK_ID)).andReturn(false);
    replayAll();
    assertFalse(taskStore.exists(TASK_ID));
  }

  @Test
  public void testSaveTask() throws Exception {
    expect(repository.save(task)).andReturn(task);

    replayAll();
    taskStore.saveTask(task);

    // Nothing to test, just verify mocks
  }
  
  @Test
  public void testGetTask() {
    expect(repository.findById(TASK_ID)).andReturn(task);
    
    replayAll();
    Task actual = taskStore.getTask(TASK_ID);
    assertEquals(task, actual);
  }
  
  @Test
  public void testListTasks() {
    expect(repository.findAll()).andReturn(ImmutableList.of(task));
    
    replayAll();
    List<Task> actual = taskStore.listTasks();
    assertEquals(1, actual.size());
    assertEquals(task, actual.get(0));
  }
  
  @Test
  public void testSearchTasks() {
    expect(repository.findByTitleContainingOrBodyContaining("foo")).andReturn(ImmutableList.of(task));
    
    replayAll();
    List<Task> actual = taskStore.searchTasks("foo");
    assertEquals(1, actual.size());
    assertEquals(task, actual.get(0));
  }
}
