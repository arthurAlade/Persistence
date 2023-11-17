package edu.uga.miage.m1.polygons.gui.shapes;

import edu.uga.miage.m1.polygons.gui.persistence.Visitable;

public abstract class AbstractShape implements SimpleShape, Visitable {
    private int x;
    private int y;

    protected AbstractShape(int x, int y) {
        this.x = x -25;
        this.y = y -25;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
}
