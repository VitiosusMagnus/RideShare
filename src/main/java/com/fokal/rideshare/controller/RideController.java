package com.fokal.rideshare.controller;

import com.fokal.rideshare.dto.RideGetAllResponse;
import com.fokal.rideshare.service.RideService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/api/rides")
public class RideController {

        private final RideService rideService;

        @GetMapping("")
        public List<RideGetAllResponse> getAllRides() {
            return rideService.getAllRides();
        }
}
