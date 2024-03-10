package com.fokal.rideshare.controller;

import com.fokal.rideshare.dto.RideCreateRequest;
import com.fokal.rideshare.dto.RideGetAllResponse;
import com.fokal.rideshare.dto.RideGetResponse;
import com.fokal.rideshare.service.RideService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

        @GetMapping("{id}")
        public RideGetResponse getRideById(@PathVariable Long id) {
            return rideService.getRideById(id);
        }

        @PostMapping("")
        public RideGetResponse createRide(@RequestBody RideCreateRequest rideCreateRequest) {
            return rideService.createRide(rideCreateRequest);
        }
}
