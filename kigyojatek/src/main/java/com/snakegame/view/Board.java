package com.snakegame.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.snakegame.BodyPartType;
import com.snakegame.DirectionToPicture;
import com.snakegame.PictureFiles;
import com.snakegame.control.Game;
import com.snakegame.model.SnakeBodyPart;
import com.snakegame.model.SnakeTheme;

/**
 * Display of the game
 *
 */
public class Board extends JPanel {

    private Logger logger = LoggerFactory.getLogger(Board.class);

    public static final String BOARD_NAME = "Board";
    private static final String PAUSED_LABEL = "PAUSED";
    private static final String FONT_OF_PRINTS = "SansSerif";

    private final int squarePixelSize = 21;
    private final int squareQuantity = 20;
    private final int maxSizeOfBoardPixels = squareQuantity * squarePixelSize;
    private Game snakeGame;
    private SnakeTheme snakeTheme;
    private Point point;
    private boolean pause;

    /**
     * Constructor.
     */
    public Board() {
	snakeGame = new Game();
	this.setBackground(Color.white);
	this.setBounds(0, 0, maxSizeOfBoardPixels, maxSizeOfBoardPixels);
	this.setLayout(new FlowLayout(FlowLayout.CENTER));
	this.setSize(640, 480);
	point = new Point(0, 0);
	this.setName(BOARD_NAME);
	this.snakeTheme = new SnakeTheme();
    }

    @Override
    public void paint(Graphics g) {

	Graphics2D g2d = (Graphics2D) g;

	point.x = (640 - 421) / 8;
	point.y = 10;
	g2d.drawImage(snakeTheme.getImage(PictureFiles.GAMEBG), 0, 00, null);
	g2d.drawImage(snakeTheme.getImage(PictureFiles.TABLE), point.x, point.y, null);
	g2d.drawImage(snakeTheme.getImage(PictureFiles.SCORE), point.x + 421, point.y, null);
	g2d.setBackground(Color.white);
	g2d.setColor(Color.orange);

	drawSnake(g2d);
	drawPause(g2d);

    }

    /**
     * Re initialize the game
     */
    public void reInitialize() {
	snakeGame.reInitialize();
    }

    /**
     * Draw the pause on the screen
     * 
     * @param g2d
     */
    public void drawPause(Graphics2D g2d) {
	if (pause) {
	    g2d.setFont(new Font(FONT_OF_PRINTS, Font.BOLD, 50));
	    g2d.drawString(PAUSED_LABEL, 235, 240);
	}
    }

    /***
     * Draw the snake
     * 
     * @param g2d
     * @throws IOException
     * @throws InterruptedException
     */
    public void drawSnake(Graphics2D g2d) {
	if (getSnakegame().getSnake().size() == 0) {
	    logger.warn("null snake");
	    return;
	}
	// food
	drawFoods(g2d);
	// head
	drawHead(g2d);
	// torso
	drawTorso(g2d);
	// tail
	drawTail(g2d);
	// draweatenfoods;
	drawEatenFoods(g2d);
	// score points
	drawScore(g2d);
    }

    /***
     * Check which slant is happening by checking three parts of the snake
     * 
     * @param before
     * @param current
     * @param after
     * @return null if no slant, else the proper picture depending on the slant
     */
    public static PictureFiles whichSlant(SnakeBodyPart before, SnakeBodyPart current, SnakeBodyPart after) {
	boolean left = false, right = false, upper = false, downer = false;

	if (before.getXCoordinate() > current.getXCoordinate())
	    right = true;
	else if (before.getXCoordinate() < current.getXCoordinate())
	    left = true;
	else if (before.getYCoordinate() > current.getYCoordinate())
	    downer = true;
	else if (before.getYCoordinate() < current.getYCoordinate())
	    upper = true;

	if (after.getXCoordinate() > current.getXCoordinate())
	    right = true;
	else if (after.getXCoordinate() < current.getXCoordinate())
	    left = true;
	else if (after.getYCoordinate() > current.getYCoordinate())
	    downer = true;
	else if (after.getYCoordinate() < current.getYCoordinate())
	    upper = true;

	if (right && upper)
	    return PictureFiles.SLANT_RIGHT_UPPER;
	else if (left && downer)
	    return PictureFiles.SLANT_LEFT_DOWNER;
	else if (left && upper)
	    return PictureFiles.SLANT_LEFT_UPPER;
	else if (right && downer)
	    return PictureFiles.SLANT_RIGHT_DOWNER;

	return null;

    }

    /***
     * Get the snake game
     * 
     * @return
     */
    public Game getSnakegame() {
	return snakeGame;
    }

    /**
     * Get the point coordinates
     * 
     * @return
     */
    public Point getPoint() {
	return point;
    }

