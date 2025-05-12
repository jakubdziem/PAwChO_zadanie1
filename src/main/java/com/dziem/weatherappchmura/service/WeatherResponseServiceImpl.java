package com.dziem.weatherappchmura.service;

import com.dziem.weatherappchmura.dtos.WeatherResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WeatherResponseServiceImpl implements WeatherResponseService {
    private static final String API_KEY = System.getenv("WEATHER_API_KEY");
    private static final String BASE_URL = "https://api.weatherapi.com/v1/current.json";
    private final RestTemplate restTemplate = new RestTemplate();
    public Optional<WeatherResponse> getWeatherResponse(String country, String city) {
        String query = city + "," + country;

        String url = BASE_URL + "?key=" + API_KEY + "&q=" + query;
        try {
            WeatherResponse weatherResponse = restTemplate.getForObject(url, WeatherResponse.class);
            return Optional.of(weatherResponse);
        } catch(Exception e) {
            return Optional.empty();
        }
    }
}
