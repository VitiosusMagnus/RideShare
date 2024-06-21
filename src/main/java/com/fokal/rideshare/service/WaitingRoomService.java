package com.fokal.rideshare.service;

import com.fokal.rideshare.model.User;
import com.fokal.rideshare.model.WaitingRoom;
import com.fokal.rideshare.repository.WaitingRoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class WaitingRoomService {

    private final WaitingRoomRepository waitingRoomRepository;

    public void joinWaitingRoom(Long id, Long userId) {
       Optional<WaitingRoom> temp = waitingRoomRepository.findByRideId(id);
       if (temp.isEmpty()) {
           throw new RuntimeException("Waiting room not found for ride with id: " + id);
       }
         WaitingRoom waitingRoom = temp.get();
            User user = new User();
            user.setId(userId);
            waitingRoom.getUsers().add(user);
            waitingRoomRepository.save(waitingRoom);

    }

    public void createWaitingRoom(WaitingRoom waitingRoom) {
        waitingRoomRepository.save(waitingRoom);
    }

    public void leaveWaitingRoom(Long id, Long userId) {
        Optional<WaitingRoom> temp = waitingRoomRepository.findByRideId(id);
        if (temp.isEmpty()) {
            throw new RuntimeException("Waiting room not found for ride with id: " + id);
        }
        WaitingRoom waitingRoom = temp.get();
        waitingRoom.getUsers().removeIf(user -> user.getId().equals(userId));
        waitingRoomRepository.save(waitingRoom);
    }
    

    protected WaitingRoom getWaitingRoomByRideId(Long id) {
        return waitingRoomRepository.findByRideId(id).orElseThrow(() -> new RuntimeException("Waiting room not found for ride with id: " + id));
    }

    protected void saveWaitingRoom(WaitingRoom waitingRoom) {
        waitingRoomRepository.save(waitingRoom);
    }

    protected void deleteWaitingRoom(Long id) {
        waitingRoomRepository.deleteById(id);
    }

}