    /**
     * is the game paused?
     * 
     * @return true if yes, otherwise false
     */
    public boolean isGamePaused() {
	return pause;
    }

    /**
     * Set the pause state
     * 
     * @param pause
     */
    public void setPaused() {
	this.pause = true;
    }

    /**
     * Switch the pause state.
     */
    public void switchPause() {
	pause = !pause;
    }

    /**
     * Get the snake Theme
     * 
     * @return
     */
    public SnakeTheme getSnakeTheme() {
	return snakeTheme;
    }

    private void drawEatenFoods(Graphics2D g2d) {
	for (int k = 0; k < snakeGame.getEatenFood().size(); k++) {
	    g2d.drawImage(snakeTheme.getImage(PictureFiles.NEWPART),
	            point.x + snakeGame.getEatenFood().get(k).getXCoordinate() * squarePixelSize + 1,
	            point.y + snakeGame.getEatenFood().get(k).getYCoordinate() * squarePixelSize + 1, null);
	}
    }

    private void drawFoods(Graphics2D g2d) {
	g2d.drawImage(snakeTheme.getImage(PictureFiles.FOOD),
	        point.x + snakeGame.getFoodList().getFirst().getXCoordinate() * squarePixelSize + 1,
	        point.y + snakeGame.getFoodList().getFirst().getYCoordinate() * squarePixelSize + 1, null);

	if (snakeGame.getFoodList().size() > 1) {
	    g2d.drawImage(snakeTheme.getImage(PictureFiles.BONUSFOOD),
	            point.x + snakeGame.getFoodList().get(1).getXCoordinate() * squarePixelSize + 1,
	            point.y + snakeGame.getFoodList().get(1).getYCoordinate() * squarePixelSize + 1, null);
	}
    }

    private void drawHead(Graphics2D g2d) {
	SnakeBodyPart head = getSnakegame().getSnake().getFirst();
	PictureFiles picture = DirectionToPicture.getPictureFromHeadDirection(head.getDirection());
	g2d.drawImage(snakeTheme.getImage(picture), point.x + head.getXCoordinate() * squarePixelSize + 1,
	        point.y + head.getYCoordinate() * squarePixelSize + 1, null);
    }

    private void drawTorso(Graphics2D g2d) {
	LinkedList<SnakeBodyPart> snakeList = getSnakegame().getSnake();

	PictureFiles picLocal;
	for (int k = 1; k < snakeList.size() - 1; k++) {
	    SnakeBodyPart snake = snakeList.get(k);
	    if (snake.getPartType() == BodyPartType.NEWPART) {

		g2d.drawImage(snakeTheme.getImage(PictureFiles.NEWPART),
		        point.x + snake.getXCoordinate() * squarePixelSize + 1,
		        point.y + snake.getYCoordinate() * squarePixelSize + 1, null);
	    } else if (isSlant(snakeList.get(k - 1), snakeList.get(k + 1))) {
		picLocal = whichSlant(snakeList.get(k - 1), snake, snakeList.get(k + 1));
		g2d.drawImage(snakeTheme.getImage(picLocal), point.x + snake.getXCoordinate() * squarePixelSize + 1,
		        point.y + snake.getYCoordinate() * squarePixelSize + 1, null);
	    } else {
		PictureFiles picfile = DirectionToPicture.getPictureFromBodyDirection(snake.getDirection());
		g2d.drawImage(snakeTheme.getImage(picfile), point.x + snake.getXCoordinate() * squarePixelSize + 1,
		        point.y + snake.getYCoordinate() * squarePixelSize + 1, null);
	    }
	}
    }

    private void drawTail(Graphics2D g2d) {
	SnakeBodyPart tail = getSnakegame().getSnake().getLast();
	PictureFiles picfile = null;
	picfile = DirectionToPicture.getPictureFromTailDirection(tail.getDirection());
	g2d.drawImage(snakeTheme.getImage(picfile), point.x + tail.getXCoordinate() * squarePixelSize + 1,
	        point.y + tail.getYCoordinate() * squarePixelSize + 1, null);
    }

    private void drawScore(Graphics2D g2d) {
	g2d.setFont(new Font(FONT_OF_PRINTS, Font.BOLD, 14));
	Color color = null;
	if (snakeGame.getScore() > 99 && snakeGame.getScore() < 200) {
	    color = Color.BLUE;
	} else if (snakeGame.getScore() > 199) {
	    color = Color.RED;
	}
	g2d.setColor(color);
	g2d.drawString("" + getSnakegame().getScore(), point.x + 421 + 184 / 2 - 5, point.y + 153 / 2 + 30);
    }

    private boolean isSlant(SnakeBodyPart before, SnakeBodyPart after) {
	return before.getXCoordinate() != after.getXCoordinate() && before.getYCoordinate() != after.getYCoordinate();
    }

}
