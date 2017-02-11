package com.snakegame.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.IOException;

import javax.swing.JPanel;

import com.snakegame.control.Game;
import com.snakegame.model.SnakeBodyPart;
import com.snakegame.model.SnakeTheme;

@SuppressWarnings("serial")
public class Board extends JPanel {

    // egy darab kis n�gyzetnek a m�rete
    final int squarepixsize = 21;
    // a n�gyzetek �sszdarabsz�ma
    final int squarecount = 20;
    // az �ssz pixel m�ret ami kell az eg�sz megrajzol�shoz
    final int maxsize = squarecount * squarepixsize;
    private Game snakegame;
    private SnakeTheme sp;
    private Point point;
    private boolean pause;

    public void paint(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        point.x = (640 - 421) / 8;
        point.y = 10;
        g2d.drawImage(sp.BIARRAY[12], 0, 00, null);
        g2d.drawImage(sp.BIARRAY[13], point.x, point.y, null);
        g2d.drawImage(sp.BIARRAY[14], point.x + 421, point.y, null);
        g2d.setBackground(Color.white);

        g2d.setColor(Color.orange);

        try {
            drawSnake_foodsver(g2d);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        drawPause(g2d);

    }

    public void reinit() {
        snakegame.reInit();
    }

    public Board() {
        snakegame = new Game();
        this.setBackground(Color.white);
        this.setBounds(0, 0, maxsize, maxsize);
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setSize(640, 480);
        point = new Point(0, 0);
        this.sp = new SnakeTheme();
    }

    public void drawPause(Graphics2D g2d) {
        if (pause) {
            g2d.setFont(new Font("SansSerif", Font.BOLD, 50));
            g2d.drawString("PAUSED", 235, 240);
        }
    }

    public void drawSnake_foodsver(Graphics2D g2d) throws IOException, InterruptedException {

        if (getSnakegame().getSnake().size() == 0) {
            System.out.println("null snake");
            return;
        }
        // food
        drawFoods(g2d);
        // head
        drawHead(g2d);
        // torso
        drawTorsoWithSlant(g2d);
        // tail
        drawTail(g2d);
        // draweatenfoods;
        drawEatenFoods(g2d);
        // score points
        drawScore(g2d);
    }

    public void drawEatenFoods(Graphics2D g2d) {
        for (int k = 0; k < snakegame.getEatenFood().size(); k++) {
            g2d.drawImage(sp.BIARRAY[20], point.x + snakegame.getEatenFood().get(k).getX() * squarepixsize + 1,
                    point.y + snakegame.getEatenFood().get(k).getY() * squarepixsize + 1, null);
        }
    }

    public void drawSnake(Graphics2D g2d) throws IOException, InterruptedException {
        if (getSnakegame().getSnake().size() == 0) {
            return;
        }
        // food
        drawFood(g2d);
        // head
        drawHead(g2d);
        // torso
        drawTorsoWithSlant(g2d);
        // tail
        drawTail(g2d);
        // score points
        drawScore(g2d);
    }

    public void drawFood(Graphics2D g2d) {
        g2d.drawImage(sp.BIARRAY[11], point.x + Game.getFood()[0].getX() * squarepixsize + 1,
                point.y + Game.getFood()[0].getY() * squarepixsize + 1, null);
        if (snakegame.isShowfood2())
            g2d.drawImage(sp.BIARRAY[SnakeTheme.PIC.FOOD2.ordinal()],
                    point.x + Game.getFood()[1].getX() * squarepixsize + 1,
                    point.y + Game.getFood()[1].getY() * squarepixsize + 1, null);
    }

    public void drawFoods(Graphics2D g2d) {

        g2d.drawImage(sp.BIARRAY[11], point.x + snakegame.getKaja2().getFirst().getX() * squarepixsize + 1,
                point.y + snakegame.getKaja2().getFirst().getY() * squarepixsize + 1, null);
        if (snakegame.getKaja2().size() > 1)
            g2d.drawImage(sp.BIARRAY[SnakeTheme.PIC.FOOD2.ordinal()],
                    point.x + snakegame.getKaja2().get(1).getX() * squarepixsize + 1,
                    point.y + snakegame.getKaja2().get(1).getY() * squarepixsize + 1, null);
    }

    public void drawHead(Graphics2D g2d) {
        switch (getSnakegame().getSnake().getFirst().getDir()) {
        case UP:
            g2d.drawImage(sp.BIARRAY[5], point.x + getSnakegame().getSnake().getFirst().getX() * squarepixsize + 1,
                    point.y + getSnakegame().getSnake().getFirst().getY() * squarepixsize + 1, null);
            break;
        case RIGHT:
            g2d.drawImage(sp.BIARRAY[6], point.x + getSnakegame().getSnake().getFirst().getX() * squarepixsize + 1,
                    point.y + getSnakegame().getSnake().getFirst().getY() * squarepixsize + 1, null);
            break;
        case DOWN:
            g2d.drawImage(sp.BIARRAY[7], point.x + getSnakegame().getSnake().getFirst().getX() * squarepixsize + 1,
                    point.y + getSnakegame().getSnake().getFirst().getY() * squarepixsize + 1, null);
            break;
        case LEFT:
            g2d.drawImage(sp.BIARRAY[8], point.x + getSnakegame().getSnake().getFirst().getX() * squarepixsize + 1,
                    point.y + getSnakegame().getSnake().getFirst().getY() * squarepixsize + 1, null);
            break;
        }
    }

    public void drawTorso(Graphics2D g2d) {

        for (int k = 1; k < getSnakegame().getSnake().size() - 1; k++) {

            switch (getSnakegame().getSnake().get(k).getDir()) {
            case UP:
                g2d.drawImage(sp.BIARRAY[9], point.x + getSnakegame().getSnake().get(k).getX() * squarepixsize + 1,
                        point.y + getSnakegame().getSnake().get(k).getY() * squarepixsize + 1, null);
                break;
            case RIGHT:
                g2d.drawImage(sp.BIARRAY[10], point.x + getSnakegame().getSnake().get(k).getX() * squarepixsize + 1,
                        point.y + getSnakegame().getSnake().get(k).getY() * squarepixsize + 1, null);
                break;
            case DOWN:
                g2d.drawImage(sp.BIARRAY[9], point.x + getSnakegame().getSnake().get(k).getX() * squarepixsize + 1,
                        point.y + getSnakegame().getSnake().get(k).getY() * squarepixsize + 1, null);
                break;
            case LEFT:
                g2d.drawImage(sp.BIARRAY[10], point.x + getSnakegame().getSnake().get(k).getX() * squarepixsize + 1,
                        point.y + getSnakegame().getSnake().get(k).getY() * squarepixsize + 1, null);
                break;
            }
        }
    }

    public void drawTorsoWithSlant(Graphics2D g2d) {

        SnakeTheme.PIC p;
        for (int k = 1; k < getSnakegame().getSnake().size() - 1; k++) {
            if (getSnakegame().getSnake().get(k).getPartKind() == SnakeBodyPart.Parts.NEWPART) {

                g2d.drawImage(sp.BIARRAY[20], point.x + getSnakegame().getSnake().get(k).getX() * squarepixsize + 1,
                        point.y + getSnakegame().getSnake().get(k).getY() * squarepixsize + 1, null);
            } else if (isSlam(getSnakegame().getSnake().get(k - 1), getSnakegame().getSnake().get(k + 1))) {
                p = whichSlam(getSnakegame().getSnake().get(k - 1), getSnakegame().getSnake().get(k),
                        getSnakegame().getSnake().get(k + 1));
                g2d.drawImage(sp.BIARRAY[p.ordinal()],
                        point.x + getSnakegame().getSnake().get(k).getX() * squarepixsize + 1,
                        point.y + getSnakegame().getSnake().get(k).getY() * squarepixsize + 1, null);
            } else {
                switch (getSnakegame().getSnake().get(k).getDir()) {
                case UP:
                    g2d.drawImage(sp.BIARRAY[9], point.x + getSnakegame().getSnake().get(k).getX() * squarepixsize + 1,
                            point.y + getSnakegame().getSnake().get(k).getY() * squarepixsize + 1, null);
                    break;
                case RIGHT:
                    g2d.drawImage(sp.BIARRAY[10], point.x + getSnakegame().getSnake().get(k).getX() * squarepixsize + 1,
                            point.y + getSnakegame().getSnake().get(k).getY() * squarepixsize + 1, null);
                    break;
                case DOWN:
                    g2d.drawImage(sp.BIARRAY[9], point.x + getSnakegame().getSnake().get(k).getX() * squarepixsize + 1,
                            point.y + getSnakegame().getSnake().get(k).getY() * squarepixsize + 1, null);
                    break;
                case LEFT:
                    g2d.drawImage(sp.BIARRAY[10], point.x + getSnakegame().getSnake().get(k).getX() * squarepixsize + 1,
                            point.y + getSnakegame().getSnake().get(k).getY() * squarepixsize + 1, null);
                    break;
                }
            }
        }
    }

    public void drawTail(Graphics2D g2d) {
        switch (getSnakegame().getSnake().getLast().getDir()) {
        case UP:
            g2d.drawImage(sp.BIARRAY[2], point.x + getSnakegame().getSnake().getLast().getX() * squarepixsize + 1,
                    point.y + getSnakegame().getSnake().getLast().getY() * squarepixsize + 1, null);
            break;
        case RIGHT:

            g2d.drawImage(sp.BIARRAY[3], point.x + getSnakegame().getSnake().getLast().getX() * squarepixsize + 1,
                    point.y + getSnakegame().getSnake().getLast().getY() * squarepixsize + 1, null);
            break;
        case DOWN:

            g2d.drawImage(sp.BIARRAY[4], point.x + getSnakegame().getSnake().getLast().getX() * squarepixsize + 1,
                    point.y + getSnakegame().getSnake().getLast().getY() * squarepixsize + 1, null);
            break;
        case LEFT:

            g2d.drawImage(sp.BIARRAY[1], point.x + getSnakegame().getSnake().getLast().getX() * squarepixsize + 1,
                    point.y + getSnakegame().getSnake().getLast().getY() * squarepixsize + 1, null);
            break;
        }
    }

    public void drawScore(Graphics2D g2d) {
        g2d.setFont(new Font("SansSerif", Font.BOLD, 14));
        if (snakegame.getScore() > 99 && snakegame.getScore() < 200)
            g2d.setColor(Color.BLUE);
        else if (snakegame.getScore() > 199)
            g2d.setColor(Color.RED);
        g2d.drawString("" + getSnakegame().getScore(), point.x + 421 + 184 / 2 - 5, point.y + 153 / 2 + 30);
    }

    public boolean isSlam(SnakeBodyPart before, SnakeBodyPart after) {
        if (before.getX() != after.getX() && before.getY() != after.getY()) {
            return true;
        }
        return false;
    }

    public SnakeTheme.PIC whichSlam(SnakeBodyPart before, SnakeBodyPart current, SnakeBodyPart after) {
        boolean l = false, r = false, u = false, d = false;
        if (before.getX() > current.getX())
            r = true;
        else if (before.getX() < current.getX())
            l = true;
        else if (before.getY() > current.getY())
            d = true;
        else if (before.getY() < current.getY())
            u = true;

        if (after.getX() > current.getX())
            r = true;
        else if (after.getX() < current.getX())
            l = true;
        else if (after.getY() > current.getY())
            d = true;
        else if (after.getY() < current.getY())
            u = true;

        if (r && u)
            return SnakeTheme.PIC.SLANTRU;
        else if (l && d)
            return SnakeTheme.PIC.SLANTLD;
        else if (l && u)
            return SnakeTheme.PIC.SLANTLU;
        else if (r && d)
            return SnakeTheme.PIC.SLANTRD;
        return null;

    }

    public void setSnakegame(Game snakegame) {
        this.snakegame = snakegame;
    }

    public Game getSnakegame() {
        return snakegame;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public void pauseSwitch() {
        pause = !pause;
    }

    public SnakeTheme getSp() {
        return sp;
    }

    public void setSp(SnakeTheme sp) {
        this.sp = sp;
    }

}