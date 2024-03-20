package com.example.tic_tac_toe_backend.controller;

import com.example.tic_tac_toe_backend.dto.MoveOnBoardDTO;
import com.example.tic_tac_toe_backend.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WebsocketController {

    private final GameService gameService;

    @MessageMapping("/move")
    public void forwardMessage(MoveOnBoardDTO moveOnBoardDTO) {
        gameService.makeMove(moveOnBoardDTO);
    }

}
