package com.webcheckers.model;

public class Space {

    public final int cellIdx;
    final int rowIdx;
    public Piece piece;
    public Space next;
    final String color;


    public Space(int cellIdx, int rowIdx){
        this.cellIdx = cellIdx;
        this.rowIdx = rowIdx;

        if ((cellIdx + rowIdx) % 2 == 0) {
            this.color = "LIGHT";
            this.piece = null;
            }
        else {
            this.color = "DARK";
            if(rowIdx < 3 ) {
                updatePiece(new Piece(Piece.type.SINGLE, Piece.color.WHITE));
            }
            else if (rowIdx > 4){
                updatePiece(new Piece(Piece.type.SINGLE, Piece.color.RED));
            }
            else {
                updatePiece(null);
            }
        }
    }

    /**
     * Returns the cell index.
     * @return The cell index
     */
    public int getCellIdx(){
        return this.cellIdx;
    }

    /**
     * Checks to see if the space is valid for a piece.
     * @return Whether its valid
     */
    public boolean isValid() {
        return (this.color.equals("DARK") && !hasPiece());
    }

    /**
     * Checks if a piece exists.
     * @return whether a piece exists
     */
    public boolean hasPiece()
    {
        if(this.piece == null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     * Returns the piece of a space.
     * @return The piece
     */
    public Piece getPiece() {
        return this.piece;
    }

    /**
     * Updates the piece
     * @param piece the piece
     */
    public void updatePiece(Piece piece)
    {
        this.piece = piece;
    }
}
