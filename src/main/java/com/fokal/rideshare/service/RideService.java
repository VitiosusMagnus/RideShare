package com.fokal.rideshare.service;

import com.fokal.rideshare.repository.RideRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RideService {

    private final RideRepository rideRepository;
    private final ModelMapper modelMapper;


}
