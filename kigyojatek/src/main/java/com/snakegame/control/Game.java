package com.snakegame.control;

import java.util.LinkedList;

import com.snakegame.BodyPartType;
import com.snakegame.Direction;
import com.snakegame.model.SnakeBodyPart;

/**
 * The game representation
 *
 */
public class Game {

    private static final int BONUS_FOOD_SCORE = 20;
    private static final int DEFAULT_SCORE_RISE = 10;
    private LinkedList<SnakeBodyPart> snake;
    private byte tablesize;
    private static SnakeBodyPart food[];
    private int score;
    private boolean isScoreRised;
    private boolean eaten;
    private Direction direction;
    private SnakeBodyPart newpart;
    private LinkedList<SnakeBodyPart> foodList;
    private LinkedList<SnakeBodyPart> eatenFood;
    private GameOver gameover;

    static {
	food = new SnakeBodyPart[2];
	food[0] = new SnakeBodyPart((byte) 5, (byte) 8);
	food[1] = new SnakeBodyPart((byte) 20, (byte) 20);
    }
    {
	foodList = new LinkedList<SnakeBodyPart>();
	eatenFood = new LinkedList<SnakeBodyPart>();
	foodList.add(new SnakeBodyPart((byte) 5, (byte) 8));
    }

    /**
     * Constructor.
     */
    public Game() {
	super();
	isScoreRised = eaten = false;
	gameover = GameOver.INGAME;
	snake = new LinkedList<SnakeBodyPart>();
	score = tablesize = 0;
	newpart = new SnakeBodyPart((byte) 5, (byte) 8);
    }

    /**
     * Initialize again
     */
    public void reInitialize() {

	foodList = new LinkedList<SnakeBodyPart>();
	eatenFood = new LinkedList<SnakeBodyPart>();
	foodList.add(new SnakeBodyPart((byte) 5, (byte) 8));
	score = 0;
	gameover = GameOver.INGAME;
    }

    public void doStep() {
	/**
	 * The snake tail (last element) will be the new head. First we set it
	 * to the exact place, then we will adjust it depending on the user
	 * input direction.
	 */
	SnakeBodyPart newBodyPart = getSnake().getLast();
	newBodyPart.setXCoordinate(getSnake().getFirst().getXCoordinate());
	newBodyPart.setYCoordinate(getSnake().getFirst().getYCoordinate());
	newBodyPart.setPartType(BodyPartType.HEAD);
	if (direction != null) {
	    if (getSnake().get(1).getDirection().equals(direction.getOppositeDirection())) {
		// if user trying to move to the opposite direction, then do
		// nothing.
	    } else {
		getSnake().getFirst().setDirection(direction);
	    }
	    setDirection(null);
	}
	switch (getSnake().getFirst().getDirection()) {
	// go to left
	case LEFT:
	    newBodyPart.setXCoordinate((byte) (newBodyPart.getXCoordinate() - 1));
	    newBodyPart.setDirection(Direction.LEFT);
	    break;
	// go to right
	case RIGHT:
	    newBodyPart.setXCoordinate((byte) (newBodyPart.getXCoordinate() + 1));
	    newBodyPart.setDirection(Direction.RIGHT);
	    break;
	// go down
	case DOWN:
	    newBodyPart.setYCoordinate((byte) (newBodyPart.getYCoordinate() + 1));
	    newBodyPart.setDirection(Direction.DOWN);
	    break;
	// go up
	case UP:
	    newBodyPart.setYCoordinate((byte) (newBodyPart.getYCoordinate() - 1));
	    newBodyPart.setDirection(Direction.UP);
	    break;
	}

	eatFood();
	getSnake().removeLast();
	getSnake().addFirst(newBodyPart);

    }

