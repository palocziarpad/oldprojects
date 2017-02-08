package com.snakegame.view;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class JRButton extends JButton {

    private int w;
    private int h;
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
        ;
    }

    @Override
    public void paintComponent(Graphics g) {
        this.w = getWidth();
        this.h = getHeight();
        this.arcw = getWidth() / wRatio;
        this.arch = getHeight() / hRatio;
        re = new RoundRectangle2D.Double(st.getLineWidth() / 2, st.getLineWidth() / 2, w - (st.getLineWidth() / 0.5),
                h - (st.getLineWidth() / 0.5), arcw, arch);

        GradientPaint push = new GradientPaint((w / 2), (h / 2), Color.LIGHT_GRAY, (w / 2), h, Color.WHITE, false);
        GradientPaint up = new GradientPaint((w / 2), (h / 2), Color.WHITE, (w / 2), h, Color.LIGHT_GRAY, false);

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

    // Test
    public static void main(String[] args) {

        JFrame j = new JFrame();
        JButton po = new JRButton("JRButton", 200, 28);
        j.setLayout(new FlowLayout(FlowLayout.CENTER));
        j.getContentPane().add(po);
        j.getRootPane().setDefaultButton(po);
        j.setSize(400, 400);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setVisible(true);

    }

}