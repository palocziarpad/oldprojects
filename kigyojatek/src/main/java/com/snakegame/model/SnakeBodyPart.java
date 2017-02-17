package com.snakegame.model;

import java.util.LinkedList;

import com.snakegame.BodyPartType;
import com.snakegame.Direction;

/**
 * Representation of the snake's body part.
 */
public class SnakeBodyPart {
    private byte xCoordinate;
    private byte yCoordinate;
    private Direction direction;
    private BodyPartType bodyPartType;

    /**
     * Constructor.
     * 
     * @param xCoordinate
     * @param yCoordinate
     */
    public SnakeBodyPart(byte xCoordinate, byte yCoordinate) {
	this.xCoordinate = xCoordinate;
	this.yCoordinate = yCoordinate;
	this.direction = Direction.RIGHT;
	this.bodyPartType = BodyPartType.HEAD;
    }

    /**
     * Constructor.
     * 
     * @param xCoordinate
     * @param yCoordinate
     * @param bodyPartType
     */
    public SnakeBodyPart(byte xCoordinate, byte yCoordinate, BodyPartType bodyPartType) {
	this.xCoordinate = xCoordinate;
	this.yCoordinate = yCoordinate;
	this.direction = Direction.RIGHT;
	this.bodyPartType = bodyPartType;
    }

    /**
     * Get the body part type
     * 
     * @return
     */
    public BodyPartType getPartType() {
	return bodyPartType;
    }

    /**
     * Set the body part type
     * 
     * @param partKind
     */
    public void setPartType(BodyPartType partKind) {
	this.bodyPartType = partKind;
    }

    /**
     * Get the X coordinate
     * 
     * @return
     */
    public byte getXCoordinate() {
	return xCoordinate;
    }

    /**
     * Set the X coordinate
     * 
     * @param x
     */
    public void setXCoordinate(byte x) {
	this.xCoordinate = x;
    }

    /**
     * Get the Y coordinate
     * 
     * @return
     */
    public byte getYCoordinate() {
	return yCoordinate;
    }

    /**
     * Set the Y coordinate
     * 
     * @param y
     */
    public void setYCoordinate(byte y) {
	this.yCoordinate = y;
    }

    /**
     * Get the direction
     * 
     * @return
     */
    public Direction getDirection() {
	return direction;
    }

    /**
     * Set the direction
     * 
     * @param direction
     */
    public void setDirection(Direction direction) {
	this.direction = direction;
    }

    /**
     * is the snake body part the given place
     * 
     * @param food
     * @return true if yes
     */
    public boolean isAt(SnakeBodyPart food) {
	return (xCoordinate == food.xCoordinate && yCoordinate == food.yCoordinate);
    }

    /**
     * is the snake body part the given places
     * 
     * @param food
     * @return
     */
    public boolean isAt(SnakeBodyPart food[]) {
	for (int i = 0; i < food.length; i++) {
	    if (xCoordinate == food[i].xCoordinate && yCoordinate == food[i].yCoordinate) {
		return true;
	    }
	}
	return false;
    }

    /**
     * The z parameter will be only for the to use the int return type.
     */
    public int isAt(LinkedList<SnakeBodyPart> foods, int z) {
	SnakeBodyPart sbp;
	for (int k = 0; k < foods.size(); k++) {
	    sbp = foods.get(k);
	    if (xCoordinate == sbp.xCoordinate && yCoordinate == sbp.yCoordinate)
		return k;
	}

	return -1;
    }

    /**
     * Set the state of the body part
     * 
     * @param xCoordinate
     * @param yCoordinate
     * @param direction
     */
    public void setState(byte xCoordinate, byte yCoordinate, Direction direction) {
	this.xCoordinate = xCoordinate;
	this.yCoordinate = yCoordinate;
	this.direction = direction;
    }

}
