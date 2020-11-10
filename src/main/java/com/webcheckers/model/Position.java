package com.webcheckers.model;



public class Position {
	private int row;
	private int cell;
	
	/**
	 * Creates a new Position with rows and cells
	 * @param row The row of the piece
	 * @param cell The column or creation index
	 */
	public Position(int row, int cell) {
		this.row = row;
		this.cell = cell;
	}
	
	/**
	 * Gets the row of this position
	 * @return The row this position points to
	 */
	public int getRow() {
		return row;
	}
	
	/**
	 * Gets the cell of this position
	 * @return The cell this position points to
	 */
	public int getCell() {
		return cell;
	}
}
