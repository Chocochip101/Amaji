package com.chocochip.amaji.city.domain.repository;

import com.chocochip.amaji.city.domain.City;
import com.chocochip.amaji.city.domain.CityEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CityRepositoryTest {
    @Autowired
    CityRepository cityRepository;

    @BeforeEach
    void setup(){
        cityRepository.deleteAll();
    }

    @DisplayName("도시를 생성할 수 있다.")
    @Test
    public void createCity() throws Exception {
        //given
        CityEnum cityName = CityEnum.OSAKA;

        City city = City
                .builder()
                .name(cityName).build();

        City savedCity = cityRepository.save(city);

        //when
        Optional<City> optionalCity = cityRepository.findById(savedCity.getId());
        City result = optionalCity.get();


        //then
        Assertions.assertEquals(result.getId(), savedCity.getId());
        Assertions.assertEquals(result.getName(), cityName);

    }

    @DisplayName("id가 없는 City 엔티티를 저장하면 순차적으로 ID를 부여하여 저장한다.")
    @Test
    public void createCityWithNoId() throws Exception {
        //given
        CityEnum cityName1 = CityEnum.OSAKA;

        City city1 = City
                .builder()
                .name(cityName1).build();

        CityEnum cityName2 = CityEnum.FUKUOKA;

        City city2 = City
                .builder()
                .name(cityName2).build();

        //when
        City savedCity1 = cityRepository.save(city1);
        City savedCity2 = cityRepository.save(city2);

        //then
        long idDiff = city2.getId() - city1.getId();
        Assertions.assertEquals(idDiff, 1L);
    }
}