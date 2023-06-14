package com.example.demo.service;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.TodoEntity;
import com.example.demo.persistence.TodoRepository;

import lombok.extern.slf4j.Slf4j;

// 로그
@Slf4j
@Service
public class TodoService {
    @Autowired
    private TodoRepository repository;

    public String testService() {
        // TodoEntity 생성
        TodoEntity entity = TodoEntity.builder().title("My first todo item").build();
        System.out.println(entity);
        // TodoEntity 저장
        repository.save(entity);
        // TodoEntity 검색
        TodoEntity savedEntity = repository.findById(entity.getId()).get();
        System.out.println(savedEntity);
        return savedEntity.getTitle();
    }

    // crate 생성 
    public List<TodoEntity> create(final TodoEntity entity) {
        // validations
        // 데이터를 검증
        validate(entity);
        // 데이터를 저장
        repository.save(entity);
        // 저장후 로그
        log.info("Entity Id : {} is saved.", entity.getId());
        
        return repository.findByUserId(entity.getUserId());
    }
    // 검증
    private void validate(final TodoEntity entity) {
        if (entity == null) {
            log.warn("Entity cannot be null.");
            throw new RuntimeException("Entity cannot be null.");
        }
        if (entity.getUserId() == null) {
            log.warn("Unknown user.");
            throw new RuntimeException("Unknown user.");
        }
    }
    // 검색
    public List<TodoEntity> retrieve(final String userId){
        return repository.findByUserId(userId);
    }
}