    public boolean isThereNextStep() {
	newpart.setXCoordinate(getSnake().getFirst().getXCoordinate());
	newpart.setYCoordinate(getSnake().getFirst().getYCoordinate());
	switch (getSnake().getFirst().getDirection()) {
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
	// if it stepped out from the boundary
	if (isOutOfBounds(newpart, tablesize)) {
	    return false;
	}
	return true;
    }

    private boolean isOutOfBounds(SnakeBodyPart newpart, byte tablesize) {
	if (newpart.getXCoordinate() < -1 || newpart.getXCoordinate() > tablesize || newpart.getYCoordinate() < -1
	        || newpart.getYCoordinate() > tablesize) {

	    return true;
	}
	return false;
    }

    public boolean eatFood() {
	int whichfood = getSnake().getFirst().isAt(foodList);
	SnakeBodyPart sbp;
	if (whichfood > -1) {
	    sbp = foodList.get(whichfood);
	    sbp.setDirection(snake.getLast().getDirection());
	    eatenFood.addFirst(new SnakeBodyPart(sbp.getXCoordinate(), sbp.getYCoordinate(), BodyPartType.NEWPART));
	    if (whichfood == 0) {
		newFoodsPlace(whichfood);
	    } else if (whichfood == 1) {
		foodList.removeLast();
	    }
	    increaseScore();
	    /**
	     * Bonus food
	     */
	    if (score == BONUS_FOOD_SCORE) {
		foodList.addLast(new SnakeBodyPart(snake.getLast().getXCoordinate(), snake.getLast().getYCoordinate()));
		newFoodsPlace(1);

	    }
	    setEaten(true);

	    return true;

	}
	return false;

    }

    /**
     * Increase the score
     */
    public void increaseScore() {
	score += DEFAULT_SCORE_RISE;
	setScoreRised(true);

    }

    public void newFoodsPlace(int which) {

	boolean collison = false;
	do {
	    foodList.get(which).setState(Util.random((byte) 0, tablesize), Util.random((byte) 0, tablesize), null);
	    collison = false;
	    for (int k = 0; k < getSnake().size(); k++) {
		if (snake.get(k).isAt(foodList.get(which))) {
		    collison = true;
		    break;
		}
	    }
	    if (collison)
		continue;
	    for (int k = 0; k < foodList.size(); k++) {
		if (k == which)
		    continue;
		if (foodList.get(k).isAt(foodList.get(which))) {
		    collison = true;
		    break;
		}
	    }
	} while (collison);
    }

    public boolean isHeadInBody() {
	for (int k = 1; k < getSnake().size(); k++) {
	    if (getSnake().get(k).isAt(getSnake().get(0))) {
		return true;
	    }
	}
	return false;
    }

    public void eatenFoodAtTail() {
	SnakeBodyPart tail = snake.getLast();
	int whichfood = tail.isAt(eatenFood);
	if (whichfood > -1) {
	    snake.addLast(eatenFood.get(whichfood));
	    snake.getLast().setDirection(tail.getDirection());
	    eatenFood.remove(whichfood);
	}

    }

    public void setSnake(LinkedList<SnakeBodyPart> snake) {
	this.snake = snake;
    }

    public LinkedList<SnakeBodyPart> getSnake() {
	return snake;
    }

    public int getScore() {
	return score;
    }

    public void setDirection(Direction direction) {
	this.direction = direction;
    }

    public void setEaten(boolean eaten) {
	this.eaten = eaten;
    }

    public boolean isEaten() {
	return eaten;
    }

    public void setScoreRised(boolean scorerised) {
	this.isScoreRised = scorerised;
    }

    public boolean isScorerised() {
	return isScoreRised;
    }

    public void setTablesize(byte tablesize) {
	this.tablesize = tablesize;
    }

    public LinkedList<SnakeBodyPart> getFoodList() {
	return foodList;
    }

    public LinkedList<SnakeBodyPart> getEatenFood() {
	return eatenFood;
    }

    public void setGameover(GameOver gameover) {
	this.gameover = gameover;
    }

    /**
     * Types of Game over.
     *
     */
    public enum GameOver {
	INGAME, // No Game over
	BITE, // Snake bitten its tail.
	WALL // Snake hits the wall.
    }
}
