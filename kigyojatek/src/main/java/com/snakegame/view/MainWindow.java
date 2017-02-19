package com.snakegame.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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

import com.snakegame.Direction;
import com.snakegame.PictureFiles;
import com.snakegame.control.Game;
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
    private SnakeClickListener clickListener;

    /***
     * Constructor.
     */
    public MainWindow() {

	// set frame's title
	super(GAME_WINDOW_TITLE);
	this.setName(GAME_WINDOW_TITLE);
	// set frame size
	this.setSize(640, 500);
	this.setResizable(false);
	init();

	backGround = new JLabel(BACKGROUND_PANEL_NAME);
	backGround.setName(BACKGROUND_PANEL_NAME);
	backGround.setIcon(
	        new ImageIcon(getClass().getResource(SnakeTheme.getSelectedTheme() + PictureFiles.OPENBG.getValue())));
	backGround.setEnabled(true);
	clickListener = new SnakeClickListener();
	panel = new JPanel();
	panel.setName(MAIN_BACKGROUND_PANEL_NAME);
	panel.setLayout(new BorderLayout());
	panel.add(backGround, BorderLayout.CENTER);
	startButton = new JButton(START_BUTTON_NAME);
	startButton.addActionListener(clickListener);
	startButton.setName(START_BUTTON_NAME);
	panel.add(startButton, BorderLayout.SOUTH);
	this.add(panel);

	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	table = new Board();

	this.addKeyListener(new MyKeyListener());
	this.setFocusable(true);
	this.addMenu();

    }

    /**
     * Initialize the game for the first time run.
     */
    public void init() {
	setNewgame(false);
	setStarted(false);

    }

    private class MyKeyListener extends KeyAdapter {

	@Override
	public void keyPressed(KeyEvent ke) {
	    char i = ke.getKeyChar();

	    if (ke.getKeyCode() == KeyEvent.VK_UP) {
		getTable().getSnakegame().setDirection(Direction.UP);
		return;

	    } else if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
		getTable().getSnakegame().setDirection(Direction.DOWN);
		return;

	    } else if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
		getTable().getSnakegame().setDirection(Direction.LEFT);
		return;

	    } else if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
		getTable().getSnakegame().setDirection(Direction.RIGHT);
		return;

	    } else
		switch (i) {

		case 'w':
		case 'W':
		    getTable().getSnakegame().setDirection(Direction.UP);
		    break;
		case 'a':
		case 'A':
		    getTable().getSnakegame().setDirection(Direction.LEFT);
		    break;
		case 's':
		case 'S':
		    getTable().getSnakegame().setDirection(Direction.DOWN);
		    break;
		case 'd':
		case 'D':
		    getTable().getSnakegame().setDirection(Direction.RIGHT);
		    break;
		case 'p':
		case 'P':
		    table.pauseSwitch();
		    break;
		}

	}
    }

    private class SnakeClickListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
	    if (e.getSource() == startButton) {
		// panel.remove(startButton);
		startButton.setVisible(false);
		backGround.setVisible(false);
		// panel.remove(backGround);
		panel.add(getTable());
		panel.repaint();
		panel.setFocusable(false);
		getTable().setFocusable(false);
		setStarted(true);
		return;
	    }
	    if (options != null) {
		if (e.getSource() == options.getCloseButton()) {
		    options.setTheme(table.getSnakeTheme());
		    options.setVisible(false);
		    backGround.setIcon(new ImageIcon(
		            getClass().getResource(SnakeTheme.getSelectedTheme() + PictureFiles.OPENBG.getValue())));
		    return;
		}
	    }
	}
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
	if (options == null) {
	    options = new OptionsWindow();
	    options.getCloseButton().addActionListener(clickListener);
	}
	options.setVisible(true);
    }

    /**
     * End the game with bite of the snake by himself
     */
    public void gameOverBite() {
	SwingUtilities.invokeLater(() -> {
	    backGround.setIcon(new ImageIcon(
	            getClass().getResource(SnakeTheme.getSelectedTheme() + PictureFiles.GAMEOVERBITE.getValue())));
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
	    backGround.setIcon(new ImageIcon(
	            getClass().getResource(SnakeTheme.getSelectedTheme() + PictureFiles.GAMEOVERSTUN.getValue())));
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