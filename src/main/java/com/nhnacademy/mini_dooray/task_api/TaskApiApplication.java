package com.nhnacademy.mini_dooray.task_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.nhnacademy.mini_dooray.task_api")
@EnableJpaRepositories(basePackages = "com.nhnacademy.mini_dooray.task_api")
public class TaskApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskApiApplication.class, args);
    }

}
