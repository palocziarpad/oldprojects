package com.snakegame.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class JRButton extends JButton {

    private int width;
    private int height;
    private RoundRectangle2D re;
    private final BasicStroke st = new BasicStroke(2f);
    private double wRatio = 200 / 15;
    private double hRatio = 25 / 12;

    private double arcw;
    private double arch;

    public JRButton(String name, int w, int h) {

        super(name);
        setContentAreaFilled(false);
        setPreferredSize(new Dimension(w, h));
        setFocusable(false);
    }

    @Override
    public void paintComponent(Graphics g) {
        this.width = getWidth();
        this.height = getHeight();
        this.arcw = getWidth() / wRatio;
        this.arch = getHeight() / hRatio;
        re = new RoundRectangle2D.Double(st.getLineWidth() / 2, st.getLineWidth() / 2,
                width - (st.getLineWidth() / 0.5), height - (st.getLineWidth() / 0.5), arcw, arch);

        GradientPaint push = new GradientPaint((width / 2), (height / 2), Color.LIGHT_GRAY, (width / 2), height,
                Color.WHITE, false);
        GradientPaint up = new GradientPaint((width / 2), (height / 2), Color.WHITE, (width / 2), height,
                Color.LIGHT_GRAY, false);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (getModel().isArmed()) {
            g2.setPaint(push);
            g2.fill(re);
        } else {
            g2.setPaint(up);
            g2.fill(re);
        }

        super.paintComponent(g2);

    }

    @Override
    public void paintBorder(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.GRAY);
        g2.setStroke(st);
        g2.draw(re);
        g2.dispose();

    }

}