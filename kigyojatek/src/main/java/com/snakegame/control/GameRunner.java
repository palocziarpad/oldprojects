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

        /**
         * @TODO 1. legyen egy j�t�k start gomb. pipa 2. pontsz�ml�l� pipa 3.
         *       v�ltoztathat� skinek helyett theme, pipa 4. t�bbf�le kaja 5.
         *       ideig ott l�v� kaja 6. kaj�l�s effekt 7. k�gy� hossz ut�n
         *       gyorsuljon pipa 8. legyenek szintek 9. j�t�km�dok 10.
         *       pontsz�ml�l� pipa 11. norm�lisabb gui pipa 12. help men� pipa
         *       13. score sz�ml�l� pipa 14. Image abszrakt oszt�ly 15. Enum
         *       oszt�lyok megfelel� oszt�lyba olvadjanak be 16. K�l�nb�z� v�gek
         *       eset�n k�l�nb�z� k�pek. 17. score t�bla, avagy toplista 18.
         *       id�m�r� 19. costum game 20. id�re gyorsuljon 21. id�r�l elt�n�
         *       cucc, ami pontcountert sz�mol le, ha 0ra �r elt�nik. 22.
         *       lehessen pause-t nyomni, ha lemegy a f�kusz a j�t�kr�l akkor
         *       autopause 23. legyenek benne achivmentek. 24. t�bb profilos
         *       legyen 25. multiplayer legyen benne. 26. mozg� kaja 27.
         *       akad�lyok 28. legyen ini f�jl 29. legyen zene �s hangok 30.
         *       ev�s el�tt nyiljon ki a sz�ja 31. var�zs kaj�n�l anim�ci�t
         *       v�lt. 32. stack overfl� bug a random miatt. jav�tva 33. bug, a
         *       dinnye ott marad. jav�tva
         */
        logger.info("End of the game.");
    }

    /**
     * Buglist: 1. az �j test megt�ri a k�pet fixed 2. �j test lukat csin�l
     * n�ha, amin �t lehet menni an�lk�l hogy v�get �rne a j�t�k 3. a dupla kaja
     * nem figyeli, hogy ha a m�sik kaj�val volt �tk�z�s fixed 4. nincs ny� g�m
     * 5.
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
