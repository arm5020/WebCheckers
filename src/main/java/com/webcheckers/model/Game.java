package com.webcheckers.model;

import spark.ModelAndView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

public class Game {
    private Player whitePlayer;
    private Player redPlayer;
    private Player currentUser;
    private Piece.color activeColor;
    private int gameID;
    private boolean gameActive;
    private ArrayList<Move> turnMoves;
    private BoardView board;
    private Player resignedPlayer;
    private boolean turnMade;

    public Game(Player whitePlayer, Player redPlayer, int id)
    {
        this.activeColor = Piece.color.RED;
        this.currentUser = redPlayer;
        this.whitePlayer = whitePlayer;
        this.redPlayer = redPlayer;
        this.gameID = id;
        this.turnMoves = new ArrayList<>();
        this.board = new BoardView();
        this.resignedPlayer = null;
        this.turnMade = false;

    }

    /**
     * Returns the resigned player.
     * @return The resigned player
     */
    public Player getResignedPlayer()
    {
        return this.resignedPlayer;
    }

    /**
     * Resigns the given player.
     * @param player The player
     */
    public void resign(Player player)
    {
        resignedPlayer = player;
    }

    /**
     * Switches the active color of the game.
     */
    public void switchActiveColor()
    {
        if(activeColor == Piece.color.WHITE)
        {
            activeColor = Piece.color.RED;
        }
        else
        {
            activeColor = Piece.color.WHITE;
        }
    }

    /**
     * Returns whether a turn has been made.
     * @return
     */
    public boolean isTurnMade()
    {
        return this.turnMade;
    }

    /**
     * Gets the white player.
     * @return The white player
     */
    public Player getWhitePlayer()
    {
        return whitePlayer;
    }

    /**
     * Gets the red player.
     * @return The red player
     */
    public Player getRedPlayer()
    {
        return redPlayer;
    }

    /**
     * Ends the current game.
     */
    public void endGame()
    {
        gameActive = false;
    }

    /**
     * Returns whether the game is over.
     * @return Whether the game is over
     */
    public boolean isGameOver()
    {
        return gameActive;
    }

    public boolean isMyTurn()
    {
        return (currentUser == redPlayer && activeColor == Piece.color.RED)
                || ( currentUser== whitePlayer && activeColor == Piece.color.WHITE);
    }

    /**
     * Returns the game id.
     * @return The game id
     */
    public int getGameID()
    {
        return gameID;
    }

    /**
     * returns the active game color
     * @return The active color
     */
    public Piece.color getActiveColor()
    {
        return this.activeColor;
    }

    /**
     * Adds a move to the list
     * @param move The move
     */
    public void addMove(Move move) {
        turnMoves.add(move);
    }

    /**
     * Returns the latest move
     * @return The latest move
     */
    public Move getLastMove(){
        if (!turnMoves.isEmpty()) {
            return turnMoves.get(turnMoves.size() - 1);
        }
        else { return null;}
    }

    /**
     * Returns the board view
     * @return The board view
     */
    public BoardView getBoard(){return this.board;}

    /**
     * Makes a move by updating the board view
     * @param move The move
     */
    public void makeMove(Move move) {
        this.board.movePiece(move);
        this.turnMade = true;
    }

    /**
     * Undoes the last move made
     * @param move The move
     */
    public void undoMove(Move move) {this.board.undoMove(move);}

    /**
     * Removes the last move from the list
     * @return The last move
     */
    public Move removeLastMove()
    {
        return turnMoves.remove(turnMoves.size() - 1);
    }

    /**
     * Clears all moves from list
     */
    public void clearMoves(){this.turnMoves = new ArrayList<>();}

    /**
     * Determines which player has won.
     * @return The winning player
     */
    public Player hasWon(){
        int whitePieces = 0;
        int redPieces = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (this.board.getBoardSpace(i, j).hasPiece()) {
                    Piece p = this.board.getBoardPiece(i, j);
                    if (p.getColor() == Piece.color.RED) {redPieces++;}
                    else if (p.getColor() == Piece.color.WHITE) {whitePieces++;}
                }
            }
        }
        System.out.println(redPieces);
        System.out.println(whitePieces);
        if (redPieces == 0) { return this.whitePlayer;}
        else if (whitePieces == 0) { return this.redPlayer;}
        else {return null;}
    }
}
