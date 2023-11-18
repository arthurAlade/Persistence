package edu.uga.miage.m1.polygons.gui.shapes;

import java.awt.Graphics2D;
import edu.uga.miage.m1.polygons.gui.persistence.Visitable;
import edu.uga.miage.m1.polygons.gui.persistence.Visitor;
import edu.uga.singleshape.CubePanel;

public class Cube implements SimpleShape, Visitable{
    private int x,y;    

    public Cube(int x, int y) {
        this.x = x-25;
        this.y = y-25;
    }

    public void draw(Graphics2D g2){
        CubePanel cube = new CubePanel(100,x+25,y+25);
        cube.paintComponent(g2);               
    }

    
    @Override
    // Accepte un visiteur
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
