package com.snakegame.view;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.snakegame.model.Options;
import com.snakegame.model.SnakePic;

@SuppressWarnings("serial")
public class OptionsWin extends JFrame {
    public JPanel panel, theme;
    public JButton save, close, cancel;
    public ButtonGroup themeopt;
    // JRadioButton rbuttons[];
    JComboBox themelist;

    public OptionsWin() {
        super("Options");
        setSize(200, 200);
        Options opt = new Options();
        themeopt = new ButtonGroup();
        // rbuttons= new JRadioButton[opt.themes.length];
        panel = new JPanel();
        themelist = new JComboBox(opt.getThemes());

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

    public void setTheme(SnakePic sp) {
        // create memory pool to optimize it.
        String s = themelist.getSelectedItem().toString();
        if (SnakePic.getDesignPrefix().equals(s))
            return;
        sp.setDesignPrefix(s);
    }
}
