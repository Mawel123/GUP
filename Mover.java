
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
public class Mover implements MathPainter, Runnable {

    protected Thread painter;
    protected JMath anim;
    protected boolean weiter;
    public long t;
    public long dT;
    protected PropertyChangeSupport pcs;

    //runner
    ArrayList<Punkt> data_runner = new ArrayList<Punkt>();
    protected Punkt runner_p1, runner_p2;
    protected double r_x, r_y;
    protected double rd_x, rd_y;

    //chaser1
    ArrayList<Punkt> data_chaser1 = new ArrayList<Punkt>();
    protected Punkt chaser1_p1, chaser1_p2;
    protected double c1_x, c1_y;
    protected double c1_dx, c1_dy;

    //chaser2
    ArrayList<Punkt> data_chaser2 = new ArrayList<Punkt>();
    protected Punkt chaser2_p1, chaser2_p2;
    protected double c2_x, c2_y;
    protected double c2_dx, c2_dy;

    protected double out_x, out_y;

    public Mover(JMath anim) {
        this.anim = anim;
        anim.setEnableMouse(false);
        pcs = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener interessent) {
        pcs.addPropertyChangeListener(interessent);
    }

    public void removePropertyChangeListener(PropertyChangeListener interessent) {
        pcs.addPropertyChangeListener(interessent);
    }

    public void mathPaint(Graphics2D g) {
        int w = anim.getWidth();
        int h = anim.getHeight();
        anim.setZero(w / 2, h / 2);
        anim.setBackground(Color.black);

        g.setColor(Color.green);
        Rectangle2D.Double rect1;
        rect1 = new Rectangle2D.Double(-out_x, -out_y, 2 * out_x, 2 * out_y);
        g.draw(rect1);

        if (!data_runner.isEmpty() && !data_chaser1.isEmpty() && !data_chaser2.isEmpty()) {
            g.setColor(Color.white);
            for (int i = 0; i < (data_runner.size() - 1); i++) {
                runner_p1 = data_runner.get(i);
                runner_p2 = data_runner.get(i + 1);

                Line2D.Double linie1;
                linie1 = new Line2D.Double(runner_p1.x, runner_p1.y, runner_p2.x, runner_p2.y);
                g.draw(linie1);

            }
            Ellipse2D.Double kreis1;
            kreis1 = new Ellipse2D.Double(runner_p2.x - 0.15, runner_p2.y - 0.15, 0.3, 0.3);
            g.fill(kreis1);

            g.setColor(Color.blue);
            for (int i = 0; i < (data_chaser1.size() - 1); i++) {
                chaser1_p1 = data_chaser1.get(i);
                chaser1_p2 = data_chaser1.get(i + 1);

                Line2D.Double linie2;
                linie2 = new Line2D.Double(chaser1_p1.x, chaser1_p1.y, chaser1_p2.x, chaser1_p2.y);
                g.draw(linie2);

            }
            Ellipse2D.Double kreis2;
            kreis2 = new Ellipse2D.Double(chaser1_p2.x - 0.15, chaser1_p2.y - 0.15, 0.3, 0.3);
            g.fill(kreis2);

            g.setColor(Color.red);
            for (int i = 0; i < (data_chaser2.size() - 1); i++) {
                chaser2_p1 = data_chaser2.get(i);
                chaser2_p2 = data_chaser2.get(i + 1);

                Line2D.Double linie3;
                linie3 = new Line2D.Double(chaser2_p1.x, chaser2_p1.y, chaser2_p2.x, chaser2_p2.y);
                g.draw(linie3);

            }
            Ellipse2D.Double kreis3;
            kreis3 = new Ellipse2D.Double(chaser2_p2.x - 0.15, chaser2_p2.y - 0.15, 0.3, 0.3);
            g.fill(kreis3);
        }

    }

    protected void init() {
        setDeltaT(10);
        weiter = false;
        t = 0;
        out_x = anim.getWidth() / 65.0;
        out_y = anim.getHeight() / 65.0;
        //runner
        r_x = 0;
        r_y = 0;
        //chser1
        c1_x = -3;
        c1_y = 6;
        //chaser2
        c2_x = -3;
        c2_y = -6;
        //build start pos
        data_runner.add(new Punkt(r_x, r_y));
        data_chaser1.add(new Punkt(c1_x, c1_y));
        data_chaser2.add(new Punkt(c2_x, c2_y));
    }

    protected void reset() {
        data_runner.clear();
        data_chaser1.clear();
        data_chaser2.clear();
        anim.repaint();
    }

    public void run() {
        while (weiter) {
            try {
                Thread.sleep(getDeltaT());
            } catch (InterruptedException ie) {
                //tue nichts
            }
            //v_runner line
            rd_x = -0.01;
            rd_y = 0;
            //v_runner sin

            //v chaser1
            double target_x = r_x - c1_x;
            double target_y = r_y - c1_y;
            double norm = Math.sqrt(target_x * target_x + target_y * target_y);
            target_x /= norm;
            target_y /= norm;
            c1_dx = target_x * 0.01;
            c1_dy = target_y * 0.01;

            //v chaser2
            target_x = r_x - c2_x;
            target_y = r_y - c2_y;
            norm = Math.sqrt(target_x * target_x + target_y * target_y);
            target_x /= norm;
            target_y /= norm;
            c2_dx = target_x * 0.015;
            c2_dy = target_y * 0.015;

            //runner: next Punkt line
//            r_x += rd_x;
//            r_y += rd_y;
            //runner: next Punkt sin
//            t += dT;
//            r_x += rd_x;
//            r_y += 3 * rd_x * Math.sin(0.005 * t);
            // runner: next Punkt circ
            t += dT;
            r_x += 2 * rd_x * Math.cos(0.002 * t);
            r_y += 2 * rd_x * Math.sin(0.002 * t);
            //chaser1: next Punkt
            c1_x += c1_dx;
            c1_y += c1_dy;
            //chaser2: next Punkt
            c2_x += c2_dx;
            c2_y += c2_dy;

            //hit detection
            double d_1, d_2;
            d_1 = Math.sqrt(Math.pow(r_x - c1_x, 2) + Math.pow(r_y - c1_y, 2));
            d_2 = Math.sqrt(Math.pow(r_x - c2_x, 2) + Math.pow(r_y - c2_y, 2));
            if (d_1 < 0.1 || d_2 < 0.1) {
                pcs.firePropertyChange("hit", null, null);
            }
            //out detection
            if (Math.abs(r_x) >= out_x || Math.abs(r_y) >= out_y) {
                pcs.firePropertyChange("hit", null, null);
            }

            //build arraylist
            data_runner.add(new Punkt(r_x, r_y));
            data_chaser1.add(new Punkt(c1_x, c1_y));
            data_chaser2.add(new Punkt(c2_x, c2_y));
            anim.repaint();

        }
        painter = null;
    }

    public void start() {
        if (painter == null) {
            painter = new Thread(this);
            weiter = true;
            painter.start();
        }
    }

    public void stop() {
        weiter = false;
    }

    /**
     * @return the deltaT
     */
    protected long getDeltaT() {
        return dT;
    }

    /**
     * @param deltaT the deltaT to set
     */
    protected void setDeltaT(long deltaT) {
        this.dT = deltaT;
    }

}
