package Entities;


public class PlayerImp implements Player{
    private final String name;
    private int currentPosition;

    public PlayerImp(String name){
        this.name = name;
        this.currentPosition = 0;
    }

    @Override
    public String getName(){
        return name;
    }

    @Override
    public int getCurrentPosition(){
        return currentPosition;
    }

    public void setCurrentPosition(int position){
        currentPosition = position;
    }
}