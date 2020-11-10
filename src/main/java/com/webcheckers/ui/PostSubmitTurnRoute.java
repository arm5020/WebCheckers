package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.WebServer;
import com.webcheckers.model.Game;
import com.webcheckers.util.Message;
import spark.*;

import java.util.logging.Logger;


public class PostSubmitTurnRoute implements Route {

    private final Gson gson;
    private final GameCenter gameCenter;
    private static final Logger LOG = Logger.getLogger(GetGameRoute.class.getName());
    private boolean isMyTurn;

    public PostSubmitTurnRoute(GameCenter gameCenter, Gson gson)
    {
        this.gameCenter = gameCenter;
        this.gson = gson;
        LOG.config("PostSubmitTurnRoute is done");
    }

    /**
     * Submits a turn.
     * @param request
     *   the HTTP request
     * @param response
     *   the HTTP response
     * @return
     *   Turn submitted message
     */
    @Override
    public Object handle(Request request, Response response) throws Exception {
        Session session = request.session();
        Game currentGame = gameCenter.getGame((int)session.attribute("gameID"));
        currentGame.clearMoves();
        currentGame.switchActiveColor();

        return gson.toJson(Message.info("turn submitted"));//String.valueOf(isMyTurn || currentGame.isGameOver())));
    }
}
