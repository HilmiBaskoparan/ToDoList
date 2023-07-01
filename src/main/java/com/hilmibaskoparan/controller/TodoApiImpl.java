package com.hilmibaskoparan.controller;

import com.hilmibaskoparan.model.Request.TodoAddRequest;
import com.hilmibaskoparan.model.Request.TodoUpdateRequest;
import com.hilmibaskoparan.model.entity.TodoEntity;
import com.hilmibaskoparan.service.ITodoService;
import com.hilmibaskoparan.util.FrontendURL;
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
@CrossOrigin(origins = FrontendURL.FRONTEND_URL)
@RequestMapping("/api/v1/todo")
public class TodoApiImpl implements ITodoApi {

    private final ITodoService todoService;

    // localhost:4040/api/v1/todo/

    /* The function receives a POST request, processes it, creates a new todoTask and
       saves it to the database, and returns a resource link to the created todoTask. */
    @Override
    @PostMapping("/add")
    public ResponseEntity<TodoEntity> addTodo(@RequestBody TodoAddRequest request) {
        TodoEntity todoEntity = todoService.addTodo(request);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("todo", "/api/v1/todo/" + todoEntity.getId().toString());
        return new ResponseEntity<>(todoEntity, httpHeaders, HttpStatus.CREATED);
    }

    // The function receives a DELETE request, deletes the todoTask with the specified ID.
    @Override
    @DeleteMapping({"/delete/{id}"})
    public ResponseEntity<TodoEntity> deleteTodo(@PathVariable("id") Long id) {
        return new ResponseEntity<>(todoService.deleteById(id),HttpStatus.OK);
    }

    // The function receives a PUT request, updates the todoTask with the specified Id and returns the updated todoTask
    @Override
    @PutMapping({"/update/{id}"})
    public ResponseEntity<TodoEntity> updateTodo(@PathVariable("id") Long id, @RequestBody TodoUpdateRequest request) {
        TodoEntity todoEntity = todoService.updateById(id, request);
        return new ResponseEntity<>(todoEntity, HttpStatus.OK);
    }

    // The function receives a GET request, processes it and gives back a list of todoTask as a response.
    @Override
    @GetMapping("/list")
    public ResponseEntity<List<TodoEntity>> listTodos() {
        List<TodoEntity> todoList = todoService.list();
        return new ResponseEntity<>(todoList, HttpStatus.OK);
    }

    // The function receives a GET request, processes it, and gives back a todoTask as a response.
    @Override
    @GetMapping("/find/{id}")
    public ResponseEntity<TodoEntity> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(todoService.finById(id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/all")
    @Override
    public ResponseEntity<String> deleteAll() {
        return ResponseEntity.ok(todoService.deleteAll());
    }

    @Override
    @GetMapping("/list/completed")
    public ResponseEntity<List<TodoEntity>> listCompletedTasks() {
        List<TodoEntity> todoList = todoService.listCompleted();
        return new ResponseEntity<>(todoList, HttpStatus.OK);
    }

    @Override
    @GetMapping("/list/uncompleted")
    public ResponseEntity<List<TodoEntity>> listUncompletedTasks() {
        List<TodoEntity> todoList = todoService.listUncompleted();
        return new ResponseEntity<>(todoList, HttpStatus.OK);
    }
    @Override
    @DeleteMapping("/delete/completed")
    public ResponseEntity<String> deleteDoneTasks() {
        return ResponseEntity.ok(todoService.deleteCompletedTodos());
    }

    /* LIST BY USERNAME
       The function receives a GET request, processes it and gives back a list of todoTask with the specified username
    @Override
    @GetMapping("list/{username}")
    public ResponseEntity<List<TodoEntity>> listByUserName(@PathVariable("username") String username) {
        List<TodoEntity> todoList = todoService.listByUserName(username);
        return new ResponseEntity<>(todoList, HttpStatus.OK);
    }*/

    /*// ADDING 10 RANDOM DATA FOR TEST
    @GetMapping("/speed/data")
    @Override
    public ResponseEntity<List<TodoEntity>> addSpeedData() {
        return ResponseEntity.ok(todoService.addSpeedData());
    }*/
}
