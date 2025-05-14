package com.example.demo.service;

import com.example.demo.model.CityInfo;
import com.example.demo.model.CityTime;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CityService {
    private final List<CityInfo> cities = new ArrayList<>();

    @PostConstruct
    public void init() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(
                        getClass().getClassLoader().getResourceAsStream("cities.csv")
                ), StandardCharsets.UTF_8)
        )) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    cities.add(new CityInfo(
                            parts[0],
                            parts[1],
                            Double.parseDouble(parts[2]),
                            Double.parseDouble(parts[3]),
                            parts[4],
                            null,
                            null,
                            null
                    ));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<CityInfo> getAllCities() {
        return getCitiesWithTime(cities);
    }

    public CityInfo getCityByName(String name) {
        return cities.stream()
                .filter(c -> c.getName().equalsIgnoreCase(name))
                .map(this::getCityWithTime).findFirst().orElse(null);
    }

    public List<CityInfo> getCitiesByCountry(String country) {
        return cities.stream()
                .filter(c -> c.getCountry().equalsIgnoreCase(country))
                .map(this::getCityWithTime).toList();
    }

    public List<CityInfo> getCitiesByTimezone(String timezone) {
        return cities.stream()
                .filter(c -> c.getTimezone().equalsIgnoreCase(timezone))
                .map(this::getCityWithTime).toList();
    }

    public CityTime getCityTimeByName(String name) {
        CityInfo city = cities.stream()
                .filter(c -> c.getName().equalsIgnoreCase(name))
                .map(this::getCityWithTime).findFirst().orElse(null);
        return new CityTime(
                Objects.requireNonNull(city).getName(), city.getLocalTime(), city.getUtcTime()
        );
    }

    private List<CityInfo> getCitiesWithTime(List<CityInfo> cities) {
        List<CityInfo> citiesWithUpdatedTime = new ArrayList<>();
        for (CityInfo city : cities) {
            citiesWithUpdatedTime.add(getCityWithTime(city));
        }
        return citiesWithUpdatedTime;
    }

    private CityInfo getCityWithTime(CityInfo city) {
        try {
            ZonedDateTime currentCityTime = ZonedDateTime.now(ZoneId.of(city.getTimezone()));
            ZonedDateTime currentUtcTime = ZonedDateTime.now(ZoneId.of("UTC"));
            city.setLocalTime(currentCityTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            city.setUtcTime(currentUtcTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            int cityHour = currentCityTime.getHour();
            int utcHour = currentUtcTime.getHour();
            int timezonesHoursDifference = currentCityTime.minusHours(currentUtcTime.getHour()).getHour();
            if (cityHour < utcHour) {
                timezonesHoursDifference -= 24;
            }
            city.setTimeDescription(String.format(
                    "%s: %s (%s%d UTC)",
                    city.getName(),
                    currentCityTime.format(DateTimeFormatter.ofPattern("HH:mm")),
                    timezonesHoursDifference < 0 ? "" : "+",
                    timezonesHoursDifference
            ));
        } catch (Exception e) {
            city.setLocalTime("Unknown");
            city.setUtcTime("Unknown");
            city.setTimeDescription("Unknown");
        }
        return city;
    }
}
