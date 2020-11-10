package com.webcheckers.model;

import java.awt.*;

public class Move {
    private Position start;
    private Position end;
    public boolean hop;
    public boolean moved;

    public Move(Position start, Position end)
    {
        this.start = start;
        this.end = end;
        this.hop = false;
        this.moved = false;
    }

    /**
     * Returns the initial state.
     * @return the initial state
     */
    public Position getStart()
    {
        return start;
    }

    /**
     * Returns the end state.
     * @return the end state
     */
    public Position getEnd() {
        return end;
    }

    /**
     * Sets aa a hop move.
     */
    public void setHop() {this.hop = true;}

    /**
     * Returns whether move is a hop move
     * @return Whether the move is a hop
     */
    public boolean isHop() {return this.hop;}

    /**
     * Sets the Move to moved.
     */
    public void setMoved() {this.moved = true;}

    /**
     * Returns if move has occurred.
     * @return Whether the move has occurred
     */
    public boolean hasMoved() {return this.moved;}
}
