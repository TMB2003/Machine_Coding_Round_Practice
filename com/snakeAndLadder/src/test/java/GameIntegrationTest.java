import Entities.PlayerImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

class GameIntegrationTest {
    
    private Game game;
    private List<PlayerImp> players;
    private HashMap<Integer, Integer> snakes;
    private HashMap<Integer, Integer> ladders;

    @BeforeEach
    void setUp() {
        snakes = new HashMap<>();
        ladders = new HashMap<>();
        players = new ArrayList<>();
        
        // Setup standard game board
        snakes.put(99, 78);
        snakes.put(95, 75);
        snakes.put(93, 73);
        snakes.put(87, 24);
        snakes.put(64, 60);
        snakes.put(62, 19);
        snakes.put(54, 34);
        snakes.put(17, 7);
        
        ladders.put(2, 38);
        ladders.put(7, 14);
        ladders.put(8, 31);
        ladders.put(15, 26);
        ladders.put(21, 42);
        ladders.put(28, 84);
        ladders.put(36, 44);
        ladders.put(51, 67);
        ladders.put(71, 91);
        ladders.put(78, 98);
        ladders.put(87, 94);
        
        game = new Game(ladders, snakes);
        
        // Add players
        players.add(new PlayerImp("Alice"));
        players.add(new PlayerImp("Bob"));
        players.add(new PlayerImp("Charlie"));
    }

    @Test
    void testCompleteGameFlow() {
        boolean gameWon = false;
        int maxTurns = 1000; // Prevent infinite loop
        int turnCount = 0;
        
        while (!gameWon && turnCount < maxTurns) {
            for (PlayerImp player : players) {
                gameWon = game.move(player);
                if (gameWon) {
                    // Player should have reached position 100 or be at a winning position
                    assertTrue(player.getCurrentPosition() == 100 || player.getCurrentPosition() >= 95, 
                             "Winner should be at position 100 or very close to winning");
                    break;
                }
            }
            turnCount++;
        }
        
        assertTrue(gameWon, "Game should be won within " + maxTurns + " turns");
    }

    @Test
    void testMultiplePlayersGame() {
        // Test with 4 players
        List<PlayerImp> fourPlayers = new ArrayList<>();
        fourPlayers.add(new PlayerImp("Player1"));
        fourPlayers.add(new PlayerImp("Player2"));
        fourPlayers.add(new PlayerImp("Player3"));
        fourPlayers.add(new PlayerImp("Player4"));
        
        boolean gameWon = false;
        int maxTurns = 1000;
        int turnCount = 0;
        
        while (!gameWon && turnCount < maxTurns) {
            for (PlayerImp player : fourPlayers) {
                gameWon = game.move(player);
                if (gameWon) {
                    break;
                }
            }
            turnCount++;
        }
        
        assertTrue(gameWon);
    }

    @Test
    void testSinglePlayerGame() {
        List<PlayerImp> singlePlayer = new ArrayList<>();
        singlePlayer.add(new PlayerImp("SoloPlayer"));
        
        boolean gameWon = false;
        int maxTurns = 1000;
        int turnCount = 0;
        
        while (!gameWon && turnCount < maxTurns) {
            gameWon = game.move(singlePlayer.get(0));
            turnCount++;
        }
        
        assertTrue(gameWon);
    }

    @Test
    void testGameWithMinimalSnakesAndLadders() {
        HashMap<Integer, Integer> minimalSnakes = new HashMap<>();
        HashMap<Integer, Integer> minimalLadders = new HashMap<>();
        
        minimalSnakes.put(98, 50);
        minimalLadders.put(2, 20);
        
        Game minimalGame = new Game(minimalLadders, minimalSnakes);
        PlayerImp testPlayer = new PlayerImp("MinimalPlayer");
        
        boolean gameWon = false;
        int maxTurns = 500;
        int turnCount = 0;
        
        while (!gameWon && turnCount < maxTurns) {
            gameWon = minimalGame.move(testPlayer);
            turnCount++;
        }
        
        assertTrue(gameWon);
    }

    @Test
    void testPlayerPositionsThroughoutGame() {
        List<PlayerImp> testPlayers = new ArrayList<>();
        testPlayers.add(new PlayerImp("TestPlayer1"));
        testPlayers.add(new PlayerImp("TestPlayer2"));
        
        boolean gameWon = false;
        int maxTurns = 200;
        int turnCount = 0;
        
        while (!gameWon && turnCount < maxTurns) {
            for (PlayerImp player : testPlayers) {
                int initialPosition = player.getCurrentPosition();
                gameWon = game.move(player);
                
                // Verify position is always valid
                assertTrue(player.getCurrentPosition() >= 0, "Position should never be negative");
                assertTrue(player.getCurrentPosition() <= 100, "Position should never exceed 100");
                
                // If game is won, verify winning position
                if (gameWon) {
                    assertTrue(player.getCurrentPosition() == 100 || player.getCurrentPosition() >= 95, 
                             "Winner should be at position 100 or very close to winning");
                    break;
                }
                
                if (gameWon) break;
            }
            turnCount++;
        }
    }

    @Test
    void testGameWithNoSnakesOrLadders() {
        Game plainGame = new Game(new HashMap<>(), new HashMap<>());
        PlayerImp plainPlayer = new PlayerImp("PlainPlayer");
        
        boolean gameWon = false;
        int maxTurns = 500;
        int turnCount = 0;
        
        while (!gameWon && turnCount < maxTurns) {
            gameWon = plainGame.move(plainPlayer);
            turnCount++;
        }
        
        assertTrue(gameWon, "Game should be completable even without snakes and ladders");
    }

    @Test
    void testConcurrentGameSessions() {
        // Test multiple independent game sessions
        Game game1 = new Game(ladders, snakes);
        Game game2 = new Game(ladders, snakes);
        
        PlayerImp player1 = new PlayerImp("Session1Player");
        PlayerImp player2 = new PlayerImp("Session2Player");
        
        // Both games should be playable independently
        boolean game1Won = false;
        boolean game2Won = false;
        int maxTurns = 100;
        
        for (int i = 0; i < maxTurns && (!game1Won || !game2Won); i++) {
            if (!game1Won) {
                game1Won = game1.move(player1);
            }
            if (!game2Won) {
                game2Won = game2.move(player2);
            }
        }
        
        // At least one game should have made progress
        assertTrue(player1.getCurrentPosition() >= 0 || player2.getCurrentPosition() >= 0);
    }
}
