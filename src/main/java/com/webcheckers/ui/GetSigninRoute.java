package com.webcheckers.ui;

import com.webcheckers.util.Message;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

public class GetSigninRoute implements Route{

    private final TemplateEngine templateEngine;
    private static final Logger LOG = Logger.getLogger(GetSigninRoute.class.getName());

    private final Message SIGNIN_MSG = Message.info("This is the Sign-In Page");

    /**
     * Create the Spark Route (UI controller) to handle all {@code GET /} HTTP requests.
     *
     * @param templateEngine
     *   the HTML template rendering engine
     */
    public GetSigninRoute(final TemplateEngine templateEngine) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        LOG.config("GetSigninRoute is initialized.");
    }

    /**
     * Render the WebCheckers Sign in page.
     *
     * @param request
     *   the HTTP request
     * @param response
     *   the HTTP response
     * @return
     *   the rendered HTML for the SIGNIN page
     */
    @Override
    public Object handle(Request request, Response response) {
        LOG.finer("GetSigninRoute is invoked.");

        Map<String, Object> vm = new HashMap<>();

        vm.put("title", "Sign In!");
        vm.put("message", SIGNIN_MSG);

        return templateEngine.render(new ModelAndView(vm , "signin.ftl"));
    }

}
