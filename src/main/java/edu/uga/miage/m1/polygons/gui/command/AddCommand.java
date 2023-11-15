package edu.uga.miage.m1.polygons.gui.command;

import edu.uga.miage.m1.polygons.gui.JDrawingFrame;
import edu.uga.miage.m1.polygons.gui.shapes.SimpleShape;

import java.awt.*;

public class AddCommand implements Command{
    private final SimpleShape shape;
    private final Graphics2D g2;

    private final JDrawingFrame frame;
    private CommandStatus status;

    public AddCommand(SimpleShape shape, Graphics2D g2, JDrawingFrame frame){
        this.shape = shape;
        this.g2 = g2;
        this.frame = frame;
        this.status = CommandStatus.WAITING;
    }

    @Override
    public boolean execute() {
        try{
            shape.draw(g2);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean undo() {
        try{
            frame.removeShape(frame.getShapesListIndex(shape));
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public CommandStatus getStatus(){
        return this.status;
    }

    public void setStatus(CommandStatus status){
        this.status = status;
    }
}
