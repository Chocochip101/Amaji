package com.chocochip.amaji.restaurant.service;

import com.chocochip.amaji.exception.AmajiException;
import com.chocochip.amaji.exception.ErrorType;
import com.chocochip.amaji.global.util.CityEnum;
import com.chocochip.amaji.member.domain.Member;
import com.chocochip.amaji.memberRestaurant.domain.MemberRestaurant;
import com.chocochip.amaji.restaurant.domain.Restaurant;
import com.chocochip.amaji.restaurant.domain.RestaurantPicture;
import com.chocochip.amaji.restaurant.domain.repository.RestaurantRepository;
import com.chocochip.amaji.restaurant.mapper.RestaurantMapper;
import com.chocochip.amaji.restaurant.ui.dto.RestaurantDetailResponse;
import com.chocochip.amaji.restaurant.ui.dto.RestaurantResponse;
import com.chocochip.amaji.review.domain.Review;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    public List<RestaurantResponse> findRestaurant(String cityName){
        if(cityName == null){
            List<Restaurant> restaurantList = restaurantRepository.findAll();
            return restaurantList.stream().map(RestaurantMapper.INSTANCE::restaurantEntityToResponse).collect(Collectors.toList());
        }

        List<Restaurant> restaurantList = restaurantRepository.findRestaurantByCityName(convertCityNameToEnum(cityName));
        return restaurantList.stream().map(RestaurantMapper.INSTANCE::restaurantEntityToResponse).collect(Collectors.toList());
    }

    public CityEnum convertCityNameToEnum(String cityName) throws AmajiException {
        String toUpperCase = cityName.toUpperCase();
        if(toUpperCase.equals("OSAKA")){
            return CityEnum.OSAKA;
        } else if (toUpperCase.equals("TOKYO")) {
            return CityEnum.TOKYO;
        }else if (toUpperCase.equals("FUKUOKA")) {
            return CityEnum.FUKUOKA;
        }else if (toUpperCase.equals("KYOTO")) {
            return CityEnum.KYOTO;
        }else if (toUpperCase.equals("NAGOYA")) {
            return CityEnum.NAGOYA;
        }else if (toUpperCase.equals("OKINAWA")) {
            return CityEnum.OKINAWA;
        }else if (toUpperCase.equals("SAPPORO")) {
            return CityEnum.SAPPORO;
        }
        throw new AmajiException(HttpStatus.BAD_REQUEST, ErrorType.CITY_NAME_ERROR);
    }

    public RestaurantDetailResponse findRestaurantDetail(Long restaurantId) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);

        return new RestaurantDetailResponse(
                restaurant.get().getName(),
                restaurant.get().getAddress(),
                restaurant.get().getLongitude(),
                restaurant.get().getLatitude(),
                restaurant.get().getRating(),
                restaurant.get().getRestaurantPictures().stream().map(RestaurantPicture::getUrl).collect(Collectors.toList()),
                restaurant.get().getReviewList().stream().map(Review::getUrl).collect(Collectors.toList()));
    }

}