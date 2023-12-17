package edu.uga.miage.m1.polygons.gui.shapes;

import edu.uga.miage.m1.polygons.gui.persistence.Visitor;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GroupShape extends AbstractShape {
    private  List<AbstractShape> shapes;
    private int xEnd;
    private int yEnd;
    private boolean isGrouped;
    public GroupShape(int x, int y) {
        super(x, y);
        this.xEnd = 0;
        this.yEnd = 0;
        this.shapes = new ArrayList<>();
        this.isGrouped = false;
    }

    public GroupShape(int x, int y, int xEnd, int yEnd, List<AbstractShape> shapes){
        super(x,y);
        this.xEnd = xEnd;
        this. yEnd = yEnd;
        this.shapes = shapes;
        this.isGrouped = true;
    }

    @Override
    public void draw(Graphics2D g2) {
        float[] dashPattern = {5, 5}; // Pattern pour la bordure en trait
        BasicStroke dashedStroke = new BasicStroke(1, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND, 1.0f, dashPattern, 0);
        g2.setStroke(dashedStroke);
        int width = xEnd - getX();
        int height = yEnd - getY();
        g2.draw(new Rectangle(getX(), getY(), width, height));
        shapes.forEach(shape -> shape.draw(g2));
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
