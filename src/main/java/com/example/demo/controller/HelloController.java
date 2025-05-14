package com.example.demo.controller;

import com.example.demo.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
public class HelloController {
    @GetMapping("/hello")
    public String sayHello() {
        String message = "Привет, мир!";
        log.info("GET query of message: {}", message);
        return message;
    }

    @PostMapping("/echo")
    public Message echo(@RequestBody Message input) {
        log.info("POST query of message: {}", input);
        return input;
    }
}
