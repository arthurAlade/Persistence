package edu.uga.miage.m1.polygons.gui.command;

import edu.uga.miage.m1.polygons.gui.JDrawingFrame;
import edu.uga.miage.m1.polygons.gui.shapes.SimpleShape;

import java.awt.*;

public class MoveCommand implements Command{
    private CommandStatus status;



    private SimpleShape shape;
    private final Graphics2D g2;
    private final JDrawingFrame frame;

    public MoveCommand(SimpleShape shape, Graphics2D g2, JDrawingFrame frame){
        this.shape = shape;
        this.g2 = g2;
        this.frame = frame;
        this.status = CommandStatus.WAITING;
    }



    @Override
    public boolean execute() {
        try{
            if (status == CommandStatus.PENDING){
                shape.draw(g2);
            }else {
                frame.removeShape(frame.getShapesListIndex(shape));
            }
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean undo() {
        try{
            //TODO
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public CommandStatus getStatus() {
        return status;
    }

    @Override
    public void setStatus(CommandStatus status) {
        this.status = status;
    }

    public void setShape(SimpleShape shape) {
        this.shape = shape;
    }

}
