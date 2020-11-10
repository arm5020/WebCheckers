package com.webcheckers.appl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Tag("Application-tier")
class GameCenterTest {

    private GameCenter CuT;

    @BeforeEach
    private void setup(){
        CuT = new GameCenter();
    }

    // Change test when method is updated
    @Test
    void getGameNotNull() {
        assertNull(CuT.getGame(null));
    }
    
}