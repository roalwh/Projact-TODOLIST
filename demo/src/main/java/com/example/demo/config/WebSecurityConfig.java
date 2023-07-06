package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.CorsFilter;

import com.example.demo.security.JwtAuthenticationFilter;

import lombok.extern.slf4j.Slf4j;

@EnableWebSecurity
@Configuration
@Slf4j
// WebSecurityConfigurerAdapter 더이상 사용 안됨
public class WebSecurityConfig {

        @Autowired
        private JwtAuthenticationFilter jdJwtAuthenticationFilter;

        @Bean
        protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                // https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
                // https://docs.spring.io/spring-security/reference/servlet/configuration/java.html
                // https://docs.spring.io/spring-security/reference/servlet/authentication/session-management.html

                // WebMvcConfig에서 이미 설정했으므로 기본 cors 설정.
                http.cors((cors) -> cors
                                .disable());
                // csrf는 현재 사용하지 않으므로 disable
                http.csrf((csrf) -> csrf
                                .disable());
                // token을 사용하므로 basic 인증 disable
                http.httpBasic((basic) -> basic
                                .disable());
                // session 기반이 아님을 선언
                http.sessionManagement((session) -> session
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
                // /와 /auth/** 경로는 인증 안해도 됨.
                http.authorizeHttpRequests((authorize) -> authorize
                                .requestMatchers("/", "/auth/**", "/error","/todo").permitAll()
                                // /와 /auth/**이외의 모든 경로는 인증 해야됨.
                                .anyRequest().authenticated());

                // http.cors().and().csrf().disable()
                // .httpBasic().disable()
                // .sessionManagement()
                // .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                // .and()
                // .authorizeRequests()
                // .antMatchers("/", "/auth/**").permitAll()
                // .anyRequest()
                // .authenticated();

                http.addFilterAfter(
                                jdJwtAuthenticationFilter,
                                CorsFilter.class);
                return http.build();
        }

}
