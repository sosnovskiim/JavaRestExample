package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityInfo {
    private String name;
    private String country;
    private double latitude;
    private double longitude;
    private String timezone;
    private String population;
    private String imageUrl;
    private String localTime;
    private String utcTime;
    private String timeDescription;
}
