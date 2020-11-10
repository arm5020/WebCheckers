package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.BoardView;
import com.webcheckers.util.Message;
import spark.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;


public class GetGameRoute implements Route {

    public enum mode
    {PLAY, SPECTATOR}


    private final TemplateEngine templateEngine;
    private final Gson gson;
    private final GameCenter gameCenter;
    private final PlayerLobby playerLobby;
    private static final Logger LOG = Logger.getLogger(GetGameRoute.class.getName());

    public GetGameRoute(final TemplateEngine templateEngine, GameCenter gameCenter, PlayerLobby playerLobby, Gson gson) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.gameCenter = gameCenter;
        this.playerLobby = playerLobby;
        this.gson = gson;
        LOG.config("GetGameRoute is done.");
    }

    /**
     * Render the WebCheckers Game page.
     * @param request
     *   the HTTP request
     * @param response
     *   the HTTP response
     * @return
     *   the rendered HTML for the Game page
     */
    @Override
    public Object handle(Request request, Response response) {
        LOG.finer("GetGameRoute is invoked.");
        Session session = request.session();

        Map<String, Object> vm = new HashMap<>();
        Map<String, Object> modeOptions = new HashMap<>(2);
        modeOptions.put("isGameOver", false);

        Player currentUser = session.attribute("currentUser");

        if(!playerLobby.isInGamePlayer(currentUser))
        {
            response.redirect("/spectator/game");
            return gson.toJson("Entering spectator mode");
        }

        Game currentGame = gameCenter.getGame(currentUser.getName());

        BoardView board = currentGame.getBoard();
        BoardView boardRev = currentGame.getBoard().ReverseBoard();


        if(currentGame.getResignedPlayer() != null)
        {
            gameCenter.endGame(currentGame);
            modeOptions.put("isGameOver", true);
            modeOptions.put("gameOverMessage", currentGame.getResignedPlayer().getName() + " has resigned!");
        }

        if(currentGame.hasWon() != null)
        {
            gameCenter.endGame(currentGame);
            modeOptions.put("isGameOver", true);
            modeOptions.put("gameOverMessage", currentGame.hasWon().getName() + " has won!");
        }

        vm.put("title", "Welcome to the GameBoard!");
        vm.put("gameID", currentGame.getGameID());
        session.attribute("gameID", currentGame.getGameID());
        vm.put("currentUser", currentUser);
        session.attribute("currentUser", currentUser);
        vm.put("viewMode", mode.PLAY);
        vm.put("modeOptionsAsJSON", gson.toJson(modeOptions));
        vm.put("redPlayer", currentGame.getRedPlayer());
        vm.put("whitePlayer", currentGame.getWhitePlayer());
        vm.put("activeColor", currentGame.getActiveColor());
        session.attribute("activeColor", currentGame.getActiveColor());
        vm.put("board", currentUser.equals(currentGame.getRedPlayer()) ? board : boardRev);

        return templateEngine.render(new ModelAndView(vm , "game.ftl"));
    }
}