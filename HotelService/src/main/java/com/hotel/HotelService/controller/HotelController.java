package com.hotel.HotelService.controller;

import com.hotel.HotelService.entities.Hotel;
import com.hotel.HotelService.services.HotelServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    @Autowired
    private HotelServices hotelServices;

    //create


    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel)
    {


        return ResponseEntity.status(HttpStatus.CREATED).body(hotelServices.create(hotel));
    }
    @GetMapping({"/{hotelId}"})
    public ResponseEntity<Hotel> getHotelById(@PathVariable String hotelId)
    {


        return ResponseEntity.status(HttpStatus.CREATED).body(hotelServices.get(hotelId));
    }
    @GetMapping
    public ResponseEntity<List<Hotel>> getAll()
    {


        return ResponseEntity.status(HttpStatus.CREATED).body(hotelServices.getAll());
    }

}
