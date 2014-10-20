package com.nwinches.dao;

import java.util.List;

import com.nwinches.entity.Task;

public interface TaskDao {
  Task getTask(String taskId);

  List<Task> listTasks();

  void saveTask(Task task);

  void deleteTask(String taskId);
}
