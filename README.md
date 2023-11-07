## TodoList 웹 애플리케이션

-   Github : [Projact-TODOLIST](https://github.com/roalwh/Projact-TODOLIST)
-   배포 페이지 : [TodoList 배포 페이지](http://ec2-43-202-189-205.ap-northeast-2.compute.amazonaws.com)

### 1\. 개요

-   프로젝트 명 : TodoList 웹 애플리케이션
-   개발 인원 : 1명
-   개발 기간 : 2023.06.14 ~ 2023.07.20
-   주요 기능 :
    -   메인페이지 : CRUD 기능
    -   사용자 : Spring Security를 이용한 회원가입 및 로그인 기능
-   개발 언어 : Java17
-   개발환경 : Spring Boot 3.1.0, Gradle, JPA, Spring Security, Javascript, React, node.js
-   데이터베이스 : MariaDB
-   간단 소개 : 간단한 TodoList 웹 애플리케이션을 만들고 AWS을 이용하여 배포한다.
-   참고자료 : React.js, 스프링 부트, AWS로 배우는 웹 개발 101 - 김다정 저

### 2\. 요구사항 분석

#### 2-1.메인 페이지

-   Todo 생성, 리스트, 수정,삭제 기능

#### 2-2.로그인 페이지

-   로그인 기능 - 이메일 로그인
-   로그아웃 기능
-   메인 페이지로 접근하기 위해선 로그인을 해야함.
-   로그인 실패시 '로그인 실패' 문구만 표시

#### 2-3.회원 가입 페이지

-   회원가입 기능 - 이메일, 아이디, 패스워드
-   중복 아이디 확인 중복 시 '사용중인 이메일입니다.'표시
-   계정 생성 후 로그인 화면으로 리다이렉트

### 3.DB 설계

![image](https://github.com/roalwh/Projact-TODOLIST/assets/83705507/b84430ca-14d5-4b6e-a954-a6a5eac29879)


### 4.API 설계

#### 4-1 Todo API

| 기능 | Method | URL |
| --- | --- | --- |
| TodoList 저장 | POST | /todo |
| TodoList 조회 | GET | /todo |
| TodoList 수정 | PUT | /todo |
| TodoList 삭제 | DELTE | /todo |

#### 4-2 User API

| 기능 | Method | URL |
| --- | --- | --- |
| 회원가입 | POST | /signup |
| 로그인 | POST | /signin |

### 5\. 화면설계

#### 5-1. 회원가입

![image](https://github.com/roalwh/Projact-TODOLIST/assets/83705507/0638bc8f-265c-4692-bd67-6805bc69291c)


-   회원 가입 페이지 및 사용중인 이메일 알

#### 5-2. 로그인

![image](https://github.com/roalwh/Projact-TODOLIST/assets/83705507/b4750829-dff9-471c-8648-665de746b9ea)


-   기본 로그인 화면 및 로그인 실패 (없는 아이디, 패스워드 불일치) 화면

#### 5-3. 메인화면

![image](https://github.com/roalwh/Projact-TODOLIST/assets/83705507/894bef94-fb16-4142-983d-23d2c29c6866)


-   상단의 입력칸에서 Todo 값을 입력 하면 등록되면서 아래의 리스트에 추가된다.

### 6\. 개발내용

-   [TodoList 프로젝트 - Todo메인 API](https://roalwh.tistory.com/32)
-   [TodoList 프로젝트 - User 로그인/회원가입 API](https://roalwh.tistory.com/33)
-   [TodoList 프로젝트 - Spring Security, JWT 설정](https://roalwh.tistory.com/36)
-   [TodoList 프로젝트 - AWS 배포](https://roalwh.tistory.com/35)

### 7\. 후기

부족한 부분은 책을 참고하여 진행한 사이드 프로젝트입니다.  
Spring Security 나 AWS 배포는 책이 아닌 공식문서를 참고하여 진행하였고 백엔드, 프론트, AWS를 통한 배포를 통해 전반적인 프로세스를 파락하기 좋은 경험이 였던것 같습니다.  
작성중에 막히거나 웹브라우저 버전 업으로 인한 리다이렉트 기능 처리를 못하는 부분이 있었는데 세션값으로 로그인을 하지않으면 자동으로 로그인 창으로 넘어가게끔 기능을 대체하거나 AWS 의경우 서버 선택 부분이 달라진부분이 좀있어 해매기도 하였지만 잘 해결하여 배포를 끝마칠수 있었습니다.
