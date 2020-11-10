package com.webcheckers.model;

public class Player {
    public String name;
    //public Player opponent; //REMOVE

    public Player(String name) {
        this.name = name;
    }

    /**
     * Returns the name of a player
     * @return The name
     */
    public String getName() {
        return this.name;
    }

    //public void addOpponent(Player opponent) { //WE NEED TO REMOVE THIS
    //    this.opponent = opponent;
    //}

    //public Player getOpponent() { // REMOVE THIS
    //   return this.opponent;
    //}

}
