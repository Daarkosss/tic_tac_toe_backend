package com.example.tic_tac_toe_backend.controller;

import com.example.tic_tac_toe_backend.dto.RoomDTO;
import com.example.tic_tac_toe_backend.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService boardService;

    @GetMapping
    public ResponseEntity<RoomDTO> getRoom(@RequestParam String roomName) {
        return ResponseEntity.ok(boardService.getRoom(roomName));
    }

    @PostMapping("/findRoomForPlayer")
    public ResponseEntity<RoomDTO> findRoomForPlayer(@RequestParam String playerName) {
        return ResponseEntity.ok(boardService.findRoomForPlayer(playerName));
    }

    @DeleteMapping("/removePlayerFromRoom")
    public ResponseEntity removePlayerFromRoom(@RequestParam String roomName, @RequestParam String playerName) {
        boardService.deletePlayerFromRoom(roomName, playerName);
        Map<String, Object> body = Collections.singletonMap("message", "Player removed successfully");
        return ResponseEntity.ok(body);
    }

}
