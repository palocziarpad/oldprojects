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
	    drawSnake_foodsver(g2d);
	} catch (IOException | InterruptedException e) {
	    logger.error(e.getMessage(), e);
	}
	drawPause(g2d);

    }

    public void reInitialize() {
	snakeGame.reInitialize();
    }

    public void drawPause(Graphics2D g2d) {
	if (pause) {
	    g2d.setFont(new Font(FONT_OF_PRINTS, Font.BOLD, 50));
	    g2d.drawString(PAUSED_LABEL, 235, 240);
	}
    }

    public void drawSnake_foodsver(Graphics2D g2d) throws IOException, InterruptedException {

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

    public void drawEatenFoods(Graphics2D g2d) {
	for (int k = 0; k < snakeGame.getEatenFood().size(); k++) {
	    g2d.drawImage(snakeTheme.getBIARRAY()[20],
		    point.x + snakeGame.getEatenFood().get(k).getX() * squarePixelSize + 1,
		    point.y + snakeGame.getEatenFood().get(k).getY() * squarePixelSize + 1, null);
	}
    }

    public void drawFoods(Graphics2D g2d) {
	g2d.drawImage(snakeTheme.getImage(PictureFiles.FOOD),
		point.x + snakeGame.getFoodList().getFirst().getX() * squarePixelSize + 1,
		point.y + snakeGame.getFoodList().getFirst().getY() * squarePixelSize + 1, null);

	if (snakeGame.getFoodList().size() > 1) {
	    g2d.drawImage(snakeTheme.getImage(PictureFiles.BONUSFOOD),
		    point.x + snakeGame.getFoodList().get(1).getX() * squarePixelSize + 1,
		    point.y + snakeGame.getFoodList().get(1).getY() * squarePixelSize + 1, null);
	}
    }

    public void drawHead(Graphics2D g2d) {
	SnakeBodyPart head = getSnakegame().getSnake().getFirst();
	PictureFiles picture = null;
	switch (head.getDir()) {
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
	g2d.drawImage(snakeTheme.getImage(picture), point.x + head.getX() * squarePixelSize + 1,
		point.y + head.getY() * squarePixelSize + 1, null);
    }

    public void drawTorso(Graphics2D g2d) {
	LinkedList<SnakeBodyPart> snakeList = getSnakegame().getSnake();

	PictureFiles p;
	for (int k = 1; k < snakeList.size() - 1; k++) {
	    SnakeBodyPart snake = snakeList.get(k);
	    if (snake.getPartKind() == BodyPartType.NEWPART) {

		g2d.drawImage(snakeTheme.getBIARRAY()[20], point.x + snake.getX() * squarePixelSize + 1,
			point.y + snake.getY() * squarePixelSize + 1, null);
	    } else if (isSlam(snakeList.get(k - 1), snakeList.get(k + 1))) {
		p = whichSlam(snakeList.get(k - 1), snake, snakeList.get(k + 1));
		g2d.drawImage(snakeTheme.getBIARRAY()[p.ordinal()], point.x + snake.getX() * squarePixelSize + 1,
			point.y + snake.getY() * squarePixelSize + 1, null);
	    } else {
		PictureFiles picfile = null;
		switch (snake.getDir()) {
		case UP:
		case DOWN:
		    picfile = PictureFiles.BODYHOR;
		    break;
		case RIGHT:
		case LEFT:
		    picfile = PictureFiles.BODYVER;
		    break;
		}
		g2d.drawImage(snakeTheme.getImage(picfile), point.x + snake.getX() * squarePixelSize + 1,
			point.y + snake.getY() * squarePixelSize + 1, null);
	    }
	}
    }

    private void drawTail(Graphics2D g2d) {
	SnakeBodyPart tail = getSnakegame().getSnake().getLast();
	PictureFiles picfile = null;
	switch (tail.getDir()) {
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
	g2d.drawImage(snakeTheme.getImage(picfile), point.x + tail.getX() * squarePixelSize + 1,
		point.y + tail.getY() * squarePixelSize + 1, null);
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
	return before.getX() != after.getX() && before.getY() != after.getY();
    }

    public PictureFiles whichSlam(SnakeBodyPart before, SnakeBodyPart current, SnakeBodyPart after) {
	boolean l = false, r = false, u = false, d = false;
	if (before.getX() > current.getX())
	    r = true;
	else if (before.getX() < current.getX())
	    l = true;
	else if (before.getY() > current.getY())
	    d = true;
	else if (before.getY() < current.getY())
	    u = true;
	if (after.getX() > current.getX())
	    r = true;
	else if (after.getX() < current.getX())
	    l = true;
	else if (after.getY() > current.getY())
	    d = true;
	else if (after.getY() < current.getY())
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

    public void setSnakegame(Game snakegame) {
	this.snakeGame = snakegame;
    }

    public Game getSnakegame() {
	return snakeGame;
    }

    public Point getPoint() {
	return point;
    }

    public void setPoint(Point point) {
	this.point = point;
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

    public SnakeTheme getSp() {
	return snakeTheme;
    }

    public void setSp(SnakeTheme sp) {
	this.snakeTheme = sp;
    }

}
