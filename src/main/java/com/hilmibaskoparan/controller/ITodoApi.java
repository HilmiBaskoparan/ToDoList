package com.hilmibaskoparan.controller;

import com.hilmibaskoparan.model.Request.TodoAddRequest;
import com.hilmibaskoparan.model.entity.TodoEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ITodoApi {

    // CRUD OPERATIONS
    // CREATE - ADD
    public ResponseEntity<TodoEntity> addTodo(TodoAddRequest request);

    // DELETE BY ID
    public ResponseEntity<TodoEntity> deleteTodo(Long id);

    // UPDATE BY ID
    public ResponseEntity<TodoEntity> updateTodo(Long id, TodoEntity todo);

    // LIST
    public ResponseEntity<List<TodoEntity>> listTodos();

    // LIST BY USERNAME
    // public ResponseEntity<List<TodoEntity>> listByUserName(String userName);

    // FIND BY ID
    public ResponseEntity<TodoEntity> findById(Long id);

    // DELETE ALL
    public ResponseEntity<String> deleteAll();

    // ADDING 10 RANDOM DATA FOR TEST
    // public ResponseEntity<List<TodoEntity>> addSpeedData();
}
