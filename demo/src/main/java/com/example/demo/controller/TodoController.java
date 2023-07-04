package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.TodoDTO;
import com.example.demo.model.TodoEntity;
import com.example.demo.service.TodoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("todo")
// 로그

public class TodoController {

    @Autowired
    private TodoService service;

    // @GetMapping("/test")
    // public ResponseEntity<?> testTodo() {
    // String str = service.testService();
    // List<String> list = new ArrayList<>();
    // list.add(str);
    // ResponseDTO<String> response =
    // ResponseDTO.<String>builder().data(list).build();
    // return ResponseEntity.ok().body(response);
    // }

    // 생성
    @PostMapping
    public ResponseEntity<?> createTodo(@AuthenticationPrincipal String userId, @RequestBody TodoDTO dto) {
        try {
            // TodoEntity로 변환
            TodoEntity entity = TodoDTO.toEntity(dto);
            // id를 null로 초기화, 초기 생성시 id값이 없어야되기떄문
            entity.setId(null);
            // 로그인 없이 사용할 계정
            entity.setUserId(userId);
            // 서비스를 이용하여 Todo 엔티티 생성
            List<TodoEntity> entities = service.create(entity);
            // 자바 스트림을 이용하여 리턴된 엔티티를 TodoDTO 리스트로 변환
            List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
            // 변환된 TodoDTO 리스트를 이용하여 ResponesDTO를 초기화
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
            // 초기화된 ResponesDTO를 리턴함
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            // 예외가 있는경우 dto 대신 error 메시지를 넣어 리턴
            String error = e.getMessage();
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    // 검색
    @GetMapping
    public ResponseEntity<?> retrieveTodoList(@AuthenticationPrincipal String userId) {
        // temporary user id
        // 서매스 메서드의 retrieve를 이용하여 Todo 리스트를 가져옴
        List<TodoEntity> entities = service.retrieve(userId);
        // 자바 스트림을 이용해 리턴된 엔티티 리스트를 TodoDTO 리스트로 변환
        List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
        // 변환된 TodoDTO 리스트를 이용해 ResponseDTO를 초기화한다
        ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
        // ResponseDTO를 리턴
        return ResponseEntity.ok(response);
    }
    /* post 로 json으로 값을 보낸후 생성된 값을 get으로 요청하여 리스트를 받는다. */

    // 수정
    @PutMapping
    public ResponseEntity<?> updateTodo(@AuthenticationPrincipal String userId, @RequestBody TodoDTO dto) {

        // dto를 entity로 변환 한다..
        TodoEntity entity = TodoDTO.toEntity(dto);
        // id를 temporaryUserId 로 초기화
        entity.setUserId(userId);
        // 서비스를 이용해 entity를 업데이트
        List<TodoEntity> entities = service.update(entity);
        // 자바 스트림을 통해 리턴된 엔ㄷ티티 리스트를 TodoDTO 리스트로 변환
        List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
        // ResponseDTO 리스트를 이용하여 ResopnseDTO를 초기화
        ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
        // ResponseDTO 리턴
        return ResponseEntity.ok(response);
    }

    // 삭제
    @DeleteMapping
    public ResponseEntity<?> deleteTodo(@AuthenticationPrincipal String userId, @RequestBody TodoDTO dto) {
        try {
            // TodoEntity로 변환
            TodoEntity entity = TodoDTO.toEntity(dto);

            entity.setUserId(userId);
            // 서비스를 이용해 entity를 삭제
            List<TodoEntity> entities = service.delete(entity);
            // 자바 스트림을 이용해 리턴된 엔티티 리스트를 TodoDTO리스트로 변환한다
            List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
            // 변환된 TOdoDTO 리스트를 이용해 ResponseDTO를 초기화 한다
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
            // ResponseDTO를 리턴
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // 혹시 예외가 있는 경우 dto 대신 error에 메시지를 넣어 리턴한다
            String error = e.getMessage();
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }

    }

}