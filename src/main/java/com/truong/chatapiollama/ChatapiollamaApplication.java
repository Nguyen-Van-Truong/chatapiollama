package com.truong.chatapiollama;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.truong.chatapiollama")
public class ChatapiollamaApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChatapiollamaApplication.class, args);
    }
}
