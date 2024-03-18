package com.example.tic_tac_toe_backend.utils.exception;

import lombok.Getter;

@Getter
public class EmptyRoomException extends RuntimeException {

    public EmptyRoomException() {
        super("Empty room");
    }
}
