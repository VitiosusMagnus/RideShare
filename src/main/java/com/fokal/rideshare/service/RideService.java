package com.fokal.rideshare.service;

import com.fokal.rideshare.dto.RideCreateRequest;
import com.fokal.rideshare.dto.RideGetAllResponse;
import com.fokal.rideshare.dto.RideGetResponse;
import com.fokal.rideshare.model.Ride;
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
        return rideRepository.findAllByIsActiveTrue().stream()
                .map(ride -> modelMapper.map(ride, RideGetAllResponse.class))
                .toList();
    }

    public RideGetResponse getRideById(Long id) {
        return modelMapper.map(rideRepository.findById(id).orElseThrow(), RideGetResponse.class);
    }


    public RideGetResponse createRide(RideCreateRequest rideCreateRequest) {
        return modelMapper.map(rideRepository.save(modelMapper.map(rideCreateRequest, Ride.class)), RideGetResponse.class);
    }

    public void deleteRide(Long id) {
        rideRepository.findById(id).ifPresent(ride -> {
            ride.setActive(false);
            rideRepository.save(ride);
        });
    }


    public void updateRide(Long id, RideCreateRequest rideCreateRequest) {
        rideRepository.findById(id).ifPresent(ride -> {
            rideRepository.save(modelMapper.map(rideCreateRequest,Ride.class));
        });
    }
}
