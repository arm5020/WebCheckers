package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.Game;
import com.webcheckers.model.Piece;
import com.webcheckers.model.Player;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.BoardView;
import com.webcheckers.util.Message;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;


public class PostResignGameRoute implements Route {

    private final PlayerLobby playerLobby;
    private final GameCenter gameCenter;
    private final Gson gson;
    private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());

    public PostResignGameRoute(PlayerLobby playerLobby, GameCenter gameCenter, Gson gson) {
        this.playerLobby = playerLobby;
        this.gameCenter = gameCenter;
        this.gson = gson;
        LOG.config("PostResignRoute is initialized.");
    }

    /**
     * Render the WebCheckers Home page after resignation.
     * @param request
     *   the HTTP request
     * @param response
     *   the HTTP response
     * @return
     *   the rendered HTML for the Home page
     */
    @Override
    public Object handle(Request request, Response response) {
        Message message;
        Session session = request.session();
        Game currentGame = gameCenter.getGame((int)session.attribute("gameID"));
        Player currentUser = session.attribute("currentUser");
        Piece.color currentUserColor;
        if(currentUser.equals(currentGame.getRedPlayer()))
        {
            currentUserColor = Piece.color.RED;
        }
        else
        {
            currentUserColor = Piece.color.WHITE;
        }
        if(currentGame.getActiveColor() != currentUserColor)
        {
            message = Message.error("You can only resign during your turn");
        }
        else
        {
            currentGame.resign(currentUser);
            message = Message.info("Resignation Successful");
        }
        return gson.toJson(message);
    }
}
