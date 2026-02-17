package com.tictactoe;

import com.tictactoe.models.Board;
import com.tictactoe.models.MoveResult;
import com.tictactoe.models.Player;

public class TicTacToeGame {
    
    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Player lastPlayer;
    
    
    public TicTacToeGame(String playerXName, String playerOName) {
        board = new Board();
        player1 = new Player(playerXName, 'X');
        player2 = new Player(playerOName, 'O');
        currentPlayer = player1;
        lastPlayer = player2;
    }
    
    public MoveResult makeMove(int row, int col) {
        Player current = currentPlayer;
        MoveResult moved = board.move(row - 1, col - 1, current);
        
        if(moved == MoveResult.SUCCESS) {
            lastPlayer = currentPlayer;
            currentPlayer = currentPlayer == player1 ? player2 : player1;
        }
        return moved;
    }

    
    public boolean checkWin() {
        return board.checkIfWon(lastPlayer.getId());
    }
    
    public boolean isBoardFull() {
        return board.isBoardFull();
    }
    
    public char[][] getBoard() {
        return board.getBoard();
    }
    
    public String getCurrentPlayerName() {
        return currentPlayer.getName();
    }

    public void printBoard(){
        board.PrintBoard();
    }
}
