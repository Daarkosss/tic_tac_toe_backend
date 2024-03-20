package com.example.tic_tac_toe_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class BoardDTO {

    private String type = "Board";
    private List<List<Integer>> board;

    public BoardDTO(List<List<Integer>> board) {
        this.board = board;
    }

}
