package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spark.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class GetHomeRouteTest {
    private GetHomeRoute CuT;

    private Request request;
    private TemplateEngine engine;
    private GameCenter gameCenter;
    private Response response;


    @BeforeEach
    public void setup()
    {
        request = mock(Request.class);
        Session session = mock(Session.class);
        when(request.session()).thenReturn(session);
        response = mock(Response.class);
        engine = mock(TemplateEngine.class);
        PlayerLobby playerLobby = mock(PlayerLobby.class);
        gameCenter = mock(GameCenter.class);

                // create a unique CuT for each test
        // the GameCenter is friendly but the engine mock will need configuration

        CuT = new GetHomeRoute(engine, playerLobby, gameCenter);
    }

    /**
     * Tests if the homepage loads when a new session is created.
     */
    @Test
    public void new_session()
    {
        final TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        CuT.handle(request, response);

        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();
        //testHelper.assertViewName(GetHomeRoute.VIEW_NAME);
        //verify(session).attribute(eq(GetHomeRoute.PLAYERLOBBY_KEY), any(PlayerLobby.class));
    }

    /**
     *  Makes sure the display name is not null during a new session
     */
    @Test
    public void display_name()
    {
        final TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());
        CuT.handle(request, response);
        assertNotNull(request.attribute("currentUser"));
    }

    /**
     * Makes sure the current user is not in the opponent list
     */
    @Test
    public void check_opponents()
    {
        final TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        ArrayList<String> names = request.attribute("opponents");
        for (String name : names) {
            assertNotEquals(request.attribute("currentUser"), name);
        }
    }
}
