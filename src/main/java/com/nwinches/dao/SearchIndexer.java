package com.nwinches.dao;

import java.util.List;

import com.nwinches.entity.Task;

public interface SearchIndexer {
  void addTask(Task task) throws Exception;
  
  void removeTask(String taskId) throws Exception;
  
  List<Task> searchTasks(String search) throws Exception;
}
