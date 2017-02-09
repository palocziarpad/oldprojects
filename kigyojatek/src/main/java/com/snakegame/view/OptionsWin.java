package com.snakegame.view;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.snakegame.model.Options;
import com.snakegame.model.SnakePic;

/***
 * Window to show the options of the game.
 *
 */
public class OptionsWin extends JFrame {
    JPanel panel, theme;
    JButton save, close, cancel;
    ButtonGroup themeopt;
    JComboBox<String> themelist;

    public OptionsWin() {
        super("Options");
        setSize(200, 200);
        Options opt = new Options();
        themeopt = new ButtonGroup();
        // rbuttons= new JRadioButton[opt.themes.length];
        panel = new JPanel();
        themelist = new JComboBox<String>(opt.getThemes());

        // themelist.setEnabled(true);
        panel.add(themelist);
        // save=new JButton("Save");
        // panel.add(save);
        close = new JButton("close");
        panel.add(close);
        // cancel=new JButton("cancel");
        // panel.add(cancel);

        setResizable(false);
        add(panel);

    }

    /**
     * Set the theme to the desired one.
     * 
     * @param snakePic
     *            snakePic that will be used to change the theme.
     */
    public void setTheme(SnakePic snakePic) {
        // create memory pool to optimize it.
        String s = themelist.getSelectedItem().toString();
        if (SnakePic.getDesignPrefix().equals(s))
            return;
        snakePic.setDesignPrefix(s);
    }
}
