package com.wzebra.cjudge;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class CJudgeApplication {

    public static void main(String[] args) {
        SpringApplication.run(CJudgeApplication.class, args);
    }

}
