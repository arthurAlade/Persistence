package edu.uga.miage.m1.polygons.gui.command;

import edu.uga.miage.m1.polygons.gui.shapes.SimpleShape;

import java.awt.*;

public class AddCommand implements Command{
    private final SimpleShape shape;
    private final Graphics2D g2;
    private CommandStatus status;

    public AddCommand(SimpleShape shape, Graphics2D g2){
        this.shape = shape;
        this.g2 = g2;
        this.status = CommandStatus.WAITING;
    }

    @Override
    public void execute() {
        shape.draw(g2);
    }

    public CommandStatus getStatus(){
        return this.status;
    }

    public void setStatus(CommandStatus status){
        this.status = status;
    }
}
