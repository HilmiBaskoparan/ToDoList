package com.hilmibaskoparan.service;

import com.hilmibaskoparan.model.Request.TodoAddRequest;
import com.hilmibaskoparan.model.Request.TodoUpdateRequest;
import com.hilmibaskoparan.model.entity.TodoEntity;

import java.util.List;

public interface ITodoService {

    TodoEntity addTodo(TodoAddRequest addRequest);

    TodoEntity deleteById(Long id);

    TodoEntity updateById(Long id, TodoUpdateRequest updateRequest);

    List<TodoEntity> list();

    TodoEntity finById(Long id);

    String deleteAll();

    List<TodoEntity> listCompleted();

    List<TodoEntity> listUncompleted();

    String deleteCompletedTodos();

    // LIST BY USERNAME
    // public List<TodoEntity> listByUserName(String userName);

    // ADD SPEED DATA
    // public List<TodoEntity> addSpeedData();
}
