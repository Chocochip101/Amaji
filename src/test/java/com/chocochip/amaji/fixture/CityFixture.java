package com.chocochip.amaji.fixture;

import com.chocochip.amaji.city.domain.City;
import com.chocochip.amaji.global.util.CityEnum;
import com.chocochip.amaji.restaurant.domain.Restaurant;

public class CityFixture {
    private CityFixture(){}

    public static City createCityKyoto() {
        return City.builder()
                .name(CityEnum.KYOTO)
                .build();
    }

    public static City createCityOsaka() {
        return City.builder()
                .name(CityEnum.OSAKA)
                .build();
    }
}
