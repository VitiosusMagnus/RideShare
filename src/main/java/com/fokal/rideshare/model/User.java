package com.fokal.rideshare.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String password;
    private boolean isActive;

    //for rides
    @OneToMany(mappedBy = "driver", fetch = FetchType.LAZY)
    private List<Ride> owningRides;

    @ManyToMany(mappedBy = "passengers", fetch = FetchType.LAZY)
    private List<Ride> joinedRides;

    //for ride proxies
    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
    private List<WaitingRoom> waitingRooms;



    //security
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Role> roles;

    //for messages
    @OneToMany(mappedBy = "sender", fetch = FetchType.LAZY)
    private List<Message> sentMessages;

    @OneToMany(mappedBy = "receiver", fetch = FetchType.LAZY)
    private List<Message> receivedMessages;

    }
