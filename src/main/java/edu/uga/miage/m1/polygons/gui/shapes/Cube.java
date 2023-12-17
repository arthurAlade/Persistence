package edu.uga.miage.m1.polygons.gui.shapes;

import java.awt.Graphics2D;
import edu.uga.miage.m1.polygons.gui.persistence.Visitor;
import edu.uga.singleshape.CubePanel;

public class Cube extends AbstractShape{
    
    CubePanel cubeObject;
    int size;

    public Cube(int x, int y) {
        super(x,y);
        size = 100;
    }

    public Cube(int x, int y, int size){
        super(x,y);
        this.size = size;
    }
    public void draw(Graphics2D g2){
        cubeObject = new CubePanel(size,super.getX(),super.getY());
        cubeObject.paintComponent(g2);               
    }

    
    public int getSize() {
        return size;
    }

    @Override
    // Accepte un visiteur
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
