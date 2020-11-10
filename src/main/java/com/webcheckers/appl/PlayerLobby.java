package com.webcheckers.appl;

import com.webcheckers.model.Player;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PlayerLobby {

    private ArrayList<Player> players;
    private ArrayList<Player> inGamePlayers;

    public PlayerLobby() {
        this.players = new ArrayList<>();
        this.inGamePlayers = new ArrayList<>();
    }

    /**
     * Returns the size of the lobby
     * @return the lobby size
     */
    public int getLobbySize()
    {
        return players.size();
    }

    /**
     * Returns the current player list
     * @return The player list
     */
    public ArrayList<Player> getPlayers()
    {
        return players;
    }

    public ArrayList<Player> getInGamePlayers()
    {
        return this.inGamePlayers;
    }

    public boolean isInGamePlayer(Player player)
    {
        for(Player p : this.inGamePlayers)
        {
            if(p.equals(player))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Removes a player from the in game list.
     * @param player The player
     */
    public void removeInGamePlayer(Player player)
    {
        inGamePlayers.remove(player);
    }

    /**
     * Adds a player to the in game list.
     * @param player The player
     */
    public void addInGamePlayer(Player player)
    {
        inGamePlayers.add(player);
    }


    /**
     * Returns the list of opponents.
     * @param currentUser the current user
     * @return The list of opponents
     */
    public ArrayList<String> getOpponentList(Player currentUser){
        ArrayList<Player> temp = new ArrayList<>();
        for(int i = 0; i < getLobbySize(); i++)
        {
            if(!players.get(i).getName().equals(currentUser.getName()))
            {
                temp.add(players.get(i));
            }
        }
        return getNames(temp);
    }

    /**
     * Returns the names of all players.
     * @return The names
     */
    public ArrayList<String> getNames(ArrayList<Player> playerList) {
        ArrayList<String> names = new ArrayList<>();
        for (Player p : playerList) {
            names.add(p.getName());
        }
        return names;
    }

    /**
     * Gets a player with a given name from the lobby.
     * @param name The name
     * @return the player
     */
    public Player getUser(String name){
        ArrayList<Player> players = this.players;
        for (Player player : players) {
            if (player.getName().equals(name)) {
                return player;
            }
        }
        return null;
    }

    /**
     * Adds a player to the lobby
     * @param player the player
     */
    public void addPlayer( Player player) {this.players.add(player);}

    /**
     * Removes a player
     * @param player The player
     */
    public void removePlayer(Player player){ this.players.remove(player); }

    /**
     * Checks to see if a player is in the lobby.
     * @param player The player
     * @return Whether they are in the lobby
     */
    public boolean contains(Player player){
        for (Player p : players) {
            if (player.getName().equals(p.getName())) {return true;}
            else {return false;}
        }
        return false;
    }

}
