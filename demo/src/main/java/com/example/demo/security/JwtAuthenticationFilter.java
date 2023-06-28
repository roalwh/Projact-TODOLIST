package com.example.demo.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private TokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            // 요청에서 토큰 가져오기
            String token = parseBearerToken(request);
            log.info("Filter is running....");

            // 토큰 검사, JWT이므로 인가 서버에 요청하지 않고도 검증가능
            if (token != null && !token.equalsIgnoreCase("null")) {
                // userId 가져오기, 위조된경우 예외처리됨(tokenProvider.java 파일 참고)
                String userId = tokenProvider.validateAndGetUserId(token);
                log.info("Authenticated user ID : " + userId);

                // 인증완료; SecurityContextHolder에 등록되야 인증된 사용자로 처리함
                AbstractAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        // userId-> 인증된 사용자의 정보, 문자열이 아니어도 아무거나 등록가능, 보통 UserDetails 오브젝트로 처리하나 책에서는 생략한다고함,
                        // AuthenticationPrincipal
                        userId,
                        null,
                        AuthorityUtils.NO_AUTHORITIES);

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                securityContext.setAuthentication(authentication);
                SecurityContextHolder.setContext(securityContext);
            }
        } catch (Exception ex) {
            logger.error("Coule not set user authentication in security context", ex);
        }
        filterChain.doFilter(request, response);
    }

    private String parseBearerToken(HttpServletRequest request) {
        // Http 요청의 헤더를 파싱하여 Bearer 토큰을 리턴시킨다.
        String bearerToken = request.getHeader("Authorization");
            // hasText(bearerToken) 널같은 값체크, startsWith("Bearer ") 시작을 Bearer로 시작하는지
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            // substring(7)  bearerToken 토큰의 앞 7자리 리턴
            return bearerToken.substring(7);
        }
        return null;
    }

}
