package com.snakegame.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Window for showing version information.
 *
 */
public class VersionBox extends JDialog {
    private JButton closeButton;

    /***
     * Constructor.
     */
    public VersionBox() {
	initComponents();
	getRootPane().setDefaultButton(closeButton);
    }

    /**
     * Close the box.
     */
    public void closeAboutBox() {
	dispose();
    }

    /**
     * Get the close button
     * 
     * @return the close button
     */
    public JButton getCloseButton() {
	return closeButton;
    }

    private void initComponents() {
	this.setSize(300, 150);
	this.setResizable(false);
	this.setTitle("Version changes");

	JPanel panel = new JPanel();

	JTextArea ta = new JTextArea();
	ta.setEditable(false);
	ta.setSize(250, 100);
	ta.setLineWrap(true);
	ta.setText("revesion 0.29 \n" + "Two new themes and p for pause. \n" + "revesion 0.28 \n"
	        + "the body break bug has been fixed. \n" + "version 0.18 \n" + "- Second food has been added\n"
	        + "- You can use the arrowkeys to control the snake\n"
	        + "- After a certain score the snake will speed up\n" + "\nversion 0.1 \n" + "- basic theme\n");

	JScrollPane areaScrollPane = new JScrollPane(ta);
	areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	areaScrollPane.setPreferredSize(new Dimension(250, 250));
	panel.setLayout(new BorderLayout());
	panel.add(areaScrollPane, BorderLayout.CENTER);
	add(panel);
    }
}
