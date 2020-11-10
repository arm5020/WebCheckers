package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Game;
import com.webcheckers.model.Piece;
import com.webcheckers.util.Message;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

public class PostSpectatorCheckTurn implements Route {

    private final GameCenter gameCenter;
    private final Gson gson;
    private final PlayerLobby playerLobby;

    public PostSpectatorCheckTurn(GameCenter gameCenter, Gson gson, PlayerLobby playerLobby)
    {
        this.gameCenter = gameCenter;
        this.gson = gson;
        this.playerLobby = playerLobby;
    }

    /**
     * Checks the turn of the game for a spectator.
     * @param request
     *   the HTTP request
     * @param response
     *   the HTTP response
     * @return
     *   If there is a new board state message
     */
    @Override
    public Object handle(Request request, Response response) {
        Session session = request.session();
        Message newBoardState;
        Game currentGame = gameCenter.getGame(session.attribute("spectatedName"));

        if(currentGame.isTurnMade())
        {
           newBoardState = Message.info("true");
        }
        else
        {
            newBoardState = Message.info("false");
        }

        if(currentGame.isGameOver())
        {
            newBoardState = Message.info("true");
        }

        return gson.toJson(newBoardState, Message.class);
    }
}
