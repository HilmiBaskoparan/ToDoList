package com.hilmibaskoparan.controller;

import com.hilmibaskoparan.model.entity.TodoEntity;
import com.hilmibaskoparan.service.ITodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@Log4j2

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/todo")
public class TodoApiImpl implements ITodoApi {

    private final ITodoService todoService;

    @Override
    public ResponseEntity<TodoEntity> addTodo(TodoEntity todo) {
        return null;
    }

    @Override
    public ResponseEntity<TodoEntity> deleteTodo(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<TodoEntity> updateTodo(Long id, TodoEntity todo) {
        return null;
    }

    @Override
    public ResponseEntity<List<TodoEntity>> listTodos() {
        return null;
    }

    @Override
    public ResponseEntity<List<TodoEntity>> listByUserName(String userName) {
        return null;
    }

    @Override
    public ResponseEntity<TodoEntity> findById(Long id) {
        return null;
    }
}
