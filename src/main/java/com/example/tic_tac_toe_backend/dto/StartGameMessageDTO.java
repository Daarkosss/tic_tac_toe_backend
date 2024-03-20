package com.example.tic_tac_toe_backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StartGameMessageDTO {

    private String type = "StartGameMessage";
    private Boolean isStarting;

    public StartGameMessageDTO(Boolean isStarting) {
        this.isStarting = isStarting;
    }

}
