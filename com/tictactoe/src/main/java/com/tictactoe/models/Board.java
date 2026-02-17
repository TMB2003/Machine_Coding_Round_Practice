package com.tictactoe.models;
import java.util.Arrays;

import com.tictactoe.Services.ConsolePrintBoard;

public class Board {
    char[][] board;

    public Board(){
        board = new char[3][3];
        for(int i = 0; i < 3; i++) Arrays.fill(board[i], '-');
    }

    public MoveResult move(int i , int j, Player player){
        if(i < 0 || i >= board.length || j < 0 || j >= board.length) return MoveResult.INVALID;
        if(!checkIfEmpty(this.board, i, j)) return MoveResult.FAILURE;

        char id = player.getId();
        board[i][j] = id;

        return MoveResult.SUCCESS;
    }

    private boolean checkIfEmpty(char[][] board, int i, int j) {
        if(board[i][j] == '-') return true;
        return false;
    }

    public boolean checkIfWon(char player) {

        for(int i = 0; i < 3; i++){
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) return true;
        }
        for(int j = 0; j < 3; j++){
            if (board[0][j] == player && board[1][j] == player && board[2][j] == player) return true;
        }

        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) return true;
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) return true;

        return false;
    }
    
    public char[][] getBoard() {
        char[][] copy = new char[3][3];
        for(int i = 0; i < 3; i++){
            copy[i][0] = board[i][0];
            copy[i][1] = board[i][1];
            copy[i][2] = board[i][2];
        }
        return copy;
    }

    public boolean isBoardFull() {
        int size = board.length;
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(board[i][j] == '-') return false;
            }
        }
        return true;
    }

    public void PrintBoard(){
        ConsolePrintBoard cb = new ConsolePrintBoard();
        cb.PrintBoard(board);
    }
}
