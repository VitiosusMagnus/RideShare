package com.fokal.rideshare.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "rides")
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Direction direction;
    private LocalDateTime departureTime;
    private int availableSeats;
    private int totalSeats;
    private int price;
    private boolean isActive;

    @ManyToOne()
    @JoinColumn(name = "driver_id")
    private User driver;
}
