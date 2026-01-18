import Entities.PlayerImp;
import java.util.*;


public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int noOfSnakes = sc.nextInt();
        HashMap<Integer, Integer> snakes = new HashMap<>();
        while(noOfSnakes-- > 0){
            int start = sc.nextInt();
            int end = sc.nextInt();
            if(start < end || start == 100){
                System.out.println("Please Enter Correct Snake");
            }
            else{
                snakes.put(start, end);
            }
        }
        
        int noOfLadder = sc.nextInt();
        HashMap<Integer, Integer> ladders = new HashMap<>();
        while(noOfLadder--> 0){
            int start = sc.nextInt();
            int end = sc.nextInt();
            if(end < start || end == 100){
                System.out.println("Please Enter Correct Ladder");
            }
            else{
                ladders.put(start, end);
            }
        }
        
        int noOfPlayer = sc.nextInt();
        List<PlayerImp> players = new ArrayList<>();
        while(noOfPlayer-- > 0){
            String name = sc.next();
            players.add(new PlayerImp(name));
        }

        sc.close();

        Game game = new Game(ladders, snakes);
        boolean win = false;
        while(!win){
            for(PlayerImp player: players){
                win = game.move(player);
            }
        }
        System.out.println("Thanks for playing this game!");
    }
}