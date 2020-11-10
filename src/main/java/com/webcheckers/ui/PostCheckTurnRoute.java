package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.WebServer;
import com.webcheckers.model.Game;
import com.webcheckers.model.Piece;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;

import java.util.Objects;
import java.util.logging.Logger;

public class PostCheckTurnRoute implements Route {

    private final TemplateEngine templateEngine;
    private final Gson gson;
    GameCenter gameCenter;
    private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());

    /**
     * Create the Spark Route (UI controller) to handle all {@code GET /} HTTP requests.
     * @param templateEngine
     *   the HTML template rendering engine
     */
    public PostCheckTurnRoute(final TemplateEngine templateEngine, Gson gson, GameCenter gameCenter) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.gson = gson;
        this.gameCenter = gameCenter;
        LOG.config("PostCheckTurnRoute is initialized.");
    }

    /**
     * Checks the current turn of the game.
     * @param request
     *   the HTTP request
     * @param response
     *   the HTTP response
     * @return
     *   Whos turn it is
     */
    @Override
    public Object handle(Request request, Response response) {
        //Game curr = GameCenter
        Player currentPlayer = request.session().attribute("currentUser");

        Game game = gameCenter.getGame(currentPlayer.getName());
        boolean myTurn = (currentPlayer == game.getRedPlayer() && game.getActiveColor() == Piece.color.RED)
                || ( currentPlayer== game.getWhitePlayer() && game.getActiveColor() == Piece.color.WHITE);
        return gson.toJson(Message.info(String.valueOf(myTurn || game.isGameOver())));
    }
}
