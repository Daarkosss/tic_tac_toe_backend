package com.example.tic_tac_toe_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StartGameMessage {

    private String dtype = "StartGameMessage";
    private Boolean isStarting;

    public StartGameMessage(Boolean isStarting) {
        this.isStarting = isStarting;
    }

}
