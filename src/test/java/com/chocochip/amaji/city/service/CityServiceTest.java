package com.chocochip.amaji.city.service;

import com.chocochip.amaji.city.domain.City;
import com.chocochip.amaji.city.domain.repository.CityRepository;
import com.chocochip.amaji.city.ui.dto.CityResponse;
import com.chocochip.amaji.global.util.CityEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static com.chocochip.amaji.fixture.CityFixture.createCityKyoto;
import static com.chocochip.amaji.fixture.CityFixture.createCityOsaka;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CityServiceTest {

    @Autowired CityService cityService;
    @Autowired CityRepository cityRepository;

    private City cityOsaka;
    private City cityKyoto;

    @BeforeEach
    void setUp(){
        cityRepository.deleteAll();

        cityOsaka = createCityOsaka();
        cityRepository.save(cityOsaka);

        cityKyoto = createCityKyoto();
        cityRepository.save(cityKyoto);

    }


    @DisplayName("도시를 전부 조회한다.")
    @Test
    void getAllCities() {
        // given & when
        List<CityResponse> cityResponseList = cityService.findAllCities();

        // then
        Assertions.assertEquals(cityResponseList.size(), 2);
        Assertions.assertEquals(cityResponseList.get(0).getCity_name(), CityEnum.OSAKA);
        Assertions.assertEquals(cityResponseList.get(1).getCity_name(), CityEnum.KYOTO);
    }


}