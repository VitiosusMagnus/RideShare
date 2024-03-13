package com.fokal.rideshare.repository;

import com.fokal.rideshare.model.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RideRepository extends JpaRepository<Ride, Long> {
    List<Ride> findAllByActiveIsTrue();
}
