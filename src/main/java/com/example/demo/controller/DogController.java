//package com.example.demo.controller;
//
//import com.example.demo.service.DogImageService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//@Controller
//public class DogController {
//    private final DogImageService dogImageService;
//
//    public DogController(DogImageService dogImageService) {
//        this.dogImageService = dogImageService;
//    }
//
//    @GetMapping("/dog-card")
//    public String getDogCard(Model model) {
//        String randomDogImage = dogImageService.getRandomDogImage();
//        model.addAttribute("imageUrl", randomDogImage);
//        return "dog-card";
//    }
//}
