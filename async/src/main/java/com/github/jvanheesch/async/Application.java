package com.github.jvanheesch.async;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class Application {
    // http://localhost:8080/example/
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
