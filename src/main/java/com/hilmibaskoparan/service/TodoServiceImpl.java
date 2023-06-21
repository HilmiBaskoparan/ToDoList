package com.hilmibaskoparan.service;

import com.hilmibaskoparan.exception.BadRequestException;
import com.hilmibaskoparan.model.entity.TodoEntity;
import com.hilmibaskoparan.model.repository.ITodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Log4j2

@Service
public class TodoServiceImpl implements ITodoService {

    private final ITodoRepository todoRepository;

    // CRUD OPERATIONS
    // CREATE - ADD
    @Transactional
    @Override
    public TodoEntity addTodo(TodoEntity todo) {
        if (todo != null) {
            return todoRepository.save(todo);
        } else if (todo == null) {
            log.error("Null Todo");
            throw new BadRequestException("There is no Todo Task");
        }
        return todo;
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
    public TodoEntity updateById(Long id, TodoEntity todo) {
        TodoEntity todoFromDb = todoRepository.findById(id).get();
        //System.out.println(todoFromDb.toString());
        if (todoFromDb != null) {
            todoFromDb.setId(id);
            todoFromDb.setUserName(todo.getUserName());
            todoFromDb.setDescription(todo.getDescription());
            todoFromDb.setCreatedDate(todo.getCreatedDate());
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

    // LIST BY USERNAME
    @Override
    public List<TodoEntity> listByUserName(String userName) {
        if (userName == null || userName == "") {
            log.error("No USERNAME");
            throw new BadRequestException("No USERNAME");
        }
        return todoRepository.findByUserName(userName);
    }

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
}
