/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mawel
 */
public class Vector {

    public double x;
// Konstruktor
    public double y;
// Konstruktor

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static double length(Vector v) {
        return Math.sqrt(v.x * v.x + v.y * v.y);
    }

    public static Vector scaleToOne(Vector target) {
        double lengthX = target.x / Math.sqrt(target.x * target.x + target.y * target.y);
        double lengthY = target.y / Math.sqrt(target.x * target.x + target.y * target.y);

        return new Vector(lengthX, lengthY);
    }

    public static Vector scaleToLength(Vector target, double scale) {
        double lengthX = scale * target.x / Math.sqrt(target.x * target.x + target.y * target.y);
        double lengthY = scale * target.y / Math.sqrt(target.x * target.x + target.y * target.y);

        return new Vector(lengthX, lengthY);
    }

    public static Vector subtract(Vector v1, Vector v2) {
        double vx = v1.x - v2.x;
        double vy = v1.y - v2.y;

        return new Vector(vx, vy);
    }

    public static Vector addUp(Vector v1, Vector v2) {
        double vx = v1.x + v2.x;
        double vy = v1.y + v2.y;

        return new Vector(vx, vy);
    }

    /**
     * @return the x
     */
    protected double getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    protected void setX(double x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    protected double getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    protected void setY(double y) {
        this.y = y;
    }
}
