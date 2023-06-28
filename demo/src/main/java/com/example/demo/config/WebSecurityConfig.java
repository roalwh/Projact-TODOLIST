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
        //https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
        //https://docs.spring.io/spring-security/reference/servlet/configuration/java.html
        //https://docs.spring.io/spring-security/reference/servlet/authentication/session-management.html
        http.cors((cors) -> cors
                .disable());
        http.csrf((csrf) -> csrf
                .disable());
        http.httpBasic((basic) -> basic
                .disable());
        http.sessionManagement((session) -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                .requestMatchers("/", "/auth/**")
                .permitAll().anyRequest().authenticated());

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
