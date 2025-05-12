package com.dziem.weatherappchmura.controller;

import com.dziem.weatherappchmura.dtos.WeatherResponse;
import com.dziem.weatherappchmura.service.WeatherResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final WeatherResponseService weatherResponseService;

    @GetMapping("/")
    public String getWeather(Model model,
                             @RequestParam(required = false, defaultValue = "UK") String country,
                             @RequestParam(required = false, defaultValue = "London") String city) {
        Optional<WeatherResponse> weatherResponse = weatherResponseService.getWeatherResponse(country, city);
        if(weatherResponse.isEmpty()) {
            model.addAttribute("badRequest", "Bad Request");
        }
        if(weatherResponse.isEmpty()) {
            model.addAttribute("weather", null);

        } else {
            model.addAttribute("weather", weatherResponse.get());
        }
        model.addAttribute("country", country);
        model.addAttribute("city", city);
        return "index";
    }



}
