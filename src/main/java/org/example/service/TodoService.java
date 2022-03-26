package org.example.service;

import lombok.AllArgsConstructor;
import org.example.model.TodoEntity;
import org.example.model.TodoReq;
import org.example.repository.TodoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoEntity add(TodoReq req) {
        TodoEntity todoEntity = new TodoEntity();
        TodoEntity entity = this.todoRepository.save(todoEntity);
        return null;
    }

    public TodoEntity searchById(Long id) {
        return this.todoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<TodoEntity> searchAll() {
        return this.todoRepository.findAll();
    }

    public TodoEntity updateById(Long id, TodoReq requests) {
        TodoEntity todoEntity = this.searchById(id);
        return null;
    }

    public void deleteById(Long id) {
        return;
    }

    public void deleteAll() {
        return;
    }
}
