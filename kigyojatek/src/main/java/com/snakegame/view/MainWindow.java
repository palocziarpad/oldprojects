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

import com.snakegame.control.Game;
import com.snakegame.model.SnakeBodyPart;
import com.snakegame.model.SnakePic;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {
    private Board table;
    private JButton startButton;
    private JPanel panel;
    private Boolean started;
    private JMenuBar mb;
    private JLabel bg;
    private Boolean newgame;
    private JDialog versionBox, aboutBox, helpBox;
    private OptionsWin opts;
    private ClickListener clickLi;

    public void init() {
        setNewgame(false);
        setStarted(false);

    }

    public void reinit() {
        int meret = table.getSnakegame().getSnake().size();
        for (int k = 3; k < meret; k++) {
            getTable().getSnakegame().getSnake().removeLast();
        }
        // System.out.println(mainwin.getTable().getSnakegame().snake.size());
        table.getSnakegame().getSnake().get(0).setState((byte) 4, (byte) 2, SnakeBodyPart.Direction.RIGHT);
        table.getSnakegame().getSnake().get(1).setState((byte) 3, (byte) 2, SnakeBodyPart.Direction.RIGHT);
        table.getSnakegame().getSnake().get(2).setState((byte) 2, (byte) 2, SnakeBodyPart.Direction.RIGHT);
        // mainwin.setVisible(false);
        // mainwin=new MainWindow();
        repaint();
        if (table.getSnakegame().getGameover() == Game.GameOver.BITE
                || table.getSnakegame().getGameover() == Game.GameOver.WALL) {
            System.out.println("newgame bite");
            remove(bg);
            add(table);
        }

        // System.out.println("repaint");
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
        bg = new JLabel();
        bg.setIcon(new ImageIcon(getClass().getResource(SnakePic.getDesignPrefix() + SnakePic.PicFile.OPENBG)));
        // bg.setSize(200, 200);
        clickLi = new ClickListener();
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(bg, BorderLayout.CENTER);
        startButton = new JButton("Start");
        // startButton.set
        startButton.addActionListener(clickLi);

        panel.add(startButton, BorderLayout.SOUTH);
        // panel.addKeyListener(new MyKeyListener());
        this.add(panel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // addMenu();

        table = new Board();

        // setLayout(new FlowLayout(FlowLayout.LEADING));
        this.addKeyListener(new MyKeyListener());
        // rajz.addKeyListener(new MyKeyListener());
        this.setFocusable(true);
        this.addMenu();

        // make this frame visible
        this.setVisible(true);
        // this.pack();
        // pause = false;
    }

    private void addMenu() {
        setMenuBar(new JMenuBar());
        JMenu file = new JMenu("File");

        mb.add(file);
        file.add(new JMenuItem("New Game", 'N'));
        // file.getItem(0).addActionListener(new MenuClickListener());
        file.add(new JMenuItem("Exit", 'E'));
        // file.getItem(1).addActionListener(new MenuClickListener());
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
            // System.out.println("\' " + i +" \'");

            if (ke.getKeyCode() == KeyEvent.VK_UP) {
                getTable().getSnakegame().setDirection(SnakeBodyPart.Direction.UP);
                return;

            } else if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
                getTable().getSnakegame().setDirection(SnakeBodyPart.Direction.DOWN);
                return;

            } else if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
                getTable().getSnakegame().setDirection(SnakeBodyPart.Direction.LEFT);
                return;

            } else if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
                getTable().getSnakegame().setDirection(SnakeBodyPart.Direction.RIGHT);
                return;

            } else
                switch (i) {

                case 'w':
                    getTable().getSnakegame().setDirection(SnakeBodyPart.Direction.UP);
                    break;
                case 'a':
                    getTable().getSnakegame().setDirection(SnakeBodyPart.Direction.LEFT);
                    break;
                case 's':
                    getTable().getSnakegame().setDirection(SnakeBodyPart.Direction.DOWN);
                    break;
                case 'd':
                    getTable().getSnakegame().setDirection(SnakeBodyPart.Direction.RIGHT);
                    break;
                case 'p':
                    // pause=!pause;
                    table.pauseSwitch();
                    // System.out.println("pause: "+ table.pause);
                    break;
                }

        }
    }

    private class ClickListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == startButton) {
                panel.remove(startButton);
                panel.remove(bg);
                // bg.setIcon(new
                // ImageIcon(getClass().getResource("resources/mira/"+PicPath.GAMEBG)));
                // panel.add(bg);
                // rajz.addKeyListener(new MyKeyListener());
                panel.add(getTable());

                // panel.addKeyListener(new MyKeyListener());
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
                    bg.setIcon(new ImageIcon(
                            getClass().getResource(SnakePic.getDesignPrefix() + SnakePic.PicFile.OPENBG)));
                    // bg.setSize(200, 200);
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
            // JFrame mainFrame = MainWindow.getApplication().getMainFrame();
            // aboutBox = new VmiAboutBox(mainFrame);
            // aboutBox.setLocationRelativeTo(mainFrame);
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
            opts.close.addActionListener(clickLi);
            opts.save.addActionListener(clickLi);

        }
        opts.setVisible(true);
    }

    public void gameOverBite() {
        bg.setIcon(new ImageIcon(getClass().getResource(SnakePic.getDesignPrefix() + SnakePic.PicFile.GAMEOVERBITE)));
        panel.remove(getTable());
        panel.add(bg);

        repaint();
        table.getSnakegame().setGameover(Game.GameOver.BITE);
    }

    public void gameOverStun() {
        bg.setIcon(new ImageIcon(getClass().getResource(SnakePic.getDesignPrefix() + SnakePic.PicFile.GAMEOVERSTUN)));
        panel.remove(getTable());
        panel.add(bg);
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
        return bg;
    }

    public void setBg(JLabel bg) {
        this.bg = bg;
    }

}