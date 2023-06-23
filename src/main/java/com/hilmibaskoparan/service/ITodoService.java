package com.hilmibaskoparan.service;

import com.hilmibaskoparan.model.Request.TodoAddRequest;
import com.hilmibaskoparan.model.Request.TodoUpdateRequest;
import com.hilmibaskoparan.model.entity.TodoEntity;

import java.util.List;

public interface ITodoService {

    // CRUD OPERATIONS
    // CREATE - ADD
    public TodoEntity addTodo(TodoAddRequest request);

    // DELETE BY ID
    public TodoEntity deleteById(Long id);

    // UPDATE BY ID
    public TodoEntity updateById(Long id, TodoUpdateRequest request);

    // LIST
    public List<TodoEntity> list();

    // LIST BY USERNAME
    // public List<TodoEntity> listByUserName(String userName);

    // FIND BY ID
    public TodoEntity finById(Long id);

    // DELETE ALL
    public String deleteAll();

    // ADD SPEED DATA
    // public List<TodoEntity> addSpeedData();
}
