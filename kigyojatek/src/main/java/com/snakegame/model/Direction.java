package com.snakegame.model;

/**
 * Representation of the direction that the Snake could face
 */
public enum Direction {
    UP, RIGHT, DOWN, LEFT;

    public Direction getOppositeDirection() {
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