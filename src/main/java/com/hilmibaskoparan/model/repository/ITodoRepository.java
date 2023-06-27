package com.hilmibaskoparan.model.repository;

import com.hilmibaskoparan.model.entity.TodoEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ITodoRepository extends CrudRepository<TodoEntity, Long> {

    @Query(value="SELECT * FROM todo_list ",nativeQuery=true)
    List<TodoEntity> findAllTodos();

    // List of Completed Tasks
    @Query(value="SELECT * FROM todo_list WHERE is_done=true ",nativeQuery=true)
    List<TodoEntity> findCompletedTodos();

    // List of Uncompleted Tasks
    @Query(value="SELECT * FROM todo_list  WHERE is_done=false",nativeQuery=true)
    List<TodoEntity> findUncompletedTodos();

    // List<TodoEntity> findByUserName(String userName);
}
