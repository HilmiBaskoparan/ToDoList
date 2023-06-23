package com.hilmibaskoparan.model.repository;

import com.hilmibaskoparan.model.entity.TodoEntity;
import org.springframework.data.repository.CrudRepository;

public interface ITodoRepository extends CrudRepository<TodoEntity, Long> {

    // List<TodoEntity> findByUserName(String userName);
}
