package com.webcheckers.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Row implements Iterable{

    private static final int SIZE = 8;
    public int index;
    public ArrayList<Space> spaces;

    public Row(int index){

        this.spaces = new ArrayList<>();
        this.index = index;
        for (int i = 0 ; i < SIZE; i++) {
            this.spaces.add(i, new Space(i, index));
        }
    }

    public Row(ArrayList<Space> arr, int index){
        this.spaces = arr;
        this.index = index;
    }

    public int getIndex()
    {
        return this.index;
    }
    /**
     * Returns the index of the row.
     * @return The index
     */
    public Space getSpace(int cellIdx)
    {
        return spaces.get(cellIdx);
    }

    /**
     * Sets a given space.
     * @param col The col of the space
     * @param piece The piece to add
     * @param valid Whether the space is valid
     */
    public void setSpace(int col, Piece piece, boolean valid) {
        Space temp = spaces.get(col);
        temp.isValid();
        temp.piece = piece;
    }

    /**
     * Reverses the spaces in a row.
     * @return The updated row
     */
    public Row reverseSpaces(){
        int index = this.index;
        ArrayList<Space> temp = new ArrayList<>();
        temp.add(0, spaces.get(7));
        temp.add(1, spaces.get(6));
        temp.add(2, spaces.get(5));
        temp.add(3, spaces.get(4));
        temp.add(4, spaces.get(3));
        temp.add(5, spaces.get(2));
        temp.add(6, spaces.get(1));
        temp.add(7, spaces.get(0));
        return new Row(temp, index);
    }

    /**
     * Returns an iterator for the spaces
     * @return The iterator
     */
    public Iterator<Space> iterator() {
        return spaces.iterator();
    }
}
