package com.example.tic_tac_toe_backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameOverMessageDTO {

    private String type = "GameOverMessage";
    private boolean isWinner;
    private boolean isDraw;

    public GameOverMessageDTO(boolean isWinner, boolean isDraw) {
        this.isWinner = isWinner;
        this.isDraw = isDraw;
    }

}
