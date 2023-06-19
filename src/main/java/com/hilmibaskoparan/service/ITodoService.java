package com.hilmibaskoparan.service;

import com.hilmibaskoparan.model.entity.TodoEntity;

import java.util.List;
import java.util.Optional;

public interface ITodoService {

    // CRUD OPERATIONS
    // CREATE - ADD
    public void addTodo(String userName, String description);

    // DELETE BY ID
    public void deleteTodo(Long id);

    // UPDATE BY ID
    public void updateTodo(TodoEntity todo);

    // LIST
    public List<TodoEntity> listTodos();

    // LIST BY USERNAME
    public List<TodoEntity> listByUserName(String userName);

    // GET BY ID
    public Optional<TodoEntity> getById(Long id);
}
