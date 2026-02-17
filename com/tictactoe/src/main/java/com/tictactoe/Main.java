package com.tictactoe;

import java.util.Scanner;
import com.tictactoe.models.MoveResult;

public class Main {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Welcome to Tic Tac Toe!");
        System.out.println("Enter player details in format: X PlayerName");
        System.out.println("Then: O PlayerName");
        
        String playerXInput = scanner.nextLine().trim();
        String playerOInput = scanner.nextLine().trim();
        
        String playerXName = extractPlayerName(playerXInput);
        String playerOName = extractPlayerName(playerOInput);
        
        TicTacToeGame game = new TicTacToeGame(playerXName, playerOName);
        
        // Print initial board
        System.out.println("Game started! Current board:");
        
        boolean gameEnded = false;
        
        while (!gameEnded) {
            System.out.println("Current player: " + game.getCurrentPlayerName());
            System.out.println("Enter move (row column) or 'exit' to quit:");

            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Game ended by user.");
                break;
            }

            try {
                String[] parts = input.split("\\s+");

                if (parts.length != 2) {
                    System.out.println("Invalid Move");
                    continue;
                }

                int row = Integer.parseInt(parts[0]);
                int col = Integer.parseInt(parts[1]);

                String currentPlayer = game.getCurrentPlayerName();

                if (game.makeMove(row, col) == MoveResult.SUCCESS) {

                    game.printBoard();

                    if (game.checkWin()) {
                        System.out.println(currentPlayer + " won the game");
                        gameEnded = true;
                    } 
                    else if (game.isBoardFull()) {
                        System.out.println("Game Over");
                        gameEnded = true;
                    }

                } else {
                    System.out.println("Invalid Move");
                }

            } catch (Exception e) {
                System.out.println("Invalid Move");
            }
        }
        scanner.close();
    }
    
    private static String extractPlayerName(String input) {
        String[] parts = input.split(" ", 2);
        if (parts.length >= 2) {
            return parts[1].trim();
        }
        return "Unknown";
    }
}
