package com.nwinches.dao;

import java.util.List;

import com.nwinches.entity.Task;
import com.nwinches.exception.NoSuchTaskException;

/**
 * Describes task storage and retrieval.
 */
public interface TaskStore {
  boolean exists(String taskId);
  
  Task getTask(String taskId) throws NoSuchTaskException;

  List<Task> listTasks();
  
  List<Task> searchTasks(String search);

  void saveTask(Task task);

  void deleteTask(String taskId);
}
