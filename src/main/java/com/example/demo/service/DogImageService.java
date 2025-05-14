//package com.example.demo.service;
//
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.Map;
//import java.util.Objects;
//
//@Service
//public class DogImageService {
//    private final RestTemplate restTemplate = new RestTemplate();
//
//    public String getRandomDogImage() {
//        String url = "https://dog.ceo/api/breeds/image/random";
//        Map<String, Object> response = restTemplate.getForObject(url, Map.class);
//        return (String) response.get("message");
//    }
//}
