package com.example.demo.service;

import java.util.List;
import java.util.Optional;

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

    // 업데이트
    public List<TodoEntity> update(final TodoEntity entity){
        // 저장할 엔티티가 유효한지 확인
        validate(entity);
        // 검증된 엔티티 id 를 이용하여 TodoEntity를 가져옴
        final Optional<TodoEntity> original = repository.findById(entity.getId());
        // 반환된 TodoEntity가 존재하면 값을 새 entity 값으로 덮어 씌움
        if(original.isPresent()){
            final TodoEntity todo = original.get();
            todo.setTitle(entity.getTitle());
            todo.setDone(entity.isDone());
            // 데이터베이스에 새 값을 저장
            repository.save(todo);
        }
        // Retrieve Todo에서 만든 메서드를 이용하여 모든 Todo 리스트를 호출
        return retrieve(entity.getUserId());
    }
    // 삭제
    public List<TodoEntity> delete(final TodoEntity entity){
        // 저장할 엔티티가 유효한지 확인한다
        validate(entity);
        try{
            // 엔티티를 삭제함
            repository.delete(entity);
        } catch(Exception e){
            // exception 발생시 id와exception을 로깅
            log.error("error deleting entity", entity.getId(),e);
            //컨트롤러로 exception을 보낸다. 데이터베이스 내부 로직을 캡슐화하려면 e를 리턴하지 않고 새 exception 오브젝트를 리턴
            throw new RuntimeException("error deleting entity"+ entity.getId());
        }
        // 새 Todo리스트를 가져와 리턴
        return retrieve(entity.getUserId());
    }
}
