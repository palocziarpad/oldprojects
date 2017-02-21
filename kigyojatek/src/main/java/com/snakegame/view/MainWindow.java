package com.snakegame.view;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.snakegame.PictureFiles;
import com.snakegame.control.Game;
import com.snakegame.control.MyKeyListener;
import com.snakegame.model.SnakeTheme;

/***
 * The main window
 *
 */
public class MainWindow extends JFrame {

    public static final String MAIN_BACKGROUND_PANEL_NAME = "SnakePanel";
    public static final String START_BUTTON_NAME = "Start";
    public static final String BACKGROUND_PANEL_NAME = "Background";
    public static final String GAME_WINDOW_TITLE = "Snake Game";

    private Board table;
    private JButton startButton;
    private JPanel panel;
    private Boolean started;
    private JMenuBar menuBar;
    private JLabel backGround;
    private Boolean newgame;
    private JDialog versionBox, aboutBox, helpBox;
    private OptionsWindow options;
    // private SnakeClickListener clickListener;
    private SnakeTheme snakeTheme;

    /***
     * Constructor.
     */
    public MainWindow() {
        super(GAME_WINDOW_TITLE);
        this.setName(GAME_WINDOW_TITLE);
        // set frame size
        this.setSize(640, 500);
        this.setResizable(false);
        this.init();
        this.snakeTheme = new SnakeTheme();
        backGround = new JLabel(BACKGROUND_PANEL_NAME);
        backGround.setName(BACKGROUND_PANEL_NAME);
        backGround.setIcon(new ImageIcon(snakeTheme.getImage(PictureFiles.OPENBG)));
        backGround.setEnabled(true);
        // this.clickListener = new SnakeClickListener();
        panel = new JPanel();
        panel.setName(MAIN_BACKGROUND_PANEL_NAME);
        panel.setLayout(new BorderLayout());
        panel.add(backGround, BorderLayout.CENTER);
        startButton = new JButton(START_BUTTON_NAME);
        // startButton.addActionListener(clickListener);
        startButton.setName(START_BUTTON_NAME);
        panel.add(startButton, BorderLayout.SOUTH);
        this.add(panel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        table = new Board(snakeTheme);

        this.addKeyListener(new MyKeyListener(table));
        this.setFocusable(true);
        this.addMenu();

    }

    /**
     * @return the startButton
     */
    public JButton getStartButton() {
        return startButton;
    }

    /**
     * Initialize the game for the first time run.
     */
    public void init() {
        setNewgame(false);
        setStarted(false);

    }

    /**
     * Change the theme
     */
    public void changeTheme() {
        options.setTheme(table.getSnakeTheme());
        options.setVisible(false);
        backGround.setIcon(new ImageIcon(snakeTheme.getImage(PictureFiles.OPENBG)));
    }

    /***
     * Start the game
     */
    public void startGame() {
        startButton.setVisible(false);
        backGround.setVisible(false);
        panel.add(getTable());
        panel.repaint();
        panel.setFocusable(false);
        table.setFocusable(false);
        started = true;
    }

    /***
     * Show the about box
     */
    public void showAboutBox() {
        if (aboutBox == null) {
            aboutBox = new AboutBox();
        }
        aboutBox.setVisible(true);
    }

    /***
     * Show the help box
     */
    public void showHelpBox() {
        if (helpBox == null) {
            helpBox = new HelpBox();
        }
        helpBox.setVisible(true);
    }

    /**
     * Show the version box
     */
    public void showVersionBox() {
        if (versionBox == null) {
            versionBox = new VersionBox();

        }
        versionBox.setVisible(true);
    }

    /**
     * Show the options window
     */
    public void showOptionsWindow() {
        options.setVisible(true);
    }

    /**
     * End the game with bite of the snake by himself
     */
    public void gameOverBite() {
        SwingUtilities.invokeLater(() -> {
            backGround.setIcon(new ImageIcon(snakeTheme.getImage(PictureFiles.GAMEOVERBITE)));
            backGround.setVisible(true);
            panel.remove(getTable());
            panel.add(backGround);

            repaint();
            table.getSnakegame().setGameover(Game.GameOver.BITE);

        });
    }

    /**
     * End the game with stun of the snake by hitting the wall
     */
    public void gameOverStun() {
        SwingUtilities.invokeLater(() -> {
            backGround.setIcon(new ImageIcon(snakeTheme.getImage(PictureFiles.GAMEOVERSTUN)));
            backGround.setVisible(true);
            panel.remove(getTable());
            panel.add(backGround);
            repaint();
            table.getSnakegame().setGameover(Game.GameOver.WALL);
        });

    }

    public JMenuBar getjMenuBar() {
        return menuBar;
    }

    public void setNewgame(Boolean newgame) {
        this.newgame = newgame;
    }

    public Boolean getNewgame() {
        return newgame;
    }

    public void setStarted(Boolean started) {
        this.started = started;
    }

    public Boolean getStarted() {
        return started;
    }

    public Board getTable() {
        return table;
    }

    public JLabel getBackGround() {
        return backGround;
    }

    public OptionsWindow getOptionsWindow() {
        if (options == null) {
            options = new OptionsWindow();
        }
        return options;
    }

    private void addMenu() {
        menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        menuBar.add(fileMenu);
        fileMenu.add(new JMenuItem("New Game", 'N'));
        fileMenu.add(new JMenuItem("Exit", 'E'));
        JMenu settings = new JMenu("Settings");
        menuBar.add(settings);
        settings.add(new JMenuItem("Difficulty"));
        settings.add(new JMenuItem("Theme"));

        JMenu about = new JMenu("About");
        menuBar.add(about);

        about.add(new JMenuItem("Help"));
        about.add(new JMenuItem("About"));
        about.add(new JMenuItem("Version"));
        this.setJMenuBar(menuBar);
    }

}