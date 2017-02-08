package com.snakegame.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class VersionBox extends JDialog {
    private JButton closeButton;

    public VersionBox() {
        initComponents();
        getRootPane().setDefaultButton(closeButton);
    }

    public void closeAboutBox() {
        dispose();
    }

    private void initComponents() {
        this.setSize(300, 150);
        this.setResizable(false);
        this.setTitle("Version changes");

        JPanel panel = new JPanel();
        add(panel);
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

        // panel.add(closeButton);

    }

    public JButton getCloseButton() {
        return closeButton;
    }

}
