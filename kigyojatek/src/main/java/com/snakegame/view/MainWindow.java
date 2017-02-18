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

import com.snakegame.Direction;
import com.snakegame.PictureFiles;
import com.snakegame.control.Game;
import com.snakegame.model.SnakeTheme;

public class MainWindow extends JFrame {
    public static final String GAME_WINDOW_TITLE = "Snake Game";
    private Board table;
    private JButton startButton;
    private JPanel panel;
    private Boolean started;
    private JMenuBar menuBar;
    private JLabel backGround;
    private Boolean newgame;
    private JDialog versionBox, aboutBox, helpBox;
    private OptionsWin options;
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
	backGround = new JLabel("Background");
	backGround.setName("Background");
	backGround.setIcon(
	        new ImageIcon(getClass().getResource(SnakeTheme.getSelectedTheme() + PictureFiles.OPENBG.getValue())));
	// bg.setSize(200, 200);
	backGround.setEnabled(true);
	clickListener = new SnakeClickListener();
	panel = new JPanel();
	panel.setLayout(new BorderLayout());
	panel.add(backGround, BorderLayout.CENTER);
	startButton = new JButton("Start");
	startButton.addActionListener(clickListener);
	startButton.setName("Start");
	panel.add(startButton, BorderLayout.SOUTH);
	this.add(panel);

	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	table = new Board();

	this.addKeyListener(new MyKeyListener());
	this.setFocusable(true);
	this.addMenu();

	// make this frame visible
	// this.setVisible(true);
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
		panel.remove(startButton);
		panel.remove(backGround);
		panel.add(getTable());
		panel.repaint();
		panel.setFocusable(false);
		getTable().setFocusable(false);
		setStarted(true);
		return;
	    }
	    if (options != null) {
		if (e.getSource() == options.close) {
		    options.setTheme(table.getSnakeTheme());
		    options.setVisible(false);
		    backGround.setIcon(new ImageIcon(
		            getClass().getResource(SnakeTheme.getSelectedTheme() + PictureFiles.OPENBG.getValue())));
		    return;
		}
		if (e.getSource() == options.save) {
		    options.setTheme(table.getSnakeTheme());
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

    public void showHelpBox() {
	if (helpBox == null) {
	    helpBox = new HelpBox();
	}
	helpBox.setVisible(true);
    }

    public void showVersionBox() {
	if (versionBox == null) {
	    versionBox = new VersionBox();

	}
	versionBox.setVisible(true);
    }

    public void showOptionsWin() {
	if (options == null) {
	    options = new OptionsWin();
	    options.close.addActionListener(clickListener);
	}
	options.setVisible(true);
    }

    public void gameOverBite() {
	backGround.setIcon(new ImageIcon(
	        getClass().getResource(SnakeTheme.getSelectedTheme() + PictureFiles.GAMEOVERBITE.getValue())));
	panel.remove(getTable());
	panel.add(backGround);

	repaint();
	table.getSnakegame().setGameover(Game.GameOver.BITE);
    }

    public void gameOverStun() {
	backGround.setIcon(new ImageIcon(
	        getClass().getResource(SnakeTheme.getSelectedTheme() + PictureFiles.GAMEOVERSTUN.getValue())));
	panel.remove(getTable());
	panel.add(backGround);
	repaint();
	table.getSnakegame().setGameover(Game.GameOver.WALL);
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

    public void setTable(Board table) {
	this.table = table;
    }

    public Board getTable() {
	return table;
    }

    public JLabel getBg() {
	return backGround;
    }

    public void setBg(JLabel bg) {
	this.backGround = bg;
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