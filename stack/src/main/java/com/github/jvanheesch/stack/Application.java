package com.github.jvanheesch.stack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

@ServletComponentScan
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public FilterRegistrationBean<Filter1> filter1() {
        FilterRegistrationBean<Filter1> registration = new FilterRegistrationBean<>();
        registration.setFilter(new Filter1());
        registration.addUrlPatterns("/*");
        registration.setOrder(1);
        return registration;
    }

    @Bean
    public FilterRegistrationBean<Filter2> filter2() {
        FilterRegistrationBean<Filter2> registration = new FilterRegistrationBean<>();
        registration.setFilter(new Filter2());
        registration.addUrlPatterns("/*");
        registration.setOrder(2);
        return registration;
    }
}
