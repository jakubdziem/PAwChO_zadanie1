package com.dziem.weatherappchmura.service;

import com.dziem.weatherappchmura.dtos.WeatherResponse;

import java.util.Optional;

public interface WeatherResponseService {
    Optional<WeatherResponse> getWeatherResponse(String country, String city);
}
