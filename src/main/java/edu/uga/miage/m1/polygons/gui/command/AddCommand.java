package edu.uga.miage.m1.polygons.gui.command;

import edu.uga.miage.m1.polygons.gui.shapes.SimpleShape;

import java.awt.*;

public class AddCommand implements Command{
    private SimpleShape shape;
    private Graphics2D g2;

    public AddCommand(SimpleShape shape, Graphics2D g2){
        this.shape = shape;
        this.g2 = g2;
    }

    @Override
    public void execute() {
        shape.draw(g2);
    }
}
