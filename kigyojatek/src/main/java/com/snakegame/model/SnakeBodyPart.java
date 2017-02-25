package com.snakegame.model;

import java.util.List;

import com.snakegame.BodyPartType;
import com.snakegame.Direction;

/**
 * Representation of the snake's body part.
 */
public class SnakeBodyPart extends Item {

    private Direction direction;
    private BodyPartType bodyPartType;

    /**
     * Constructor.
     * 
     * @param xCoordinate
     * @param yCoordinate
     */
    public SnakeBodyPart(byte xCoordinate, byte yCoordinate) {
        this(xCoordinate, yCoordinate, BodyPartType.HEAD);
    }

    /**
     * Constructor.
     * 
     * @param xCoordinate
     * @param yCoordinate
     * @param bodyPartType
     */
    public SnakeBodyPart(byte xCoordinate, byte yCoordinate, BodyPartType bodyPartType) {
        super(xCoordinate, yCoordinate);
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
     * @param snakeBodyPart
     * @return true if yes
     */
    public boolean isAt(SnakeBodyPart snakeBodyPart) {
        return (xCoordinate == snakeBodyPart.xCoordinate && yCoordinate == snakeBodyPart.yCoordinate);
    }

    /**
     * is the snake body part the given place
     * 
     * @param snakeBodyPart
     * @return true if yes
     */
    public boolean isAt(byte xCoordinate, byte yCoordinate) {
        return (xCoordinate == this.xCoordinate && yCoordinate == this.yCoordinate);
    }

    /**
     * is the snake body part the given places
     * 
     * @param bodyPartsList
     * @return
     */
    public int isAt(List<SnakeBodyPart> bodyPartsList) {
        SnakeBodyPart sbp;
        for (int k = 0; k < bodyPartsList.size(); k++) {
            sbp = bodyPartsList.get(k);
            if (isAt(sbp)) {
                return k;
            }
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

    @Override
    public String toString() {
        return "SnakeBodyPart [direction=" + direction + ", bodyPartType=" + bodyPartType + ", xCoordinate="
                + xCoordinate + ", yCoordinate=" + yCoordinate + "]";
    }

}
