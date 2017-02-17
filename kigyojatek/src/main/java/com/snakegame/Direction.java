package com.snakegame;

/**
 * Representation of the direction that the Snake could face
 */
public enum Direction {
    UP, RIGHT, DOWN, LEFT;
    /**
     * Get the opposite of the current direction.
     * 
     * @return
     */
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