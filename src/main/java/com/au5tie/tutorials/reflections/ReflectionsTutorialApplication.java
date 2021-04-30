package com.au5tie.tutorials.reflections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
public class ReflectionsTutorialApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReflectionsTutorialApplication.class, args);
    }

    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("America/New_York"));
    }
}
