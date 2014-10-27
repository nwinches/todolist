package com.nwinches.dao;

import java.util.List;

import jersey.repackaged.com.google.common.collect.ImmutableList;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;

import com.nwinches.dao.springdata.MongoTaskRepository;
import com.nwinches.entity.Task;
import com.nwinches.exception.NoSuchTaskException;

/**
 * Uses Spring Data's MongoRepository to send calls to mongo.
 */
@Setter
public class MongoTaskStore implements TaskStore {
  @Autowired
  private MongoTaskRepository repository;

  @Override
  public boolean exists(String taskId) {
    return repository.exists(taskId);
  }

  @Override
  public Task getTask(String taskId) throws NoSuchTaskException {
    Task task = repository.findById(taskId);
    if (task == null) {
      throw new NoSuchTaskException("No task found");
    }
    return task;
  }

  @Override
  public List<Task> listTasks() {
    return ImmutableList.copyOf(repository.findAll());
  }

  @Override
  public List<Task> searchTasks(String search) {
    return repository.findByTitleContainingOrBodyContaining(search);
  }

  @Override
  public void saveTask(Task task) {
    repository.save(task);
  }

  @Override
  public void deleteTask(String taskId) {
    repository.delete(taskId);
  }
}
