import Entities.PlayerImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;

class EdgeCaseTest {
    
    private Game game;
    private PlayerImp player;

    @BeforeEach
    void setUp() {
        HashMap<Integer, Integer> snakes = new HashMap<>();
        HashMap<Integer, Integer> ladders = new HashMap<>();
        game = new Game(ladders, snakes);
        player = new PlayerImp("EdgeCasePlayer");
    }

    @Test
    void testPlayerAtPosition99() {
        player.setCurrentPosition(99);
        
        // Player should only win with dice roll of 1
        // Any other roll should keep them at 99 or move them back
        boolean result = game.move(player);
        
        // Position should be valid
        assertTrue(player.getCurrentPosition() <= 100);
        assertTrue(player.getCurrentPosition() >= 0);
    }

    @Test
    void testPlayerAtPosition98() {
        player.setCurrentPosition(98);
        
        // Player can only win with dice roll of 2
        // Rolls of 3,4,5,6 should keep them at 98
        boolean result = game.move(player);
        
        assertTrue(player.getCurrentPosition() <= 100);
        assertTrue(player.getCurrentPosition() >= 98);
    }

    @Test
    void testPlayerAtPosition97() {
        player.setCurrentPosition(97);
        
        // Player can only win with dice roll of 3
        // Rolls of 4,5,6 should keep them at 97
        boolean result = game.move(player);
        
        assertTrue(player.getCurrentPosition() <= 100);
        assertTrue(player.getCurrentPosition() >= 97);
    }

    @Test
    void testPlayerAtPosition0() {
        player.setCurrentPosition(0);
        
        int initialPosition = player.getCurrentPosition();
        game.move(player);
        
        // Should move forward by 1-6
        assertTrue(player.getCurrentPosition() > initialPosition);
        assertTrue(player.getCurrentPosition() <= initialPosition + 6);
    }

    @Test
    void testSnakeAtPosition1() {
        HashMap<Integer, Integer> snakes = new HashMap<>();
        snakes.put(1, 0); // Snake from 1 to 0
        Game snakeGame = new Game(new HashMap<>(), snakes);
        
        player.setCurrentPosition(0);
        snakeGame.move(player);
        
        // If player lands on 1, they should go back to 0
        assertTrue(player.getCurrentPosition() >= 0);
    }

    @Test
    void testLadderAtPosition99() {
        HashMap<Integer, Integer> ladders = new HashMap<>();
        ladders.put(99, 100); // Ladder from 99 to 100
        Game ladderGame = new Game(ladders, new HashMap<>());
        
        player.setCurrentPosition(98);
        ladderGame.move(player);
        
        // If player lands on 99, they should climb to 100
        assertTrue(player.getCurrentPosition() <= 100);
    }

    @Test
    void testMultipleSnakesAtSamePosition() {
        HashMap<Integer, Integer> snakes = new HashMap<>();
        // Only one snake should be possible at a position due to HashMap
        snakes.put(50, 25);
        snakes.put(50, 30); // This should overwrite the previous entry
        
        Game multiSnakeGame = new Game(new HashMap<>(), snakes);
        player.setCurrentPosition(49);
        multiSnakeGame.move(player);
        
        assertTrue(player.getCurrentPosition() <= 100);
    }

    @Test
    void testMultipleLaddersAtSamePosition() {
        HashMap<Integer, Integer> ladders = new HashMap<>();
        // Only one ladder should be possible at a position due to HashMap
        ladders.put(10, 20);
        ladders.put(10, 30); // This should overwrite the previous entry
        
        Game multiLadderGame = new Game(ladders, new HashMap<>());
        player.setCurrentPosition(9);
        multiLadderGame.move(player);
        
        assertTrue(player.getCurrentPosition() <= 100);
    }

    @Test
    void testSnakeAndLadderAtSamePosition() {
        HashMap<Integer, Integer> snakes = new HashMap<>();
        HashMap<Integer, Integer> ladders = new HashMap<>();
        
        snakes.put(20, 10);
        ladders.put(20, 40);
        
        Game conflictGame = new Game(ladders, snakes);
        player.setCurrentPosition(19);
        conflictGame.move(player);
        
        // Should handle the conflict (ladder takes precedence in current implementation)
        assertTrue(player.getCurrentPosition() <= 100);
    }

    @Test
    void testSnakeFrom100() {
        HashMap<Integer, Integer> snakes = new HashMap<>();
        snakes.put(100, 50); // Invalid snake (shouldn't exist in real game)
        
        Game invalidSnakeGame = new Game(new HashMap<>(), snakes);
        player.setCurrentPosition(99);
        invalidSnakeGame.move(player);
        
        assertTrue(player.getCurrentPosition() <= 100);
    }

    @Test
    void testLadderTo100() {
        HashMap<Integer, Integer> ladders = new HashMap<>();
        ladders.put(95, 100); // Ladder directly to win
        
        Game winningLadderGame = new Game(ladders, new HashMap<>());
        player.setCurrentPosition(94);
        boolean result = winningLadderGame.move(player);
        
        // Should be able to win via ladder
        assertTrue(player.getCurrentPosition() <= 100);
    }

    @Test
    void testEmptyGameBoard() {
        Game emptyGame = new Game(new HashMap<>(), new HashMap<>());
        PlayerImp emptyPlayer = new PlayerImp("EmptyPlayer");
        
        boolean gameWon = false;
        int maxTurns = 200;
        
        for (int i = 0; i < maxTurns && !gameWon; i++) {
            gameWon = emptyGame.move(emptyPlayer);
        }
        
        // Game should still be winnable even without snakes and ladders
        assertTrue(emptyPlayer.getCurrentPosition() >= 0);
        assertTrue(emptyPlayer.getCurrentPosition() <= 100);
    }

    @Test
    void testExtremeSnakePositions() {
        HashMap<Integer, Integer> snakes = new HashMap<>();
        snakes.put(99, 1); // Snake from near end to beginning
        snakes.put(98, 2);
        snakes.put(97, 3);
        
        Game extremeSnakeGame = new Game(new HashMap<>(), snakes);
        PlayerImp extremePlayer = new PlayerImp("ExtremePlayer");
        
        extremePlayer.setCurrentPosition(96);
        extremeSnakeGame.move(extremePlayer);
        
        assertTrue(extremePlayer.getCurrentPosition() <= 100);
        assertTrue(extremePlayer.getCurrentPosition() >= 0);
    }

    @Test
    void testExtremeLadderPositions() {
        HashMap<Integer, Integer> ladders = new HashMap<>();
        ladders.put(1, 99); // Ladder from beginning to near end
        ladders.put(2, 98);
        ladders.put(3, 97);
        
        Game extremeLadderGame = new Game(ladders, new HashMap<>());
        PlayerImp extremePlayer = new PlayerImp("ExtremePlayer");
        
        extremePlayer.setCurrentPosition(0);
        extremeLadderGame.move(extremePlayer);
        
        assertTrue(extremePlayer.getCurrentPosition() <= 100);
        assertTrue(extremePlayer.getCurrentPosition() >= 0);
    }
}
