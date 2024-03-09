package com.fokal.rideshare.repository;

import com.fokal.rideshare.model.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RideRepository extends JpaRepository<Ride, Long> {
}
