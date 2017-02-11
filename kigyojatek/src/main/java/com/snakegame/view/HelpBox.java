package com.snakegame.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Help window.
 *
 */
public class HelpBox extends JDialog {
    /**
     * Constructor.
     */
    public HelpBox() {
        // super(parent);
        initComponents();
    }

    /**
     * Close the about window
     */
    public void closeAboutBox() {
        dispose();
    }

    private void initComponents() {
        this.setSize(300, 150);
        this.setResizable(false);
        this.setTitle("Help");

        JPanel panel = new JPanel();
        add(panel);

        JTextArea jt = new JTextArea();
        jt.setText("For the control use WASD keys.\n" + "W or up arrow for Up\n" + "A or left arrow for Left \n"
                + "S or down arrow for Down\n" + "D or right arrow for Right\n" + "p for pause");
        jt.setEditable(false);
        jt.setLineWrap(true);

        JScrollPane areaScrollPane = new JScrollPane(jt);
        areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        areaScrollPane.setPreferredSize(new Dimension(250, 250));

        panel.setLayout(new BorderLayout());
        panel.add(areaScrollPane, BorderLayout.CENTER);

    }
}