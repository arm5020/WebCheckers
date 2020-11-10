package com.webcheckers.model;

public class Piece {

    public enum type
    {SINGLE, KING}

    public enum color
    {RED, WHITE}

    private type type;
    private color color;

    public Piece(type pieceType, color pieceColor)
    {
        this.type = pieceType;
        this.color = pieceColor;
    }

    /**
     * Returns the piece type.
     * @return The piece type
     */
    public type getType() {
        return this.type;
    }

    /**
     * Returns the color type.
     * @return The color type
     */
    public color getColor()
    {
        return this.color;
    }

    /**
     * Crowns a non king piece.
     */
    public void crown()
    {
        this.type = type.KING;
    }
}
