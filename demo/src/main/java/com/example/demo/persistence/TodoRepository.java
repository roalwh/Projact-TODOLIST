package com.example.demo.persistence;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.TodoEntity;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity,String>{
    // select 
    // ?1은 메서드의 매개변수의 순서 위치다. 
    // @Query("select * from Todo t where t.userId=?1")
    //  findByUserId 스프링 데이터 JPA가 메서드 이름을 파싱해서
    //  SELECT * FROM TodoRepository WHERE userId = '{userId}'와 같은 쿼리를 작성해 실행
    //  메서드 이름은 쿼리, 매개변수는 쿼리의 where문에 들어갈 값을 의미
    List<TodoEntity> findByUserId(String userId);

    
}
