package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.util.Message;
import spark.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;


/**
 * The UI Controller to GET the Home page.
 *
 * @author <a href='mailto:bdbvse@rit.edu'>Bryan Basham</a>
 */
public class GetHomeRoute implements Route {

  private final Message WELCOME_MSG = Message.info("Welcome to the world of online Checkers!");
  private final TemplateEngine templateEngine;
  private final PlayerLobby playerLobby;
  private final GameCenter gameCenter;
  private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());

  final String PLAYERLOBBY_KEY = "playerLobby";
  final String VIEW_NAME = "home.ftl";

  /**
   * Create the Spark Route (UI controller) to handle all {@code GET /} HTTP requests.
   * @param templateEngine
   *   the HTML template rendering engine
   */
  public GetHomeRoute(final TemplateEngine templateEngine, PlayerLobby playerLobby, GameCenter gameCenter) {
    this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
    this.playerLobby = playerLobby;
    this.gameCenter = gameCenter;
    LOG.config("GetHomeRoute is initialized.");
  }

  /**
   * Render the WebCheckers Home page.
   * @param request
   *   the HTTP request
   * @param response
   *   the HTTP response
   * @return
   *   the rendered HTML for the Home page
   */
  @Override
  public Object handle(Request request, Response response) {
    LOG.finer("GetHomeRoute is invoked.");
    Session session = request.session(true);

    Map<String, Object> vm = new HashMap<>();
    vm.put("title", "Welcome!");
    Player currentUser = session.attribute("currentUser");
    if(!playerLobby.getPlayers().isEmpty()) {
      vm.put("playerAmount", playerLobby.getLobbySize());

        if (currentUser != null) {
            ArrayList<String> opponentList = playerLobby.getOpponentList(currentUser);
            if(playerLobby.isInGamePlayer(currentUser))
            {
                response.redirect("/game");
            }
            if (!opponentList.isEmpty()) {
              vm.put("opponents", opponentList);
            }
        }
    }
    else {vm.put("playerAmount", 0);
      System.out.println(0);}


    vm.put("message", WELCOME_MSG);

    if (session.attribute("currentUser") != null) {vm.put("currentUser", session.attribute("currentUser"));}
    return templateEngine.render(new ModelAndView(vm , "home.ftl"));
  }
}
