package com.snakegame.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.snakegame.view.MainWindow;
import com.snakegame.view.OptionsWindow;

/***
 * Click listener for the different mainwindow actions.
 *
 */
public class SnakeClickListener implements ActionListener {
    private MainWindow mainWindow;

    /***
     * Constructor.
     * 
     * @param mainWindow
     */
    public SnakeClickListener(MainWindow mainWindow) {
        super();
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mainWindow.getStartButton()) {
            mainWindow.startGame();
            return;
        }
        OptionsWindow options = mainWindow.getOptionsWindow();
        if (options != null) {
            if (e.getSource() == options.getCloseButton()) {
                mainWindow.changeTheme();
                return;
            }
        }
    }
}