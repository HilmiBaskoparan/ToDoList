package com.hilmibaskoparan.controller;

import com.hilmibaskoparan.model.Request.TodoAddRequest;
import com.hilmibaskoparan.model.Request.TodoUpdateRequest;
import com.hilmibaskoparan.model.entity.TodoEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface ITodoApi {

    ResponseEntity<TodoEntity> addTodo(TodoAddRequest request);

    ResponseEntity<TodoEntity> deleteTodo(Long id);

    ResponseEntity<TodoEntity> updateTodo(Long id, TodoUpdateRequest request);

    ResponseEntity<List<TodoEntity>> listTodos();

    ResponseEntity<TodoEntity> findById(Long id);

    ResponseEntity<String> deleteAll();

    ResponseEntity<List<TodoEntity>> listCompletedTasks();

    ResponseEntity<List<TodoEntity>> listUncompletedTasks();

    ResponseEntity<String> deleteDoneTasks();

    // LIST BY USERNAME
    // public ResponseEntity<List<TodoEntity>> listByUserName(String userName);

    // ADDING 10 RANDOM DATA FOR TEST
    // public ResponseEntity<List<TodoEntity>> addSpeedData();
}
