import Entities.PlayerImp;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlayerImpTest {

    @Test
    void testPlayerCreation() {
        PlayerImp player = new PlayerImp("Alice");
        assertEquals("Alice", player.getName());
        assertEquals(0, player.getCurrentPosition());
    }

    @Test
    void testSetCurrentPosition() {
        PlayerImp player = new PlayerImp("Bob");
        player.setCurrentPosition(50);
        assertEquals(50, player.getCurrentPosition());
    }

    @Test
    void testSetCurrentPositionToWinningPosition() {
        PlayerImp player = new PlayerImp("Charlie");
        player.setCurrentPosition(100);
        assertEquals(100, player.getCurrentPosition());
    }

    @Test
    void testMultiplePositionUpdates() {
        PlayerImp player = new PlayerImp("Dave");
        player.setCurrentPosition(10);
        assertEquals(10, player.getCurrentPosition());
        
        player.setCurrentPosition(25);
        assertEquals(25, player.getCurrentPosition());
        
        player.setCurrentPosition(0);
        assertEquals(0, player.getCurrentPosition());
    }

    @Test
    void testPlayerNameImmutability() {
        PlayerImp player = new PlayerImp("Eve");
        assertEquals("Eve", player.getName());
        // Name should remain constant throughout the game
        assertEquals("Eve", player.getName());
    }
}
