package com.chocochip.amaji.city.domain.repository;

import com.chocochip.amaji.city.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long>, CityRepositoryCustom{
}
