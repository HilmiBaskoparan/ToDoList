package com.hilmibaskoparan.model.repository;

import com.hilmibaskoparan.model.entity.TodoEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ITodoRepository extends CrudRepository<TodoEntity, Long> {

    @Query(value="SELECT * FROM todo_list ",nativeQuery=true)
    List<TodoEntity> findAllTodos();

    // List<TodoEntity> findByUserName(String userName);
}
