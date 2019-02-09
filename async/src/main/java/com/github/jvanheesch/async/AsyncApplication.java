package com.github.jvanheesch.async;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;

@ServletComponentScan
@SpringBootApplication
public class AsyncApplication {
    public static void main(String[] args) {
        SpringApplication.run(AsyncApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean<LogPostBodyFilter> postBodyFilter() {
        FilterRegistrationBean<LogPostBodyFilter> filterRegistrationBean = new FilterRegistrationBean<>(new LogPostBodyFilter());
        filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        filterRegistrationBean.setAsyncSupported(true);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<AsyncFilter> asyncFilter() {
        FilterRegistrationBean<AsyncFilter> filterRegistrationBean = new FilterRegistrationBean<>(new AsyncFilter());
        filterRegistrationBean.setOrder(Ordered.LOWEST_PRECEDENCE);
        filterRegistrationBean.setAsyncSupported(true);
        filterRegistrationBean.addUrlPatterns(AsyncFilter.URL_PATTERN_WITH_WILDCARD);
        return filterRegistrationBean;
    }
}
