
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Mawel
 */
public class Simulation implements MathPainter, Runnable {

    private Thread step;
    public JMath animation;
    public boolean next;
    private long timePassed;
    private long stepTime;
    public PropertyChangeSupport pcs;

    Graphics2D g;

    //runner
    ArrayList<Coordinate> dataRunner = new ArrayList<>();
//    private Coordinate runner_p1, runner_p2;
    private double r_x, r_y;
    private double rd_x, rd_y;

    //chaser1
    ArrayList<Coordinate> dataChaserBlue = new ArrayList<>();
    private Coordinate chaserBlue_p1, chaserRed_p2;
    private double c1_x, c1_y;
    private double c1_dx, c1_dy;

    //chaser2
    ArrayList<Coordinate> dataChaserRed = new ArrayList<>();
    private Coordinate chaser2_p1, chaser2_p2;
    private double c2_x, c2_y;
    private double c2_dx, c2_dy;

    private double boundingBox_x, boundingBox_y;

    public Simulation(JMath anim) {
        this.animation = anim;
        anim.setEnableMouse(false);
        pcs = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener interessent) {
        pcs.addPropertyChangeListener(interessent);
    }

    public void removePropertyChangeListener(PropertyChangeListener interessent) {
        pcs.addPropertyChangeListener(interessent);
    }

    private void fillCircle(Coordinate lowerLeftCorner, double radius) {
        Ellipse2D.Double circle = new Ellipse2D.Double(lowerLeftCorner.getX() - radius, lowerLeftCorner.getY() - radius, 2 * radius, 2 * radius);
        g.fill(circle);
    }
    
    private void drawLine(Coordinate startingPoint, Coordinate endPoint) {
        Line2D.Double line = new Line2D.Double(startingPoint.getX(), startingPoint.getY(), endPoint.getX(), endPoint.getY());
        g.draw(line);
    }
    
    private void paintCoordinateList(ArrayList<Coordinate> list, double radius) {
            for (int i = 0; i < (list.size() - 1); i++) {
                drawLine(list.get(i), list.get(i + 1));
            }
            fillCircle(list.get(list.size() - 1), 0.15);
    }

    @Override
    public void mathPaint(Graphics2D g) {
        int w = animation.getWidth();
        int h = animation.getHeight();
        animation.setZero(w / 2, h / 2);
        animation.setBackground(Color.black);

        //MathPaint coords ?!?!?!?!
        boundingBox_x = animation.getWidth() / 65.0;
        boundingBox_y = animation.getHeight() / 65.0;

        g.setColor(Color.green);
        Rectangle2D.Double rect1;
        rect1 = new Rectangle2D.Double(-boundingBox_x, -boundingBox_y, 2 * boundingBox_x, 2 * boundingBox_y);
        g.draw(rect1);

        if (dataRunner.size() > 1 && dataChaserBlue.size() > 1 && dataChaserRed.size() > 1) {
//            g.setColor(Color.white);
//            for (int i = 0; i < (dataRunner.size() - 1); i++) {
//                runner_p1 = dataRunner.get(i);
//                runner_p2 = dataRunner.get(i + 1);
//
//                Line2D.Double linie1;
//                linie1 = new Line2D.Double(runner_p1.getX(), runner_p1.getY(), runner_p2.getX(), runner_p2.getY());
//                g.draw(linie1);
//
//            }
            g.setColor(Color.white);
            paintCoordinateList(dataRunner, 0.15);

            g.setColor(Color.blue);
            for (int i = 0; i < (dataChaserBlue.size() - 1); i++) {
                chaserBlue_p1 = dataChaserBlue.get(i);
                chaserRed_p2 = dataChaserBlue.get(i + 1);

                Line2D.Double linie2;
                linie2 = new Line2D.Double(chaserBlue_p1.getX(), chaserBlue_p1.getY(), chaserRed_p2.getX(), chaserRed_p2.getY());
                g.draw(linie2);

            }
            Ellipse2D.Double kreis2;
            kreis2 = new Ellipse2D.Double(chaserRed_p2.getX() - 0.15, chaserRed_p2.getY() - 0.15, 0.3, 0.3);
            g.fill(kreis2);

            g.setColor(Color.red);
            for (int i = 0; i < (dataChaserRed.size() - 1); i++) {
                chaser2_p1 = dataChaserRed.get(i);
                chaser2_p2 = dataChaserRed.get(i + 1);

                Line2D.Double linie3;
                linie3 = new Line2D.Double(chaser2_p1.getX(), chaser2_p1.getY(), chaser2_p2.getX(), chaser2_p2.getY());
                g.draw(linie3);

            }
            Ellipse2D.Double kreis3;
            kreis3 = new Ellipse2D.Double(chaser2_p2.getX() - 0.15, chaser2_p2.getY() - 0.15, 0.3, 0.3);
            g.fill(kreis3);
        }

    }

