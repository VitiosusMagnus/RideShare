package com.fokal.rideshare.dto;

import com.fokal.rideshare.model.Direction;

public class RideGetAllResponse {
    private Long id;
    private Direction direction;
    private String departureTime;
    private int availableSeats;
    private int totalSeats;
    private int price;
    private UserGetResponse driver;
}
