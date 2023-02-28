package com.chocochip.amaji.menu.mapper;

import com.chocochip.amaji.menu.domain.Menu;
import com.chocochip.amaji.menu.ui.dto.MenuResponse;
import com.chocochip.amaji.restaurant.domain.Restaurant;
import com.chocochip.amaji.restaurant.mapper.RestaurantMapper;
import com.chocochip.amaji.restaurant.ui.dto.RestaurantResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public class MenuMapper {
    MenuMapper INSTANCE = Mappers.getMapper(MenuMapper.class);
    
}
