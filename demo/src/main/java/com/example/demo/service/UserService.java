package com.example.demo.service;

import java.io.Console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.UserEntity;
import com.example.demo.persistence.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity create(final UserEntity userEntity) {
        // unll 체크
        if (userEntity == null || userEntity.getUsername() == null) {
            throw new RuntimeException("Invalid arguments");
        }
        final String username = userEntity.getUsername();
        
        // 기존 아이디값 체크
        if (userRepository.existsByUsername(username)) {
            log.warn("Username alreay exists {}", username);
            throw new RuntimeException("Username already exits");
        }
        return userRepository.save(userEntity);
    }
    // 아이디 패스워드 찾기
    public UserEntity getByCredentials(final String username, final String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }
}
