package com.tictactoe.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.tictactoe.TicTacToeGame;
import com.tictactoe.models.MoveResult;

class TicTacToeGameHardTest {

    private TicTacToeGame game;

    @BeforeEach
    void setUp() {
        game = new TicTacToeGame("Gaurav", "Sagar");
    }

    // ------------------- INITIAL STATE -------------------

    @Test
    void testInitialState() {
        char[][] board = game.getBoard();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals('-', board[i][j]);   // your board uses '-'
            }
        }

        assertEquals("Gaurav", game.getCurrentPlayerName());
        assertFalse(game.isBoardFull());
        assertFalse(game.checkWin());
    }

    // ------------------- VALID MOVE -------------------

    @Test
    void testValidMovePlacesCorrectSymbol() {
        assertEquals(MoveResult.SUCCESS, game.makeMove(1, 1));
        assertEquals('X', game.getBoard()[0][0]);

        assertEquals(MoveResult.SUCCESS, game.makeMove(1, 2));
        assertEquals('O', game.getBoard()[0][1]);
    }

    // ------------------- INVALID MOVE -------------------

    @Test
    void testInvalidMoveDoesNotSwitchPlayer() {
        assertEquals(MoveResult.SUCCESS, game.makeMove(1, 1));  // X
        assertEquals("Sagar", game.getCurrentPlayerName());

        assertEquals(MoveResult.FAILURE, game.makeMove(1, 1)); // occupied
        assertEquals("Sagar", game.getCurrentPlayerName()); // still Sagar
    }

    @Test
    void testOutOfBoundsMove() {
        assertEquals(MoveResult.INVALID, game.makeMove(0, 1));
        assertEquals(MoveResult.INVALID, game.makeMove(4, 4));
    }

    // ------------------- WIN TESTS -------------------

    @Test
    void testRowWin() {
        game.makeMove(1, 1); // X
        game.makeMove(2, 1); // O
        game.makeMove(1, 2); // X
        game.makeMove(2, 2); // O
        game.makeMove(1, 3); // X wins

        assertTrue(game.checkWin());
    }

    @Test
    void testColumnWin() {
        game.makeMove(1, 1); // X
        game.makeMove(1, 2); // O
        game.makeMove(2, 1); // X
        game.makeMove(2, 2); // O
        game.makeMove(3, 1); // X wins

        assertTrue(game.checkWin());
    }

    @Test
    void testDiagonalWin() {
        game.makeMove(1, 1); // X
        game.makeMove(1, 2); // O
        game.makeMove(2, 2); // X
        game.makeMove(2, 1); // O
        game.makeMove(3, 3); // X wins

        assertTrue(game.checkWin());
    }

    // ------------------- DRAW -------------------

    @Test
    void testDrawBoardFullWithoutWin() {
        game.makeMove(1,1);
        game.makeMove(1,2);
        game.makeMove(1,3);
        game.makeMove(2,1);
        game.makeMove(2,3);
        game.makeMove(2,2);
        game.makeMove(3,1);
        game.makeMove(3,3);
        game.makeMove(3,2);

        assertTrue(game.isBoardFull());
        assertFalse(game.checkWin());
    }

    // ------------------- FULL GAME SIMULATION -------------------

    @Test
    void testCompleteGameSimulation() {

        game.makeMove(1,1); // X
        game.makeMove(1,2); // O
        game.makeMove(2,2); // X
        game.makeMove(1,3); // O
        game.makeMove(3,3); // X wins

        assertTrue(game.checkWin());

        char[][] board = game.getBoard();
        assertEquals('X', board[0][0]);
        assertEquals('X', board[1][1]);
        assertEquals('X', board[2][2]);
    }
}
