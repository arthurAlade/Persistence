package edu.uga.miage.m1.polygons.gui.shapes;

import java.awt.Graphics2D;
import edu.uga.miage.m1.polygons.gui.persistence.Visitor;
import edu.uga.singleshape.CubePanel;

public class Cube extends AbstractShape{
    
    CubePanel cubeObject;

    public Cube(int x, int y) {
        super(x,y);
    }

    public void draw(Graphics2D g2){
        cubeObject = new CubePanel(100,super.getX(),super.getY());
        cubeObject.paintComponent(g2);               
    }

    
    @Override
    // Accepte un visiteur
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
