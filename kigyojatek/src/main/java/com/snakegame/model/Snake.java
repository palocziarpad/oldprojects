package com.snakegame.model;

import java.util.ArrayList;
import java.util.List;

import com.snakegame.BodyPartType;
import com.snakegame.Direction;

/***
 * Representation of the snake
 *
 */
public class Snake {
    private List<SnakeBodyPart> snakeFullBody;

    /**
     * Consructor.
     */
    public Snake() {
        super();
        snakeFullBody = new ArrayList<>();
    }

    /**
     * Get the head of the snake
     * 
     * @return the head of the snake
     */
    public SnakeBodyPart getHead() {
        return snakeFullBody.get(0);
    }

    /***
     * Add the bodypart to the head
     * 
     */
    public void addHead(SnakeBodyPart snakeBodyPart) {
        snakeFullBody.add(0, snakeBodyPart);
    }

    /***
     * Add the bodypart to the tail
     * 
     */
    public SnakeBodyPart getTail() {
        return snakeFullBody.get(snakeFullBody.size() - 1);
    }

    /***
     * Get the tail of the snake.
     * 
     * @return tail of the snake.
     */
    public void addTail(SnakeBodyPart snakeBodyPart) {
        snakeFullBody.add(snakeFullBody.size(), snakeBodyPart);
    }

    /**
     * Make a snake step happen
     */
    public void doStep(Direction directionOfTheNextStep) {
        /**
         * The snake tail (last element) will be the new head. First we set it
         * to the exact place, then we will adjust it depending on the user
         * input direction.
         */
        SnakeBodyPart newHead = this.getTail();
        newHead.setXCoordinate(this.getHead().getXCoordinate());
        newHead.setYCoordinate(this.getHead().getYCoordinate());
        newHead.setPartType(BodyPartType.HEAD);
        if (directionOfTheNextStep != null) {
            if (snakeFullBody.get(1).getDirection().equals(directionOfTheNextStep.getOppositeDirection())) {
                // if user trying to move to the opposite direction, then do
                // nothing.
            } else {
                this.getHead().setDirection(directionOfTheNextStep);
            }
            directionOfTheNextStep = null;
        }
        setNewHeadValues(newHead);

        snakeFullBody.remove(getTail());
        snakeFullBody.add(0, newHead);

    }

    /***
     * Set the new head values
     * 
     * @param newHead
     */
    public void setNewHeadValues(SnakeBodyPart newHead) {
        switch (this.getHead().getDirection()) {
        case LEFT:
            newHead.setXCoordinate((byte) (newHead.getXCoordinate() - 1));
            newHead.setDirection(Direction.LEFT);
            break;
        case RIGHT:
            newHead.setXCoordinate((byte) (newHead.getXCoordinate() + 1));
            newHead.setDirection(Direction.RIGHT);
            break;
        case DOWN:
            newHead.setYCoordinate((byte) (newHead.getYCoordinate() + 1));
            newHead.setDirection(Direction.DOWN);
            break;
        case UP:
            newHead.setYCoordinate((byte) (newHead.getYCoordinate() - 1));
            newHead.setDirection(Direction.UP);
            break;
        }
    }

    /***
     * Check if there is next step.
     * 
     * @return true if there is, false otherwise
     */
    public boolean isThereNextStep(SnakeBodyPart newpart, byte tablesize) {
        newpart.setXCoordinate(this.getHead().getXCoordinate());
        newpart.setYCoordinate(this.getHead().getYCoordinate());
        switch (this.getHead().getDirection()) {
        case LEFT:
            newpart.setXCoordinate((byte) (newpart.getXCoordinate() - 1));
            break;
        case RIGHT:
            newpart.setXCoordinate((byte) (newpart.getXCoordinate() + 1));
            break;
        case DOWN:
            newpart.setYCoordinate((byte) (newpart.getYCoordinate() + 1));
            break;
        case UP:
            newpart.setYCoordinate((byte) (newpart.getYCoordinate() - 1));
            break;
        }
        if (isOutOfBounds(newpart, tablesize)) {
            return false;
        }
        return true;
    }

    /**
     * Check if the snake has stepped out from the board boundary.
     * 
     * @param newpart
     * @param tablesize
     * @return true if it stepped out, false otherwise
     */
    private boolean isOutOfBounds(SnakeBodyPart newpart, byte tablesize) {
        if (newpart.getXCoordinate() < -1 || newpart.getXCoordinate() > tablesize || newpart.getYCoordinate() < -1
                || newpart.getYCoordinate() > tablesize) {

            return true;
        }
        return false;
    }

    /**
     * Check if the head is collided with the body.
     * 
     * @return true if the head is same place as one of the body part, otherwise
     *         false
     */
    public boolean isHeadInBody() {
        for (int k = 1; k < snakeFullBody.size(); k++) {
            if (snakeFullBody.get(k).isAtGivenCoordinate(this.getHead())) {
                return true;
            }
        }
        return false;
    }

    /**
     * The size of the snake
     * 
     * @return the size of the snake
     */
    public int getSize() {
        return snakeFullBody.size();
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index
     *            index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException
     *             if the index is out of range
     *             (<tt>index &lt; 0 || index &gt;= size()</tt>)
     */
    public SnakeBodyPart get(int index) {
        return snakeFullBody.get(index);
    }

    /**
     * Remove the last element
     */
    public void removeLast() {
        snakeFullBody.remove(snakeFullBody.size() - 1);

    }

    /**
     * Eat the given part.
     * 
     * @param snakeBodyPart
     */
    public void eat(SnakeBodyPart snakeBodyPart) {
        snakeFullBody.add(snakeBodyPart);

    }

    /***
     * Check if the given coordinates are at same point as one of the snake body
     * part
     * 
     * @param xCoordinate
     * @param yCoordinate
     * @return true if yes, otherwise false
     */
    public boolean isCoordinatesOnSnake(byte xCoordinate, byte yCoordinate) {
        for (SnakeBodyPart snakeBodyPart : snakeFullBody) {
            if (snakeBodyPart.isAt(xCoordinate, yCoordinate)) {
                return true;
            }
        }
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Snake [snakeFullBody=" + snakeFullBody + "]";
    }

}
