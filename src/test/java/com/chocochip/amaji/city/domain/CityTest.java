package com.chocochip.amaji.city.domain;

import com.chocochip.amaji.global.util.CityEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class CityTest {
    @DisplayName("도시를 빌더 패턴을 활용하여 생성할 수 있다")
    @Test
    public void createCity() throws Exception {
        //given
        CityEnum cityName = CityEnum.OSAKA;

        //when
        City city = City.builder()
                .name(cityName).build();

        //then
        Assertions.assertEquals(city.getName(), cityName);
    }

}