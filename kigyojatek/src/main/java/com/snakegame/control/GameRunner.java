package com.snakegame.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JMenuBar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.snakegame.Direction;
import com.snakegame.model.SnakeBodyPart;
import com.snakegame.view.Board;
import com.snakegame.view.MainWindow;

/**
 * The game runner class
 *
 */
public class GameRunner {

    private static Logger logger = LoggerFactory.getLogger(GameRunner.class);

    private static final int SNAKE_INITIAL_SIZE = 3;
    // Size of the grid
    final static byte gridsize = 20;
    // the main window
    private MainWindow mainWindow;

    private int sleepTime;
    private final static int DEFAULTDELAY = 250;
    private boolean isGameGoing;

    public GameRunner() {
        super();
        isGameGoing = true;
        sleepTime = DEFAULTDELAY;
    }

    public void run() {
        initMainWindow(initSnake(), new MenuClickListener());

        game();

    }

    private void initMainWindow(LinkedList<SnakeBodyPart> snake, MenuClickListener clickListener) {
        mainWindow = new MainWindow();
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
        mainWindow.getTable().getSnakegame().setTablesize(gridsize);
    }

    private LinkedList<SnakeBodyPart> initSnake() {
        LinkedList<SnakeBodyPart> snake = new LinkedList<SnakeBodyPart>();
        snake.add(new SnakeBodyPart((byte) 4, (byte) 2));
        snake.add(new SnakeBodyPart((byte) 3, (byte) 2));
        snake.add(new SnakeBodyPart((byte) 2, (byte) 2));
        return snake;
    }

    public static void main(String[] args) {
        logger.info("Start of the game.");
        new GameRunner().run();
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
    private void newGame() {
        sleepTime = DEFAULTDELAY;
        mainWindow.setNewgame(true);
        LinkedList<SnakeBodyPart> snake = mainWindow.getTable().getSnakegame().getSnake();
        int size = snake.size();
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
            sleep(sleepTime);
            Board table = mainWindow.getTable();
            table.getSnakegame().doStep();
            table.repaint();
            table.getSnakegame().eatenFoodAtTail();

            if (mainWindow.getNewgame() == true) {
                mainWindow.setNewgame(false);
                isGameGoing = false;
                break;
            }
            if (table.getSnakegame().isEaten()) {
                table.getSnakegame().setEaten(false);
            } else {
                if (table.getSnakegame().isHeadInBody()) {
                    mainWindow.gameOverBite();
                    break;
                }
            }

            if (!table.getSnakegame().isThereNextStep()) {
                mainWindow.gameOverStun();

                break;

            }
            makeFasterIfScoreRised();
            waitIfPause();
        }
    }

    private void waitIfPause() {
        while (mainWindow.getTable().isPause()) {
            sleep(sleepTime);
        }
    }

    private void makeFasterIfScoreRised() {
        Board table = mainWindow.getTable();
        if (table.getSnakegame().getScore() == 100 && table.getSnakegame().isScorerised()) {
            sleepTime /= 2;
            table.getSnakegame().setScoreRised(false);
        } else if (table.getSnakegame().getScore() == 200 && table.getSnakegame().isScorerised()) {
            sleepTime /= 2;
            table.getSnakegame().setScoreRised(false);
        }
    }

    private void sleep(int millisec) {
        try {
            Thread.sleep(millisec);
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
        }
    }

    private void delayUntilPlayerStart() {
        /**
         * Wait until the player click on the start.
         */
        while (true) {
            if (mainWindow.getStarted() == true) {
                break;
            }
            sleep(sleepTime);
        }
    }

    /** Action listener for the upper menu */
    private class MenuClickListener implements ActionListener {

        public void actionPerformed(ActionEvent actionEvent) {
            mainWindow.getTable().setPause(true);
            if (actionEvent.getSource().equals(mainWindow.getjMenuBar().getMenu(0).getItem(1))) {
                logger.info("Exiting from game via File/Exit.");
                isGameGoing = false;
                System.exit(0);
                return;
            }
            if (actionEvent.getSource().equals(mainWindow.getjMenuBar().getMenu(0).getItem(0))) {
                newGame();
                return;
            }
            if (actionEvent.getSource().equals(mainWindow.getjMenuBar().getMenu(2).getItem(1))) {
                mainWindow.showAboutBox();
                return;
            }
            if (actionEvent.getSource().equals(mainWindow.getjMenuBar().getMenu(2).getItem(0))) {
                mainWindow.showHelpBox();
                return;
            }
            if (actionEvent.getSource().equals(mainWindow.getjMenuBar().getMenu(2).getItem(2))) {
                mainWindow.showVersionBox();
                return;
            }
            if (actionEvent.getSource().equals(mainWindow.getjMenuBar().getMenu(1).getItem(1))) {
                mainWindow.showOptionsWin();
                return;
            }
            if (actionEvent.getSource().equals(mainWindow.getjMenuBar().getMenu(1).getItem(0))) {
                return;
            }

        }

    }
}
