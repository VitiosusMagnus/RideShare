package com.fokal.rideshare.controller;

import com.fokal.rideshare.service.RideService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@AllArgsConstructor
@RestController
@RequestMapping("/api/rides")
public class RideController {

        private final RideService rideService;

}
