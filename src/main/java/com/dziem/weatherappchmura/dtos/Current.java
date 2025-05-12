package com.dziem.weatherappchmura.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Current {
    private double temp_c;
    private Condition condition;
    private double wind_kph;
    private double humidity;
    private double pressure_mb;
}