package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ResponseDTO;

@RestController
@RequestMapping("test") // 리소스

public class TestController {
   // http://localhost:8080/test
   @GetMapping
   public String testController() {
      return "Hello world!!";
   }

   // http://localhost:8080/test/testGetMapping
   @GetMapping("/testGetMapping")
   public String testControllerWithPath() {
      return "hello world!! testGetMapping";
   }

   // PathVariable 를 이용한 매개변수 전달 id 에 매핑
   // http://localhost:8080/test/511515
   // (required = false)는 이 매개변수가 꼭 필요한 것은 아니라는 뜻
   @GetMapping("/{id}")
   public String testControllerWithPathVariables(@PathVariable(required = false) int id) {
      return "Heel World! ID : " + id;
   }

   // RequestParam id={id}와 같이 요청 매개변수로 넘어오는 값을 변수로 받는다
   // http://localhost:8080/test/testRequestParam?id=132123
   @GetMapping("/testRequestParam")
   public String testControllerRequestParam(@RequestParam(required = false) int id) {
      return "Heel World! ID : " + id;
   }

   //RequestBody
   //RequestBody는 보통 반환하고자 하는 리소스가 복잡할 때 사용
   //오브젝트처럼 복잡한 자료형을 통째로 요청에 보내고 싶은 경우
   //@RequestBody TestRequestBodyDTO testRequestBodyDTO는 RequestBody로 보내오는 
   //JSON을 TestRequestBodyDTO 오브젝트로 변환해 가져오라는 뜻
   
   @GetMapping("/testRequestBody")
   public ResponseDTO<String> testControllerResponsBody(){
      List<String> list = new ArrayList<>();
      list.add("Hello World! I'm ResponseDTO");
      ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
      return response;
   }

   @GetMapping("/testResponseEntity")
   public ResponseEntity<?> testControllerresponseEntity(){
      List<String> list = new ArrayList<>();
      list.add("Hello World! I'm ResponseEntity. And you got 400!");
      ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
      return ResponseEntity.badRequest().body(response);
   }
}