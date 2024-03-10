package com.fokal.rideshare.dto;

import com.fokal.rideshare.model.Direction;

import java.time.LocalDateTime;

public class RideGetResponse {
    private Long id;
    private Direction direction;
    private LocalDateTime departureTime;
    private int availableSeats;
    private int totalSeats;
    private int price;
    private UserGetResponse driver;
}
