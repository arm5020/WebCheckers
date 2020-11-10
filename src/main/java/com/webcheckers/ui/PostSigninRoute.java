package com.webcheckers.ui;

import com.webcheckers.model.Player;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.util.Message;
import spark.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PostSigninRoute implements Route {

    private final TemplateEngine templateEngine;
    private final PlayerLobby playerLobby;

    public PostSigninRoute(TemplateEngine templateEngine, PlayerLobby playerLobby) {
        this.playerLobby = playerLobby;
        this.templateEngine = templateEngine;
    }

    /**
     * Render the WebCheckers Home page after sign in or sign in if invalid username.
     * @param request
     *   the HTTP request
     * @param response
     *   the HTTP response
     * @return
     *   the rendered HTML for the Home or Sign in page
     */
    @Override
    public Object handle(Request request, Response response) {
        Session session = request.session();
        Map<String, Object> vm = new HashMap<>();

        String UserID = request.queryParams("name");
        Player currentPlayer = new Player(UserID);
        boolean isAlphaNumeric = UserID != null &&
                UserID.chars().allMatch(Character::isLetterOrDigit);

        boolean validSignin = true;

        if (UserID.equals("")) {
            vm.put("message", Message.error("Username must not be blank!"));
            vm.put("title", "Sign-In Page...");
            validSignin = false;
        }
        else if (!isAlphaNumeric) {
            vm.put("message", Message.error("Username must contain only alphanumeric characters!"));
            vm.put("title", "Sign-In Page...");
            validSignin = false;
        }
        else if (playerLobby.contains(currentPlayer)) {
            vm.put("message", Message.error("Username must not be already taken!"));
            vm.put("title", "Sign-In Page...");
            validSignin = false;
        }

        if(validSignin) {
            if (session.attribute("playerList") == null) {
                playerLobby.addPlayer(currentPlayer);
                session.attribute("playerList", playerLobby.getNames(playerLobby.getPlayers()));
            }
            else { playerLobby.addPlayer(currentPlayer); session.attribute("playerList", playerLobby.getNames(playerLobby.getPlayers()));}
            System.out.println(session.attribute("playerList").toString());

            session.attribute("currentUser", currentPlayer);

            ArrayList playerList = playerLobby.getNames(playerLobby.getPlayers());
            if (session.attribute("currentUser") != null) {
                Player currentUser = session.attribute("currentUser");
                ArrayList opponentList = playerList;
                opponentList.remove(currentUser.name);
                if (!opponentList.isEmpty()) {
                    vm.put("opponents", opponentList);
                }
            }
            response.redirect("/home");
        }
        else
        {
            return templateEngine.render( new ModelAndView(vm, "signin.ftl"));
        }
        return null;
    }

}

