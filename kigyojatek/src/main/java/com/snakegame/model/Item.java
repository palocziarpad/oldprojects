package com.snakegame.model;

/***
 * Representation of an item on the board
 *
 */
public class Item {
    protected byte xCoordinate;
    protected byte yCoordinate;

    /***
     * Constructor.
     * 
     * @param xCoordinate
     * @param yCoordinate
     */
    public Item(byte xCoordinate, byte yCoordinate) {
        super();
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    /**
     * @return the xCoordinate
     */
    public byte getXCoordinate() {
        return xCoordinate;
    }

    /**
     * @param xCoordinate
     *            the xCoordinate to set
     */
    public void setXCoordinate(byte xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    /**
     * @return the yCoordinate
     */
    public byte getYCoordinate() {
        return yCoordinate;
    }

    /**
     * @param yCoordinate
     *            the yCoordinate to set
     */
    public void setYCoordinate(byte yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

}
