package com.snakegame.view;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.snakegame.model.SnakeTheme;
import com.snakegame.model.ThemeOptions;

/***
 * Window to show the options of the game.
 *
 */
public class OptionsWin extends JFrame {
    private static final String CLOSE_TEXT = "close";
    JPanel panel, theme;
    JButton save, close, cancel;
    ButtonGroup themeopt;
    JComboBox<String> themelist;

    /**
     * Constructor.
     */
    public OptionsWin() {
        super("Options");
        setSize(200, 200);
        ThemeOptions opt = new ThemeOptions();
        themeopt = new ButtonGroup();
        panel = new JPanel();
        themelist = new JComboBox<String>(opt.getThemes());

        panel.add(themelist);
        close = new JButton(CLOSE_TEXT);
        panel.add(close);

        setResizable(false);
        add(panel);

    }

    /**
     * Set the theme to the desired one.
     * 
     * @param snakePic
     *            snakePic that will be used to change the theme.
     */
    public void setTheme(SnakeTheme snakePic) {
        // @TODO create memory pool to optimize it.
        String s = themelist.getSelectedItem().toString();
        if (SnakeTheme.getSelectedTheme().equals(s))
            return;
        snakePic.setTheme(s);
    }
}
