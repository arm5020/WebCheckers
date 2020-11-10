package com.webcheckers.appl;

import com.webcheckers.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@Tag("Application-tier")
public class PlayerTest {

    /**
     * Parameters used for testing.
     */
    private Player CuT;
    private String name;
    private Player opponent;

    /**
     * Initialization.
     */
    @BeforeEach
    private void setup(){
        name = "Player";
        opponent = null;
        CuT = new Player(name);
    }

    @Test
    public void testgetName(){
        assertEquals(name, CuT.name);
    }


}
