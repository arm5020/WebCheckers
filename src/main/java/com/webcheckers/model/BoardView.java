package com.webcheckers.model;

import java.awt.*;
import java.util.*;

public class BoardView implements Iterable {

    private final int SIZE = 8;
    private ArrayList<Row> rows;
    Map<Position, Piece> removed = new HashMap<>();


    public BoardView() {
        this.rows = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            this.rows.add(new Row(i));
        }
    }

    public BoardView(ArrayList<Row> arr){
        this.rows = arr;
    }

    /**
     * Reverses the board for the white player.
     * @return The new board
     */
    public BoardView ReverseBoard(){
        ArrayList<Row> temp = new ArrayList<>();
        temp.add(0, rows.get(7).reverseSpaces());
        temp.add(1, rows.get(6).reverseSpaces());
        temp.add(2, rows.get(5).reverseSpaces());
        temp.add(3, rows.get(4).reverseSpaces());
        temp.add(4, rows.get(3).reverseSpaces());
        temp.add(5, rows.get(2).reverseSpaces());
        temp.add(6, rows.get(1).reverseSpaces());
        temp.add(7, rows.get(0).reverseSpaces());
        return new BoardView(temp);
    }


    /**
     * Returns a piece given a row and cell
     * @param rowIdx the row
     * @param cellIdx the cell
     * @return the piece
     */
    public Piece getBoardPiece(int rowIdx, int cellIdx)
    {
        return rows.get(rowIdx).getSpace(cellIdx).getPiece();
    }

    /**
     * Returns a space given a row and cell.
     * @param rowIdx the row
     * @param cellIdx the cell
     * @return the piece
     */
    public Space getBoardSpace(int rowIdx, int cellIdx)
    {
        return rows.get(rowIdx).getSpace(cellIdx);
    }

    /**
     * Completes a given move. Move validation is NOT checked by this function.
     * Must be validated while creating the move.
     * @param move the move
     */
    public void movePiece(Move move)
    {
        Position init = move.getStart();
        Position end = move.getEnd();
        Piece selected = getBoardPiece(init.getRow(), init.getCell());
        getBoardSpace(init.getRow(), init.getCell()).updatePiece(null);
        if(Math.abs(move.getStart().getRow() - move.getEnd().getRow()) == 2) {
            Position pos = new Position((move.getEnd().getRow() + move.getStart().getRow()) / 2, ((move.getEnd().getCell() % 8) + (move.getStart().getCell() % 8)) / 2);
            removed.put(pos, getBoardPiece(pos.getRow(), pos.getCell()));
            setSpace(pos, null, true);
        }
        if(end.getRow() == 0 && selected.getColor() == Piece.color.RED ||
                end.getRow() == 7 && selected.getColor() == Piece.color.WHITE)
        {
            getBoardSpace(end.getRow(), end.getCell()).updatePiece(new Piece(Piece.type.KING, selected.getColor()));
        }
        else if(selected.getType() == Piece.type.KING)
        {
            getBoardSpace(end.getRow(), end.getCell()).updatePiece(new Piece(Piece.type.KING, selected.getColor()));
        }
        else
        {
            getBoardSpace(end.getRow(), end.getCell()).updatePiece(new Piece(selected.getType(), selected.getColor()));
        }
    }

    /**
     * Undoes a given move.
     * @param move the move
     */
    public void undoMove(Move move)
    {
        Position init = move.getStart();
        Position end = move.getEnd();
        Piece selected = getBoardPiece(end.getRow(), end.getCell());
        getBoardSpace(end.getRow(), end.getCell()).updatePiece(null);
        if(end.getRow() == 0 && selected.getColor() == Piece.color.RED ||
                end.getRow() == 7 && selected.getColor() == Piece.color.WHITE)
        {
            getBoardSpace(init.getRow(), init.getCell()).updatePiece(new Piece(Piece.type.SINGLE, selected.getColor()));
        }
        else
        {
            getBoardSpace(init.getRow(), init.getCell()).updatePiece(new Piece(selected.getType(), selected.getColor()));
        }

    }

    /**
     * Iterator for the rows of the board.
     * @return The iterator
     */
    public Iterator<Row> iterator() {
        return rows.iterator();
    }

    public void setSpace(Position pos, Piece piece, boolean valid) {
        this.rows.get(pos.getRow()).setSpace(pos.getCell() % 8, piece, valid);
    }
}



