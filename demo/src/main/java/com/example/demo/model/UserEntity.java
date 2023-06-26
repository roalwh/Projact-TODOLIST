package com.example.demo.model;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "username") })
public class UserEntity {
    // 기본키 설정
    // id 생성 기준값
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    // 유저에게 고유하게 부여되는 id
    private String id;

    // 컬럼에 널 비허용
    @Column(nullable = false)
    // 아이디로 사용될 유저이름 (이메일 or 문자열)
    private String username;
    // 패스워드
    // Oauth 로그인 때문에 null 허용
    private String password;
    // 어드민,일반사용자 구분
    private String role;
    // Oauth에서 사용할 유저 정보 제공자:github
    private String authProvider;

}
