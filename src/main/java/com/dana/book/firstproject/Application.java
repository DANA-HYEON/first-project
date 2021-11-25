package com.dana.book.firstproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableJpaAuditing //JPA Auditing활성화
//스프링부트의 자동설정, 스프링 Bean읽기와 생성 모두 자동으로 설정
//SpringBootApplication이 있는 위치부터 설정을 읽어나가기 때문에 이 클래스는 항상 프로젝트의 최상단에 위치해야한다.
@SpringBootApplication
public class Application {
    public static void main(String[] args){
        //내장 WAS(Web Application Server)실행
        //->서버에 톰캣을 설치할 필요X, 스프링부트로 만들어진 Jar파일(실행 가능한 Java패키지 파일)로 실행하면 됨
        SpringApplication.run(Application.class, args);
    }
}
