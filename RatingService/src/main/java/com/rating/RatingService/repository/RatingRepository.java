package com.rating.RatingService.repository;

import com.rating.RatingService.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating,String> {

    //custom methods
      List<Rating> findByUserId(String userId);
    List<Rating> findByHotelId(String hotelId);



}
