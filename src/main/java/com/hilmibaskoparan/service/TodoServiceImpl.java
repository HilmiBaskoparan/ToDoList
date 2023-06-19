package com.hilmibaskoparan.service;

import com.hilmibaskoparan.model.entity.TodoEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements ITodoService {

    @Override
    public void addTodo(String userName, String description) {

    }

    @Override
    public void deleteTodo(Long id) {

    }

    @Override
    public void updateTodo(TodoEntity todo) {

    }

    @Override
    public List<TodoEntity> listTodos() {
        return null;
    }

    @Override
    public List<TodoEntity> listByUserName(String userName) {
        return null;
    }

    @Override
    public Optional<TodoEntity> getById(Long id) {
        return Optional.empty();
    }
}
