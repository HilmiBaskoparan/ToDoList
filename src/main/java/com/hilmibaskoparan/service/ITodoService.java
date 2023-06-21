package com.hilmibaskoparan.service;

import com.hilmibaskoparan.model.entity.TodoEntity;

import java.util.List;

public interface ITodoService {

    // CRUD OPERATIONS
    // CREATE - ADD
    public TodoEntity addTodo(TodoEntity todo);

    // DELETE BY ID
    public TodoEntity deleteById(Long id);

    // UPDATE BY ID
    public TodoEntity updateById(Long id, TodoEntity todo);

    // LIST
    public List<TodoEntity> list();

    // LIST BY USERNAME
    public List<TodoEntity> listByUserName(String userName);

    // FIND BY ID
    public TodoEntity finById(Long id);

    // DELETE ALL
    public String deleteAll();
}
