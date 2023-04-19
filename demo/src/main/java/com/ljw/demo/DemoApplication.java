package com.ljw.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = {"com.ljw"})
@MapperScan(basePackages = {"com.ljw.mapper"})
public class DemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(DemoApplication.class, args);
        String[] beannames = run.getBeanDefinitionNames();
        for (String name : beannames) {
            if (name.contains("SecurityConfig") || name.contains("User")) {
                System.out.println(name);
            }
        }
    }

}
