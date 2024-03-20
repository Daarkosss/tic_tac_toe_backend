package com.example.tic_tac_toe_backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OpponentLeftGameMessageDTO {

    private String type = "OpponentLeftGameMessage";
    private String message = "Your opponent has left the game";

}
