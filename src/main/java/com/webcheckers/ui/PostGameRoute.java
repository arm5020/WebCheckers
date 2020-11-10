package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import spark.*;

public class PostGameRoute implements Route {

    private TemplateEngine templateEngine;
    private GameCenter gameCenter;
    private PlayerLobby playerLobby;

    public PostGameRoute(TemplateEngine templateEngine, GameCenter gameCenter, PlayerLobby playerLobby){
        this.templateEngine = templateEngine;
        this.gameCenter = gameCenter;
        this.playerLobby = playerLobby;
    }

    /**
     * Inits a game before redirecting to the game or spectator route.
     * @param request
     *   the HTTP request
     * @param response
     *   the HTTP response
     * @return
     *   null
     */
    @Override
    public Object handle(Request request, Response response){
        Session session = request.session();

        Player currentUser = session.attribute("currentUser");
        String whitePlayerName = request.queryParams("whitePlayer");

        if(gameCenter.getGame(currentUser.getName()) == null
                && !playerLobby.isInGamePlayer(playerLobby.getUser(whitePlayerName))) {
            System.out.print("Game Created!");
            gameCenter.addGame(playerLobby.getUser(whitePlayerName), currentUser);
            playerLobby.addInGamePlayer(playerLobby.getUser(whitePlayerName));
            playerLobby.addInGamePlayer(currentUser);
        }
        else
        {
            session.attribute("spectatedName", whitePlayerName);
        }
        response.redirect("/game");
        return null;
    }
}
