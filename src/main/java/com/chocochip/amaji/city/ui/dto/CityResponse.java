package com.chocochip.amaji.city.ui.dto;


import com.chocochip.amaji.city.domain.CityEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CityResponse {
    private CityEnum city_name;
}
