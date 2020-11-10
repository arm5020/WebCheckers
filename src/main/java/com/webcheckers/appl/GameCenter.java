package com.webcheckers.appl;

import com.webcheckers.model.Game;
import com.webcheckers.model.Player;

import java.util.ArrayList;


public class GameCenter {

    private ArrayList<Game> gameList;
    private int numGames;

    public GameCenter()
    {
        this.gameList = new ArrayList<>();
        this.numGames = 0;
    }

    public void endGame(Game game)
    {
        gameList.remove(game);
    }

    /**
     * Adds a new game to the list
     * @param whitePlayer The white player
     * @param redPlayer The red player
     * @return success status
     */
    public void addGame(Player whitePlayer, Player redPlayer)
    {
        Game g = new Game(whitePlayer, redPlayer, gameList.size());
        numGames++;
        gameList.add(g);
    }

    /**
     * Returns the number of games in the game center.
     * @return The number of games
     */
    public int getNumGames()
    {
        return numGames;
    }

    /**
     * Returns all the games.
     * @return gamesList
     */
    public ArrayList<Game> getGamesList()
    {
        return gameList;
    }

    /**
     * Gets a game by it's id
     * @param id The id
     * @return The game
     */
    public Game getGame(int id) {
        return gameList.get(id);
    }

    /**
     * gets a game by it's playerName
     * @param playerName The id
     * @return The game
     */
    public Game getGame(String playerName)
    {
        for(int i = 0; i < gameList.size(); i++)
        {
            if(gameList.get(i).getRedPlayer().getName().equals(playerName) || gameList.get(i).getWhitePlayer().getName().equals(playerName))
            {
                return gameList.get(i);
            }
        }
        return null;
    }
}
