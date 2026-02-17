package com.tictactoe.models;

public class Player {
    private final String name;
    private final char id;

    public Player(String name, char id) {
        this.name = name;
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public char getId(){
        return this.id;
    }
}
