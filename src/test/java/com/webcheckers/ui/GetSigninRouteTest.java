package com.webcheckers.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Tag("UI-tier")
public class GetSigninRouteTest {

    /**
     * Parameters used for testing.
     */
    private GetSigninRoute CuT;
    private TemplateEngine templateEngine;
    private Request request;
    private Session session;
    private Response response;

    /**
     * Initialization.
     */
    @BeforeEach
    private void setup() {
        request = mock(Request.class);
        session = mock(Session.class);
        templateEngine = mock(TemplateEngine.class);
        CuT = new GetSigninRoute(templateEngine);
    }

    /**
     * See if the attributes from getSignInRoute works.
     */
    @Test
    public void testAttributes(){

        final TemplateEngineTester helper = new TemplateEngineTester();

        when(templateEngine.render(any(ModelAndView.class) )).thenAnswer(helper.makeAnswer());
        CuT.handle(request, response);

        helper.assertViewModelExists();
        helper.assertViewModelIsaMap();



    }
}
