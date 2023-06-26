package com.example.demo.security;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.example.demo.model.UserEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service

public class TokenProvider {
    private static final String SECRET_KEY = "5DFD5AE7874FDEE2780848A70938647D162CAB8B5DFD5AE7874FDEE2780848A70938647D162CAB8B";
    byte[] keyBytes = SECRET_KEY.getBytes();
    Key key = Keys.hmacShaKeyFor(keyBytes);

    public String create(UserEntity userEntity) {
        // 기한 시작으로부터 1일로 설정
        Date expiryDate = Date.from(
                Instant.now().plus(1, ChronoUnit.DAYS));

        return Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS512)
                .setSubject(userEntity.getId())
                .setIssuer("demo app")
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .compact();
    }

    public String validateAndGetUserId(String token) {
        // parseClaimsJws 메서드가 Base 64로 디코딩 및 파싱.
        // 즉, 헤더와 페이로드를 setSigningKey로 넘어온 시크릿을 이용해 서명 후, token의 서명과 비교.
        // 위조되지 않았다면 페이로드(Claims) 리턴, 위조라면 예외를 날림
        // 그 중 우리는 userId가 필요하므로 getBody를 부른다.
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key).build()
                .parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

}
