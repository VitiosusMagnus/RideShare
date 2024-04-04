package com.fokal.rideshare.repository;

import com.fokal.rideshare.model.WaitingRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WaitingRoomRepository  extends JpaRepository<WaitingRoom, Long> {


    Optional<WaitingRoom> findByRideId(Long id);
}
