import Entities.PlayerImp;
import java.util.*;

public class Game{
    private HashMap<Integer, Integer> ladders;
    private HashMap<Integer, Integer> snakes;

    public Game(HashMap<Integer, Integer> ladders, HashMap<Integer, Integer> snakes){
        this.ladders = ladders;
        this.snakes = snakes;
    }

    public boolean move(PlayerImp player){
        int position = player.getCurrentPosition();
        String name = player.getName();
        Random random = new Random();
        int dice = random.nextInt(6) + 1;

        int newPosition = position + dice;

        if(newPosition == 100) {
            System.out.println(name + " rolled a " + dice + " and won!");
            return true;
        }

        if (newPosition > 100) {
            System.out.println(name + " rolled a " + dice + " but cannot move");
            return false;
        }

        if(snakes.containsKey(newPosition)){
            newPosition = snakes.get(newPosition);
        }

        if(ladders.containsKey(newPosition)){
            newPosition = ladders.get(newPosition);
        }

        player.setCurrentPosition(newPosition);
        System.out.println(name + " rolled a " + dice + " and moved from " + position + " to " + newPosition);
        return false;
    }
}