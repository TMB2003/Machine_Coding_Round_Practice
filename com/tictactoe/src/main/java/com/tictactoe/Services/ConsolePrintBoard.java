package com.tictactoe.Services;

public class ConsolePrintBoard implements Print {
    @Override
    public void PrintBoard(char[][] board) {
        int size = board.length;
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                System.out.print(board[i][j]);
                if(j < size - 1) System.out.print(" ");
            }
            System.out.println();
        }
    }
}