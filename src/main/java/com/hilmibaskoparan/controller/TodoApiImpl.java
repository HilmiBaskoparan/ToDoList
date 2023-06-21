package com.hilmibaskoparan.controller;

import com.hilmibaskoparan.model.entity.TodoEntity;
import com.hilmibaskoparan.service.ITodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Log4j2

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/v1/todo")
public class TodoApiImpl implements ITodoApi {

    private final ITodoService todoService;

    // localhost:4040/api/v1/todo/

    // CREATE - ADD
    /* The function receives a POST request, processes it, creates a new todoTask and
       saves it to the database, and returns a resource link to the created todoTask. */
    @Override
    @PostMapping("/add")
    public ResponseEntity<TodoEntity> addTodo(@RequestBody TodoEntity todo) {
        TodoEntity todoEntity = todoService.addTodo(todo);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("todo", "/api/v1/todo/" + todoEntity.getId().toString());
        return new ResponseEntity<>(todoEntity, httpHeaders, HttpStatus.CREATED);
    }

    // DELETE BY ID
    // The function receives a DELETE request, deletes the todoTask with the specified ID.
    @Override
    @DeleteMapping({"/delete/{id}"})
    public ResponseEntity<TodoEntity> deleteTodo(@PathVariable("id") Long id) {
        todoService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    // UPDATE BY ID
    // The function receives a PUT request, updates the todoTask with the specified Id and returns the updated todoTask
    @Override
    @PutMapping({"/update/{id}"})
    public ResponseEntity<TodoEntity> updateTodo(@PathVariable("id") Long id, @RequestBody TodoEntity todo) {
        todoService.updateById(id, todo);
        return new ResponseEntity<>(todoService.finById(id), HttpStatus.OK);
    }

    // LIST
    // The function receives a GET request, processes it and gives back a list of todoTask as a response.
    @Override
    @GetMapping("/list")
    public ResponseEntity<List<TodoEntity>> listTodos() {
        List<TodoEntity> todoList = todoService.list();
        return new ResponseEntity<>(todoList, HttpStatus.OK);
    }

    // LIST BY USERNAME
    // The function receives a GET request, processes it and gives back a list of todoTask with the specified username
    @Override
    @GetMapping("list/{username}")
    public ResponseEntity<List<TodoEntity>> listByUserName(@PathVariable("username") String username) {
        List<TodoEntity> todoList = todoService.listByUserName(username);
        return new ResponseEntity<>(todoList, HttpStatus.OK);
    }

    // FIND BY ID
    // The function receives a GET request, processes it, and gives back a todoTask as a response.
    @Override
    @GetMapping("/find/{id}")
    public ResponseEntity<TodoEntity> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(todoService.finById(id), HttpStatus.OK);
    }

    @GetMapping("/delete/all")
    @Override
    public ResponseEntity<String> deleteAll() {
        return ResponseEntity.ok(todoService.deleteAll());
    }
}
