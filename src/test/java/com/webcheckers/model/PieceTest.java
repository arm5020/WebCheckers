package com.webcheckers.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Tag("Model-tier")
public class PieceTest {

    private Piece CuT; // Component under Test

    private Piece.type type;
    private Piece.color color;

    /**
     * Set up the CuT
     */
    @BeforeEach
    public void setup() {
        this.type = Piece.type.SINGLE;
        this.color = Piece.color.RED;
        CuT = new Piece(type, color);
    }

    /**
     * Tests to see if the color is not null
     */
    @Test
    public void ctor_valid_color() {
        assertNotNull(CuT);
        assertNotNull(CuT.getColor());
    }

    /**
     * Tests to see if the type is not null
     */
    @Test
    public void ctor_valid_type() {
        assertNotNull(CuT);
        assertNotNull(CuT.getType());
    }

    /**
     * Tests whether the piece is crowned correctly
     */
    @Test
    public void ctor_test_crown() {
        CuT.crown();
        assertEquals(Piece.type.KING, CuT.getType());
    }
}
