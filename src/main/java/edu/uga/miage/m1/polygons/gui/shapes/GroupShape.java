package edu.uga.miage.m1.polygons.gui.shapes;

import edu.uga.miage.m1.polygons.gui.persistence.Visitor;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GroupShape extends AbstractShape {
    private  List<AbstractShape> shapes;
    private int xEnd, yEnd;
    private boolean isGrouped;
    public GroupShape(int x, int y) {
        super(x, y);
        this.xEnd = 0;
        this.yEnd = 0;
        this.shapes = new ArrayList<>();
        this.isGrouped = false;
    }

    @Override
    public void draw(Graphics2D g2) {

    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public List<AbstractShape> getShapes() {
        return shapes;
    }

    public void setShapes(List<AbstractShape> shapes) {
        this.shapes = shapes;
    }

    public int getxEnd() {
        return xEnd;
    }

    public int getyEnd() {
        return yEnd;
    }

    public void setxEnd(int xEnd) {
        this.xEnd = xEnd;
    }

    public void setyEnd(int yEnd) {
        this.yEnd = yEnd;
    }

    public boolean isGrouped() {
        return isGrouped;
    }
    public void setGrouped(boolean grouped) {
        isGrouped = grouped;
    }
}
