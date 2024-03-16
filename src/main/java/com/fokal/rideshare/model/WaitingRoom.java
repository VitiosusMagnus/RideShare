package com.fokal.rideshare.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "waiting_rooms")
public class WaitingRoom {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne()
    @JoinColumn(name = "ride_id")
    private Ride ride;

    @ManyToMany()
    @JoinTable(
            name = "waiting_room_user",
            joinColumns = @JoinColumn(name = "waiting_room_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;


}

