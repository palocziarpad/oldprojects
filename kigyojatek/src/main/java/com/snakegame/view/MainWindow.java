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

@SuppressWarnings("serial")
public class MainWindow extends JFrame {
    private Board table;
    private JButton startButton;
    private JPanel panel;
    private Boolean started;
    private JMenuBar mb;
    private JLabel backGround;
    private Boolean newgame;
    private JDialog versionBox, aboutBox, helpBox;
    private OptionsWin opts;
    private ClickListener clickListener;

    /**
     * Initialize the game for the first time run.
     */
    public void init() {
        setNewgame(false);
        setStarted(false);

    }

    /**
     * Initialize the game again to run with clean.
     */
    public void reinit() {
        int meret = table.getSnakegame().getSnake().size();
        for (int k = 3; k < meret; k++) {
            getTable().getSnakegame().getSnake().removeLast();
        }
        table.getSnakegame().getSnake().get(0).setState((byte) 4, (byte) 2, Direction.RIGHT);
        table.getSnakegame().getSnake().get(1).setState((byte) 3, (byte) 2, Direction.RIGHT);
        table.getSnakegame().getSnake().get(2).setState((byte) 2, (byte) 2, Direction.RIGHT);
        repaint();
        if (table.getSnakegame().getGameover() == Game.GameOver.BITE
                || table.getSnakegame().getGameover() == Game.GameOver.WALL) {
            System.out.println("newgame bite");
            remove(backGround);
            add(table);
        }

        table.getSnakegame().setScore(0);
        table.reinit();

    }

    public MainWindow() {

        // set frame's title
        super("Snake Game");
        // set frame size

        this.setSize(640, 500);
        this.setResizable(false);
        init();
        backGround = new JLabel();
        backGround.setIcon(
                new ImageIcon(getClass().getResource(SnakeTheme.getSelectedTheme() + PictureFiles.OPENBG.getValue())));
        // bg.setSize(200, 200);
        clickListener = new ClickListener();
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(backGround, BorderLayout.CENTER);
        startButton = new JButton("Start");
        startButton.addActionListener(clickListener);

        panel.add(startButton, BorderLayout.SOUTH);
        this.add(panel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        table = new Board();

        this.addKeyListener(new MyKeyListener());
        this.setFocusable(true);
        this.addMenu();

        // make this frame visible
        this.setVisible(true);
    }

    private void addMenu() {
        setMenuBar(new JMenuBar());
        JMenu file = new JMenu("File");

        mb.add(file);
        file.add(new JMenuItem("New Game", 'N'));
        file.add(new JMenuItem("Exit", 'E'));
        JMenu settings = new JMenu("Settings");
        mb.add(settings);
        settings.add(new JMenuItem("Difficulty"));
        settings.add(new JMenuItem("Theme"));

        JMenu about = new JMenu("About");
        mb.add(about);

        about.add(new JMenuItem("Help"));
        about.add(new JMenuItem("About"));
        about.add(new JMenuItem("Version"));
        this.setJMenuBar(mb);
    }

    public class MyKeyListener extends KeyAdapter {
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
                    getTable().getSnakegame().setDirection(Direction.UP);
                    break;
                case 'a':
                    getTable().getSnakegame().setDirection(Direction.LEFT);
                    break;
                case 's':
                    getTable().getSnakegame().setDirection(Direction.DOWN);
                    break;
                case 'd':
                    getTable().getSnakegame().setDirection(Direction.RIGHT);
                    break;
                case 'p':
                    table.pauseSwitch();
                    break;
                }

        }
    }

    private class ClickListener implements ActionListener {

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
            if (opts != null) {
                if (e.getSource() == opts.close) {
                    opts.setTheme(table.getSp());
                    opts.setVisible(false);
                    backGround.setIcon(new ImageIcon(
                            getClass().getResource(SnakeTheme.getSelectedTheme() + PictureFiles.OPENBG.getValue())));
                    return;
                }
                if (e.getSource() == opts.save) {
                    opts.setTheme(table.getSp());
                    return;
                }
            }
        }
    }

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
        if (opts == null) {
            opts = new OptionsWin();
            opts.close.addActionListener(clickListener);
        }
        opts.setVisible(true);
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

    public void setMenuBar(JMenuBar menuBar) {
        this.mb = menuBar;
    }

    public JMenuBar getjMenuBar() {
        return mb;
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

}