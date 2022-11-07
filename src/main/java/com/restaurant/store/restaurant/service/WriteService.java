package com.restaurant.store.restaurant.service;

import com.restaurant.store.restaurant.domain.Restaurant;
import com.restaurant.store.restaurant.dto.WriteReq;
import com.restaurant.store.restaurant.dto.WriteRes;
import com.restaurant.store.restaurant.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
