package com.snakegame.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * About window, information about the game.
 *
 */
public class AboutBox extends JDialog {
    private JButton closeButton;

    /**
     * Constructor.
     */
    public AboutBox() {
	initComponents();
	getRootPane().setDefaultButton(closeButton);
    }

    /**
     * Close the About box.
     */
    public void closeAboutBox() {
	dispose();
    }

    private void initComponents() {
	this.setSize(300, 150);
	this.setResizable(false);
	this.setTitle("About...");
	JPanel panel = new JPanel();
	add(panel);
	JTextArea ta = new JTextArea();
	ta.setEditable(false);
	ta.setSize(250, 100);
	ta.setText("Created by Saznetro Soft." + "\n\nGraphics by Mira web: tnelly.daportfolio.com/");
	ta.setLineWrap(true);
	JScrollPane areaScrollPane = new JScrollPane(ta);
	areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	areaScrollPane.setPreferredSize(new Dimension(250, 250));
	panel.setLayout(new BorderLayout());
	panel.add(areaScrollPane, BorderLayout.CENTER);

    }

}
