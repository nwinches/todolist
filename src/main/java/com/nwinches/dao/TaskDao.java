package com.nwinches.dao;

import java.util.List;

import com.nwinches.entity.Task;
import com.nwinches.exception.NoSuchTaskException;

/**
 * Describes task storage and retrieval.
 */
public interface TaskDao {
  Task getTask(String taskId) throws NoSuchTaskException;

  List<Task> listTasks();

  void saveTask(Task task);

  void deleteTask(String taskId);
}
