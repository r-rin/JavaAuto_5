package com.github.rin.javaauto;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class WebApplication {

    public static void start(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

}
