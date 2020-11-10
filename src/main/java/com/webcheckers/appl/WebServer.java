package com.webcheckers.appl;

import com.google.gson.Gson;
import com.webcheckers.ui.*;
import spark.TemplateEngine;

import java.util.Objects;
import java.util.logging.Logger;

import static spark.Spark.post;
import static spark.Spark.get;
import static spark.Spark.staticFileLocation;

/**
 * The server that initializes the set of HTTP request handlers.
 * This defines the <em>web application interface</em> for this
 * WebCheckers application.
 *
 * <p>
 * There are multiple ways in which you can have the client issue a
 * request and the application generate responses to requests. If your team is
 * not careful when designing your approach, you can quickly create a mess
 * where no one can remember how a particular request is issued or the response
 * gets generated. Aim for consistency in your approach for similar
 * activities or requests.
 * </p>
 *
 * <p>Design choices for how the client makes a request include:
 * <ul>
 *     <li>Request URL</li>
 *     <li>HTTP verb for request (GET, POST, PUT, DELETE and so on)</li>
 *     <li><em>Optional:</em> Inclusion of request parameters</li>
 * </ul>
 * </p>
 *
 * <p>Design choices for generating a response to a request include:
 * <ul>
 *     <li>View templates with conditional elements</li>
 *     <li>Use different view templates based on results of executing the client request</li>
 *     <li>Redirecting to a different application URL</li>
 * </ul>
 * </p>
 *
 * @author <a href='mailto:bdbvse@rit.edu'>Bryan Basham</a>
 */
public class WebServer {

  private final TemplateEngine templateEngine;
  private final PlayerLobby playerLobby;
  private final GameCenter gameCenter;
  private final Gson gson;
  private final Logger LOG = Logger.getLogger(WebServer.class.getName());

  /**
   * The URL pattern to request the Home page.
   */
  private static final String LOCAL_HOST_DEFAULT = "/";
  private static final String HOME_URL = "/home";
  private static final String SIGNIN_URL = "/signin";
  private static final String RESIGN_URL = "/resignGame";
  private static final String GAME_URL = "/game";
  private static final String CHECK_TURN_URL = "/checkTurn";
  private static final String SIGNOUT_URL = "/signout";
  private static final String SUBMIT_TURN_URL = "/submitTurn";
  private static final String VALIDATE_MOVE_URL = "/validateMove";
  private static final String BACKUP_MOVE_URL = "/backupMove";
  private static final String SPECTATOR_GAME_URL = "/spectator/game";
  private static final String SPECTATOR_STOPWATCHING_URL = "/spectator/stopWatching";
  private static final String SPECTATOR_CHECK_TURN = "/spectator/checkTurn";
  /**
   * The constructor for the Web Server.
   *
   * @param templateEngine
   *    The default {@link TemplateEngine} to render page-level HTML views.
   * @param gson
   *    The Google JSON parser object used to render Ajax responses.
   *
   * @throws NullPointerException
   *    If any of the parameters are {@code null}.
   */
  public WebServer(final TemplateEngine templateEngine, final Gson gson) {
    // validation
    Objects.requireNonNull(templateEngine, "templateEngine must not be null");
    Objects.requireNonNull(gson, "gson must not be null");
    //
    this.playerLobby = new PlayerLobby();
    this.gameCenter = new GameCenter();
    this.templateEngine = templateEngine;
    this.gson = gson;
  }

  /**
   * Initialize all of the HTTP routes that make up this web application.
   *
   * <p>
   * Initialization of the web server includes defining the location for static
   * files, and defining all routes for processing client requests. The method
   * returns after the web server finishes its initialization.
   * </p>
   */
  public void initialize() {
    // Configuration to serve static files
    staticFileLocation("/public");

    get(LOCAL_HOST_DEFAULT, new GetHomeRoute(templateEngine, playerLobby, gameCenter));

    get(HOME_URL, new GetHomeRoute(templateEngine, playerLobby, gameCenter));

    get(GAME_URL, new GetGameRoute(templateEngine, gameCenter, playerLobby, gson));

    get(SIGNIN_URL, new GetSigninRoute(templateEngine));

    get(SPECTATOR_GAME_URL, new GetSpectatorGameRoute(templateEngine, gameCenter, gson));

    get(SPECTATOR_STOPWATCHING_URL, new GetSpectatorStopWatchingRoute());

    post(SIGNIN_URL, new PostSigninRoute(templateEngine, playerLobby));

    post(SIGNOUT_URL, new PostSignoutRoute(templateEngine, playerLobby));

    post(RESIGN_URL, new PostResignGameRoute(playerLobby, gameCenter, gson));

    post(CHECK_TURN_URL, new PostCheckTurnRoute(templateEngine, gson, gameCenter));

    post(GAME_URL, new PostGameRoute(templateEngine, gameCenter, playerLobby));

    post(SUBMIT_TURN_URL, new PostSubmitTurnRoute(gameCenter, gson));

    post(VALIDATE_MOVE_URL, new PostValidateMove(gameCenter, gson));

    post(BACKUP_MOVE_URL, new PostBackupMove(gameCenter, gson));

    post(SPECTATOR_CHECK_TURN, new PostSpectatorCheckTurn(gameCenter, gson, playerLobby));

    LOG.config("WebServer is initialized.");
  }

}