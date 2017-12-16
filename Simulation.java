
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Toolkit;
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
    private Double alpha;
    public boolean mouseControlEnabled = false;
    public double animationScale = 1;

    public double stepLengthRunner = 0.01;
    public double stepLengthChaserBlue = 0.01;
    public double stepLengthChaserRed = 0.01;
    public double stepLengthRiver = 0.0075 * animationScale;
    public double radius = 0.15;

    Graphics2D g;
    final double guesswork = 65.0; //Faulheit
    protected double boundingBox_x, boundingBox_y;

    ArrayList<Vector> dataRunner = new ArrayList<>();
    ArrayList<Vector> dataChaserBlue = new ArrayList<>();
    ArrayList<Vector> dataChaserRed = new ArrayList<>();

    public boolean chaserBlueEnabled = true;
    public boolean chaserRedEnabled = true;
    public boolean paintRunnerDirection = false;
    public boolean paintRunnerDirectionDone = true;
    public boolean riverEnabled = true;
    public String runnerMode = "Gerade";
    public Vector runnerDirection;
    public Line2D.Double runnerDirectionLine = new Line2D.Double();

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

    protected void drawCircle(Vector lowerLeftCorner, double radius) {
        Ellipse2D.Double circle = new Ellipse2D.Double(lowerLeftCorner.getX() - radius, lowerLeftCorner.getY() - radius,
                2 * radius, 2 * radius);
        g.draw(circle);
    }

    protected void drawLine(Vector startingPoint, Vector endPoint) {
        Line2D.Double line = new Line2D.Double(startingPoint.getX(), startingPoint.getY(),
                endPoint.getX(), endPoint.getY());
        g.draw(line);
    }

    protected void drawLine(Line2D.Double line) {
        g.draw(line);
    }

    protected void drawRectangle(double x1, double y1, double x2, double y2) {
        Rectangle2D.Double rectangle = new Rectangle2D.Double(x1, y1, x2, y2);
        g.draw(rectangle);
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
        fillCircle(list.get(list.size() - 1), radius);
    }

    private void paintRiver() {
        g.setColor(Color.cyan);
        drawRectangle(-boundingBox_x, -boundingBox_y + 0.3 * boundingBox_y, 2 * boundingBox_x, boundingBox_y - 0.6 * boundingBox_y);
    }

    @Override
    public void mathPaint(Graphics2D g) {
        this.g = g;
        animation.setZero(animation.getWidth() / 2, animation.getHeight() / 2);
        animation.setBackground(Color.black);

        if (mouseControlEnabled && paintRunnerDirection && (runnerMode.equals("Gerade") || runnerMode.equals("Sinus"))) {
            g.setColor(Color.lightGray);
            drawLine(runnerDirectionLine);
        }

        if (paintRunnerDirectionDone && (runnerMode.equals("Gerade") || runnerMode.equals("Sinus"))) {
            g.setColor(Color.lightGray);
            drawLine(runnerDirectionLine);
        }

        g.setColor(Color.green);
        drawBoundlingBox();

        if (riverEnabled) {
            paintRiver();
        }

        if (dataRunner.size() > 1) {
            g.setColor(Color.white);
            plotCoordinateList(dataRunner, radius);
        } else if (dataRunner.size() == 1) {
            g.setColor(Color.white);
            fillCircle(dataRunner.get(0), radius);
        }
        if (chaserBlueEnabled) {
            if (dataChaserBlue.size() > 1) {
                g.setColor(Color.blue);
                plotCoordinateList(dataChaserBlue, radius);
            } else if (dataChaserBlue.size() == 1) {
                g.setColor(Color.blue);
                fillCircle(dataChaserBlue.get(0), radius);
            }
        } else if (!dataChaserBlue.isEmpty()) {
            g.setColor(Color.blue);
            drawCircle(dataChaserBlue.get(dataChaserBlue.size() - 1), radius);
        }

        if (chaserRedEnabled) {
            if (dataChaserRed.size() > 1) {
                g.setColor(Color.red);
                plotCoordinateList(dataChaserRed, radius);
            } else if (dataChaserRed.size() == 1) {
                g.setColor(Color.red);
                fillCircle(dataChaserRed.get(0), radius);
            }
        } else if (!dataChaserRed.isEmpty()) {
            g.setColor(Color.red);
            drawCircle(dataChaserRed.get(dataChaserRed.size() - 1), radius);
        }
        Toolkit.getDefaultToolkit().sync();
    }

    protected void fireCoordinateChange() {
        pcs.firePropertyChange("nextRunner", null, dataRunner.get(dataRunner.size() - 1));
        pcs.firePropertyChange("nextChaserBlue", null, dataChaserBlue.get(dataChaserBlue.size() - 1));
        pcs.firePropertyChange("nextChaserRed", null, dataChaserRed.get(dataChaserRed.size() - 1));
    }

    public void init() {
        stepTime = 10;
        next = false;
        timePassed = 0;
        runnerDirection = new Vector(1, 0);

        if (dataRunner.isEmpty()) {
            runnerDirectionLine.setLine(-animationScale * Math.PI, 0, 100, 0);
        }

        boundingBox_x = animation.getWidth() / guesswork;
        boundingBox_y = animation.getHeight() / guesswork;

        //runner
        if (dataRunner.isEmpty()) {
            dataRunner.add(new Vector(-animationScale * Math.PI, 0));
            runnerDirectionLine.setLine(-animationScale * Math.PI, 0, 100, 0);
            alpha = 0.0;
        }
        //chaser1
        if (dataChaserBlue.isEmpty()) {
            dataChaserBlue.add(new Vector(-2 * animationScale * Math.PI, 4 * animationScale / Math.sqrt(2)));
        }
        //chaser2
        if (dataChaserRed.isEmpty()) {
            dataChaserRed.add(new Vector(2 * animationScale * Math.PI, -4 * animationScale / Math.sqrt(2)));
        }

        fireCoordinateChange();
    }

    public void reset() {
        dataRunner.clear();
        dataChaserBlue.clear();
        dataChaserRed.clear();

        stepLengthRunner = 0.01;

        animation.repaint();
    }

    private Vector applyRiverCurrent(Vector last, Vector next) {
        if (last.y >= -boundingBox_y + 0.3 * boundingBox_y
                && last.y <= -boundingBox_y + 0.3 * boundingBox_y + boundingBox_y - 0.6 * boundingBox_y) {
            next = Vector.addUp(next, new Vector(stepLengthRiver, 0.0));
        }
        return next;
    }

    @Override
    public void run() {
        while (next) {
            try {
                Thread.sleep(stepTime);
            } catch (InterruptedException ie) {
                //tue nichts
            }

            Vector runnerLastInList = dataRunner.get(dataRunner.size() - 1);
            Vector chaserBlueLastInList = dataChaserBlue.get(dataChaserBlue.size() - 1);
            Vector chaserRedLastInList = dataChaserRed.get(dataChaserRed.size() - 1);

            //hit detection
            double hitbox = 0.03;
            if (Vector.length(Vector.subtract(chaserBlueLastInList, runnerLastInList)) <= hitbox
                    || Vector.length(Vector.subtract(chaserRedLastInList, runnerLastInList)) <= hitbox) {
                pcs.firePropertyChange("hit", null, null);
            }
            //out detection
            if (Math.abs(runnerLastInList.getX()) >= boundingBox_x || Math.abs(runnerLastInList.getY()) >= boundingBox_y) {
                pcs.firePropertyChange("hit", null, null);
            }

            //calculating next runner point
            Vector nextRunner;
            Vector stepRunner;
            double nextTrigonX, nextTrigonY;
            double scale = 0.00025 * stepLengthRunner * 100 * (1 / animationScale);
            double amplitude = 0.01 * stepLengthRunner * 100 * Math.PI;

            alpha = Math.atan2(runnerDirectionLine.y2 - runnerDirectionLine.y1, runnerDirectionLine.x2 - runnerDirectionLine.x1);
            //line
            if (runnerMode.equals("Gerade")) {
                stepRunner = Vector.scaleToLength(runnerDirection, stepLengthRunner);
                nextRunner = Vector.addUp(runnerLastInList, Vector.rotateByDegree(Vector.scaleToLength(stepRunner, stepLengthRunner), alpha));
                if (riverEnabled) {
                    nextRunner = applyRiverCurrent(runnerLastInList, nextRunner);
                }
                //sine
            } else if (runnerMode.equals("Sinus")) {
                nextTrigonY = amplitude * (Math.cos((2 * Math.PI * scale) * timePassed));
                runnerDirection = new Vector(stepLengthRunner, nextTrigonY);
                stepRunner = Vector.scaleToLength(runnerDirection, stepLengthRunner);
                nextRunner = Vector.addUp(runnerLastInList, Vector.rotateByDegree(Vector.scaleToLength(stepRunner, stepLengthRunner), alpha));
                if (runnerLastInList.y >= -3.1 && runnerLastInList.y <= -1.3) {
                    nextRunner = Vector.addUp(nextRunner, new Vector(stepLengthRiver, 0.0));
                }
                if (riverEnabled) {
                    nextRunner = applyRiverCurrent(runnerLastInList, nextRunner);
                }
                //circle
            } else if (runnerMode.equals("Kreis")) {
                nextTrigonX = amplitude * (Math.sin((2 * Math.PI * scale / 5) * timePassed));
                nextTrigonY = amplitude * (Math.cos((2 * Math.PI * scale / 5) * timePassed));
                runnerDirection = new Vector(nextTrigonX, nextTrigonY);
                stepRunner = Vector.scaleToLength(runnerDirection, stepLengthRunner);
                nextRunner = Vector.addUp(runnerLastInList, Vector.scaleToLength(stepRunner, stepLengthRunner));
                if (riverEnabled) {
                    nextRunner = applyRiverCurrent(runnerLastInList, nextRunner);
                }
            } else {
                nextRunner = runnerLastInList;
            }
            timePassed += stepTime;

            //calculating next chaserBlue point
            Vector nextChaserBlue;
            Vector stepChaserBlue = Vector.scaleToLength(Vector.subtract(runnerLastInList, chaserBlueLastInList), stepLengthChaserBlue);
            if (chaserBlueEnabled) {
                nextChaserBlue = (Vector.addUp(chaserBlueLastInList, stepChaserBlue));
            } else {
                nextChaserBlue = chaserBlueLastInList;
            }
            if (riverEnabled) {
                nextChaserBlue = applyRiverCurrent(chaserBlueLastInList, nextChaserBlue);
            }
            //calculating next chaserRed point
            Vector nextChaserRed;
            Vector stepChaserRed = Vector.scaleToLength(Vector.subtract(runnerLastInList, chaserRedLastInList), stepLengthChaserRed);
            if (chaserRedEnabled) {
                nextChaserRed = (Vector.addUp(chaserRedLastInList, stepChaserRed));
            } else {
                nextChaserRed = chaserRedLastInList;
            }
            if (riverEnabled) {
                nextChaserRed = applyRiverCurrent(chaserRedLastInList, nextChaserRed);
            }

            //River
            //building arraylists
            dataRunner.add(nextRunner);
            dataChaserBlue.add(nextChaserBlue);
            dataChaserRed.add(nextChaserRed);

            fireCoordinateChange();

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
