package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import spark.*;

import java.util.HashMap;
import java.util.Map;

public class GetSpectatorGameRoute implements Route {

    private final GameCenter gameCenter;
    private final Gson gson;
    private final TemplateEngine templateEngine;

    public GetSpectatorGameRoute(TemplateEngine templateEngine, GameCenter gameCenter, Gson gson)
    {
        this.gameCenter = gameCenter;
        this.gson = gson;
        this.templateEngine = templateEngine;
    }


    /**
     * Inits the spectator page.
     * @param request
     *   the HTTP request
     * @param response
     *   the HTTP response
     * @return
     *   the rendered HTML for the Spectator page
     */
    @Override
    public Object handle(Request request, Response response) {
        Session session = request.session();
        Player currentUser = session.attribute("currentUser");
        Game currentGame = gameCenter.getGame(session.attribute("spectatedName"));

        Map<String, Object> vm = new HashMap<>();

        if(currentGame.getResignedPlayer() != null)
        {
            gameCenter.endGame(currentGame);
            vm.put("message", currentGame.getResignedPlayer().getName() + " has resigned!");
            return null;
        }

        if(currentGame.hasWon() != null)
        {
            gameCenter.endGame(currentGame);
            vm.put("message", currentGame.hasWon().getName() + " has won!");
            return null;
        }

        vm.put("title", "Spectator Mode");
        vm.put("gameID", currentGame.getGameID());
        vm.put("viewMode", GetGameRoute.mode.SPECTATOR);
        vm.put("currentUser", currentUser);
        vm.put("redPlayer", currentGame.getRedPlayer());
        vm.put("whitePlayer", currentGame.getWhitePlayer());
        vm.put("activeColor", currentGame.getActiveColor());
        vm.put("board", currentGame.getBoard());


        return templateEngine.render(new ModelAndView(vm,"game.ftl"));
    }
}
