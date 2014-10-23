package com.nwinches.dao;

import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.nwinches.entity.Task;
import com.nwinches.exception.NoSuchTaskException;

public class InMemoryTaskStore implements TaskStore {
  private Map<String, Task> tasks = Maps.newLinkedHashMap();

  @Override
  public boolean exists(String taskId) {
    return tasks.containsKey(taskId);
  }

  @Override
  public Task getTask(String taskId) throws NoSuchTaskException {
    if (!tasks.containsKey(taskId)) {
      throw new NoSuchTaskException("No such task");
    }
    return tasks.get(taskId);
  }

  @Override
  public List<Task> listTasks() {
    return ImmutableList.copyOf(tasks.values());
  }
  
  /**
   * Initial implementation naively does a contains search on title and body.
   * 
   * @param search
   * @return
   */
  @Override
  public List<Task> searchTasks(String search) {
    List<Task> filteredTasks = Lists.newArrayList();
    String lowerCaseSearch = search.toLowerCase();
    for (Task task : tasks.values()) {
      if (task.getTitle().toLowerCase().contains(lowerCaseSearch)
          || task.getBody().toLowerCase().contains(lowerCaseSearch)) {
        filteredTasks.add(task);
      }
    }
    return filteredTasks;
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
