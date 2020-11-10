package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.*;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

import java.util.ArrayList;
import java.util.List;

public class PostValidateMove implements Route {
    private final Gson gson;
    private final GameCenter gameCenter;

    public PostValidateMove(GameCenter gameCenter, Gson gson)
    {
        this.gameCenter = gameCenter;
        this.gson = gson;
    }


    /**
     * Determines if a move is valid in a game.
     * @param move The move
     * @param game The game
     * @return Whether the move is valid in this game
     */
    public boolean isValid(Move move, Game game){
        boolean hop = false;
        boolean moved = false;
        if (game.getLastMove() != null && game.getLastMove().isHop()){ hop = true;}
        if (game.getLastMove() != null && game.getLastMove().hasMoved()){ moved = true;}
        //Normal RED move
        if (game.getActiveColor() == Piece.color.RED){
            if (move.getStart().getRow() - 1 == move.getEnd().getRow()){
                if (move.getStart().getCell() + 1 < 8 && move.getStart().getCell() + 1 == move.getEnd().getCell()){
                    move.setMoved();
                    return !hop && !moved;
                }
                else if (move.getStart().getCell() - 1 == move.getEnd().getCell()){
                    move.setMoved();
                    return !hop && !moved;
                }
            }
        }
        //RED hop
        if (game.getActiveColor() == Piece.color.RED){
            if (move.getStart().getRow() - 2 == move.getEnd().getRow()){
                if (move.getStart().getCell() + 1 < 8 && game.getBoard().getBoardPiece(move.getStart().getRow() - 1 , (move.getStart().getCell() + 1)) != null && game.getBoard().getBoardPiece(move.getStart().getRow() - 1 , (move.getStart().getCell() + 1)).getColor() == Piece.color.WHITE) {
                    if (move.getStart().getCell() + 2 < 8 && move.getStart().getCell() + 2 == move.getEnd().getCell()) {
                        move.setHop();
                        return !moved;
                    }
                }
                    if (game.getBoard().getBoardPiece(move.getStart().getRow() - 1 , (move.getStart().getCell() - 1)).getColor() != null && game.getBoard().getBoardPiece(move.getStart().getRow() - 1 , (move.getStart().getCell() - 1)).getColor() == Piece.color.WHITE){
                        if (move.getStart().getCell() - 2 == move.getEnd().getCell()) {
                            move.setHop();
                            return !moved;
                    }
                }
            }
        }
        //King RED move
        if (game.getActiveColor() == Piece.color.RED){
            if ( game.getBoard().getBoardPiece(move.getStart().getRow() , (move.getStart().getCell())) != null && game.getBoard().getBoardPiece(move.getStart().getRow() , (move.getStart().getCell())).getType() == Piece.type.KING) {
                if (move.getStart().getRow() + 1 == move.getEnd().getRow()) {
                    if (move.getStart().getCell() + 1 < 8 && move.getStart().getCell() + 1 == move.getEnd().getCell()) {
                        move.setMoved();
                        return !hop && !moved;
                    } else if (move.getStart().getCell() - 1 == move.getEnd().getCell()) {
                        move.setMoved();
                        return !hop && !moved;
                    }
                }
            }
        }
        //King RED hop
        if (game.getActiveColor() == Piece.color.RED) {
            if (game.getBoard().getBoardPiece(move.getStart().getRow(), (move.getStart().getCell())) != null && game.getBoard().getBoardPiece(move.getStart().getRow(), (move.getStart().getCell())).getType() == Piece.type.KING) {
                if (move.getStart().getRow() + 1 < 8 && move.getStart().getCell() + 1 < 8 && game.getBoard().getBoardPiece(move.getStart().getRow() + 1 , (move.getStart().getCell() + 1)) != null && game.getBoard().getBoardPiece(move.getStart().getRow() + 1 , (move.getStart().getCell() + 1)).getColor() == Piece.color.WHITE) {
                    if (move.getStart().getRow() + 2 == move.getEnd().getRow()) {
                        if (move.getStart().getCell() + 2 < 8 && move.getStart().getCell() + 2 == move.getEnd().getCell()) {
                            move.setHop();
                            return !moved;
                        }
                        if (game.getBoard().getBoardPiece(move.getStart().getRow() + 1 , (move.getStart().getCell() - 1)) != null && game.getBoard().getBoardPiece(move.getStart().getRow() + 1 , (move.getStart().getCell() - 1)).getColor() == Piece.color.WHITE) {
                            if (move.getStart().getRow() + 2 == move.getEnd().getRow()) {
                                if (move.getStart().getCell() - 2 == move.getEnd().getCell()) {
                                    move.setHop();
                                    return !moved;
                                }
                            }
                        }
                    }
                }
            }
        }
        //Normal WHITE move
        if (game.getActiveColor() == Piece.color.WHITE){
            if (move.getStart().getRow() + 1 < 8 && move.getStart().getRow() + 1 == move.getEnd().getRow()){
                if (move.getStart().getCell() + 1 == move.getEnd().getCell()){
                    move.setMoved();
                    return !hop && !moved;
                }
                else if (move.getStart().getCell() - 1 == move.getEnd().getCell()){
                    move.setMoved();
                    return !hop && !moved;
                }
            }
        }
        //WHITE hop
        if (game.getActiveColor() == Piece.color.WHITE){
            if (move.getStart().getRow() + 2 < 8 && move.getStart().getRow() + 2 == move.getEnd().getRow()){
                if (move.getStart().getRow() + 1 < 8 && move.getStart().getCell() + 1 < 8 && game.getBoard().getBoardPiece(move.getStart().getRow() + 1 , (move.getStart().getCell() + 1)) != null && game.getBoard().getBoardPiece(move.getStart().getRow() + 1 , (move.getStart().getCell() + 1)).getColor() == Piece.color.RED) {
                    if (move.getStart().getCell() + 2 == move.getEnd().getCell()) {
                        move.setHop();
                        return !hop && !moved;
                    }
                }
                if (move.getStart().getRow() + 1 < 8 && game.getBoard().getBoardPiece(move.getStart().getRow() + 1 , (move.getStart().getCell() - 1)) != null && game.getBoard().getBoardPiece(move.getStart().getRow() + 1 , (move.getStart().getCell() - 1)).getColor() == Piece.color.RED){
                    if (move.getStart().getCell() - 2 == move.getEnd().getCell()) {
                        move.setHop();
                        return !hop && !moved;
                    }
                }
            }
        }
        //King WHITE move
        if (game.getActiveColor() == Piece.color.WHITE){
            if ( game.getBoard().getBoardPiece(move.getStart().getRow() , (move.getStart().getCell())) != null && game.getBoard().getBoardPiece(move.getStart().getRow() , (move.getStart().getCell())).getType() == Piece.type.KING) {
                if (move.getStart().getRow() - 1 == move.getEnd().getRow()) {
                    if (move.getStart().getCell() + 1 == move.getEnd().getCell()) {
                        move.setMoved();
                        return !hop && !moved;
                    } else if (move.getStart().getCell() - 1 == move.getEnd().getCell()) {
                        move.setMoved();
                        return !hop && !moved;
                    }
                }
            }
        }
        //King WHITE hop
        if (game.getActiveColor() == Piece.color.WHITE) {
            if (game.getBoard().getBoardPiece(move.getStart().getRow(), (move.getStart().getCell())) != null && game.getBoard().getBoardPiece(move.getStart().getRow(), (move.getStart().getCell())).getType() == Piece.type.KING) {
                if (game.getBoard().getBoardPiece(move.getStart().getRow() - 1 , (move.getStart().getCell() + 1)) != null && game.getBoard().getBoardPiece(move.getStart().getRow() - 1 , (move.getStart().getCell() + 1)).getColor() == Piece.color.RED) {
                    if (move.getStart().getRow() - 2 == move.getEnd().getRow()) {
                        if (move.getStart().getCell() + 2 == move.getEnd().getCell()) {
                            move.setHop();
                            return !moved;
                        }
                        if (game.getBoard().getBoardPiece(move.getStart().getRow() - 1 , (move.getStart().getCell() - 1)) != null && game.getBoard().getBoardPiece(move.getStart().getRow() - 1 , (move.getStart().getCell() - 1)).getColor() == Piece.color.RED) {
                            if (move.getStart().getRow() - 2 == move.getEnd().getRow()) {
                                if (move.getStart().getCell() - 2 == move.getEnd().getCell()) {
                                    move.setHop();
                                    return !moved;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Validates a move
     * @param request
     *   the HTTP request
     * @param resp
     *   the HTTP response
     * @return
     *   A message telling us if the move is valid
     */
    @Override
    public Object handle(Request request, Response resp)
    {
        Session session = request.session();

        Move move = gson.fromJson(request.queryParams("actionData"), Move.class);
        Game currentGame = gameCenter.getGame((int)session.attribute("gameID"));

        boolean valid = isValid(move, currentGame);
        System.out.println(valid);
        if (valid) {
            currentGame.addMove(move);
            currentGame.makeMove(move);
            return gson.toJson("Valid move");
        }
        return gson.toJson("Invalid Move, take it back.");
    }
}