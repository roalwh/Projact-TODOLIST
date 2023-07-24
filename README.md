# TodoList
스프링 부트 리액트
https://roalwhtodoapp.roalwh.pe.kr/


#책과다른 수정된부분
책에서는 http status 로 403 체크하여 로그인 여부를 파악하지만
최신 버전에서는 403을 바로 반환하지 않기때문에
로컬스토리지의 토큰을 기준으로 리다이렉션 조건을 적용함

#Oauth2 미적용
Oauth2 의 부분도 깃과 연동이긴하나 책의 내용으로 하기엔 이미 설정값이 변동된 부분이 있음
작가님 깃에도 확인해보았으나 책의 7장관련 예제는 없어진? 상태라 작업은 따로 하지않았습니다.




#사용버전
스프링 
	id 'org.springframework.boot' version '3.1.0'
	id 'io.spring.dependency-management' version '1.1.0'
 	implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
	implementation 'org.springframework.boot:spring-boot-starter-web'
	implementation 'org.springframework.boot:spring-boot-starter-security'
	compileOnly 'org.projectlombok:lombok'
	developmentOnly 'org.springframework.boot:spring-boot-devtools'
	runtimeOnly 'com.h2database:h2'
	annotationProcessor 'org.projectlombok:lombok'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
	// https://mvnrepository.com/artifact/com.google.guava/guava
	implementation group: 'com.google.guava', name: 'guava', version: '31.1-jre'
	// https://mvnrepository.com/artifact/org.projectlombok/lombok
	compileOnly group: 'org.projectlombok', name: 'lombok', version: '1.18.24'
	// https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-api
	implementation group: 'io.jsonwebtoken', name: 'jjwt-api', version: '0.11.2'
	// https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-jackson
	runtimeOnly group: 'io.jsonwebtoken', name: 'jjwt-jackson', version: '0.11.2'
	// https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-impl
	runtimeOnly group: 'io.jsonwebtoken', name: 'jjwt-impl', version: '0.11.2'
	// https://mvnrepository.com/artifact/mysql/mysql-connector-java
	implementation group: 'mysql', name: 'mysql-connector-java', version: '8.0.30'
	runtimeOnly 'mysql:mysql-connector-java'

리액트
{
  "name": "todo-react-app",
  "version": "0.1.0",
  "private": true,
  "engines": {
    "node": "18.16.0"
  },
  "dependencies": {
    "@emotion/react": "^11.11.1",
    "@emotion/styled": "^11.11.0",
    "@fontsource/roboto": "^5.0.3",
    "@mui/icons-material": "^5.11.16",
    "@mui/material": "^5.13.5",
    "@testing-library/jest-dom": "^5.16.5",
    "@testing-library/react": "^13.4.0",
    "@testing-library/user-event": "^13.5.0",
    "react": "^17.0.0 || ^18.0.0",
    "react-dom": "^17.0.0 || ^18.0.0",
    "react-router-dom": "^6.14.1",
    "react-scripts": "^5.0.1",
    "web-vitals": "^2.1.4"

  },
  "peerDependencies": {
    "react": "^17.0.0 || ^18.0.0",
    "react-dom": "^17.0.0 || ^18.0.0"
  }
}
 
