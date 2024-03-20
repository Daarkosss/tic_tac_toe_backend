package com.example.tic_tac_toe_backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MoveOnBoardDTO {

    private String type = "MoveOnBoard";
    private String roomName;
    private int i;
    private int j;
    private String playerName;

}
