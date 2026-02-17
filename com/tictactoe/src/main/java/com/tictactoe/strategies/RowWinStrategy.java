package com.tictactoe.strategies;

public class RowWinStrategy implements WinStrategy {
    @Override
    public boolean checkWin(char[][] board, char player) {
        int size = board.length;
        for(int i = 0; i < size; i++) {
            boolean win = true;
            for(int j = 0; j < size; j++) {
                if(board[i][j] != player) {
                    win = false;
                    break;
                }
            }
            if(win) return true;
        }
        return false;
    }
}
