package com.restaurant.store.Restaurant.service;

import com.restaurant.store.Restaurant.domain.Restaurant;
import com.restaurant.store.Restaurant.dto.WriteReq;
import com.restaurant.store.Restaurant.dto.WriteRes;
import com.restaurant.store.Restaurant.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WriteService {
    @Autowired
    RestaurantRepository restaurantRepository;
    public List<Restaurant> write(WriteReq writeReq){
        WriteRes writeRes = new WriteRes();
        List<Restaurant> restaurant = restaurantRepository.findAll();

        System.out.println(restaurant);

        return restaurant;
    }

}
