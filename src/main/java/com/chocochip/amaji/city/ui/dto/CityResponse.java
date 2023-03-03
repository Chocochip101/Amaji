package com.chocochip.amaji.city.ui.dto;


import com.chocochip.amaji.global.util.CityEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CityResponse {
    private CityEnum city_name;
}
