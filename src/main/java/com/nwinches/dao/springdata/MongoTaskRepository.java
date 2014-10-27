package com.nwinches.dao.springdata;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nwinches.entity.Task;

/**
 * MongoRepository provides the basic CRUD operations, this provides additional lookup capabilities.
 */
public interface MongoTaskRepository extends MongoRepository<Task, String> {

  Task findById(String taskId);
  
  List<Task> findByTitleContainingOrBodyContaining(String search);
}
