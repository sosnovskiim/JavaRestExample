package com.example.demo.controller;

import com.example.demo.model.CityInfo;
import com.example.demo.model.CityTime;
import com.example.demo.service.CityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/cities")
@RequiredArgsConstructor
public class CityController {
    private final CityService cityService;

    @GetMapping
    public List<CityInfo> getAllCities() {
        log.info("GET query of all cities");
        return cityService.getAllCities();
    }

    @GetMapping("/{name}")
    public CityInfo getCityByName(@PathVariable String name) {
        log.info("GET query of city by name {}", name);
        return cityService.getCityByName(name);
    }

    @GetMapping("/countries/{country}")
    public List<CityInfo> getCitiesByCountry(@PathVariable String country) {
        log.info("GET query of cities by country: {}", country);
        return cityService.getCitiesByCountry(country);
    }

    @GetMapping("/timezones/{region}/{city}")
    public List<CityInfo> getCitiesByTimezone(@PathVariable String region, @PathVariable String city) {
        log.info("GET query of cities by timezone: {}/{}", region, city);
        return cityService.getCitiesByTimezone(String.format("%s/%s", region, city));
    }

    @GetMapping("/time/{name}")
    public CityTime getCityTimeByName(@PathVariable String name) {
        log.info("GET query of city time by name: {}", name);
        return cityService.getCityTimeByName(name);
    }
}
