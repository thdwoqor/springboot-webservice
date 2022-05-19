package com.example.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // 감사 -> 최소 1개 엔티티가 있어야함
@SpringBootApplication
public class Application{
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}
//https://velog.io/@linger0310/DDD