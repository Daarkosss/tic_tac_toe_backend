package com.example.tic_tac_toe_backend.dto;


import com.example.tic_tac_toe_backend.entity.Room;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class RoomDTO {

    private String type = "Room";
    private String roomName;
    private int freeSlots;
    private PlayerDTO player1;
    private PlayerDTO player2;
    private List<List<Integer>> board;

    public static RoomDTO of(Room room) {
        return RoomDTO.builder()
                .type("Room")
                .roomName(room.getRoomName())
                .freeSlots(room.getFreeSlots())
                .player1(room.getPlayer1() != null ? PlayerDTO.of(room.getPlayer1()) : null)
                .player2(room.getPlayer2() != null ? PlayerDTO.of(room.getPlayer2()) : null)
                .board(room.getBoard())
                .build();
    }

}
