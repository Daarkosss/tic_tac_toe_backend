package com.example.tic_tac_toe_backend.service;

import com.example.tic_tac_toe_backend.dto.BoardDTO;
import com.example.tic_tac_toe_backend.dto.GameOverMessage;
import com.example.tic_tac_toe_backend.dto.PlayerMove;
import com.example.tic_tac_toe_backend.entity.Player;
import com.example.tic_tac_toe_backend.entity.Room;
import com.example.tic_tac_toe_backend.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class GameService {

    private final RoomRepository roomRepository;
    private final SimpMessagingTemplate simpMessagingTemplate;


    public void makeMove(PlayerMove playerMove) {

        Room room = roomRepository.getRoomByName(playerMove.getRoomName());

        makeMoveOnBoard(room, playerMove);

        BoardDTO board = new BoardDTO(room.getFields());

        if(room.getPlayer1().getName().equals(playerMove.getPlayerName())) {
            simpMessagingTemplate.convertAndSend("/topic/" + room.getPlayer2().getName(), board);
        } else {
            simpMessagingTemplate.convertAndSend("/topic/" + room.getPlayer1().getName(), board);
        }

        if(checkWin(room)) {
            startNewGame(room);
            return;
        }
        if(checkDraw(room)){
            startNewGame(room);
            return;
        }
    }

    private void makeMoveOnBoard(Room room, PlayerMove playerMove) {
        List<List<Integer>> fields = room.getFields();
        int symbol = 1;
        if(room.getPlayer2().getName().equals(playerMove.getPlayerName())) {
            symbol = 2;
        }

        fields.get(playerMove.getX()).set(playerMove.getY(), symbol);
        room.setFields(fields);

        Player player1 = room.getPlayer1();
        player1.setStarting(!player1.isStarting());

        Player player2 = room.getPlayer2();
        player2.setStarting(!player2.isStarting());
    }

    private boolean checkWin(Room room) {
        return checkRows(room) || checkColumns(room) || checkDiagonal(room);
    }

    private void sendGameOverMessage(Room room, Integer winnerSymbol) {
        GameOverMessage gameOverMessage = new GameOverMessage(winnerSymbol == 1, false);
        simpMessagingTemplate.convertAndSend("/topic/" + room.getPlayer1().getName(), gameOverMessage);
        gameOverMessage.setWinner(winnerSymbol == 2);
        simpMessagingTemplate.convertAndSend("/topic/" + room.getPlayer2().getName(), gameOverMessage);
    }

    private boolean checkRows(Room room) {
        for (List<Integer> row : room.getFields()) {
            if (row.stream().allMatch(cell -> cell.equals(row.get(0)) && cell != 0)) {
                sendGameOverMessage(room, row.get(0));
                return true;
            }
        }
        return false;
    }

    private boolean checkColumns(Room room) {
        List<List<Integer>> fields = room.getFields();
        for (int columnIndex = 0; columnIndex < 3; columnIndex++) {
            final int col = columnIndex;
            if (IntStream.range(0, 3)
                    .allMatch(rowIndex -> fields.get(rowIndex).get(col).equals(fields.get(0).get(col)) && fields.get(rowIndex).get(col) != 0)) {
                sendGameOverMessage(room, fields.get(0).get(columnIndex));
                return true;
            }
        }
        return false;
    }


    private boolean checkDiagonal(Room room) {
        List<List<Integer>> fields = room.getFields();

        // Pierwsza przekątna
        boolean diagonal1 = IntStream.range(0, 3).allMatch(i -> fields.get(i).get(i).equals(fields.get(0).get(0)) && fields.get(i).get(i) != 0);
        if (diagonal1) {
            sendGameOverMessage(room, fields.get(0).get(0));
            return true;
        }

        // Druga przekątna
        boolean diagonal2 = IntStream.range(0, 3).allMatch(i -> fields.get(i).get(2 - i).equals(fields.get(0).get(2)) && fields.get(i).get(2 - i) != 0);
        if (diagonal2) {
            sendGameOverMessage(room, fields.get(0).get(2));
            return true;
        }
        return false;
    }


    private boolean checkDraw(Room room) {
        int countEmptyFields = 0;
        List<List<Integer>> fields = room.getFields();
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(fields.get(i).get(j) == 0) {
                    countEmptyFields++;
                }
            }
        }
        if(countEmptyFields == 0) {
            sendGameOverMessage(room, 0);
            return true;
        }
        return false;
    }

    private void startNewGame(Room room) {
        room.initializeFields();
    }


}
