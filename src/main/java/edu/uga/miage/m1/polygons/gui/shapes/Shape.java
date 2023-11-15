package edu.uga.miage.m1.polygons.gui.shapes;

import edu.uga.miage.m1.polygons.gui.persistence.Visitable;
import edu.uga.miage.m1.polygons.gui.persistence.Visitor;

import java.awt.*;

public abstract class Shape implements SimpleShape, Visitable {
    private int x;
    private int y;

    public Shape(int x, int y) {
        this.x = x -25;
        this.y = y -25;
    }

    public abstract void draw(Graphics2D g2);
    public abstract void accept(Visitor visitor);

    public int getX() { return x; }
    public int getY() { return y; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
}
