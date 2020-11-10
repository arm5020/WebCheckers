package com.webcheckers.ui;

import com.webcheckers.appl.PlayerLobby;
import spark.*;

import java.util.Objects;

public class PostSignoutRoute implements Route {
    private final TemplateEngine templateEngine;
    private final PlayerLobby playerLobby;

    public PostSignoutRoute(TemplateEngine templateEngine, PlayerLobby playerLobby) {
        this.playerLobby = playerLobby;
        this.templateEngine = templateEngine;
    }

    /**
     * Render the WebCheckers Home page after sign out.
     * @param request
     *   the HTTP request
     * @param response
     *   the HTTP response
     * @return
     *   the rendered HTML for the Home page
     */
    @Override
    public Object handle(Request request, Response response) {
        final Session session = request.session();

        if(session.attribute("currentUser") != null) {
            playerLobby.removePlayer(session.attribute("currentUser"));
            session.attribute("currentUser", null);
            session.attribute("playerList", playerLobby.getNames(playerLobby.getPlayers()));
            response.redirect("/home");
        }
        return null;
    }
}