    public void init() {
        setDeltaT(10);
        next = false;
        timePassed = 0;
        boundingBox_x = animation.getWidth() / 65.0;
        boundingBox_y = animation.getHeight() / 65.0;
        //runner
        if (dataRunner.isEmpty()) {
            r_x = 0;
            r_y = 0;
        }
        //chaser1
        if (dataChaserBlue.isEmpty()) {
            c1_x = -3;
            c1_y = 6;
        }
        //chaser2
        if (dataChaserRed.isEmpty()) {
            c2_x = -3;
            c2_y = -6;
        }
        //build start pos
        dataRunner.add(new Coordinate(r_x, r_y));
        dataChaserBlue.add(new Coordinate(c1_x, c1_y));
        dataChaserRed.add(new Coordinate(c2_x, c2_y));
    }

    public void reset() {
        dataRunner.clear();
        dataChaserBlue.clear();
        dataChaserRed.clear();
        animation.repaint();
    }

    @Override
    public void run() {
        while (next) {
            try {
                Thread.sleep(getDeltaT());
            } catch (InterruptedException ie) {
                //tue nichts
            }
            //v runner
            rd_x = 0.01;
            rd_y = 0.01;
            //runner: next Punkt line
//            r_x += rd_x;
//            r_y += rd_y;
            //runner: next Punkt sin
            timePassed += stepTime;
            Coordinate p = dataRunner.get(dataRunner.size() - 1);
            p.setX(p.getX() + rd_x);
            p.setY(p.getY() + 3 * rd_x * Math.sin(0.005 * timePassed));
            // runner: next Punkt circ
            timePassed += stepTime;
//            r_x += 2 * rd_x * Math.cos(0.002 * t);
//            r_y += 2 * rd_x * Math.sin(0.002 * t);

            //v chaser1
            double direction_x = p.getX() - c1_x;
            double direction_y = p.getY() - c1_y;
            double scaleToOne = Math.sqrt(direction_x * direction_x + direction_y * direction_y);
            direction_x /= scaleToOne;
            direction_y /= scaleToOne;
            c1_dx = direction_x * 0.01;
            c1_dy = direction_y * 0.01;

            //v chaser2
            direction_x = p.getX() - c2_x;
            direction_y = p.getY() - c2_y;
            scaleToOne = Math.sqrt(direction_x * direction_x + direction_y * direction_y);
            direction_x /= scaleToOne;
            direction_y /= scaleToOne;
            c2_dx = direction_x * 0.015;
            c2_dy = direction_y * 0.015;

            //chaser1: next Punkt
            c1_x += c1_dx;
            c1_y += c1_dy;
            //chaser2: next Punkt
            c2_x += c2_dx;
            c2_y += c2_dy;

            //hit detection
            double distance_1, distance_2;
            distance_1 = Math.sqrt(Math.pow(p.getX() - c1_x, 2) + Math.pow(p.getY() - c1_y, 2));
            distance_2 = Math.sqrt(Math.pow(p.getX() - c2_x, 2) + Math.pow(p.getY() - c2_y, 2));
            if (distance_1 < 0.1 || distance_2 < 0.1) {
                pcs.firePropertyChange("hit", null, null);
            }
            //out detection
            if (Math.abs(p.getX()) >= boundingBox_x || Math.abs(p.getY()) >= boundingBox_y) {
                pcs.firePropertyChange("hit", null, null);
            }

            //build arraylist
            dataRunner.add(new Coordinate(p.getX(), p.getY()));
            dataChaserBlue.add(new Coordinate(c1_x, c1_y));
            dataChaserRed.add(new Coordinate(c2_x, c2_y));
            animation.repaint();

        }
        step = null;
    }

    public void start() {
        if (step == null) {
            step = new Thread(this);
            next = true;
            step.start();
        }
    }

    public void stop() {
        next = false;
    }

    /**
     * @return the deltaT
     */
    protected long getDeltaT() {
        return stepTime;
    }

    /**
     * @param deltaT the deltaT to set
     */
    protected void setDeltaT(long deltaT) {
        this.stepTime = deltaT;
    }

}
