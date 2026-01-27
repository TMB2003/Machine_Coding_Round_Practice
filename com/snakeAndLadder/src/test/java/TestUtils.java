import Entities.PlayerImp;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class TestUtils {
    
    public static HashMap<Integer, Integer> createStandardSnakes() {
        HashMap<Integer, Integer> snakes = new HashMap<>();
        snakes.put(99, 78);
        snakes.put(95, 75);
        snakes.put(93, 73);
        snakes.put(87, 24);
        snakes.put(64, 60);
        snakes.put(62, 19);
        snakes.put(54, 34);
        snakes.put(17, 7);
        return snakes;
    }
    
    public static HashMap<Integer, Integer> createStandardLadders() {
        HashMap<Integer, Integer> ladders = new HashMap<>();
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
        return ladders;
    }
    
    public static HashMap<Integer, Integer> createMinimalSnakes() {
        HashMap<Integer, Integer> snakes = new HashMap<>();
        snakes.put(98, 50);
        snakes.put(95, 75);
        return snakes;
    }
    
    public static HashMap<Integer, Integer> createMinimalLadders() {
        HashMap<Integer, Integer> ladders = new HashMap<>();
        ladders.put(2, 20);
        ladders.put(7, 14);
        return ladders;
    }
    
    public static List<PlayerImp> createPlayers(String... names) {
        List<PlayerImp> players = new ArrayList<>();
        for (String name : names) {
            players.add(new PlayerImp(name));
        }
        return players;
    }
    
    public static PlayerImp createPlayerAtPosition(String name, int position) {
        PlayerImp player = new PlayerImp(name);
        player.setCurrentPosition(position);
        return player;
    }
    
    public static boolean isValidPosition(int position) {
        return position >= 0 && position <= 100;
    }
    
    public static boolean isValidSnake(int start, int end) {
        return start > end && start <= 100 && end >= 0 && start != 100;
    }
    
    public static boolean isValidLadder(int start, int end) {
        return end > start && end <= 100 && start >= 0 && end != 100;
    }
    
    public static void assertValidGameState(List<PlayerImp> players) {
        for (PlayerImp player : players) {
            assert isValidPosition(player.getCurrentPosition()) : 
                "Player " + player.getName() + " has invalid position: " + player.getCurrentPosition();
        }
    }
    
    public static void printGameState(List<PlayerImp> players) {
        System.out.println("Current Game State:");
        for (PlayerImp player : players) {
            System.out.println(player.getName() + ": " + player.getCurrentPosition());
        }
        System.out.println();
    }
}
