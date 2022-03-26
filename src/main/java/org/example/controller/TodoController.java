package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.model.TodoEntity;
import org.example.model.TodoReq;
import org.example.model.TodoRes;
import org.example.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/")
public class TodoController {
    private  final TodoService service;

    @PostMapping
    public ResponseEntity<TodoRes> create(@RequestBody TodoReq request) {
        if(ObjectUtils.isEmpty(request.getTitle()))
            return ResponseEntity.badRequest().build();
        if(ObjectUtils.isEmpty(request.getOrder()))
            request.setOrder(0L);
        if(ObjectUtils.isEmpty(request.getCompleted()))
            request.setCompleted(false);
        TodoEntity result = this.service.add(request);
        return ResponseEntity.ok(new TodoRes(result));
    }

    @GetMapping("{id}")
    public ResponseEntity<TodoRes> readOne(@PathVariable Long id) {
        TodoEntity result = this.service.searchById(id);
        return ResponseEntity.ok(new TodoRes(result));
    }

    @GetMapping
    public ResponseEntity<List<TodoRes>> readAll() {
        List<TodoEntity> list = this.service.searchAll();
        List<TodoRes> response = list.stream().map(TodoRes::new).collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @PatchMapping("{id}")
    public ResponseEntity<TodoRes> update(@PathVariable Long id, @RequestBody TodoReq request) {
        TodoEntity result = this.service.updateById(id, request);
        return ResponseEntity.ok(new TodoRes(result));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteOne(@PathVariable Long id) {
        this.service.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAll() {
        this.service.deleteAll();
        return ResponseEntity.ok().build();
    }
}
