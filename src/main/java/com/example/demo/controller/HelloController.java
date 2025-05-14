package com.example.demo.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
public class HelloController {
    @GetMapping("/hello")
    public String sayHello() {
        String message = "Привет, мир!";
        log.info("GET-запрос сообщения: {}", message);
        return message;
    }

    @PostMapping("/echo")
    public Message echo(@RequestBody Message input) {
        log.info("POST-запрос сообщения: {}", input);
        return input;
    }

    @Data
    @AllArgsConstructor
    static class Message {
        private String text;
    }
}
