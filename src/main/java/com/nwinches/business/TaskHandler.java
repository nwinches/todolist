package com.nwinches.business;

import java.util.List;
import java.util.UUID;

import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;

import com.nwinches.dao.SearchIndexer;
import com.nwinches.dao.SmsNotifier;
import com.nwinches.dao.TaskStore;
import com.nwinches.entity.Task;
import com.nwinches.exception.NoSuchTaskException;
import com.nwinches.exception.TaskModificationNotAllowedException;

/**
 * Contains business logic to store/search/update tasks.
 */
@Setter
public class TaskHandler {
  @Autowired
  private TaskStore taskStore;
  @Autowired
  private SmsNotifier smsNotifier;
  @Autowired
  private SearchIndexer searchIndexer;

  public boolean exists(String taskId) {
    return taskStore.exists(taskId);
  }

  public Task getTask(String taskId) throws NoSuchTaskException {
    return taskStore.getTask(taskId);
  }

  public List<Task> listTasks() {
    return taskStore.listTasks();
  }

  public List<Task> searchTasks(String search) {
    try {
      return searchIndexer.searchTasks(search);
    } catch (Exception e) {
      return taskStore.searchTasks(search);
    }
  }
  
  public void updateTask(Task task, String number) throws TaskModificationNotAllowedException {
    Task existing = taskStore.getTask(task.getId());
    try {
      searchIndexer.addTask(task);
      taskStore.saveTask(task);
      if (!existing.isComplete() && task.isComplete()) {
        String message = String.format("Completed task: '%s'", task.getTitle());
        smsNotifier.sendSms(message, number);
      }
    } catch (Exception e) {
      System.out.println("Exception storing task");
      e.printStackTrace();
    }
  }

  /**
   * Saves a task to the taskStore. If no ID is provided, uses a random UUID.
   * 
   * @param task
   */
  public void saveTask(Task task) throws TaskModificationNotAllowedException {
    if (task.getId() == null) {
      task.setId(UUID.randomUUID().toString());
    } else if (taskStore.exists(task.getId())) {
      throw new TaskModificationNotAllowedException();
    }
    try {
      searchIndexer.addTask(task);
      taskStore.saveTask(task);
    } catch (Exception e) {
      System.out.println("Exception storing task");
      e.printStackTrace();
    }
  }

  public void deleteTask(String taskId) {
    try {
      searchIndexer.removeTask(taskId);
      taskStore.deleteTask(taskId);
    } catch (Exception e) {
      System.out.println("Exception deleting task");
      e.printStackTrace();
    }
  }
}
