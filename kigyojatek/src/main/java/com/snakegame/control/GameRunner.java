package com.snakegame.control;

import javax.swing.JMenuBar;
import javax.swing.SwingUtilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.snakegame.Direction;
import com.snakegame.Util;
import com.snakegame.model.Snake;
import com.snakegame.model.SnakeBodyPart;
import com.snakegame.view.Board;
import com.snakegame.view.MainWindow;

/**
 * The game runner class
 *
 */
public class GameRunner implements Runnable {

    private static Logger logger = LoggerFactory.getLogger(GameRunner.class);

    private static final int PHASE_ADVANCED_SCORE_LIMIT = 200;
    private static final int PHASE_BEGINNER_SCORE_LIMIT = 100;
    private static final int SNAKE_INITIAL_SIZE = 3;
    private final static int DEFAULTDELAY = 250;
    // Size of the grid
    private final static byte gridsize = 20;
    // the main window
    private MainWindow mainWindow;

    private int sleepTime;

    private boolean isGameGoing;

    /**
     * Constructor.
     */
    public GameRunner() {
        super();
        isGameGoing = true;
        sleepTime = DEFAULTDELAY;
        SwingUtilities.invokeLater(() -> {
            mainWindow = new MainWindow();
            SnakeClickListener clicklistner = new SnakeClickListener(mainWindow);
            mainWindow.getStartButton().addActionListener(clicklistner);
            mainWindow.getOptionsWindow().getCloseButton().addActionListener(clicklistner);
        });
    }

    /**
     * @return the mainWindow
     */
    public MainWindow getMainWindow() {
        return mainWindow;
    }

    /**
     * @return the isGameGoing
     */
    public boolean isGameGoing() {
        return isGameGoing;
    }

    /**
     * @param isGameGoing
     *            the isGameGoing to set
     */
    public void setGameGoing(boolean isGameGoing) {
        this.isGameGoing = isGameGoing;
    }

    /***
     * Start the game
     */
    public void run() {
        initMainWindow(initSnake(), new MenuClickListener(this));
        game();
    }

    private void initMainWindow(Snake snake, MenuClickListener clickListener) {

        SwingUtilities.invokeLater(() -> {
            mainWindow.setVisible(true);
            JMenuBar jmenubar = mainWindow.getjMenuBar();
            jmenubar.getMenu(0).getItem(0).addActionListener(clickListener);
            jmenubar.getMenu(0).getItem(1).addActionListener(clickListener);
            jmenubar.getMenu(1).getItem(0).addActionListener(clickListener);
            jmenubar.getMenu(1).getItem(1).addActionListener(clickListener);
            jmenubar.getMenu(2).getItem(0).addActionListener(clickListener);
            jmenubar.getMenu(2).getItem(1).addActionListener(clickListener);
            jmenubar.getMenu(2).getItem(2).addActionListener(clickListener);

            mainWindow.getTable().repaint();
            mainWindow.getTable().getSnakegame().setSnake(snake);
            mainWindow.getTable().getSnakegame().setTableSize(gridsize);
        });

    }

    private Snake initSnake() {
        Snake snake = new Snake();
        snake.eat(new SnakeBodyPart((byte) 4, (byte) 2));
        snake.eat(new SnakeBodyPart((byte) 3, (byte) 2));
        snake.eat(new SnakeBodyPart((byte) 2, (byte) 2));
        return snake;
    }

    /**
     * The start point of the application
     * 
     * @param args
     */
    public static void main(String[] args) {
        logger.info("Start of the game.");
        Thread gameThread = new Thread(new GameRunner());

        gameThread.start();

    }

    /***
     * Start a new game
     */
    public void newGame() {
        sleepTime = DEFAULTDELAY;
        mainWindow.setNewgame(true);
        Snake snake = mainWindow.getTable().getSnakegame().getSnake();
        int size = snake.getSize();
        for (int k = SNAKE_INITIAL_SIZE; k < size; k++) {
            snake.removeLast();
        }
        snake.get(0).setState((byte) 4, (byte) 2, Direction.RIGHT);
        snake.get(1).setState((byte) 3, (byte) 2, Direction.RIGHT);
        snake.get(2).setState((byte) 2, (byte) 2, Direction.RIGHT);
        mainWindow.repaint();
        game();

    }

    private void game() {
        delayUntilPlayerStart();
        snakeMover();
    }

    private void snakeMover() {
        /**
         * The snake mover.
         */
        while (isGameGoing) {
            Util.sleep(sleepTime);
            Board table = mainWindow.getTable();
            Game snakegame = table.getSnakegame();
            snakegame.doStep();
            table.repaint();
            snakegame.eatenFoodAtTail();

            if (mainWindow.getNewgame() == true) {
                mainWindow.setNewgame(false);
                isGameGoing = false;
                break;
            }
            if (snakegame.isEaten()) {
                snakegame.setEaten(false);

            } else if (snakegame.getSnake().isHeadInBody()) {
                mainWindow.gameOverBite();
                break;
            }

            if (!snakegame.isThereNextStep()) {
                mainWindow.gameOverStun();

                break;

            }
            makeFasterIfScoreRised();
            waitIfPause();
        }
    }

    private void waitIfPause() {
        while (mainWindow.getTable().isGamePaused()) {
            Util.sleep(sleepTime);
        }
    }

    private void makeFasterIfScoreRised() {
        Board table = mainWindow.getTable();
        Game snakegame = table.getSnakegame();
        int score = snakegame.getScore();
        boolean scorerised = snakegame.isScorerised();
        if (score == PHASE_BEGINNER_SCORE_LIMIT && scorerised) {
            sleepTime /= 2;
            snakegame.setScoreRised(false);
        } else if (score == PHASE_ADVANCED_SCORE_LIMIT && scorerised) {
            sleepTime /= 2;
            snakegame.setScoreRised(false);
        }
    }

    private void delayUntilPlayerStart() {
        while (mainWindow == null) {
            Util.sleep(500);
        }
        while (mainWindow.getBackGround() == null) {
            Util.sleep(500);
        }
        /**
         * Wait until the player click on the start.
         */
        while (true) {
            if (mainWindow.getStarted() == true) {
                break;
            }
            Util.sleep(sleepTime);
        }
    }

}
