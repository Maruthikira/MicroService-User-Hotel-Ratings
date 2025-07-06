package com.user.service.UserService.services.impl;

import com.user.service.UserService.entities.Hotel;
import com.user.service.UserService.entities.Rating;
import com.user.service.UserService.entities.User;
import com.user.service.UserService.exceptions.ResourceNotFoundException;
import com.user.service.UserService.external.services.HotelService;
import com.user.service.UserService.repositories.UserRepository;
import com.user.service.UserService.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {


    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private HotelService hotelService;

    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUSer() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {

        //getting data from the rating data base
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with ID " + userId + " not found"));

        Rating[] ratingOfTheUser = restTemplate.getForObject("http://RATINGSERVICE/ratings/user/" + user.getUserId(), Rating[].class);
        logger.info("Fetched ratings: {}", ratingOfTheUser);

        List<Rating> ratings = Arrays.stream(ratingOfTheUser).toList();
        List<Rating> ratingList = ratings.stream().map(rating -> {
         //   ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTELMICROSERVICE/hotels/" + rating.getHotelId(), Hotel.class);
            Hotel hotel= hotelService.getHotel(rating.getHotelId());
           // Hotel hotel = forEntity.getBody();
           // logger.info("Response status code: {}", forEntity.getStatusCode());
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);


        return user;
    }

}
