package com.nwinches.dao;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;

import java.util.List;

import lombok.Setter;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import com.nwinches.entity.Task;
import com.nwinches.exception.NoSuchTaskException;

@Setter
public class SearchlySearchIndexer implements SearchIndexer {
  private static final String INDEX_NAME = "todolist";
  private static final String TYPE_NAME = "task";
  
  @Autowired
  private JestClient jestClient;
  
  @Override
  public void addTask(Task task) throws Exception {
    Index index = new Index.Builder(task).index(INDEX_NAME).type(TYPE_NAME).build();
    jestClient.execute(index);
  }

  @Override
  public void removeTask(String taskId) {

  }

  @Override
  public List<Task> searchTasks(String searchQuery) throws Exception {
    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
    searchSourceBuilder.query(QueryBuilders.multiMatchQuery(searchQuery, "title^3", "body"));

    Search search = (Search) new Search.Builder(searchSourceBuilder.toString()).addIndex(INDEX_NAME)
        .addType(TYPE_NAME).build();
     
    JestResult result = jestClient.execute(search);
    if (result.isSucceeded()) {
      return result.getSourceAsObjectList(Task.class);
    } else {
      throw new NoSuchTaskException("No tasks found");
    }
  }
}
