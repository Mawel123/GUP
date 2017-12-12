
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
    final double guesswork = 65.0;
    private double boundingBox_x, boundingBox_y;
    
    ArrayList<Vector> dataRunner = new ArrayList<>();
    ArrayList<Vector> dataChaserBlue = new ArrayList<>();
    ArrayList<Vector> dataChaserRed = new ArrayList<>();
    
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

    protected void fillCircle(Vector lowerLeftCorner, double radius) {
        Ellipse2D.Double circle = new Ellipse2D.Double(lowerLeftCorner.getX() - radius, lowerLeftCorner.getY() - radius,
                2 * radius, 2 * radius);
        g.fill(circle);
    }

    protected void drawLine(Vector startingPoint, Vector endPoint) {
        Line2D.Double line = new Line2D.Double(startingPoint.getX(), startingPoint.getY(),
                endPoint.getX(), endPoint.getY());
        g.draw(line);
    }

    protected void drawBoundlingBox() {
        Rectangle2D.Double rect1;
        rect1 = new Rectangle2D.Double(-animation.getWidth() / guesswork, -animation.getHeight() / guesswork,
                2 * animation.getWidth() / guesswork,
                2 * animation.getHeight() / guesswork); //toMathPaint coords ?!?!?!?!
        g.draw(rect1);
    }

    protected void plotCoordinateList(ArrayList<Vector> list, double radius) {
        for (int i = 0; i < (list.size() - 1); i++) {
            drawLine(list.get(i), list.get(i + 1));
        }
        fillCircle(list.get(list.size() - 1), 0.15);
    }

    @Override
    public void mathPaint(Graphics2D g) {
        this.g = g;
        animation.setZero(animation.getWidth() / 2, animation.getHeight() / 2);
        animation.setBackground(Color.gray);

        g.setColor(Color.green);
        drawBoundlingBox();
        if (dataRunner.size() > 1) {
            g.setColor(Color.white);
            plotCoordinateList(dataRunner, 0.15);
        }
        if (dataChaserBlue.size() > 1) {
            g.setColor(Color.blue);
            plotCoordinateList(dataChaserBlue, 0.15);
        }
        if (dataChaserRed.size() > 1) {
            g.setColor(Color.red);
            plotCoordinateList(dataChaserRed, 0.15);
        }
    }

    public void init() {
        stepTime = 10;
        next = false;
        timePassed = 0;
         
        boundingBox_x = animation.getWidth() / guesswork;
        boundingBox_y = animation.getHeight() / guesswork;
        
        //runner
        if (dataRunner.isEmpty()) {
            dataRunner.add(new Vector(0, 0));
        }
        //chaser1
        if (dataChaserBlue.isEmpty()) {
            dataChaserBlue.add(new Vector(-3, 6));
        }
        //chaser2
        if (dataChaserRed.isEmpty()) {
            dataChaserRed.add(new Vector(-3, -6));
        }
        animation.repaint();
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
                Thread.sleep(stepTime);
            } catch (InterruptedException ie) {
                //tue nichts
            }
            //step Lengths
            double stepLengthRunner = 0.01;
            double stepLengthChaserBlue = 0.02;
            double stepLengthChaserRed = 0.021;
            
            Vector runnerLastInList = dataRunner.get(dataRunner.size() - 1);
            Vector chaserBlueLastInList = dataChaserBlue.get(dataChaserBlue.size() - 1);
            Vector chaserRedLastInList = dataChaserRed.get(dataChaserRed.size() - 1);
            
            //hit detection
            if (Vector.length(Vector.subtract(chaserBlueLastInList, runnerLastInList)) <= stepLengthChaserBlue
                    || Vector.length(Vector.subtract(chaserRedLastInList, runnerLastInList)) <= stepLengthChaserRed) {
                pcs.firePropertyChange("hit", null, null);
            }
            //out detection
            if (Math.abs(runnerLastInList.getX()) >= boundingBox_x || Math.abs(runnerLastInList.getY()) >= boundingBox_y) {
                pcs.firePropertyChange("hit", null, null);
            }
//==============================================================================           
            //runner: next Punkt line
//            r_x += rd_x;
//            r_y += rd_y;

            // runner: next Punkt circ
//            r_x += 2 * rd_x * Math.cos(0.002 * t);
//            r_y += 2 * rd_x * Math.sin(0.002 * t);

            //runner: next Punkt sin
            Vector nextRunner = new Vector(runnerLastInList.getX() + stepLengthRunner, 
                                           runnerLastInList.getY() + 3 * stepLengthRunner * Math.sin(0.005 * timePassed));
//==============================================================================           
            Vector stepChaserBlue = Vector.scaleToLength(Vector.subtract(runnerLastInList, chaserBlueLastInList), stepLengthChaserBlue);
            Vector nextChaserBlue = (Vector.addUp(chaserBlueLastInList, stepChaserBlue));
            
            Vector stepChaserRed = Vector.scaleToLength(Vector.subtract(runnerLastInList, chaserRedLastInList), stepLengthChaserRed);
            Vector nextChaserRed = (Vector.addUp(chaserRedLastInList, stepChaserRed));
            
            timePassed += stepTime;
            //build arraylist
            dataRunner.add(nextRunner);
            dataChaserBlue.add(nextChaserBlue);
            dataChaserRed.add(nextChaserRed);
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
}
