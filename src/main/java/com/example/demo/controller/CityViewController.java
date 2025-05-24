package com.example.demo.controller;

import com.example.demo.exception.CityNotFoundException;
import com.example.demo.model.CityInfo;
import com.example.demo.service.CityService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CityViewController {
    private final CityService cityService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("cities", cityService.getAllCities());
        return "index";
    }

    @SneakyThrows(CityNotFoundException.class)
    @GetMapping("/city/{name}")
    public String cityDetails(@PathVariable String name, Model model) {
        CityInfo city = cityService.getCityByName(name);
        if (city == null) throw new CityNotFoundException();
        model.addAttribute("city", city);
        return "city";
    }

    @GetMapping("/search")
    public String search(@RequestParam String query, Model model) {
        List<CityInfo> results;
        if (query.isEmpty()) {
            results = cityService.getAllCities();
        } else {
            results = cityService.searchCities(query);
        }
        model.addAttribute("cities", results);
        return "index";
    }

    @ExceptionHandler(CityNotFoundException.class)
    public String handleError() {
        return "error";
    }
}
