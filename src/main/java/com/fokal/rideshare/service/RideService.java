package com.fokal.rideshare.service;

import com.fokal.rideshare.dto.RideCreateRequest;
import com.fokal.rideshare.dto.RideGetAllResponse;
import com.fokal.rideshare.dto.RideGetResponse;
import com.fokal.rideshare.model.Ride;
import com.fokal.rideshare.model.WaitingRoom;
import com.fokal.rideshare.repository.RideRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class RideService {

    private final RideRepository rideRepository;
    private final ModelMapper modelMapper;
    private final WaitingRoomService waitingRoomService;


    public List<RideGetAllResponse> getAllActiveRides() {
        return rideRepository.findAllByIsActiveTrue().stream()
                .map(ride -> modelMapper.map(ride, RideGetAllResponse.class))
                .toList();
    }

    public RideGetResponse getRideById(Long id) {
        Optional<Ride> ride = rideRepository.findById(id);
        if (ride.isPresent() && ride.get().isActive()) {
            return modelMapper.map(ride.get(), RideGetResponse.class);
        }
        throw new RuntimeException("Ride not found with id: " + id);
    }


    public RideGetResponse createRide(RideCreateRequest rideCreateRequest) {
        Ride ride = modelMapper.map(rideCreateRequest, Ride.class);
        ride.setActive(true);
        ride.setWaitingRoom(null);
        ride.setId(null);
        ride = rideRepository.save(ride);
        WaitingRoom waitingRoom = new WaitingRoom();
        waitingRoom.setRide(ride);
        waitingRoom.setId(null);
        waitingRoomService.createWaitingRoom(waitingRoom);
        return modelMapper.map(ride, RideGetResponse.class);
    }

    public void softDeleteRide(Long id) {
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

    public void joinWaitingRoom(Long id, Long userId) {
        waitingRoomService.joinWaitingRoom(id, userId);
    }

    public void leave(Long id, Long userId) {
        waitingRoomService.leaveWaitingRoom(id, userId);
        Optional<Ride> Ride = rideRepository.findById(id);
        if (Ride.isPresent()){
            Ride.get().getPassengers().removeIf(user -> user.getId().equals(userId));
        }
    }

    public void acceptRide(Long id, Long userId) {
        WaitingRoom waitingRoom = waitingRoomService.getWaitingRoomByRideId(id);
        if(waitingRoom.getUsers().removeIf(user -> user.getId().equals(userId))){
            waitingRoomService.saveWaitingRoom(waitingRoom);
        }

       }

    public void getWaitingRoomByRideId(Long id) {
        waitingRoomService.getWaitingRoomByRideId(id);
    }

    // every 10 minutes update active status of rides
    @Scheduled(fixedRate = 600000)
    private void updateRideStatus(){
        rideRepository.findAll().forEach(ride -> {
            if(ride.getDepartureTime().isBefore(LocalDateTime.now())){
                waitingRoomService.deleteWaitingRoom(ride.getWaitingRoom().getId());
                ride.setActive(false);
                rideRepository.save(ride);
            }
        });
    }
}
