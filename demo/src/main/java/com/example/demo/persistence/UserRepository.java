package com.example.demo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

	//이메일 찾기
	UserEntity findByEmail(String email);
	//이메일 값이 있는지 검사
	Boolean existsByEmail(String email);
	//이메일and패스워드 찾기
	UserEntity findByEmailAndPassword(String email, String password);

}
