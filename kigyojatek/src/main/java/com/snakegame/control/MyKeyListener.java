package com.snakegame.control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.snakegame.Direction;
import com.snakegame.view.Board;

/***
 * Keylistener for the Snake Game
 *
 */
public class MyKeyListener extends KeyAdapter {
    private Game snakeGame;
    private Board table;

    /***
     * Constructor.
     * 
     * @param snakeGame
     */
    public MyKeyListener(Board table) {
        super();
        this.snakeGame = table.getSnakegame();
        this.table = table;
    }

    @Override
    public void keyPressed(KeyEvent ke) {

        // Playing using arrows
        switch (ke.getKeyCode()) {
        case KeyEvent.VK_UP:
            snakeGame.setDirection(Direction.UP);
            break;
        case KeyEvent.VK_LEFT:
            snakeGame.setDirection(Direction.LEFT);
            break;
        case KeyEvent.VK_DOWN:
            snakeGame.setDirection(Direction.DOWN);
            break;
        case KeyEvent.VK_RIGHT:
            snakeGame.setDirection(Direction.RIGHT);
            break;
        }
        // Playing using wasd or WASD and pause
        switch (ke.getKeyChar()) {
        case 'w':
        case 'W':
            snakeGame.setDirection(Direction.UP);
            break;
        case 'a':
        case 'A':
            snakeGame.setDirection(Direction.LEFT);
            break;
        case 's':
        case 'S':
            snakeGame.setDirection(Direction.DOWN);
            break;
        case 'd':
        case 'D':
            snakeGame.setDirection(Direction.RIGHT);
            break;
        case 'p':
        case 'P':
            table.switchPause();
            break;
        }

    }
}