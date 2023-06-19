package com.hilmibaskoparan.service;

import com.hilmibaskoparan.model.entity.TodoEntity;
import com.hilmibaskoparan.model.repository.ITodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Log4j2

@Service
public class TodoServiceImpl implements ITodoService {

    private final ITodoRepository todoRepository;

    // CRUD OPERATIONS
    // CREATE - ADD
    @Transactional
    @Override
    public void addTodo(String userName, String description) {
        todoRepository.save(new TodoEntity(userName, description));
    }

    // DELETE BY ID
    @Transactional
    @Override
    public void deleteById(Long id) {
        Optional<TodoEntity> todoEntity = todoRepository.findById(id);
        if (todoEntity.isPresent()) {
            todoRepository.delete(todoEntity.get());
        }
    }

    // UPDATE BY ID
    @Transactional
    @Override
    public void updateById(TodoEntity todo) {
        todoRepository.save(todo);
    }

    // LIST
    @Override
    public List<TodoEntity> list() {
        Iterable<TodoEntity> todoEntities = todoRepository.findAll();
        List<TodoEntity> todoList = new ArrayList<>();
        for (TodoEntity todo : todoEntities) {
            todoList.add(todo);
        }
        return todoList;
    }

    // LIST BY USERNAME
    @Override
    public List<TodoEntity> listByUserName(String userName) {
        return todoRepository.findByUserName(userName);
    }

    // GET BY ID
    @Override
    public Optional<TodoEntity> getById(Long id) {
        return todoRepository.findById(id);
    }
}
