package com.tictactoe.strategies;

public class ColumnWinStrategy implements WinStrategy {
    @Override
    public boolean checkWin(char[][] board, char player) {
        int size = board.length;
        for(int j = 0; j < size; j++) {
            boolean win = true;
            for(int i = 0; i < size; i++) {
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
