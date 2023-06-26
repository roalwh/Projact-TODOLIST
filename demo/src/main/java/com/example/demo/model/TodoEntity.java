package com.example.demo.model;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
// 데이터 베이스에 매핑
// Table을 추가하지 않거나 Entity에 이름을 지정 하지않은 경우 
// 자동으로 클래스의 이름을 테이블 이름으로 간주
@Table(name = "Todo")
public class TodoEntity {
    // 테이블의 기본키가 될 필드에 @Id 지정 
    // 커스텀 Generator 
    // id 자동생성
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid",strategy = "uuid")
    private String id; // 이 오브젝트의 아이디
    private String userId; // 이 오브젝트를 생성한 사용자의 아이디
    private String title; // Todo 타이틀(예: 운동하기)
    private boolean done; // true - todo를 완료한 경우(checked)
}