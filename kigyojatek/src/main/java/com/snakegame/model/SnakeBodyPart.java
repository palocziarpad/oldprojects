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
    private Direction dir;
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
        this.dir = Direction.RIGHT;
        this.bodyPartType = BodyPartType.HEAD;
    }

    /**
     * Constructor.
     * 
     * @param xCoordinate
     * @param yCoordinate
     * @param p
     */
    public SnakeBodyPart(byte xCoordinate, byte yCoordinate, BodyPartType p) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.dir = Direction.RIGHT;
        this.bodyPartType = p;
    }

    public BodyPartType getPartKind() {
        return bodyPartType;
    }

    public void setPartKind(BodyPartType partKind) {
        this.bodyPartType = partKind;
    }

    public byte getX() {
        return xCoordinate;
    }

    public void setX(byte x) {
        this.xCoordinate = x;
    }

    public byte getY() {
        return yCoordinate;
    }

    public void setY(byte y) {
        this.yCoordinate = y;
    }

    public Direction getDirection() {
        return getDir();
    }

    public void setDirection(Direction direction) {
        this.setDir(direction);
    }

    public boolean isAt(SnakeBodyPart food) {
        return (xCoordinate == food.xCoordinate && yCoordinate == food.yCoordinate);
    }

    public boolean isAt(SnakeBodyPart food[]) {
        for (int i = 0; i < food.length; i++) {
            if (xCoordinate == food[i].xCoordinate && yCoordinate == food[i].yCoordinate)
                return true;
        }
        return false;
    }

    public boolean isAt(LinkedList<SnakeBodyPart> foods) {
        SnakeBodyPart sbp;
        for (int k = 0; k < foods.size(); k++) {
            sbp = foods.get(k);
            if (xCoordinate == sbp.xCoordinate && yCoordinate == sbp.yCoordinate)
                return true;
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

    public int isAt(SnakeBodyPart food[], int vmi) {
        for (int i = 0; i < food.length; i++) {
            if (xCoordinate == food[i].xCoordinate && yCoordinate == food[i].yCoordinate)
                return i;
        }
        return -1;
    }

    public void setState(byte x, byte y, Direction dir) {
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.setDir(dir);
    }

    public void setDir(Direction dir) {
        this.dir = dir;
    }

    public Direction getDir() {
        return dir;
    }

}
