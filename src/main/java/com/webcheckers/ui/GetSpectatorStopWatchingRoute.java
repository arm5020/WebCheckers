package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.Game;
import spark.Request;
import spark.Response;
import spark.Route;

public class GetSpectatorStopWatchingRoute implements Route {

    public GetSpectatorStopWatchingRoute(){}

    /**
     * Ends a session of spectating.
     * @param request
     *   the HTTP request
     * @param response
     *   the HTTP response
     * @return
     *   null
     */
    @Override
    public Object handle(Request request, Response response) {
        response.redirect("/");
        return null;
    }
}
