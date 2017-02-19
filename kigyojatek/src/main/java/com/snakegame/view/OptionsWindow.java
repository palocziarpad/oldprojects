package com.snakegame.view;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.snakegame.model.SnakeTheme;
import com.snakegame.model.ThemeOptions;

/***
 * Window to show the options of the game.
 */
public class OptionsWindow extends JFrame {
    private static final String OPTIONS_WINDOW_TITLE = "Options";
    private static final String CLOSE_BUTTON_TEXT = "close";
    private JPanel panel;
    private JButton closeButton;
    private JComboBox<String> themelist;

    /**
     * Constructor.
     */
    public OptionsWindow() {
	super(OPTIONS_WINDOW_TITLE);
	setSize(300, 150);
	ThemeOptions opt = new ThemeOptions();
	panel = new JPanel();
	themelist = new JComboBox<String>(opt.getThemes());

	panel.add(themelist);
	closeButton = new JButton(CLOSE_BUTTON_TEXT);
	panel.add(closeButton);

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

    /**
     * Get the close button
     * 
     * @return the close button
     */
    public JButton getCloseButton() {
	return closeButton;
    }

}
