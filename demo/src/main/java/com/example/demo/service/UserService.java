package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    public UserEntity getByCredentials(final String username, final String password, final PasswordEncoder encoder) {
        final UserEntity originalUser = userRepository.findByUsername(username);
        // matches 메서드를 이용해 패스워드가 같은지 확인
        // matches-> BCryptPasswordEncoder는 같은 값을 인코딩 하더라고 할때마다 같이다르기 때문에 matchse() 메서드를
        // 이용하여 어떤 두값이 일치하는지 확인한다.
        if (originalUser != null &&
                encoder.matches(password,
                        originalUser.getPassword())) {
            return originalUser;
        }
        return null;
    }

}
