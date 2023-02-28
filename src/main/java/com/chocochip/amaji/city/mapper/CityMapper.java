package com.chocochip.amaji.city.mapper;

import com.chocochip.amaji.city.domain.City;
import com.chocochip.amaji.city.ui.dto.CityResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CityMapper {
    CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);

    @Mapping(target = "city_name", source = "name")
    CityResponse cityEntityToResponse(City city);
}
