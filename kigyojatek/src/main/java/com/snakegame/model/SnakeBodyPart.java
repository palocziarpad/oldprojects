package com.snakegame.model;

import java.util.LinkedList;

public class SnakeBodyPart {
    private byte x;
    private byte y;
    private Direction dir;
    private Parts partKind;

    public SnakeBodyPart(byte x, byte y) {
        this.x = x;
        this.y = y;
        this.dir = Direction.RIGHT;
        this.partKind = Parts.HEAD;
    }

    public SnakeBodyPart(byte x, byte y, Parts p) {
        this.x = x;
        this.y = y;
        this.dir = Direction.RIGHT;
        this.partKind = p;
    }

    public Parts getPartKind() {
        return partKind;
    }

    public void setPartKind(Parts partKind) {
        this.partKind = partKind;
    }

    public byte getX() {
        return x;
    }

    public void setX(byte x) {
        this.x = x;
    }

    public byte getY() {
        return y;
    }

    public void setY(byte y) {
        this.y = y;
    }

    public Direction getIrany() {
        return getDir();
    }

    public void setIrany(Direction direction) {
        this.setDir(direction);
    }

    public boolean isAt(SnakeBodyPart food) {
        return (x == food.x && y == food.y);
    }

    public boolean isAt(SnakeBodyPart food[]) {
        for (int i = 0; i < food.length; i++) {
            if (x == food[i].x && y == food[i].y)
                return true;
        }
        return false;
        // return (x==food[0].x&&y==food[0].y);
    }

    public boolean isAt(LinkedList<SnakeBodyPart> foods) {
        SnakeBodyPart sbp;
        for (int k = 0; k < foods.size(); k++) {
            sbp = foods.get(k);
            if (x == sbp.x && y == sbp.y)
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
            if (x == sbp.x && y == sbp.y)
                return k;
        }

        return -1;
    }

    public int isAt(SnakeBodyPart food[], int vmi) {
        for (int i = 0; i < food.length; i++) {
            if (x == food[i].x && y == food[i].y)
                return i;
        }
        return -1;
        // return (x==food[0].x&&y==food[0].y);
    }

    public void setState(byte x, byte y, Direction dir) {
        this.x = x;
        this.y = y;
        this.setDir(dir);
    }

    public void setDir(Direction dir) {
        this.dir = dir;
    }

    public Direction getDir() {
        return dir;
    }

    public enum Parts {
        HEAD, TORSO, TAIL, FOOD, NEWPART
    }

    public enum Direction {
        UP, RIGHT, DOWN, LEFT;

        public Direction opposite() {

            switch (this) {
            case UP:
                return DOWN;
            case RIGHT:
                return LEFT;
            case LEFT:
                return RIGHT;
            case DOWN:
                return UP;
            }
            return null;
        }
    }
}
