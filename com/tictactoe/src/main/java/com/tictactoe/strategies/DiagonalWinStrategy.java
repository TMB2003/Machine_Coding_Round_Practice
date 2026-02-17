package com.tictactoe.strategies;

public class DiagonalWinStrategy implements WinStrategy {
    @Override
    public boolean checkWin(char[][] board, char player) {
        int size = board.length;
        
        // Check main diagonal (top-left to bottom-right)
        boolean mainDiagonal = true;
        for(int i = 0; i < size; i++) {
            if(board[i][i] != player) {
                mainDiagonal = false;
                break;
            }
        }
        if(mainDiagonal) return true;
        
        // Check anti-diagonal (top-right to bottom-left)
        boolean antiDiagonal = true;
        for(int i = 0; i < size; i++) {
            if(board[i][size - 1 - i] != player) {
                antiDiagonal = false;
                break;
            }
        }
        
        return antiDiagonal;
    }
}
