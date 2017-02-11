package com.snakegame.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import com.snakegame.Direction;
import com.snakegame.model.SnakeBodyPart;
import com.snakegame.view.MainWindow;

/**
 * The game runner class
 *
 */
public class GameRunner {

    private static final int SNAKE_INITIAL_SIZE = 3;
    // Size of the grid
    final static byte gridsize = 20;
    // the main window
    MainWindow mainwin;

    int delay;
    private final static int DEFAULTDELAY = 250;

    public GameRunner() {
        super();
        delay = DEFAULTDELAY;
    }

    public void run() {
        LinkedList<SnakeBodyPart> snake = initSnake();
        MenuClickListener clistener = new MenuClickListener();
        initMainWindow(snake, clistener);

        game();

    }

    private void initMainWindow(LinkedList<SnakeBodyPart> snake, MenuClickListener clistener) {
        mainwin = new MainWindow();

        mainwin.getjMenuBar().getMenu(0).getItem(0).addActionListener(clistener);
        mainwin.getjMenuBar().getMenu(0).getItem(1).addActionListener(clistener);
        mainwin.getjMenuBar().getMenu(1).getItem(0).addActionListener(clistener);
        mainwin.getjMenuBar().getMenu(1).getItem(1).addActionListener(clistener);
        mainwin.getjMenuBar().getMenu(2).getItem(0).addActionListener(clistener);
        mainwin.getjMenuBar().getMenu(2).getItem(1).addActionListener(clistener);
        mainwin.getjMenuBar().getMenu(2).getItem(2).addActionListener(clistener);

        mainwin.getTable().repaint();
        mainwin.getTable().getSnakegame().setSnake(snake);
        mainwin.getTable().getSnakegame().setTablesize(gridsize);
    }

    private LinkedList<SnakeBodyPart> initSnake() {
        LinkedList<SnakeBodyPart> snake = new LinkedList<SnakeBodyPart>();
        snake.add(new SnakeBodyPart((byte) 4, (byte) 2));
        snake.add(new SnakeBodyPart((byte) 3, (byte) 2));
        snake.add(new SnakeBodyPart((byte) 2, (byte) 2));
        return snake;
    }

    public static void main(String[] args) {
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
    }

    /**
     * Buglist: 1. az �j test megt�ri a k�pet fixed 2. �j test lukat csin�l
     * n�ha, amin �t lehet menni an�lk�l hogy v�get �rne a j�t�k 3. a dupla kaja
     * nem figyeli, hogy ha a m�sik kaj�val volt �tk�z�s fixed 4. nincs ny� g�m
     * 5.
     */
    public void newGame() {
        delay = DEFAULTDELAY;
        int size = mainwin.getTable().getSnakegame().getSnake().size();
        for (int k = SNAKE_INITIAL_SIZE; k < size; k++) {
            mainwin.getTable().getSnakegame().getSnake().removeLast();
        }
        mainwin.getTable().getSnakegame().getSnake().get(0).setState((byte) 4, (byte) 2, Direction.RIGHT);
        mainwin.getTable().getSnakegame().getSnake().get(1).setState((byte) 3, (byte) 2, Direction.RIGHT);
        mainwin.getTable().getSnakegame().getSnake().get(2).setState((byte) 2, (byte) 2, Direction.RIGHT);
        mainwin.repaint();
        game();

    }

    public void newGame2() {
        mainwin.setVisible(false);
        delay = DEFAULTDELAY;
        mainwin.setNewgame(true);
        new GameRunner().run();
    }

    public void game() {
        delayUntilPlayerStart();
        snakeMover();

    }

    private void snakeMover() {
        /**
         * The snake mover.
         */
        while (true) {
            try {
                Thread.sleep(delay);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mainwin.getTable().getSnakegame().step_foodsver();
            mainwin.getTable().repaint();
            mainwin.getTable().getSnakegame().eatenFoodAtTail();

            if (mainwin.getNewgame() == true)
                break;
            if (mainwin.getTable().getSnakegame().isEaten()) {
                mainwin.getTable().getSnakegame().setEaten(false);
            } else {
                if (mainwin.getTable().getSnakegame().isHeadInBody()) {
                    mainwin.gameOverBite();
                    break;
                }
            }

            if (!mainwin.getTable().getSnakegame().isThereNextStep()) {
                mainwin.gameOverStun();

                break;

            }
            if (mainwin.getTable().getSnakegame().getScore() == 100
                    && mainwin.getTable().getSnakegame().isScorerised()) {
                delay /= 2;
                mainwin.getTable().getSnakegame().setScorerised(false);
            } else if (mainwin.getTable().getSnakegame().getScore() == 200
                    && mainwin.getTable().getSnakegame().isScorerised()) {
                delay /= 2;
                mainwin.getTable().getSnakegame().setScorerised(false);
            }
            while (mainwin.getTable().isPause()) {
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void delayUntilPlayerStart() {
        /**
         * Wait until the player click on the start.
         */
        while (true) {
            if (mainwin.getStarted() == true)
                break;
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /** Action listener for the upper menu */
    private class MenuClickListener implements ActionListener {

        public void actionPerformed(ActionEvent actionEvent) {
            mainwin.getTable().setPause(true);
            if (actionEvent.getSource().equals(mainwin.getjMenuBar().getMenu(0).getItem(1))) {
                System.exit(0);
                return;
            }
            if (actionEvent.getSource().equals(mainwin.getjMenuBar().getMenu(0).getItem(0))) {
                newGame();
                return;
            }
            if (actionEvent.getSource().equals(mainwin.getjMenuBar().getMenu(2).getItem(1))) {
                mainwin.showAboutBox();
                return;
            }
            if (actionEvent.getSource().equals(mainwin.getjMenuBar().getMenu(2).getItem(0))) {
                mainwin.showHelpBox();
                return;
            }
            if (actionEvent.getSource().equals(mainwin.getjMenuBar().getMenu(2).getItem(2))) {
                mainwin.showVersionBox();
                return;
            }
            if (actionEvent.getSource().equals(mainwin.getjMenuBar().getMenu(1).getItem(1))) {
                mainwin.showOptionsWin();
                return;
            }
            if (actionEvent.getSource().equals(mainwin.getjMenuBar().getMenu(1).getItem(0))) {
                return;
            }

        }

    }
}
