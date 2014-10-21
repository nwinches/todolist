package com.nwinches.dao;

import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.nwinches.entity.Task;
import com.nwinches.exception.NoSuchTaskException;

public class InMemoryTaskDao implements TaskDao {
  private Map<String, Task> tasks = Maps.newLinkedHashMap();

  @Override
  public Task getTask(String taskId) throws NoSuchTaskException {
    if (!tasks.containsKey(taskId)) {
      throw new NoSuchTaskException("No such task");
    }
    return tasks.get(taskId);
  }

  @Override
  public List<Task> listTasks() {
    System.out.println(tasks.size());
    return ImmutableList.copyOf(tasks.values());
  }

  @Override
  public void saveTask(Task task) {
    tasks.put(task.getId(), task);
  }

  @Override
  public void deleteTask(String taskId) {
    if (tasks.containsKey(taskId)) {
      tasks.remove(taskId);
    }
  }
}
