/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mawel
 */
public class Coordinate {

    public double x;
// Konstruktor
    public double y;
// Konstruktor

    public Coordinate(double x, double y) {
        this.x = x;
        this.y = y;
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
