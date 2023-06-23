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

    @Transactional
    @Override
    public TodoEntity deleteById(Long id) {

        TodoEntity existTodo = finById(id);
        if (Objects.nonNull(existTodo)) {
            todoRepository.deleteById(id);
            return existTodo;
        } else {
            log.error("Todo is not found");
            throw new BadRequestException("Todo is not found");
        }

        /*Optional<TodoEntity> todoEntity = todoRepository.findById(id);
        if (todoEntity.isPresent()) {
            todoRepository.delete(todoEntity.get());
            return todoEntity.get();
        } else {
            throw new ResourceNotFoundException("There is no Todo");
        }*/
    }

    @Transactional
    @Override
    public TodoEntity updateById(Long id, TodoUpdateRequest request) {
        TodoEntity todoFromDb = todoRepository.findById(id).get();
        if (Objects.isNull(todoFromDb)) {
            log.error("Todo not found.");
            throw new BadRequestException("Todo is not found.");
        }

        if (Objects.nonNull(request.getDescription())) {
            todoFromDb.setDescription(request.getDescription());
        }

        if (Objects.nonNull(request.getIsDone())) {
            todoFromDb.setIsDone(request.getIsDone());
        }
        return todoRepository.save(todoFromDb);
    }

    @Override
    public List<TodoEntity> list() {
        /*
        // ########## FIRST WAY ##########
        Iterable<TodoEntity> todoEntities = todoRepository.findAll();
        List<TodoEntity> todoList = new ArrayList<>();
        for (TodoEntity todo : todoEntities) {
            todoList.add(todo);
        }
        return todoList;

        // ########## SECOND WAY ##########
        List<TodoEntity> todoList = new ArrayList<>();
        todoRepository.findAll().forEach(todoList::add);
        return todoList;*/

        return todoRepository.findAllTodos();
    }

    @Override
    public TodoEntity finById(Long id) {
        /*TodoEntity todoTask = null;
            todoTask = todoRepository.findById(id)
                    .orElseThrow(() -> new BadRequestException("There is no ID Number " + id));*/
        return todoRepository.findById(id).orElse(null);
    }

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
