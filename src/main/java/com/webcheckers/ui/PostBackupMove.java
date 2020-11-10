package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.Game;
import com.webcheckers.model.Move;
import com.webcheckers.util.Message;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

public class PostBackupMove implements Route {

    private GameCenter gameCenter;
    private Gson gson;

    public PostBackupMove(GameCenter gameCenter, Gson gson)
    {
        this.gameCenter = gameCenter;
        this.gson = gson;
    }

    /**
     * Undoes a move
     * @param request
     *   the HTTP request
     * @param response
     *   the HTTP response
     * @return
     *   Success message
     */
    @Override
    public Object handle(Request request, Response response)
    {
        Session session = request.session();
        Game currentGame = gameCenter.getGame((int)session.attribute("gameID"));

        currentGame.undoMove(currentGame.removeLastMove());

        return gson.toJson(Message.info("Undo move successful"));
    }
}
