package com.railwaycrossing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class RailwaycrossingApplication {

    public static void main(String[] args) {
        SpringApplication.run(RailwaycrossingApplication.class, args);
    }

}
