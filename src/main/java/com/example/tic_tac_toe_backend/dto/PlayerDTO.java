package com.example.tic_tac_toe_backend.dto;

import com.example.tic_tac_toe_backend.entity.Player;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class PlayerDTO {

    private String dtype = "Player";
    private String name;
    private boolean isStarting;

    public static PlayerDTO of(Player player) {
        return PlayerDTO.builder()
                .dtype("Player")
                .name(player.getName())
                .isStarting(player.isStarting())
                .build();
    }

}
