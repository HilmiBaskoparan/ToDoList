package com.hilmibaskoparan.service;

import com.hilmibaskoparan.exception.BadRequestException;
import com.hilmibaskoparan.model.Request.TodoAddRequest;
import com.hilmibaskoparan.model.Request.TodoUpdateRequest;
import com.hilmibaskoparan.model.entity.TodoEntity;
import com.hilmibaskoparan.model.repository.ITodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@RequiredArgsConstructor
@Log4j2

@Service
public class TodoServiceImpl implements ITodoService {

    private final ITodoRepository todoRepository;

    // CRUD OPERATIONS
    // CREATE - ADD
    @Transactional
    @Override
    public TodoEntity addTodo(TodoAddRequest todoRequest) {
        TodoEntity entityTodo = null;
        if (Objects.nonNull(todoRequest) && Objects.nonNull(todoRequest.getDescription())) {
            entityTodo = new TodoEntity(todoRequest.getDescription(), false);
            return todoRepository.save(entityTodo);
        } else {
            log.error("Todo or Description is null.");
            throw new BadRequestException("The task is not added.");
        }
    }

    // DELETE
    @Transactional
    @Override
    public TodoEntity deleteById(Long id) {

        /*Optional<TodoEntity> todoEntity = todoRepository.findById(id);
        if (todoEntity.isPresent()) {
            todoRepository.delete(todoEntity.get());
        } else {
            throw new ResourceNotFoundException("There is no Todo");
        }*/

        TodoEntity findTodo = null;
        if (id != null) {
            findTodo = finById(id);
            todoRepository.deleteById(id);
        } else if (id == null) {
            log.error("Null Todo ID");
            throw new BadRequestException("There is no Todo ID");
        }
        //return todoEntity.get();
        return findTodo;
    }

    // UPDATE
    @Transactional
    @Override
    public TodoEntity updateById(Long id, TodoUpdateRequest request) {
        TodoEntity todoFromDb = todoRepository.findById(id).get();
        //System.out.println(todoFromDb.toString());
        if (todoFromDb != null) {
            todoFromDb.setDescription(request.getDescription());
            todoFromDb.setIsDone(request.getIsDone());
            todoFromDb.setCreatedDate(new Date(System.currentTimeMillis()));
            todoRepository.save(todoFromDb);
        }
        return todoFromDb;
    }

    // LIST
    @Override
    public List<TodoEntity> list() {
        Iterable<TodoEntity> todoEntities = todoRepository.findAll();
        List<TodoEntity> todoList = new ArrayList<>();
        for (TodoEntity todo : todoEntities) {
            todoList.add(todo);
        }
        //todoRepository.findAll().forEach(todoList::add);
        return todoList;
    }

    /* LIST BY USERNAME
    @Override
    public List<TodoEntity> listByUserName(String userName) {
        if (userName == null || userName == "") {
            log.error("No USERNAME");
            throw new BadRequestException("No USERNAME");
        }
        return todoRepository.findByUserName(userName);
    }*/

    // FIND BY ID
    @Override
    public TodoEntity finById(Long id) {
        TodoEntity todo = null;
        if (id != null) {
            todo = todoRepository.findById(id)
                    .orElseThrow(() -> new BadRequestException("There is no ID Number " + id));
        } else if (id == null) {
            log.error(id + " Task is NULL");
            throw new BadRequestException(id + " Task is NULL");
        }
        //return todoRepository.findById(id).get();
        return todo;
    }

    // DELETE ALL
    @Override
    public String deleteAll() {
        todoRepository.deleteAll();
        log.info("All Todo Tasks are deleted.");
        return "All Todo Tasks are deleted.";
    }

    /* ADDING 10 RANDOM DATA FOR TEST
    @Override
    public List<TodoEntity> addSpeedData() {
        List<TodoEntity> list = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            TodoEntity todo = TodoEntity.builder()
                    .description("That is description " + (int) Math.floor(Math.random() * 100))
                    .build();
            addTodo(todo);
            list.add(todo);
        }
        return list;
    }*/
}
