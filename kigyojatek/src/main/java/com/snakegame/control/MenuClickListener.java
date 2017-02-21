package com.snakegame.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuBar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.snakegame.view.MainWindow;

/** Action listener for the upper menu */
public class MenuClickListener implements ActionListener {
    private static Logger logger = LoggerFactory.getLogger(GameRunner.class);
    private GameRunner gameRunner;

    /**
     * Constructor.
     * 
     * @param gameRunner
     */
    public MenuClickListener(GameRunner gameRunner) {
        super();
        this.gameRunner = gameRunner;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        MainWindow mainWindow = gameRunner.getMainWindow();
        mainWindow.getTable().setPaused();
        JMenuBar jMenuBar = mainWindow.getjMenuBar();
        if (actionEvent.getSource().equals(jMenuBar.getMenu(0).getItem(1))) {
            logger.info("Exiting from game via File/Exit.");
            gameRunner.setGameGoing(false);
            System.exit(0);
            return;
        }
        if (actionEvent.getSource().equals(jMenuBar.getMenu(0).getItem(0))) {
            gameRunner.newGame();
            return;
        }
        if (actionEvent.getSource().equals(jMenuBar.getMenu(2).getItem(1))) {
            mainWindow.showAboutBox();
            return;
        }
        if (actionEvent.getSource().equals(jMenuBar.getMenu(2).getItem(0))) {
            mainWindow.showHelpBox();
            return;
        }
        if (actionEvent.getSource().equals(jMenuBar.getMenu(2).getItem(2))) {
            mainWindow.showVersionBox();
            return;
        }
        if (actionEvent.getSource().equals(jMenuBar.getMenu(1).getItem(1))) {
            mainWindow.showOptionsWindow();
            return;
        }
        if (actionEvent.getSource().equals(jMenuBar.getMenu(1).getItem(0))) {
            return;
        }

    }
}
