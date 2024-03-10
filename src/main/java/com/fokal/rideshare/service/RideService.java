package com.fokal.rideshare.service;

import com.fokal.rideshare.dto.RideGetAllResponse;
import com.fokal.rideshare.repository.RideRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class RideService {

    private final RideRepository rideRepository;
    private final ModelMapper modelMapper;


    public List<RideGetAllResponse> getAllRides() {
        return rideRepository.findAllByActiveIsTrue().stream()
                .map(ride -> modelMapper.map(ride, RideGetAllResponse.class))
                .toList();
    }
}
