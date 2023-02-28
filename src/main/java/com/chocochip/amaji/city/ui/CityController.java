package com.chocochip.amaji.city.ui;

import com.chocochip.amaji.city.service.CityService;
import com.chocochip.amaji.city.ui.dto.CityResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cities")
public class CityController {

    private final CityService cityService;

    @GetMapping
    public ResponseEntity<List<CityResponse>> getAllCities(){
        List<CityResponse> cityResponseList = cityService.findAllCities();
        return ResponseEntity.status(HttpStatus.OK).body(cityResponseList);
    }


}
