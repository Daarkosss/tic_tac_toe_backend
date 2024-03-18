package com.example.tic_tac_toe_backend.repository;

import com.example.tic_tac_toe_backend.entity.Room;
import com.example.tic_tac_toe_backend.utils.exception.RoomNotFoundException;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Component
public class RoomRepository {

    private final List<Room> rooms;
    private int roomCounter = 1;

    public RoomRepository() {
        rooms = new ArrayList<>(List.of(new Room("room" + roomCounter++)));
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void removeRoom(Room room) {
        rooms.remove(room);
    }

    public Room getRoomByName(String roomName) {
        return rooms.stream()
                .filter(room -> room.getRoomName().equals(roomName))
                .findFirst()
                .orElseThrow(RoomNotFoundException::new);
    }

    public Optional<Room> getRoomWithOneFreeSlot() {
        return rooms.stream()
                .filter(room -> room.getFreeSlots() == 1)
                .findFirst();
    }

    public Optional<Room> getRoomWithTwoFreeSlots() {
        return rooms.stream()
                .filter(room -> room.getFreeSlots() == 2)
                .findFirst();
    }

    public void setRoomCounter(int roomCounter) {
        this.roomCounter = roomCounter;
    }

}
