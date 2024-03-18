package com.example.tic_tac_toe_backend.controller;

import com.example.tic_tac_toe_backend.dto.RoomDTO;
import com.example.tic_tac_toe_backend.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService boardService;

    @GetMapping
    public ResponseEntity<RoomDTO> getRoom(@RequestParam String roomName) {
        return ResponseEntity.ok(boardService.getRoom(roomName));
    }

    @PostMapping("/chooseRoomForPlayer")
    public ResponseEntity<RoomDTO> chooseRoomForPlayer(@RequestParam String playerName) {
        return ResponseEntity.ok(boardService.chooseRoomForPlayer(playerName));
    }

    @DeleteMapping("/deletePlayerFromRoom")
    public ResponseEntity deletePlayerFromRoom(@RequestParam String roomName, @RequestParam String playerName) {
        boardService.deletePlayerFromRoom(roomName, playerName);
        return ResponseEntity.ok().build();
    }

}
