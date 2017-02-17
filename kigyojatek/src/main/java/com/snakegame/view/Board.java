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

	try {
	    drawSnake(g2d);
	} catch (IOException | InterruptedException e) {
	    logger.error(e.getMessage(), e);
	}
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
    public void drawSnake(Graphics2D g2d) throws IOException, InterruptedException {
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
	PictureFiles picture = null;
	switch (head.getDirection()) {
	case UP:
	    picture = PictureFiles.HEADUP;
	    break;
	case RIGHT:
	    picture = PictureFiles.HEADRIGHT;
	    break;
	case DOWN:
	    picture = PictureFiles.HEADDOWN;
	    break;
	case LEFT:
	    picture = PictureFiles.HEADLEFT;
	    break;
	}
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
	    } else if (isSlam(snakeList.get(k - 1), snakeList.get(k + 1))) {
		picLocal = whichSlam(snakeList.get(k - 1), snake, snakeList.get(k + 1));
		g2d.drawImage(snakeTheme.getImage(picLocal), point.x + snake.getXCoordinate() * squarePixelSize + 1,
		        point.y + snake.getYCoordinate() * squarePixelSize + 1, null);
	    } else {
		PictureFiles picfile = null;
		switch (snake.getDirection()) {
		case UP:
		case DOWN:
		    picfile = PictureFiles.BODYHOR;
		    break;
		case RIGHT:
		case LEFT:
		    picfile = PictureFiles.BODYVER;
		    break;
		}
		g2d.drawImage(snakeTheme.getImage(picfile), point.x + snake.getXCoordinate() * squarePixelSize + 1,
		        point.y + snake.getYCoordinate() * squarePixelSize + 1, null);
	    }
	}
    }

    private void drawTail(Graphics2D g2d) {
	SnakeBodyPart tail = getSnakegame().getSnake().getLast();
	PictureFiles picfile = null;
	switch (tail.getDirection()) {
	case UP:
	    picfile = PictureFiles.TAILUP;
	    break;
	case RIGHT:
	    picfile = PictureFiles.TAILRIGHT;
	    break;
	case DOWN:
	    picfile = PictureFiles.TAILDOWN;
	    break;
	case LEFT:
	    picfile = PictureFiles.TAILLEFT;
	    break;
	}
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

    private boolean isSlam(SnakeBodyPart before, SnakeBodyPart after) {
	return before.getXCoordinate() != after.getXCoordinate() && before.getYCoordinate() != after.getYCoordinate();
    }

    public static PictureFiles whichSlam(SnakeBodyPart before, SnakeBodyPart current, SnakeBodyPart after) {
	boolean l = false, r = false, u = false, d = false;
	if (before.getXCoordinate() > current.getXCoordinate())
	    r = true;
	else if (before.getXCoordinate() < current.getXCoordinate())
	    l = true;
	else if (before.getYCoordinate() > current.getYCoordinate())
	    d = true;
	else if (before.getYCoordinate() < current.getYCoordinate())
	    u = true;
	if (after.getXCoordinate() > current.getXCoordinate())
	    r = true;
	else if (after.getXCoordinate() < current.getXCoordinate())
	    l = true;
	else if (after.getYCoordinate() > current.getYCoordinate())
	    d = true;
	else if (after.getYCoordinate() < current.getYCoordinate())
	    u = true;
	if (r && u)
	    return PictureFiles.SLANTRU;
	else if (l && d)
	    return PictureFiles.SLANTLD;
	else if (l && u)
	    return PictureFiles.SLANTLU;
	else if (r && d)
	    return PictureFiles.SLANTRD;
	return null;

    }

    public Game getSnakegame() {
	return snakeGame;
    }

    public Point getPoint() {
	return point;
    }

    public boolean isPause() {
	return pause;
    }

    public void setPause(boolean pause) {
	this.pause = pause;
    }

    public void pauseSwitch() {
	pause = !pause;
    }

    public SnakeTheme getSnakeTheme() {
	return snakeTheme;
    }

}
