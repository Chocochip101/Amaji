package com.chocochip.amaji.city.service;

import com.chocochip.amaji.city.domain.City;
import com.chocochip.amaji.city.domain.repository.CityRepository;
import com.chocochip.amaji.city.mapper.CityMapper;
import com.chocochip.amaji.city.ui.dto.CityResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class CityService {

    private final CityRepository cityRepository;

    public List<CityResponse> findAllCities(){
        List<City> cityList = cityRepository.findAll();
        return cityList.stream().map(CityMapper.INSTANCE::cityEntityToResponse).collect(Collectors.toList());
    }
}
